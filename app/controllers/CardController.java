package controllers;

import java.util.List;

import models.magic.Card;
import models.magic.ColorEnum;
import play.data.Form;
import play.mvc.Result;
import views.html.card.*;
import daos.CardDao;

public class CardController extends BaseController {

	public static Result searchForm() {
		Form<Card> form = Form.form(Card.class);
		return wrapOk(searchCards.render(loggedUser() ,null, form, ColorEnum.MAP()));
	}

	public static Result search() {
		Form<Card> form = Form.form(Card.class).bindFromRequest();
		List<Card> list = CardDao.cardsByFilledFields(form);
		return wrapOk(searchCards.render(loggedUser(), list, form, ColorEnum.MAP()));
	}

	public static void getObjects(final String term){
		//TODO
	}
	
	public static Result card(String id) {
		Card foundCard = CardDao.cardByMultiverseId(id);
		return wrapOk(card.render(foundCard));
	}
}
