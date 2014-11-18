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
	public void setId(Long id) {
	}
	@Override
	public String getLogin() {
		return "Ghost";
	}
	@Override
	public void setLogin(String login) {
	}
	@Override
	public String getPassword() {
		return "";
	}
	@Override
	public void setPassword(String password) {
	}
	@Override
	public String getEmail() {
		return "ghost@thewalkerspub.com";
	}
	@Override
	public void setEmail(String email) {
	}
	@Override
	public List<Deck> getDecks() {
		return super.getDecks();
	}
	@Override
	public void setDecks(List<Deck> decks) {
	}
	@Override
	public User addDeck(Deck deck) {
		return this;
	}
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Ghost);
	}
}
