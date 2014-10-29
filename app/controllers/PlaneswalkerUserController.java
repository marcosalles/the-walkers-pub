package controllers;

import infra.UserValidator;
import infra.Validator;
import models.PlaneswalkerUser;
import play.data.Form;
import play.data.validation.ValidationError;
import play.libs.Crypto;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.signUp;
import daos.UserDao;

public class PlaneswalkerUserController extends Controller {

	public static Result signUpLink() {
		Form<PlaneswalkerUser> form = Form.form(PlaneswalkerUser.class);
		return ok(signUp.render(form));
	}

	public static Result users() {
		return ok(Json.toJson(UserDao.list()));
	}
	public static Result signUp() {
		Form<PlaneswalkerUser> form = Form.form(PlaneswalkerUser.class).bindFromRequest();
		PlaneswalkerUser user = form.get();
		Validator validator = new UserValidator(form).validated();
		if (validator.isValid()) {
			if (user.getPassword().equals(form.field("re-password").value())) {
				user.setPassword(Crypto.encryptAES(user.getPassword()));
				user.save();
				flash().put("success", "Account created");
				return redirect(routes.MainController.home());
			}
		}
		PlaneswalkerUser loginUser = new PlaneswalkerUser();
		loginUser.setLogin(user.getLogin());
		Form<PlaneswalkerUser> invalidForm = form.fill(loginUser);
		if (!user.getPassword().equals(form.field("re-password").value())) {
			invalidForm.reject("re-password", "Passwords don't match!");
		}
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
		Form<PlaneswalkerUser> form = Form.form(PlaneswalkerUser.class).bindFromRequest();
		PlaneswalkerUser user = form.get();
		user.setPassword(Crypto.encryptAES(user.getPassword()));
		Validator validator = new UserValidator(user).validated();
		if(validator.isValid()){
			session().put("login", Crypto.encryptAES(user.getLogin()));
			flash().put("success", "Logged in successfully!!");
			return redirect(routes.MainController.home());
		}
		
		for (ValidationError error : validator.getErrors()) {
			flash().put("danger", error.message());
		}
		return redirect(routes.MainController.home());
	}
	
}
