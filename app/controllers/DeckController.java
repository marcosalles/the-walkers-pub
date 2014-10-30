package controllers;

import java.util.List;

import models.Deck;
import play.mvc.Result;
import views.html.deck.*;
import daos.DeckDao;

public class DeckController extends BaseController {

	public static Result searchForm() {
		return ok();
	}

	public static Result featuredDecks() {
		List<Deck> decks = DeckDao.list();
		return ok(featuredDecksList.render(decks));
	}
}
