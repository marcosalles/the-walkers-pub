package controllers;

import play.mvc.Result;
import play.mvc.Security.Authenticated;
import views.html.main.*;

public class MainController extends BaseController {

	public static Result home() {
		return wrapOk(home.render("hello, world!"));
	}

	//EXAMPLE
	@Authenticated
	public static Result authenticated() {
		return TODO;
	}

}
