package controllers;

import play.mvc.Result;
import views.html.main.home;

public class MainController extends BaseController {

	public static Result home() {
		return wrapOk(home.render("hello, world!"));
	}

	public static Result authenticated() {
		return TODO;
	}

}
