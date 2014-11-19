package daos;

import java.util.List;
import java.util.Random;

import models.Deck;
import models.User;
import play.db.ebean.Model.Finder;

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
}
