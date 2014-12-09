package controllers;

import java.util.List;

import models.CollectionCard;
import models.User;
import models.magic.MagicCard;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import daos.CardDao;
import daos.CollectionDao;

public class CollectionController extends BaseController {

	public static Result removeCard() {
		DynamicForm form = Form.form().bindFromRequest();
		String id = form.field("id").value();
		CollectionCard card = CardDao.collectionCardById(id);
		if (loggedUser().removeFromCollection(card)) {
			loggedUser().update();
			return ok("removed card from your collection");
		}
		return badRequest("card removal from collection failed");
	}

	public static Result tradeCard() {
		DynamicForm form = Form.form().bindFromRequest();
		form.field("userId");
		return TODO;
	}

	public static Result addToCollection(){
		Form<CollectionCard> form = Form.form(CollectionCard.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest("failed to add card to collection");
		}
		String multiverseId = form.field("cardId").value();
		MagicCard card = CardDao.cardByMultiverseId(multiverseId);
		CollectionCard collectionCard = form.get();
		collectionCard.setCard(card);
		/*
		int quantity = 0;
		boolean tradable= false;
		double acquiredPrice= 0;
		String tradeSuggestion="";
		String cardId ="";
		try {
			quantity = Integer.parseInt(form.field("quantity").value());
			tradable = Boolean.parseBoolean(form.field("tradable").value());
			acquiredPrice = Double.parseDouble(form.field("acquiredePrice").value());
			tradeSuggestion = form.field("tradeSuggestion").value();
			cardId = form.field("cardId").value();
		} catch (NumberFormatException e) {
			flash("info","Something went wrong!!!!!");
			return badRequest("failed to add card to collection");
		}
		
		CollectionCard collectionCard = new CollectionCard(card);
		collectionCard.setAcquiredPrice(acquiredPrice);
		collectionCard.setQuantity(quantity);
		collectionCard.setTradable(tradable);
		collectionCard.setTradeSuggestion(tradeSuggestion);*/
		//TODO maybe put this in a DAO
		loggedUser().addToCollection(collectionCard).update();
		return ok("card successfully added to collection");
	}
	/**
	 * Esse método recebe uma carta e procura 
	 * pessoas que tenham a carta e está marcada
	 * como possivel troca	 * 
	 * @return 
	 */
	public static Result ownersForTrade(){
		Form<MagicCard> form = Form.form(MagicCard.class).bindFromRequest();
		List<User> owners = CollectionDao.owners(form.field("multiverseId").value());
		//TODO have no idea how to return the users
		return ok();
	}
	
	public static Result userCollection(Long id){
		Form<User> form = Form.form(User.class).bindFromRequest();
		List<CollectionCard> collection = CollectionDao.userCollecion(id);
		return ok();
	}
}
