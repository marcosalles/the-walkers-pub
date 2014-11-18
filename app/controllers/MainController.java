package controllers;

import play.mvc.Result;
import views.html.main.*;

public class MainController extends BaseController {

	public static Result home() {
		return wrapOk(home.render());
	}

	public static Result authenticated() {
		return ok(views.html.refactor.test.render());
	}

}
