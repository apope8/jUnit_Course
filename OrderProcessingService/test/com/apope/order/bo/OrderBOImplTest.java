package com.apope.order.bo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.apope.order.bo.exception.BOException;
import com.apope.order.dao.OrderDAO;
import com.apope.order.dto.Order;

public class OrderBOImplTest {
	
	@Mock
	OrderDAO dao;
	private OrderBOImpl bo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}
	
	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
		
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(1));
		
		boolean result = bo.placeOrder(order);
		
		assertTrue(result);
		verify(dao).create(order);
	}
	
	@Test
	public void placeOrder_Should_Not_Create_An_Order() throws SQLException, BOException {
		
		
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		
		boolean result = bo.placeOrder(order);
		
		assertFalse(result);
		verify(dao).create(order);
	}

}
