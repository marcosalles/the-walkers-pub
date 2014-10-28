package controllers;

import com.avaje.ebean.Ebean;

import models.PlaneswalkerUser;
import play.data.Form;
import play.libs.Crypto;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class PlaneswalkerUserController extends Controller{

	public static Result signUpLink(){
		Form<PlaneswalkerUser> form = Form.form(PlaneswalkerUser.class);
		return ok(signUp.render(form));
	}
	
	public static Result signUp(){
		Form<PlaneswalkerUser> form = Form.form(PlaneswalkerUser.class).bindFromRequest();
		
		if(!form.hasErrors()){
			PlaneswalkerUser user = form.get();
			
			if(Ebean.createQuery(PlaneswalkerUser.class).where().eq("login", user.getLogin()).findUnique() == null){
				String repassword = form.field("re-password").value();
				
				if(user.getPassword().equals(repassword)){
					user.setPassword(Crypto.encryptAES(repassword));
					user.save();
					flash().put("success", "Account created");
					return redirect(routes.MainController.home());
				} else {
					flash().put("danger", "Passwords don't match!");
					PlaneswalkerUser planeswalkerUser = new PlaneswalkerUser();
					planeswalkerUser.setLogin(user.getLogin());
					form = form.fill(planeswalkerUser);
				}				
			} else {
				flash().put("danger", "Login already taken!");
				form = form.fill(new PlaneswalkerUser());
			}
		}
		return ok(signUp.render(form));
	}
	
	public static Result login() {
		Form<PlaneswalkerUser> bindFromRequest = Form.form(PlaneswalkerUser.class).bindFromRequest();
		if(bindFromRequest.hasErrors()){
			return badRequest(bindFromRequest.errorsAsJson());
		}
		
		PlaneswalkerUser planeswalkerUser = bindFromRequest.get();
		planeswalkerUser.setPassword(Crypto.encryptAES(planeswalkerUser.getPassword()));
		
		PlaneswalkerUser user = Ebean.createQuery(PlaneswalkerUser.class)
				.where()
				.eq("login", planeswalkerUser.getLogin())
				.eq("password", planeswalkerUser.getPassword())
				.findUnique();
		
		if(user == null){
			return ok("usuario n existe");
		}
		
		session().put("login", Crypto.encryptAES(user.getLogin()));
		flash().put("success", "Logged in successfully!!");
		return redirect(routes.MainController.home());
	}
	
}
