package models;

import java.util.List;

import models.magic.MagicCard;

public interface Card {

	public Long getId();
	public MagicCard getMagicCard();
	public String getName();
	public String getText();
	public Integer getQuantity();
	public Long getMultiverseId();
	public String getManaCost();
	public List<Deck> getDecks();
}
