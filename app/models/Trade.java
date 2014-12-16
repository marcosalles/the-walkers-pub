package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity
public class Trade extends Model {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User interested;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User owner;
	//Magic Card ou Collection card??TODO
	@ManyToOne(cascade = CascadeType.PERSIST)
	private CollectionCard card;
	private String proposal;
	
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
	public User getOwner() {
		return owner;
	}
	public User getInterested() {
		return interested;
	}
	public void setInterested(User interested) {
		this.interested = interested;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getProposal() {
		return proposal;
	}
	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	
}
