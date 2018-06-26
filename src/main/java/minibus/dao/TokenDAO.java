package minibus.dao;

import minibus.entities.Token;

public interface TokenDAO {
	Token getByToken(String token);
	Token getById(int id);
	Token getByUserId(int userId);
	
	void addToken(Token token);
	void removeToken(Token token);
}
