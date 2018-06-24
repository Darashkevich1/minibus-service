package minibus.entities;

public interface Token {
	String getAccessToken();
	int getUserId();
	int getId();
	
	void setAccessToken(String accessToken);
	void setUserId(int userId);
	void setId(int id);
}
