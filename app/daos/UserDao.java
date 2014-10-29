package daos;

import java.util.List;

import models.PlaneswalkerUser;
import models.User;
import play.db.ebean.Model.Finder;

public class UserDao {

	private static Finder<Long, PlaneswalkerUser> find = new Finder<Long, PlaneswalkerUser>(Long.class, PlaneswalkerUser.class);

	public static List<PlaneswalkerUser> list() {
		return find.all();
	}

	public static User userById(Long id) {
		User user = find.byId(id);
		return user;
	}

	public static User userByLogin(String login) {
		User user = find.where()
				.eq("login", login)
				.findUnique();
		return user;
	}

	public static boolean userExists(User user) {
		User userByLogin = userByLogin(user.getLogin());
		return userByLogin != null;
	}
}
