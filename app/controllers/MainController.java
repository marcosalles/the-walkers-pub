package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security.Authenticated;
import views.html.home;

public class MainController extends Controller {

    public static Result home() {
        return ok(home.render("Hello, world!"));
    }

	@Authenticated
    public static Result authenticated() {
        return TODO;
    }

}
