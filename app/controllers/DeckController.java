package controllers;

import infra.Module;

import java.util.Arrays;
import java.util.List;

import models.Deck;
import models.DeckCard;
import models.magic.MagicCard;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import views.html.decks.createForm;
import views.html.decks.deck;
import views.html.decks.list;
import views.html.decks.searchDecks;
import views.html.shared.modular;
import daos.CardDao;
import daos.DeckDao;

public class DeckController extends BaseController {

	public static final String HIGHLIGHTED = "highlighted";
	public static final String LATEST = "latest";
	public static final String RANDOM = "random";

	public static Result content(String content) {
		if (content.equalsIgnoreCase(HIGHLIGHTED)) {
			List<Deck> decks = DeckDao.highlightedDecks();
			return ok(list.render(decks));
		}
		else if (content.equalsIgnoreCase(LATEST)) {
			List<Deck> decks = DeckDao.latestDecks();
			return ok(list.render(decks));
		}
		else if (content.equalsIgnoreCase(RANDOM)) {
			Deck random = DeckDao.random();
			return ok(deck.render(random, true));
		}
		return badRequest("content not found");
	}

	public static Result search() {
		Form<Deck> form = Form.form(Deck.class).bindFromRequest();
		String name = form.field("name").valueOr("");
		List<Deck> list = null;
		if (form.field("q").valueOr("").equals("1")) {
			list = DeckDao.decksByName(name);
		}
		return wrapOk(searchDecks.render(form, list));
	}

	public static Result addCard() {
		try {
			DynamicForm form = Form.form().bindFromRequest();
			Long deckId = Long.parseLong(form.field("deckId").value());
			String cardId = form.field("cardId").value();
			int quantity = Integer.parseInt(form.field("quantity").value());
			boolean side = Boolean.parseBoolean(form.field("side").value());

			Deck deck = DeckDao.deckById(deckId);
			MagicCard card = CardDao.cardByMultiverseId(cardId);

			DeckCard deckCard = new DeckCard(card).setSide(side)
					.setQuantity(quantity)
					.setDeck(deck);
			deck.addCard(deckCard);
			deck.update();

			return ok("card added successfully");
		} catch (Exception e) {
			return badRequest("failed to add card");
		}
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
		return redirect(routes.CardController.search());
	}

	public static Result randomDeck() {
		Module random = new Module(routes.DeckController.content(RANDOM), RANDOM, 5);
		List<Module> list = Arrays.asList(random);
		return wrapOk(modular.render(list));
	}
}
