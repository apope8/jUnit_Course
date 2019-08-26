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
	
	private static final int ORDERID = 123;
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
	
	@Test(expected = BOException.class)
	public void placeOrder_Should_Not_Throw_BOException() throws SQLException, BOException {	
		
		Order order = new Order();
		when(dao.create(order)).thenThrow(SQLException.class);
		
		boolean result = bo.placeOrder(order);
		
	}
	
	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(ORDERID)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(ORDERID);
		
		assertTrue(result);
		verify(dao).read(ORDERID);
		verify(dao).update(order);
	}
	
	@Test
	public void cancelOrder_Should_Not_Cancel_The_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(ORDERID)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(ORDERID);
		
		assertFalse(result);
		verify(dao).read(ORDERID);
		verify(dao).update(order);
	}
	
	@Test(expected = BOException.class)
	public void cancelOrder_Should_Throw_BOException_On_Read() throws SQLException, BOException {
		when(dao.read(ORDERID)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDERID);
	}
	
	@Test(expected = BOException.class)
	public void cancelOrder_Should_Throw_BOException_On_Update() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(ORDERID)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDERID);
		
	}

	
	@Test
	public void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
		when(dao.delete(ORDERID)).thenReturn(1);
		bo.deleteOrder(ORDERID);
		boolean result = bo.deleteOrder(ORDERID);
		assertTrue(result);
		verify(dao).delete(ORDERID);
	}
}
