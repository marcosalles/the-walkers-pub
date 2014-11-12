package controllers;

import java.util.List;

import models.Deck;
import models.DeckCard;
import models.magic.Card;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import views.html.decks.*;
import daos.CardDao;
import daos.DeckDao;

public class DeckController extends BaseController {

	public static Result searchForm() {
		Form<Deck> form = Form.form(Deck.class);
		return wrapOk(searchDecks.render(form, null));
	}

	public static Result search() {
		Form<Deck> form = Form.form(Deck.class).bindFromRequest();
		String name = form.field("name").valueOr("");
		List<Deck> list = DeckDao.listByName(name);
		return wrapOk(searchDecks.render(form, list));
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

		DeckCard deckCard = new DeckCard();
		deckCard.setCard(card);
		deckCard.setQuantity(quantity);
		deck.addCard(deckCard);

		deck.update();
		return ok("Card added successfully!!!");
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
