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
public class DeckCard extends Model implements Card {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private BaseCard card;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Deck deck;
	private Integer quantity;
	private Boolean side;

	public DeckCard() {
	}
	public DeckCard(BaseCard card) {
		setCard(card);
	}
	public DeckCard(MagicCard card) {
		setCard(card);
	}

	public Long getId() {
		return id;
	}
	public DeckCard setId(Long id) {
		this.id = id;
		return this;
	}
	public BaseCard getCard() {
		return card;
	}
	public DeckCard setCard(BaseCard card) {
		this.card = card;
		return this;
	}
	public DeckCard setCard(MagicCard card) {
		this.card = new BaseCard(card);
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
		return card.getName();
	}
	@Override
	public MagicCard getMagicCard() {
		return card.getCard();
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
	public List<Deck> getDecks() {
		return card.getDecks();
	}
	@Override
	public String getText() {
		return card.getText();
	}
}
