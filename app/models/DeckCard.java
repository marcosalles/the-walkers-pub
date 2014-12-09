package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import models.magic.MagicCard;
import play.db.ebean.Model;

@Entity
@SuppressWarnings("serial")
public class DeckCard extends Model implements Card {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MagicCard card;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Deck deck;
	private Integer quantity;
	private Boolean side;

	public DeckCard() {
	}
	public DeckCard(MagicCard card) {
		this.card = card;
	}

	public Long getId() {
		return id;
	}
	public DeckCard setId(Long id) {
		this.id = id;
		return this;
	}
	public MagicCard getCard() {
		return card;
	}
	public DeckCard setCard(MagicCard card) {
		this.card = card;
		return this;
	}
	public Deck getDeck() {
		return deck;
	}
	public DeckCard setDeck(Deck deck) {
		this.deck = deck;
		return this;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public DeckCard setQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}
	public Boolean getSide() {
		return side;
	}
	public DeckCard setSide(Boolean side) {
		this.side = side;
		return this;
	}
	@Override
	public String getName() {
		return card.getSuggestText();
	}
	@Override
	public Long getMultiverseId() {
		return card.getMultiverseId();
	}
	@Override
	public String getManaCost() {
		return card.getManaCost();
	}
	@Override
	public String getText() {
		return card.getText();
	}
}
