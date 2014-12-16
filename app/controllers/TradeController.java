package controllers;

import java.util.List;

import models.CollectionCard;
import models.Trade;
import models.User;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import views.html.user.trades;
import daos.CardDao;
import daos.TradeDao;
import daos.UserDao;

public class TradeController extends BaseController {
	// TODO melhorar
	public static Result listTrades() {
		List<Trade> tradesList = TradeDao.tradesByUser(loggedUser());
		return wrapOk(trades.render(tradesList,loggedUser()));
	}

	public static Result addTrades() {
		Form<Trade> form = Form.form(Trade.class).bindFromRequest();
		CollectionCard cc = CardDao.collectionCardById(form.field("cardId")
				.value());
		User owner = UserDao.userById(Long.parseLong(form.field("ownerId")
				.value()));
		String proposal = form.field("proposal").value();
		Trade trade = new Trade();
		trade.setCard(cc);
		trade.setOwner(owner);
		trade.setProposal(proposal);
		trade.setInterested(loggedUser());
		trade.save();
		return ok("Trade proposal created");
	}

	public static Result tradeConclude() {
		Form<Trade> form = Form.form(Trade.class).bindFromRequest();
		Trade trade = TradeDao.getById(Long.parseLong(form.field("tradeId")
				.value()));
		User owner = trade.getOwner();
		if (loggedUser().equals(owner)) {
			CollectionCard card = trade.getCard();
			User interested = trade.getInterested();
			String proposal = trade.getProposal();
			trade.delete();
			CollectionCard newCard = new CollectionCard(card);
			owner.removeFromCollection(card);
			owner.update();
			card.delete();
			newCard.setTradeSuggestion(
					"was traded by" + proposal + "user "
							+ owner.getLogin());
			
			interested.addToCollection(newCard).update();
			
			return ok("you have traded!");
		}else{
			return ok("Only owner can conclude trade!!!");
		}
	}
}
