package com.jdc.shop.utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jdc.shop.model.dto.form.PurchaseAddressForm;
import com.jdc.shop.model.dto.form.PurchasePaidForm;
import com.jdc.shop.model.dto.vo.CartItemVo;
import com.jdc.shop.model.dto.vo.ProductDetailsVo;

public class ShoppingCart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private PurchaseAddressForm address;
	private List<PurchasePaidForm> paidInformations;
	private Map<Integer, CartItemVo> items;
	
	public ShoppingCart() {
		items = new LinkedHashMap<>();
		paidInformations = new ArrayList<>();
	}

	public boolean add(int productId) {
		
		var item = items.get(productId);
		if(null != item) {
			return item.addOne();
		}
		
		return false;
	}

	public void addNewProduct(ProductDetailsVo product) {
		var item = new CartItemVo(product);
		items.put(product.getProduct().getId(), item);
	}
	
	public PurchaseAddressForm getAddress() {
		return address;
	}
	
	public void setAddress(PurchaseAddressForm address) {
		this.address = address;
	}
	
	public List<PurchasePaidForm> getPaidInformations() {
		return paidInformations;
	}
	
	public int getSize() {
		if(!items.isEmpty()) {
			return items.values().stream()
					.mapToInt(CartItemVo::getQuantity).sum();
		}
		
		return 0;
	}
	
	public List<CartItemVo> getItems() {
		return new ArrayList<>(items.values());
	}
	
	public int getTotal() {
		return getItems().stream().mapToInt(CartItemVo::getTotal).sum();
	}

	public int getTotalPaid() {
		if(null != paidInformations && !paidInformations.isEmpty()) {
			return paidInformations.stream().mapToInt(PurchasePaidForm::getAmount).sum();
		}
		
		return 0;
	}
	
	public int getRemain() {
		return getTotal() - getTotalPaid();
	}
	
	public boolean isPaid() {
		return getRemain() <= 0;
	}

	public void addPaidInfo(PurchasePaidForm paidForm) {
		paidInformations.add(paidForm);
	}

	public void removePaid(int index) {
		paidInformations.remove(index);
	}

}
