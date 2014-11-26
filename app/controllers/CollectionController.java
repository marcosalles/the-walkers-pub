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
	
	public static Result addToCollection(){
		DynamicForm form = Form.form().bindFromRequest();
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
			flash().put("info","Something went wrong!!!!!");
			return badRequest("failed to add card to collection");
		}
		
		MagicCard card = CardDao.cardByMultiverseId(cardId);
		CollectionCard collectionCard = new CollectionCard(card);
		collectionCard.setAcquiredPrice(acquiredPrice);
		collectionCard.setQuantity(quantity);
		collectionCard.setTradable(tradable);
		collectionCard.setTradeSuggestion(tradeSuggestion);
		//TODO maybe put this in a DAO
		loggedUser().addCardToCollection(collectionCard);
		//TODO end
		flash().put("info","Card successfully added to collection!");
		return ok();
	}
	/**
	 * Esse método recebe uma carta e procura 
	 * pessoas que tenham a carta e está marcada
	 * como possivel troca	 * 
	 * @return 
	 */
	public static Result ownersForTrade(){
		Form<MagicCard> form = Form.form(MagicCard.class).bindFromRequest();
		List<User> owner = CollectionDao.owners(form.field("multiverseId").value());
		//TODO have no idea how to return the users
		return ok();
	}
	
}
