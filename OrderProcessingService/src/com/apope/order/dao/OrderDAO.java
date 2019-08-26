package com.apope.order.dao;

import java.sql.SQLException;

import com.apope.order.dto.Order;

public interface OrderDAO {
	
	int create(Order order) throws SQLException;
	
	Order read(int id) throws SQLException;
	
	int update(Order order) throws SQLException;
	
	int delete(int d) throws SQLException;
	
}
