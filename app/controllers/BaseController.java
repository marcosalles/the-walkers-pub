package controllers;

import models.User;
import play.api.libs.Crypto;
import play.api.templates.Html;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.shared.template;
import daos.UserDao;

public class BaseController extends Controller {

	protected static Result toPreviousUrl() {
		String url = session().get("previousUrl");
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
		return template.render(loggedUser(), content);
	}

	public static User loggedUser() {
		String login = session().get("login");
		if (login != null && !login.equals("")) {
			login = Crypto.decryptAES(login);
		}
		else {
			login = "";
		}
		User user = UserDao.userByLogin(login);
		return user;
	}
}
