package com.cognizant.truyum.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.cognizant.truyum.dao.CartDaoCollectionImpl;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;

class CartDaoTest {
	CartDaoCollectionImpl cart=new CartDaoCollectionImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testAddCartItem() throws CartEmptyException {
		cart.addCartItem(1, 2);
		List<MenuItem> menuItemList=cart.getAllCartItems(1);
		assertEquals(menuItemList,cart.getAllCartItems(1));
	}
	@Test
	public void testGetAllCartItems() throws CartEmptyException {
		List<MenuItem> menuItemList=cart.getAllCartItems(1);
		assertEquals(menuItemList,cart.getAllCartItems(1));
	}
	@Test
	public void testRemoveCartItem() {
		cart.removeCartItem(1, 2);
		try {
			assertEquals("",cart.getAllCartItems(1));
		}catch(CartEmptyException e) {
			System.out.println("Cart is Empty");
		}
	}
}
