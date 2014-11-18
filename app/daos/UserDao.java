package daos;

import java.util.List;

import models.Ghost;
import models.User;
import play.db.ebean.Model.Finder;

public class UserDao {

	private static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

	public static List<User> list() {
		return find.all();
	}

	public static User userById(Long id) {
		User user = find.byId(id);
		if (user == null) {
			user = Ghost.instance();
		}
		return user;
	}

	public static User userByLogin(String login) {
		User user = find.where()
				.ieq("login", login)
				.findUnique();
		if (user == null) {
			user = Ghost.instance();
		}
		return user;
	}

	public static User userByEmail(String email) {
		User user = find.where()
				.ieq("email", email)
				.findUnique();
		if (user == null) {
			user = Ghost.instance();
		}
		return user;
	}
}
