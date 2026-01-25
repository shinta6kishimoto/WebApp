package model;

import dao.UserDAO;
import dto.User;

public class LoginLogic {
	public User execute(String name, String pass) {

		UserDAO dao = new UserDAO();

		User user = new User();

		user = dao.selectByName(name, pass);

		return user;

	}
}
