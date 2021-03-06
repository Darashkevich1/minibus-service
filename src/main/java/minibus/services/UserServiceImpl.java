package minibus.services;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileSystemUtils;

import minibus.UserRole;
import minibus.dao.TokenDAO;
import minibus.dao.UserDAO;
import minibus.entities.AccessToken;
import minibus.entities.Token;
import minibus.entities.User;
import minibus.entities.UserImpl;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDataAccess;
	
	@Autowired
	TokenDAO accessTokenDAO;
	
	public Token login(String email, String password) {
		if(!email.isEmpty() && !password.isEmpty()) {
			
			User user = userDataAccess.getByEmail(email);
			if(user != null && user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
				Token token = accessTokenDAO.getByUserId(user.getId());
				
				if(token != null) {
					return token;
				}
				
				Token t = new AccessToken();
				t.setAccessToken(UUID.randomUUID().toString());
				t.setUserId(user.getId());
				accessTokenDAO.addToken(t);
				return accessTokenDAO.getByToken(t.getAccessToken());
			}
		}
		return null;
	}

	@Override
	public boolean logout(String token) {
		if(!token.isEmpty()) {
			Token t = accessTokenDAO.getByToken(token);
			if(t != null) {
				accessTokenDAO.removeToken(t);
				return true;
			}
		}
		return false;
	}

	@Override
	public Token registerUser(String email, String password, UserRole role) {
		
		// !check email and password!
		
		if(!email.isEmpty() && !password.isEmpty()) {
			User user = userDataAccess.getByEmail(email);
			if(user == null) {
				User new_user = new UserImpl();
				new_user.setEmail(email);
				new_user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
				new_user.setRole(role);
				userDataAccess.addUser(new_user);
				return login(email, password);
			}
		}
		
		return null;
	}

	@Override
	public boolean deleteUser(int id, String token) {
		
		if(!token.isEmpty() && id > 0) {
			User user_by_id = userDataAccess.getById(id);
			Token user_token = accessTokenDAO.getByToken(token);
			User user_by_token = null;
			
			if(user_token != null) {
				user_by_token = userDataAccess.getById(user_token.getUserId());
			}
			
			if(user_by_id != null && user_by_token != null) {
				if(user_by_token.getRole() == UserRole.Admin || user_by_token.getId() == user_by_id.getId()) {
					Token t = accessTokenDAO.getByUserId(user_by_id.getId());
					if(t != null) {
						logout(t.getAccessToken());
					}
					File f = new File("images-storage/"+String.valueOf(user_by_id.getId()));
					if(f.exists()) {
						FileSystemUtils.deleteRecursively(f);
					}
					userDataAccess.removeUser(user_by_id);
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public User getById(int id) {
		return userDataAccess.getById(id);
	}

	@Override
	public boolean emailIsFree(String email) {
		User user = userDataAccess.getByEmail(email);
		return user == null ? true : false;
	}

	@Override
	public boolean updateUser(String firstname, String lastname, String phone, String address, String token) {
		Token accessToken = accessTokenDAO.getByToken(token);
		if(accessToken != null) {
			User user = userDataAccess.getById(accessToken.getUserId());
			if(user != null) {
				user.setAddress(address);
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setPhone(phone);
				userDataAccess.updateUser(user);
				return true;
			}
		}
		return false;
	}
	

}
