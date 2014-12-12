package daos;

import java.util.List;

import models.Card;
import models.Trade;
import models.User;
import play.db.ebean.Model.Finder;

public class TradeDao {
	private static Finder<Long, Trade> find = new Finder<Long, Trade>(
			Long.class, Trade.class);

	public static List<Trade> tradesByUser(User user) {
		List<Trade> trades = find
				.where()
				.or(com.avaje.ebean.Expr.eq("cardOwner", user),
						com.avaje.ebean.Expr.eq("interested", user)).findList();
		return trades;
	}

	public static List<Trade> tradeByCardUser(User user, Card card) {
		List<Trade> trades = find
				.where()
				.and(com.avaje.ebean.Expr.eq("card", card),
						com.avaje.ebean.Expr.or(
								com.avaje.ebean.Expr.eq("cardOwner", user),
								com.avaje.ebean.Expr.eq("interested", user)))
				.findList();
		return trades;
	}
}
