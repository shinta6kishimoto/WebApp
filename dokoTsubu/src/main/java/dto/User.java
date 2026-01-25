package dto;

import java.io.Serializable;

public class User implements Serializable {
	//	id
	private Integer id;
	//	ユーザー名
	private String name;
	//	パスワード
	private String pass;

	public User() {
	}

	public User(Integer id, String name, String pass) {
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

}
