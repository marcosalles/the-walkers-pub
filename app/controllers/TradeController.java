package controllers;

import java.util.List;

import models.Trade;
import models.User;
import play.data.Form;
import play.mvc.Result;
import daos.TradeDao;

public class TradeController extends BaseController {
	 //TODO melhorar
	public static Result listTrades() {
		List<Trade> trades = TradeDao.tradesByUser(loggedUser());
		return (trades.isEmpty() ? ok("No trades") : ok("todo"));
	}

	public static Result addTrades() {
		Form<Trade> form = Form.form(Trade.class).bindFromRequest();
		boolean b = true;
		if (!form.hasErrors()) {
			b = false;
			Trade trade = form.get();
			trade.save();
		}
		return (b ? badRequest("Something went wrong!!") : ok("trade started"));
	}

	public static Result tradeConclude() {
		Form<Trade> form = Form.form(Trade.class).bindFromRequest();
		boolean b = true;
		if (!form.hasErrors()) {
			b = false;
			Trade trade = form.get();
			User interested = trade.getInterested();
			interested.getCollection().add(trade.getCard());
			interested.save();
			User owner =trade.getCardOwner();
			owner.getCollection().remove(trade.getCard());
			owner.save();
		}
		return (b ? ok("Something went wrong") : ok("todo"));
	}
}
