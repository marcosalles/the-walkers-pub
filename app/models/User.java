package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
@Table(name="users")
@SuppressWarnings("serial")
public class User extends Model {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String login;
	private String password;
	private String email;
	@OneToMany(mappedBy="owner", cascade=CascadeType.PERSIST)
	private List<Deck> decks;
	private List<Deck> favorites;

	public User() {
		decks = new ArrayList<Deck>();
		favorites = new ArrayList<Deck>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (login == null) login = "";
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null) password = "";
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Deck> getDecks() {
		return decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}

	public List<Deck> getFavorites() {
		return favorites;
	}
	public void addDeck(Deck deck) {
		decks.add(deck);
//		deck.setOwner(this);
	}

	public void setFavorites(List<Deck> favorites) {
		this.favorites = favorites;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (getLogin() == null) {
			if (other.getLogin() != null)
				return false;
		} else if (!getLogin().equals(other.getLogin()))
			return false;
		if (getPassword() == null) {
			if (other.getPassword() != null)
				return false;
		} else if (!getPassword().equals(other.getPassword()))
			return false;
		return true;
	}

	
}
