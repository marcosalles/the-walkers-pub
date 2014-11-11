package controllers;

import java.util.List;

import models.Deck;
import models.magic.Card;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import views.html.deck.*;
import daos.CardDao;
import daos.DeckDao;

public class DeckController extends BaseController {

	public static Result searchForm() {
		return ok();
	}

	public static Result featuredDecks() {
		List<Deck> decks = DeckDao.list();
		return wrapOk(featuredDecksList.render(decks));
	}
	public static Result addCard() {
		DynamicForm form = Form.form().bindFromRequest();
		Long deckId = Long.parseLong(form.field("deckId").value());
		String cardId = form.field("cardId").value();
		int quantity = Integer.parseInt(form.field("quantity").value());

		Deck deck = DeckDao.deckById(deckId);
		Card card = CardDao.cardByMultiverseId(cardId);
		for (int i = 0; i < quantity; i++) {
			deck.addCard(card);
		}
		deck.update();
		return redirect(routes.CardController.searchForm());
	}

	public static Result createDeckForm() {
		Form<Deck> form = Form.form(Deck.class);
		return wrapOk(createForm.render(form));
	}

	public static Result createDeck() {
		Deck deck = new Deck();
		DynamicForm form = Form.form().bindFromRequest();
		deck.setName(form.field("name").value());
		deck.setDescription(form.field("description").valueOr(""));
		loggedUser().addDeck(deck).update();
		flash().put("success", "Deck created successfully!!");
		return redirect(routes.CardController.searchForm());
	}
}
