
package controllers;

import static infra.UserValidator.ValidationType.LOGIN;
import static infra.UserValidator.ValidationType.SIGNUP;
import static infra.UserValidator.ValidationType.UPDATE;
import infra.UserValidator;
import infra.Validator;

import java.util.List;

import models.Deck;
import models.User;
import play.data.Form;
import play.data.validation.ValidationError;
import play.libs.Crypto;
import play.mvc.Result;
import views.html.user.*;
import daos.UserDao;

public class UserController extends BaseController {

	public static Result signUpForm() {
		Form<User> form = Form.form(User.class);
		return wrapOk(signUp.render(form));
	}

	public static Result signUp() {
		Form<User> form = Form.form(User.class).bindFromRequest();
		String fromLogin = form.field("fromLogin").value();
		if (fromLogin != null) {
			flash().put("info", "Fill the form to complete your registration!");
			return wrapOk(signUp.render(form));
		}
		User user = form.get();
		Validator validator = new UserValidator(SIGNUP).fromForm(form).validated();
		if (validator.isValid()) {
			String password = form.field("password").value();
			user.setPassword(Crypto.encryptAES(password));
			user.save();
			flash().put("success", String.format("Account '%s' created", user.getLogin()));
			doLogin(user);
			return redirect(routes.UserController.profileForm(user.getId()));
		}
		user.setPassword("");
		Form<User> invalidForm = form.fill(user);
		for (ValidationError error : validator.getErrors()) {
			if (!error.key().equals("global")) {
				invalidForm.reject(error);
			} else {
				flash().put("danger", error.message());
			}
		}
		return wrapBadRequest(signUp.render(invalidForm));
	}

	public static Result loginForm() {
		return ok(login.render());
	}

	public static Result login() {
		Form<User> form = Form.form(User.class).bindFromRequest();
		User user = form.get();
		user.setPassword(Crypto.encryptAES(user.getPassword()));
		Validator validator = new UserValidator(LOGIN).fromUser(user).validated();
		if (validator.isValid()) {
			doLogin(user);
			return toPreviousUrl();
		}

		for (ValidationError error : validator.getErrors()) {
			flash().put("danger", error.message());
		}
		return toPreviousUrl();
	}

	public static Result logout() {
		session().clear();
		flash().put("warning", "Logged out successfully!!");
		return redirect(routes.MainController.home());
	}

//	@Authenticated
	public static Result settingsForm(Long id) {
		return TODO;
	}

//	@Authenticated
	public static Result profileForm(Long id) {
		User user = UserDao.userById(id);
		user.setPassword("");
		Form<User> form = Form.form(User.class).fill(user);
		return wrapOk(profileForm.render(form));
	}

	public static Result update() {
		Form<User> form = Form.form(User.class).bindFromRequest();
		User user = form.get();
		Validator validator = new UserValidator(UPDATE).fromForm(form).validated();
		if (validator.isValid()) {
			String password = form.field("password").value();
			user.setPassword(Crypto.encryptAES(password));
			user.update();
			flash().put("success", String.format("Account '%s' updated", user.getLogin()));
			doLogin(user);
			return redirect(routes.UserController.profileForm(user.getId()));
		}
		user.setPassword("");
		Form<User> invalidForm = form.fill(user);
		for (ValidationError error : validator.getErrors()) {
			if (!error.key().equals("global")) {
				invalidForm.reject(error);
			} else {
				flash().put("danger", error.message());
			}
		}
		return wrapBadRequest(profileForm.render(invalidForm));
	}

	public static Result decks(Long id) {
		User user = UserDao.userById(id);
		List<Deck> list = user.getDecks();
		return wrapOk(decks.render(list));
	}

	private static void doLogin(User user) {
		session().put("login", Crypto.encryptAES(user.getLogin()));
		flash().put("success", "Logged in successfully!!");
	}
}
