package interfaces;

import dbadapter.UserAccount;

public interface IUserAcc {
	
	public UserAccount createUser(String username, String email, String password, int age);
	
	public boolean checkUserAlreadyExists(String email);
}
