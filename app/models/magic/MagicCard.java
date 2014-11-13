package models.magic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@SuppressWarnings("serial")
@Table(name="card")
public class MagicCard extends Model implements Comparable<MagicCard> {

	@Id
	@GeneratedValue
	private Long id;
	@Column(columnDefinition = "text")
	private String text;
	@Column(columnDefinition = "text")
	private String flavorText;
	private String suggestText;
	private String expansionText;
	private String type;
	private Integer rarity;
	private String manaCost;
	private Integer cmc;
	private Double power;
	private Double toughness;
	private Integer loyalty;
	private String artist;
	private String number;
	private Long multiverseId;
	@Enumerated(EnumType.STRING)
	private MagicColorEnum color;
	private String rulings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	public String getSuggestText() {
		return suggestText;
	}

	public void setSuggestText(String suggestText) {
		this.suggestText = suggestText;
	}

	public String getExpansionText() {
		return expansionText;
	}

	public void setExpansionText(String expansionText) {
		this.expansionText = expansionText;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRarity() {
		return rarity;
	}

	public void setRarity(Integer rarity) {
		this.rarity = rarity;
	}

	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public Integer getCmc() {
		return cmc;
	}

	public void setCmc(Integer cmc) {
		this.cmc = cmc;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public Double getToughness() {
		return toughness;
	}

	public void setToughness(Double toughness) {
		this.toughness = toughness;
	}

	public Integer getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(Integer loyalty) {
		this.loyalty = loyalty;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getMultiverseId() {
		return multiverseId;
	}

	public void setMultiverseId(Long multiverseId) {
		this.multiverseId = multiverseId;
	}

	public MagicColorEnum getColor() {
		return color;
	}

	public void setColor(MagicColorEnum color) {
		this.color = color;
	}

	public String getRulings() {
		return rulings;
	}

	public void setRulings(String rulings) {
		this.rulings = rulings;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof MagicCard) {
			MagicCard card = (MagicCard)obj;
			if (card.getSuggestText().equals(this.getSuggestText())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int compareTo(MagicCard card) {
		if (this.getMultiverseId() > card.getMultiverseId()) {
			return 1;
		}
		else if (this.getMultiverseId() < card.getMultiverseId()) {
			return -1;
		}
		return 0;
	}

}
