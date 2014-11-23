
package controllers;

import infra.Module;
import infra.UserValidator;
import infra.UserValidator.ValidationType;
import infra.Validator;

import java.util.Arrays;
import java.util.List;

import models.Deck;
import models.User;
import play.data.Form;
import play.data.validation.ValidationError;
import play.libs.Crypto;
import play.mvc.Result;
import views.html.decks.list;
import views.html.shared.modular;
import views.html.user.login;
import views.html.user.menu;
import views.html.user.profileForm;
import views.html.user.signUp;
import daos.UserDao;

public class UserController extends BaseController {

	public static final String LOGIN = "login";
	public static final String MENU = "menu";
	public static final String SIGN_UP = "sign-up";

	public static Result content(String content) {
		if (content.equalsIgnoreCase(LOGIN)) {
			return ok(login.render());
		} else if (content.equalsIgnoreCase(MENU)) {
			return ok(menu.render(loggedUser()));
		} else if (content.equalsIgnoreCase(SIGN_UP)) {
			Form<User> form = Form.form(User.class);
			return ok(signUp.render(form));
		}
		return badRequest("content not found");
	}

	public static Result signUpForm() {
		Module signUp = new Module(routes.UserController.content(SIGN_UP), SIGN_UP, 5);
		List<Module> modules = Arrays.asList(signUp);
		return wrapOk(modular.render(modules));
	}

	public static Result signUp() {
		Form<User> form = Form.form(User.class).bindFromRequest();
		String fromLogin = form.field("fromLogin").value();
		if (fromLogin != null) {
			flash().put("info", "Fill the form to complete your registration!");
			return wrapOk(signUp.render(form));
		}
		User user = form.get();
		Validator validator = new UserValidator(ValidationType.SIGNUP).fromForm(form).validated();
		if (validator.isValid()) {
			String password = form.field("password").value();
			user.setPassword(Crypto.encryptAES(password));
			user = User.fromUser(user);
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

	public static Result userMenu() {
		return ok(menu.render(loggedUser()));
	}

	public static Result login() {
		Form<User> form = Form.form(User.class).bindFromRequest();
		User user = form.get();
		user.setPassword(Crypto.encryptAES(user.getPassword()));
		Validator validator = new UserValidator(ValidationType.LOGIN).fromUser(user).validated();
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
		Validator validator = new UserValidator(ValidationType.UPDATE).fromForm(form).validated();
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
		List<Deck> decks = user.getDecks();
		return wrapOk(list.render(decks));
	}

	private static void doLogin(User user) {
		session().put("login", Crypto.encryptAES(user.getLogin()));
		flash().put("info", "Logged in successfully!!");
	}
}
