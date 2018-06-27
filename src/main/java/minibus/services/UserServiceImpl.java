package minibus.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
	public boolean deleteUser(String email, String token) {
		
		if(!email.isEmpty() && !token.isEmpty()) {
			User user_by_email = userDataAccess.getByEmail(email);
			Token user_token = accessTokenDAO.getByToken(token);
			User user_by_token = null;
			
			if(user_token != null) {
				user_by_token = userDataAccess.getById(user_token.getUserId());
			}
			
			if(user_by_email != null && user_by_token != null) {
				if(user_by_token.getRole() == UserRole.Admin || user_by_token.getId() == user_by_email.getId()) {
					Token t = accessTokenDAO.getByUserId(user_by_email.getId());
					if(t != null) {
						logout(t.getAccessToken());
					}
					userDataAccess.removeUser(user_by_email);
					return true;
				}
			}
		}
		
		return false;
	}
	

}
