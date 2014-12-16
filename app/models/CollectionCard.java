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
public class CollectionCard extends Model implements Card {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MagicCard card;
	private Integer quantity;
	private Double acquiredPrice;
	private String tradeSuggestion;
	private Boolean tradable;
	
	public CollectionCard(){
	}
	
	public CollectionCard(MagicCard card){
		this.card = card;
	}
	public CollectionCard(CollectionCard card) {
		this.card = card.getCard();
		this.quantity = card.getQuantity();
		this.tradable = false;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MagicCard getCard() {
		return card;
	}
	public void setCard(MagicCard card) {
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
