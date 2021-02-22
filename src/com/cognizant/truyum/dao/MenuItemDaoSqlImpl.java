package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		final String sql = "select * from menu_item";
		Connection con = ConnectionHandler.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("Name");
				float price = rs.getFloat("Price");
				String active = rs.getString("active");
				Date date = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				String free_delivery = rs.getString("free_delivery");
				boolean act = false;
				if (active.equalsIgnoreCase("yes")) {
					act = true;
				}
				boolean free = false;
				if (free_delivery.equalsIgnoreCase("yes")) {
					free = true;
				}
				MenuItem item1 = new MenuItem(id, name, price, act, date, category , free);
				menuItemList.add(item1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> menuItemListCust = new ArrayList<MenuItem>();
		Connection con = ConnectionHandler.getConnection();
		final String sql = "select * from menu_item where date_of_launch >= '2017-08-21' and active = 'yes'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("Name");
				float price = rs.getFloat("Price");
				String active = rs.getString("active");
				Date date1 = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				String free_delivery = rs.getString("free_delivery");
				boolean act = false;
				if (active.equalsIgnoreCase("yes")) {
					act = true;
				}
				boolean free = false;
				if (free_delivery.equalsIgnoreCase("yes")) {
					free = true;
				}
				MenuItem item1 = new MenuItem(id, name, price, act, date1, category , free);
				menuItemListCust.add(item1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return menuItemListCust;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		Connection con = ConnectionHandler.getConnection();
		long id = menuItem.getId();
		String name = menuItem.getName();
		float price = menuItem.getPrice();
		boolean active = menuItem.isActive();

		java.sql.Date d = (java.sql.Date) new Date(menuItem.getDateOfLaunch().getTime());
		String category = menuItem.getCategory();
		boolean free = menuItem.isFreeDelivery();
		String act;
		if (active == true) {
			act = "Yes";
		} else {
			act = "No";
		}
		String fd;
		if (free == true) {
			fd = "Yes";
		} else {
			fd = "No";
		}
		final String sql = "update menu_item set name = ?,price = ?,active = ?,date_of_launch = ?,category = ?,"
				+ "free_delivery = ? where id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setFloat(2, price);
			ps.setString(3, act);
			ps.setDate(4, d);
			ps.setString(5, category);
			ps.setString(6, fd);
			ps.setLong(7, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		Connection con = ConnectionHandler.getConnection();
		MenuItem item = null;
		final String sql = "select * from menu_item where id=?";
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(4, menuItemId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("Name");
				float price = rs.getFloat("Price");
				String active = rs.getString("active");
				Date date1 = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				String free_delivery = rs.getString("free_delivery");
				boolean act = false;
				if (active.equalsIgnoreCase("yes")) {
					act = true;
				}
				boolean free = false;
				if (free_delivery.equalsIgnoreCase("yes")) {
					free = true;
				}
				item = new MenuItem(id, name, price, act, date1, category , free);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return item;
	}

}
