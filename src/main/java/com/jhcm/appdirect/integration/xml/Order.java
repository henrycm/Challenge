package com.jhcm.appdirect.integration.xml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
public class Order {
	private String pricingDuration;

	private Item[] item;

	private String editionCode;

	public String getPricingDuration() {
		return pricingDuration;
	}

	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

	public Item[] getItem() {
		return item;
	}

	public void setItem(Item[] item) {
		this.item = item;
	}

	public String getEditionCode() {
		return editionCode;
	}

	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

}