package controllers;

import java.util.List;

import models.CollectionCard;
import models.User;
import models.magic.MagicCard;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import views.html.user.collection;
import views.html.user.owners;
import daos.CardDao;
import daos.CollectionDao;
import daos.UserDao;

public class CollectionController extends BaseController {

	public static Result removeCard() {
		DynamicForm form = Form.form().bindFromRequest();
		String id = form.field("id").value();
		int quantity = Integer.parseInt(form.field("quantity").value());
		CollectionCard card = CardDao.collectionCardById(id);
		if (quantity >= card.getQuantity()) {
			if (loggedUser().removeFromCollection(card)) {
				loggedUser().update();
				String name = card.getName();
				card.delete();
				return ok("removed all "+name+"'s from your collection");
			}
		} else {
			card.setQuantity(card.getQuantity()-quantity);
			card.update();
			return ok("removed "+quantity+" x "+card.getName()+" from your collection");
		}
		return badRequest("card removal from collection failed");
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
		String multiverseId = form.field("multiverseId").value();
		List<User> ownersList = CollectionDao.owners(multiverseId);
		return wrapOk(owners.render(ownersList,Long.parseLong(multiverseId)));
	}
	
	public static Result userCollection(Long id){
		User user = UserDao.userById(id);
		return ok(collection.render(user));
	}
}
