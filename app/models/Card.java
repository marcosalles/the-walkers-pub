package models;


public interface Card {

	public Long getId();
	public String getName();
	public String getText();
	public Integer getQuantity();
	public Long getMultiverseId();
	public String getManaCost();
}
