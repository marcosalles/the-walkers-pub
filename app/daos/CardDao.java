package daos;

import java.util.List;
import java.util.Set;

import models.magic.Card;
import play.db.ebean.Model.Finder;

public class CardDao {

	public static Finder<Long, Card> find = new Finder<Long, Card>(Long.class, Card.class);

	public static Set<Card> setCardsByName(String name) {
		return find.where()
				.ilike("suggestText", "%"+name+"%")
				.findSet();
	}

	public static List<Card> distinctCardsByName(String name) {
		return find.where()
				.ilike("suggestText", "%"+name+"%")
				.order().desc("multiverseid")
				.findList();
	}

	public static List<Card> cardsByName(String name) {
		return find.where()
				.ilike("suggestText", "%"+name+"%")
				.findList();
	}

	public static Card cardByMultiverseId(String id) {
		return find.where()
				.eq("multiverseId", id)
				.findUnique();
	}
}
