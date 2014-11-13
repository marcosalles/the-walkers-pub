package daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.magic.MagicCard;
import play.data.Form;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.ExpressionList;

public class CardDao {

	public static Finder<Long, MagicCard> find = new Finder<Long, MagicCard>(Long.class, MagicCard.class);

	public static List<MagicCard> cardsByName(String name) {
		return find.where()
				.ilike("suggestText", "%"+name+"%")
				.findList();
	}

	public static MagicCard cardByMultiverseId(String id) {
		return find.where()
				.eq("multiverseId", id)
				.findUnique();
	}

	public static List<MagicCard> cardsByFilledFields(Form<MagicCard> form) {
		ExpressionList<MagicCard> where = find.where();
		MagicCard card = form.get();
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
		Map<String,MagicCard> map = new HashMap<String, MagicCard>();
		for (MagicCard c : where.findList()) {
			map.put(c.getSuggestText(), c);
		}
		return new ArrayList<MagicCard>(map.values());
	}

	public static List<MagicCard> list() {
		return find.all();
	}

//	r: 66 terreno
//	r: 67 commum
//	r: 77 mitica
//	r: 82 rara
//	r: 84 especial (time spiral)
//	r: 85 incomum
}
