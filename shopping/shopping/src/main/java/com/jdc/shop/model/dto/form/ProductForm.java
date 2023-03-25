package com.jdc.shop.model.dto.form;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdc.shop.utilities.Integers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

	private int id;
	private String name;
	private String brand;
	private boolean soldOut;
	private int price;
	private String description;

	private List<Integer> categories;
	private Map<String, String> felatures;

	private ProductForm(int id, String name, String brand, boolean soldOut, int price, String description,
			List<Integer> categories, Map<String, String> felatures) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.soldOut = soldOut;
		this.price = price;
		this.description = description;
		this.categories = categories;
		this.felatures = felatures;
	}
	
	public static Builder buidler() {
		return new Builder();
	}

	public static class Builder {
		private int id;
		private String name;
		private String brand;
		private boolean soldOut;
		private int price;
		private String description;

		private List<Integer> categories;
		private Map<String, String> felatures;
		
		private Builder() {
		}
		
		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder brand(String brand) {
			this.brand = brand;
			return this;
		}

		public Builder soldOut(boolean soldOut) {
			this.soldOut = soldOut;
			return this;
		}

		public Builder price(int price) {
			this.price = price;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder categories(String[] array) {
			this.categories = Arrays.stream(array).map(Integers::parse).toList();
			return this;
		}

		public Builder features(String [] names, String [] values) {
			this.felatures = new HashMap<>();
			for (int i = 0; i < names.length; i++) {
				felatures.put(names[i], values[i]);
			}
			return this;
		}

		public ProductForm build() {
			return new ProductForm(id, name, brand, soldOut, price, description, categories, felatures);
		}
	}

}
