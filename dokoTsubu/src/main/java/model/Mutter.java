package model;

import java.io.Serializable;

public class Mutter implements Serializable {

	//ユーザー名
	private String userName;
	//	つぶやき内容
	private String text;

	public Mutter() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getUserName() {
		return userName;
	}

}
