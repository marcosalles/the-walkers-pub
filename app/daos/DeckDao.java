package daos;

import java.util.List;
import java.util.Random;

import models.Deck;
import models.User;
import play.data.Form;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.ExpressionList;

public class DeckDao {

	private static Finder<Long, Deck> find= new Finder<Long, Deck>(Long.class, Deck.class);

	public static List<Deck> list() {
		List<Deck> list = find.where()
				.eq("availableToPublic", true)
				.findList();
		return list;
	}

	public static Deck deckById(Long id) {
		Deck deck = find.byId(id);
		return deck;
	}

	public static List<Deck> decksByOwner(User owner) {
		List<Deck> list = find.where()
				.eq("owner", owner)
				.findList();
		return list;
	}

	public static List<Deck> decksByName(String name) {
		List<Deck> list = find.where()
				.eq("availableToPublic", true)
				.ilike("name", "%"+name+"%")
				.findList();
		return list;
	}

	public static Deck random() {
		List<Deck> list = list();
		if (list.size() < 1) {
			return null;
		}
		Random random = new Random();
		int n = random.nextInt(list.size());
		return list.get(n);
	}

	public static List<Deck> highlightedDecks() {
		return find.all().subList(0, 5);
	}

	public static List<Deck> latestDecks() {
		return find.where()
				.order()
				.desc("id")
				.findList()
				.subList(0, 5);
	}

	public static List<Deck> decksByFilledFields(Form<Deck> form) {
		ExpressionList<Deck> where = find.where();
		Deck deck = form.get();
		if (deck == null) {
			return null;
		}
		String name = deck.getName();
		String description = deck.getDescription();
		String htp = deck.getHowToPlay();

		if (name != null && (name = name.trim()).length() > 0) {
			where = where.ilike("name", "%"+name+"%");
		}
		if (description != null && (description = description.trim()).length() > 0) {
			where = where.ilike("description", "%"+description+"%");
		}
		if (htp != null && (htp = htp.trim()).length() > 0) {
			where = where.ilike("howToPlay", "%"+htp+"%");
		}
		
		return where.findList();
	}
}
