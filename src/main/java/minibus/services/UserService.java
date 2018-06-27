package minibus.services;

import minibus.UserRole;
import minibus.entities.Token;

public interface UserService {
	Token login(String email, String password);
	boolean logout(String token);
	
	Token registerUser(String email, String password, UserRole role);
	boolean deleteUser(String email, String token);
}
