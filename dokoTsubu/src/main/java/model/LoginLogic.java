package model;

public class LoginLogic {
	public boolean execute(User user) {
		if (user.getPass().equals("test")) {
			return true;
		}
		return false;
	}
}
