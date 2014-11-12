package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import models.magic.Card;
import play.db.ebean.Model;

@Entity
@SuppressWarnings("serial")
public class DeckCard extends Model {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Deck deck;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Card card;
	private Integer quantity;
	private Boolean side;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Boolean getSide() {
		return side;
	}
	public void setSide(Boolean side) {
		this.side = side;
	}
}
