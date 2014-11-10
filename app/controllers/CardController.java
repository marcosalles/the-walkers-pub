package controllers;

import java.util.List;

import models.magic.Card;
import models.magic.Color;
import play.data.Form;
import play.mvc.Result;
import views.html.card.searchCards;
import daos.CardDao;

public class CardController extends BaseController {

	public static Result searchForm() {
		Form<Card> form = Form.form(Card.class);
		return wrapOk(searchCards.render(null, form, Color.MAP()));
	}

	public static Result search() {
		Form<Card> form = Form.form(Card.class).bindFromRequest();
		List<Card> list = CardDao.cardsByFilledFields(form.get());
		return wrapOk(searchCards.render(list, form, Color.MAP()));
	}

	public static Result card(String id) {
//		Card card = CardDao.cardByMultiverseId(id);
		return TODO;
	}
}
