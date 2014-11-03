package controllers;

import java.util.List;

import models.magic.Card;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import views.html.card.cards;
import views.html.card.searchForm;
import daos.CardDao;

public class CardController extends BaseController {

	public static Result searchForm() {
		Form<Card> form = Form.form(Card.class);
		return wrapOk(searchForm.render(form));
	}

	public static Result search() {
		DynamicForm form = Form.form().bindFromRequest();
		String name = form.field("suggestText").value();
		List<Card> list = CardDao.cardsByName(name);
		return wrapOk(cards.render(list));
	}

	public static Result card(String id) {
//		Card card = CardDao.cardByMultiverseId(id);
		return TODO;
	}
}
