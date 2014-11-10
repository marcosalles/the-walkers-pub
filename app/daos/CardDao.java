package daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.magic.Card;
import play.data.Form;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.ExpressionList;

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

	public static List<Card> cardsByFilledFields(Form<Card> form) {
		ExpressionList<Card> where = find.where();
		Card card = form.get();
		String name = card.getSuggestText().trim();
		String text = card.getText().trim();
		String type = card.getType().trim();
		String colors = form.field("colors[]").value();
		System.out.println("colors: '"+colors+"'");

		if (name != null && name.length() > 0) {
			where = where.ilike("suggestText", "%"+name+"%");
		}
		if (text != null && text.length() > 0) {
			where = where.ilike("text", "%"+text+"%");
		}
		if (type != null && type.length() > 0) {
			where = where.ilike("text", "%"+type+"%");
		}
		Map<String,Card> map = new HashMap<String, Card>();
		for (Card c : where.findList()) {
			map.put(c.getSuggestText(), c);
		}
		return new ArrayList<Card>(map.values());
	}

//	r: 66 terreno
//	r: 67 commum
//	r: 77 mitica
//	r: 82 rara
//	r: 84 especial (time spiral)
//	r: 85 incomum
}
