package br.com.manager.tasks.security.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptHelper {

	public static String hash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	public static Boolean checkPassword(String password, String passwordEncrypted) {
		return BCrypt.checkpw(password, passwordEncrypted);
	}

}
