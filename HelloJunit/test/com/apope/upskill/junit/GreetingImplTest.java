package com.apope.upskill.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;


class GreetingImplTest {
	
	private Greeting greeting;

	@Before
	public void setup() {
		System.out.println("Setup");
		greeting = new GreetingImpl();
		
	}

	@Test
	void greetShouldReturnValidOutput() {
		System.out.println("greetShouldReturnValidOutput");
		String result = greeting.greet("Hello Junit5");
		assertNotNull(result);
		assertEquals("Hello Junit5", result);
	}
	
	@After
	public void teardown() {
		System.out.println("teardown");
		greeting = null;
	}


}
