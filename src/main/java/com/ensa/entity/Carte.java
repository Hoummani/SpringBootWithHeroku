package com.ensa.entity;

import java.io.Serializable;

public class Carte implements Serializable{
	
	private String numero;
	private String dateExperation;
	private String otherNumbers;
	public Carte(String numero, String dateExperation, String otherNumbers) {
		super();
		this.numero = numero;
		this.dateExperation = dateExperation;
		this.otherNumbers = otherNumbers;
	}
	public Carte() {
		super();
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getDateExperation() {
		return dateExperation;
	}
	public void setDateExperation(String dateExperation) {
		this.dateExperation = dateExperation;
	}
	public String getOtherNumbers() {
		return otherNumbers;
	}
	public void setOtherNumbers(String otherNumbers) {
		this.otherNumbers = otherNumbers;
	}
	
	
	
}
