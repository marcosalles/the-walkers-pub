package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.home;
import play.mvc.Security.Authenticated;

public class MainController extends Controller {

    public static Result home() {
        return ok(home.render("Hello, world!"));
    }

	@Authenticated
    public static Result authenticated() {
        return TODO;
    }

}
