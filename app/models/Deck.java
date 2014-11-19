package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.magic.MagicFormat;
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
	private String howToPlay;
	@ManyToOne
	private MagicFormat format;
	@OneToMany(mappedBy = "deck", cascade = CascadeType.PERSIST)
	private List<DeckCard> cards;
	@Column(columnDefinition="boolean not null default true")
	private Boolean availableToPublic = true;

	public Deck() {
		cards = new ArrayList<DeckCard>();
	}

	public Long getId() {
		return id;
	}

	public Deck setId(Long id) {
		this.id = id;
		return this;
	}

	public User getOwner() {
		return owner;
	}

	public Deck setOwner(User owner) {
		this.owner = owner;
		return this;
	}

	public String getName() {
		return name;
	}

	public Deck setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Deck setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getHowToPlay() {
		return howToPlay;
	}

	public Deck setHowToPlay(String howToPlay) {
		this.howToPlay = howToPlay;
		return this;
	}

	public MagicFormat getFormat() {
		return format;
	}

	public Deck setFormat(MagicFormat format) {
		this.format = format;
		return this;
	}

	public List<DeckCard> getCards() {
		return cards;
	}

	public Deck setCards(List<DeckCard> cards) {
		this.cards = cards;
		return this;
	}

	public Deck addCard(DeckCard card) {
		cards.add(card);
		return this;
	}

	public Boolean getAvailableToPublic() {
		return availableToPublic;
	}

	public Deck setAvailableToPublic(Boolean availableToPublic) {
		this.availableToPublic = availableToPublic;
		return this;
	}

}
