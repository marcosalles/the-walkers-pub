package infra;

import java.util.ArrayList;

import models.PlaneswalkerUser;
import models.User;
import play.data.Form;
import play.data.validation.ValidationError;
import daos.UserDao;

public class UserValidator extends Validator {

	private Form<PlaneswalkerUser> form;
	private User user;

	public UserValidator(Form<PlaneswalkerUser> form) {
		super();
		this.form = form;
	}
	public UserValidator(User user) {
		super();
		this.user = user;
	}

	public void validate() {
		errors = new ArrayList<ValidationError>();
		if (form != null) {
			user = form.get();
			signUpValidation();
		}
		else {
			loginValidation();
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
		if (UserDao.userExists(user)) {
			errors.add(new ValidationError("login", "Login already taken!"));
		}

		if (errors.size() > 0) {
			errors.add(new ValidationError("global", "Something went wrong.."));
		}
	}
	private void loginValidation() {
		User userByLogin = UserDao.userByLogin(user.getLogin());
		if (userByLogin == null ||
			!user.equals(userByLogin)) {
			errors.add(new ValidationError("global", "Login or password invalid. Try again!"));
		}
	}
}
