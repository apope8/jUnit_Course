/**
 * 
 */
package com.apope.jUnit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author apope
 *
 */
public class GreetingImplTest {

	private Greeting greeting;

	@BeforeEach
	public void setup() {
		System.out.println("Setup");
		greeting = new GreetingImpl();
	}

	@Test
	public void greetShouldRunAValidOutput() {
		System.out.println("greetShouldRunAValidOutput");
		String result = greeting.greet("jUnit5");
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Hello Junit", result);
	}

	@Test
	public void greetShouldThrowAnExceptionFor_NameIsNull() {
		System.out.println("greetShouldThrowAnExceptionFor_NameIsNull");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			greeting.greet(null);
		});
	}

	@Test
	public void greetShouldThrowAnExceptionFor_NameIsBlank() {
		System.out.println("greetShouldThrowAnExceptionFor_NameIsBlank");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			greeting.greet(" ");
		});

	}

	@AfterEach
	public void teardown() {
		System.out.println("teardown");
		greeting = null;
	}

}
