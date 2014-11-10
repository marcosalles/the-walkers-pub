package infra;

import java.util.ArrayList;

import models.Ghost;
import models.User;
import play.data.Form;
import play.data.validation.ValidationError;
import play.libs.Crypto;
import daos.UserDao;

public class UserValidator extends Validator {

	public enum ValidationType {
		LOGIN,
		SIGNUP,
		UPDATE
	}

	private Form<User> form;
	private User user;
	private ValidationType type;

	public UserValidator(ValidationType type) {
		super();
		this.type = type;
	}

	public UserValidator fromForm(Form<User> form) {
		this.form = form;
		this.user = form.get();
		return this;
	}

	public UserValidator fromUser(User user) {
		this.user = user;
		return this;
	}

	protected void validate() {
		errors = new ArrayList<ValidationError>();
		switch(type) {
			case LOGIN: {
				loginValidation();
				break;
			}
			case SIGNUP: {
				signUpValidation();
				break;
			}
			case UPDATE: {
				updateValidation();
				break;
			}
			default:break;
		}
	}

	private void updateValidation() {
		User userById = UserDao.userById(user.getId());
		// Password check 
		String oldPassword = form.field("old-password").valueOr("");
		if (oldPassword.length() > 0) {
			if (Crypto.encryptAES(oldPassword).equals(userById.getPassword())) {
				if (!form.field("re-password").valueOr("").equals(user.getPassword())) {
					errors.add(new ValidationError("password", ""));
					errors.add(new ValidationError("re-password", "Passwords don't match"));
				}
			}
			else {
				errors.add(new ValidationError("old-password", "Wrong password"));
			}
		}
		// Email check
		if (!userById.getEmail().equals(user.getEmail())) {
			if (!Ghost.instance().equals(UserDao.userByEmail(user.getEmail()))) {
				errors.add(new ValidationError("email", "Email already is use"));
			}
		}

		// Length check
		if (stringNotLongEnough(user.getLogin(), 3)) {
			errors.add(new ValidationError("login", "Login has to be at least 3 characters long"));
		}
		if (stringNotLongEnough(user.getPassword(), 3)) {
			errors.add(new ValidationError("password", ""));
			errors.add(new ValidationError("re-password", "New password has to be at least 3 characters long"));
		}
		// Pattern check
		if (!user.getLogin().matches("[\\w]+")) {
			errors.add(new ValidationError("login", "Please use only characters, numbers and underscores"));
		}
		// Unique check
		if (!userById.getLogin().equals(user.getLogin())) {
			if (!Ghost.instance().equals(UserDao.userByLogin(user.getLogin()))) {
				errors.add(new ValidationError("login", "Login already taken!"));
			}
		}

		if (errors.size() > 0) {
			errors.add(new ValidationError("global", "Something went wrong.."));
		}
	}

	private void signUpValidation() {
		// Form check
		if (!form.field("re-password").valueOr("").equals(user.getPassword())) {
			errors.add(new ValidationError("password", ""));
			errors.add(new ValidationError("re-password", "Passwords don't match"));
		}
		// Length check
		if (stringNotLongEnough(user.getLogin(), 3)) {
			errors.add(new ValidationError("login", "Login has to be at least 3 characters long"));
		}
		if (stringNotLongEnough(user.getPassword(), 3)) {
			errors.add(new ValidationError("password", ""));
			errors.add(new ValidationError("re-password", "Password has to be at least 3 characters long"));
		}
		// Pattern check
		if (!user.getLogin().matches("[\\w]+")) {
			errors.add(new ValidationError("login", "Please use only characters, numbers and underscores"));
		}
		// Unique check
		if (!Ghost.instance().equals(UserDao.userByLogin(user.getLogin()))) {
			errors.add(new ValidationError("login", "Login already taken!"));
		}
		if (!Ghost.instance().equals(UserDao.userByEmail(user.getEmail()))) {
			errors.add(new ValidationError("login", "Login already taken!"));
		}
		if (errors.size() > 0) {
			errors.add(new ValidationError("global", "Something went wrong.."));
		}
	}

	private void loginValidation() {
		User userByLogin = UserDao.userByLogin(user.getLogin());
		if (!user.equals(userByLogin)) {
			errors.add(new ValidationError("global", "Login or password invalid. Try again!"));
		}
	}
}
