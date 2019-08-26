package com.apope.mockito.scrapbook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ATest {

	@Mock
	B b;
	private A a;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		a = new A(b);
	}

	@Test
	public void usesVoidMethodShoulCallTheVoidMethod() throws Exception {
		doNothing().when(b).voidMethod();
		assertSame(1, a.usesVoidMethod());
		verify(b).voidMethod();
	}

	@Test(expected = RuntimeException.class)
	public void usesVoidMethodShouldThrowRuntimeException() throws Exception {
		doThrow(Exception.class).when(b).voidMethod();
		a.usesVoidMethod();
	}

	@Test(expected = RuntimeException.class)
	public void testConsecutiveCalls() throws Exception {
		doNothing().doThrow(Exception.class).when(b).voidMethod();
		a.usesVoidMethod();
		verify(b).voidMethod();
		a.usesVoidMethod();

	}
}

