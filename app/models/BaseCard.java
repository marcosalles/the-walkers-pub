package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import models.magic.MagicCard;
import play.db.ebean.Model;

@Entity
@SuppressWarnings("serial")
public class BaseCard extends Model {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MagicCard card;
	private List<Deck> decks;

	public BaseCard() {
		super();
	}
	public BaseCard(MagicCard card) {
		this.card = card;
	}

	public Long getId() {
		return id;
	}
	public BaseCard setId(Long id) {
		this.id = id;
		return this;
	}
	public MagicCard getCard() {
		return card;
	}
	public BaseCard setCard(MagicCard card) {
		this.card = card;
		return this;
	}
	public List<Deck> getDecks() {
		return decks;
	}
	public BaseCard setDecks(List<Deck> decks) {
		this.decks = decks;
		return this;
	}
	public BaseCard addDeck(Deck deck) {
		decks.add(deck);
		return this;
	}
	public BaseCard removeDeck(Deck deck) {
		decks.remove(deck);
		return this;
	}

	public String getName() {
		return card.getSuggestText();
	}
	public Long getMultiverseId() {
		return card.getMultiverseId();
	}
	public String getManaCost() {
		return card.getManaCost();
	}
	public String getText() {
		return card.getText();
	}
}
