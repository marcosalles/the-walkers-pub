package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name = "users")
@SuppressWarnings("serial")
public class User extends Model {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String login;
	private String password;
	@Column(unique = true)
	private String email;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private List<CollectionCard> collection;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
	private List<Deck> decks;
	private String location;

	public static User fromUser(User user) {
		User newUser = new User();
		newUser.setLogin(user.getLogin());
		newUser.setPassword(user.getPassword());
		newUser.setEmail(user.getEmail());
		newUser.setLocation(user.getLocation());
		newUser.setDecks(user.getDecks());
		return newUser;
	}

	public User() {
		decks = new ArrayList<Deck>();
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
		if (login == null)
			login = "";
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null)
			password = "";
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

	public User addDeck(Deck deck) {
		decks.add(deck);
		return this;
	}

	public boolean isGhost() {
		return (this instanceof Ghost);
	}

	public List<CollectionCard> getCollection() {
		return collection;
	}

	public void setCollection(List<CollectionCard> collection) {
		this.collection = collection;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
