package models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

public class Trade extends Model {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private User interested;
	@ManyToOne
	private User cardOwner;
	//Magic Card ou Collection card??TODO
	private CollectionCard card;
	private String proposoal;
	
	public CollectionCard getCard() {
		return card;
	}
	public void setCard(CollectionCard card) {
		this.card = card;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getCardOwner() {
		return cardOwner;
	}
	public User getInterested() {
		return interested;
	}
	public void setInterested(User interested) {
		this.interested = interested;
	}
	public void setCardOwner(User cardOwner) {
		this.cardOwner = cardOwner;
	}
	public String getProposoal() {
		return proposoal;
	}
	public void setProposoal(String proposoal) {
		this.proposoal = proposoal;
	}
	
}
