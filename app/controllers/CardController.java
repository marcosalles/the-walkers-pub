package controllers;

import java.util.List;

import models.magic.MagicCard;
import models.magic.MagicColorEnum;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import views.html.cards.*;
import daos.CardDao;

public class CardController extends BaseController {

	public static Result searchForm() {
		Form<MagicCard> form = Form.form(MagicCard.class);
		return wrapOk(searchCards.render(loggedUser() ,null, form, MagicColorEnum.MAP()));
	}

	public static Result search() {
		Form<MagicCard> form = Form.form(MagicCard.class).bindFromRequest();
		List<MagicCard> list = CardDao.cardsByFilledFields(form);
		return wrapOk(searchCards.render(loggedUser(), list, form, MagicColorEnum.MAP()));
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
