package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.Logger;
import play.db.ebean.Model;
import play.libs.Json;

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
	@OneToMany(cascade = CascadeType.PERSIST)
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
		collection = new ArrayList<CollectionCard>();
	}

	public Long getId() {
		return id;
	}

	public User setId(Long id) {
		this.id = id;
		return this;
	}

	public String getLogin() {
		return login;
	}

	public User setLogin(String login) {
		if (login == null)
			login = "";
		this.login = login;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		if (password == null)
			password = "";
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public List<Deck> getDecks() {
		return decks;
	}

	public User setDecks(List<Deck> decks) {
		this.decks = decks;
		return this;
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

	public User setCollection(List<CollectionCard> collection) {
		this.collection = collection;
		return this;
	}
	
	
	public User addToCollection(CollectionCard card){
		this.collection.add(card);
		return this;
	}

	public boolean removeFromCollection(CollectionCard card){
		return this.collection.remove(card);
	}

	public CollectionCard cardByMultiverseId(Long multiverseId){
		for(CollectionCard c: this.collection){
			if(c.getMultiverseId().equals(multiverseId)){
				return c;
			}
		}
		return null;
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

	public User setLocation(String location) {
		this.location = location;
		return this;
	}

}
