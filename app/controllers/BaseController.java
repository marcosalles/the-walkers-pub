package controllers;

import java.util.Random;

import infra.Module;
import models.User;
import play.api.libs.Crypto;
import play.api.templates.Html;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.shared.footer;
import views.html.shared.header;
import views.html.shared.template;
import daos.UserDao;

public class BaseController extends Controller {

	private static Random rng = new Random();

	protected static Result toPreviousUrl() {
		String url = session().get("currentUrl");
		if (url == null) url = "";
		return redirect(url);
	}

	protected static Result wrapOk(Html content) {
		return ok(wrappedContent(content));
	}

	protected static Result wrapBadRequest(Html content) {
		return badRequest(wrappedContent(content));
	}

	protected static Result wrapUnauthorized(Html content) {
		return unauthorized(wrappedContent(content));
	}

	protected static Result wrapNotFound(Html content) {
		return notFound(wrappedContent(content));
	}

	private static Html wrappedContent(Html content) {
		if (content == null) {
			content = new Html(null);
		}
		Module userModule = userModule(loggedUser());
		return template.render(header.render(userModule), content, footer.render());
	}

	private static Module userModule(User user) {
		String id = UserController.LOGIN;
		if (!user.isGhost()) {
			id = UserController.MENU;
		}
		return new Module(routes.UserController.content(id), id, 99);
	}

	public static User loggedUser() {
		String login = session().get("login");
		if (login != null) {
			login = Crypto.decryptAES(login);
		}
		else {
			login = "";
		}
		User user = UserDao.userByLogin(login);
		return user;
	}

	public static void flash(String key, String message) {
		if (flash().get(key) != null) {
			key += rng.nextFloat();
		}
		flash().put(key, message);
	}
}
