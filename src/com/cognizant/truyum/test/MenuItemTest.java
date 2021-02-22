package com.cognizant.truyum.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.cognizant.truyum.dao.MenuItemDaoCollectionImpl;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

class MenuItemTest {

	MenuItemDaoCollectionImpl menu=new MenuItemDaoCollectionImpl();
	@Test
	public void testGetMenuItemListAdmin() {
		//List<MenuItem> menuItemList = menu.getMenuItemListAdmin();
		assertNotNull(menu.getMenuItemListAdmin());
	}
	@Test
	public void testGetMenuItemListCustomer() {
		List<MenuItem> menuItemList = menu.getMenuItemListCustomer();
		assertEquals(menuItemList,menu.getMenuItemListCustomer());
	}
	@Test
	public void testModifyMenuItem() {
	MenuItem menuitem = new MenuItem(1, "Dosa", 30.00f, true, DateUtil.convertToDate("22/07/2015"),"Main Course" ,true);
	menu.modifyMenuItem(menuitem);
	assertEquals(menuitem,menu.getMenuItem(1));
	}
}
