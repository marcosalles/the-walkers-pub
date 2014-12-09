package models;

import java.util.List;

@SuppressWarnings("serial")
public class Ghost extends User {

	private static Ghost instance;
	public static Ghost instance() {
		if (instance == null) {
			instance = new Ghost();
		}
		return instance;
	}
	private Ghost() {
	}
	@Override
	public Long getId() {
		return 0l;
	}
	@Override
	public User setId(Long id) {
		return this;
	}
	@Override
	public String getLogin() {
		return "Ghost";
	}
	@Override
	public User setLogin(String login) {
		return this;
	}
	@Override
	public String getPassword() {
		return "";
	}
	@Override
	public User setPassword(String password) {
		return this;
	}
	@Override
	public String getEmail() {
		return "ghost@thewalkerspub.com";
	}
	@Override
	public User setEmail(String email) {
		return this;
	}
	@Override
	public User setDecks(List<Deck> decks) {
		return this;
	}
	@Override
	public User addDeck(Deck deck) {
		return this;
	}
	@Override
	public User setCollection(List<CollectionCard> collection) {
		return this;
	}
	@Override
	public User addToCollection(CollectionCard card) {
		return this;
	}
	@Override
	public User setLocation(String location) {
		return this;
	}
	
}