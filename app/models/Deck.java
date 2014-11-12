package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.magic.Format;
import play.db.ebean.Model;

@Entity
@SuppressWarnings("serial")
public class Deck extends Model {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User owner;
	private String name;
	private String description;
	private Format format;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<DeckCard> cards;

	public Deck() {
		cards = new ArrayList<DeckCard>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public List<DeckCard> getCards() {
		return cards;
	}

	public void setCards(List<DeckCard> cards) {
		this.cards = cards;
	}

	public void addCard(DeckCard card) {
		cards.add(card);
	}
}
