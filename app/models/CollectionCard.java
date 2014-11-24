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
public class CollectionCard extends Model implements Card {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private BaseCard card;
	private Integer quantity;
	private Double acquiredPrice;
	private String tradeSuggestion;
	private Boolean tradable;
	
	public CollectionCard(){
	}
	
	public CollectionCard(MagicCard card){
		setCard(card);
	}
	
	public void setCard(MagicCard card) {
		this.card = new BaseCard(card);
	}

	public CollectionCard(BaseCard card) {
		this.card = card;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BaseCard getCard() {
		return card;
	}
	public void setCard(BaseCard card) {
		this.card = card;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getAcquiredPrice() {
		return acquiredPrice;
	}
	public void setAcquiredPrice(Double acquiredPrice) {
		this.acquiredPrice = acquiredPrice;
	}
	public String getTradeSuggestion() {
		return tradeSuggestion;
	}
	public void setTradeSuggestion(String tradeSuggestion) {
		this.tradeSuggestion = tradeSuggestion;
	}
	public Boolean getTradable() {
		return tradable;
	}
	public void setTradable(Boolean tradable) {
		this.tradable = tradable;
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
