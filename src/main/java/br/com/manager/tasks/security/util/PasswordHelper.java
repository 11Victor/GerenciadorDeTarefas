package br.com.manager.tasks.security.util;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class PasswordHelper {

	public static String passwordGenerate() {
		PasswordGenerator gen = new PasswordGenerator();
		CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(2);

		CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(2);

		CharacterData digitChars = EnglishCharacterData.Digit;
		CharacterRule digitRule = new CharacterRule(digitChars);
		digitRule.setNumberOfCharacters(4);

		CharacterData specialChars = new CharacterData() {
			public String getErrorCode() {
				return null;
			}

			public String getCharacters() {
				return "!#$%&'()*+,-./";
			}
		};
		CharacterRule splCharRule = new CharacterRule(specialChars);
		splCharRule.setNumberOfCharacters(4);

		return gen.generatePassword(15, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
	}

	public static String passwordEncrypted(String password) {
		return BcryptHelper.hash(password);
	}
}