package controllers;

import java.util.List;

import models.User;
import models.magic.MagicCard;
import models.magic.MagicColorEnum;
import play.api.templates.Html;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import views.html.cards.card;
import views.html.cards.searchCards;
import views.html.user.signUp;
import daos.CardDao;

public class CardController extends BaseController {

	public static final String SEARCH = "search";
	public static final String LIST = "list";

	public static Result content(String content) {
		if (content.equalsIgnoreCase(SEARCH)) {
			return ok();
		} else if (content.equalsIgnoreCase(LIST)) {
			Form<User> form = Form.form(User.class);
			return ok(signUp.render(form));
		}
		return badRequest("content not found");
	}

	public static Result search() {
		Form<MagicCard> form = Form.form(MagicCard.class).bindFromRequest();
		List<MagicCard> list = null;
		if (form.field("q").valueOr("").equals("1")) {
			list = CardDao.cardsByFilledFields(form);
		}
		Html render = searchCards.render(loggedUser(), list, form, MagicColorEnum.MAP());
		return wrapOk(render);
	}

	public static Result searchAutoComplete(){
		Form<MagicCard> form = Form.form(MagicCard.class).bindFromRequest();
		List<MagicCard> list = CardDao.cardsByName(form.field("name").value());
		return ok(Json.toJson(list));
	}
	
	public static Result card(String id) {
		MagicCard foundCard = CardDao.cardByMultiverseId(id);
		return wrapOk(card.render(foundCard));
	}
}
