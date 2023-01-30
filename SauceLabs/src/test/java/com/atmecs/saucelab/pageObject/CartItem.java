package com.atmecs.saucelab.pageObject;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	Integer qty;
	String price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, price, qty);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		return Objects.equals(name, other.name) && Objects.equals(price, other.price) && Objects.equals(qty, other.qty);
	}
	@Override
	public String toString() {
		return "CartItem [name=" + name + ", qty=" + qty + ", price=" + price + "]";
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

}
