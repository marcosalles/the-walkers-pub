package daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.ExpressionList;

import models.magic.Card;
import models.magic.Color;
import play.db.ebean.Model.Finder;

public class CardDao {

	public static Finder<Long, Card> find = new Finder<Long, Card>(Long.class, Card.class);

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

	public static List<Card> cardsByFilledFields(Card card) {
		ExpressionList<Card> where = find.where();
		String name = card.getSuggestText();
		String text = card.getText();
		if (name != null) where = where.ilike("suggestText", name);
		if (text != null) where = where.ilike("text", text);
		if (card.getColor() != null);
		return where.findList();
	}

//	r: 66 terreno
//	r: 67 commum
//	r: 77 mitica
//	r: 82 rara
//	r: 84 especial (time spiral)
//	r: 85 incomum
}
