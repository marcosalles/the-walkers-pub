package controllers;

import play.mvc.Controller;

public class BaseController extends Controller {

	protected static String previousUrl() {
		String url = session().get("previousUrl");
		return url;
	}
}
