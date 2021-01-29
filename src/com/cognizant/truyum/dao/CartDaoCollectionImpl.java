package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {

	private static Map<Long, Cart> userCarts;

	public CartDaoCollectionImpl() {
		if (userCarts == null) {
			userCarts = new HashMap<Long, Cart>();
		}
	}

	@Override
	public void addCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);
		if (!userCarts.containsKey(userId)) {
			userCarts.put(userId, new Cart(new ArrayList<MenuItem>(), 0));
		}
		List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
		menuItemList.add(menuItem);

	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		// TODO Auto-generated method stub
		Cart cart = userCarts.get(userId);
		if (cart.getMenuItemList().isEmpty()) {
			throw new CartEmptyException("");
		} else {
			for (MenuItem menuItemList : cart.getMenuItemList()) {
				double total = cart.getTotal() + menuItemList.getPrice();
				cart.setTotal(total);
			}
		}
		return cart.getMenuItemList();
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getId() == menuItemId) {
				menuItemList.remove(menuItem);
				break;
			}
		}

	}

}
