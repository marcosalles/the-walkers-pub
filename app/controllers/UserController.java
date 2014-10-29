package controllers;

import infra.UserValidator;
import infra.Validator;
import models.User;
import play.data.Form;
import play.data.validation.ValidationError;
import play.libs.Crypto;
import play.mvc.Result;
import views.html.signUp;

public class UserController extends BaseController {

	public static Result signUpLink() {
		Form<User> form = Form.form(User.class);
		return ok(signUp.render(form));
	}

	public static Result signUp() {
		Form<User> form = Form.form(User.class).bindFromRequest();
		User user = form.get();
		Validator validator = new UserValidator(form).validated();
		if (validator.isValid()) {
			String password = form.field("re-password").value();
			user.setPassword(Crypto.encryptAES(password));
			user.save();
			flash().put("success", "Account created");
			return redirect(previousUrl());
		}
		User invalidUser = new User();
		invalidUser.setLogin(user.getLogin());
		Form<User> invalidForm = form.fill(invalidUser);
		for (ValidationError error : validator.getErrors()) {
			if (!error.key().equals("global")) {
				invalidForm.reject(error);
			}
			else {
				flash().put("danger", error.message());
			}
		}
		return ok(signUp.render(invalidForm));
	}
	
	public static Result login() {
		Form<User> form = Form.form(User.class).bindFromRequest();
		User user = form.get();
		user.setPassword(Crypto.encryptAES(user.getPassword()));
		Validator validator = new UserValidator(user).validated();
		if(validator.isValid()){
			session().put("login", Crypto.encryptAES(user.getLogin()));
			flash().put("success", "Logged in successfully!!");
			return redirect(previousUrl());
		}
		
		for (ValidationError error : validator.getErrors()) {
			flash().put("danger", error.message());
		}
		return redirect(previousUrl());
	}

	public static Result logout() {
		session().clear();
		flash().put("info", "Logged out successfully!!");
		return redirect(routes.MainController.home());
	}
}
