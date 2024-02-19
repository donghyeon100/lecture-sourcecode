package com.kh.practice.snack.model.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class Snack {
	// 필드
	private String kind;
	private String name;
	private String flavor;
	private int numOf;
	private int price;
	
	public Snack() {} // 기본생성자
	
	// 매개변수 생성자
	public Snack(String kind, String name, String flavor, int numOf, int price) {
		super();
		this.kind = kind;
		this.name = name;
		this.flavor = flavor;
		this.numOf = numOf;
		this.price = price;
	}
	
	// getter/setter
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlavor() {
		return flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	public int getNumOf() {
		return numOf;
	}
	@Override
	public int hashCode() {
		return Objects.hash(flavor, kind, name, numOf, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Snack other = (Snack) obj;
		return Objects.equals(flavor, other.flavor) && Objects.equals(kind, other.kind)
				&& Objects.equals(name, other.name) && numOf == other.numOf && price == other.price;
	}

	public void setNumOf(int numOf) {
		this.numOf = numOf;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String infomation() {
		Map<String, String> map = new  HashMap<String, String>();
		Set<Entry<String, String>> entrySet = map.entrySet();
		return kind +"(" + name + " - " + flavor +  ") " + numOf +"개 " + price+"원";
	}
	
	
}
