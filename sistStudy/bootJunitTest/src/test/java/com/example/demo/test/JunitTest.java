package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.controller.TestController;

class JunitTest {

	@Test
	void testList() {
		TestController t = new TestController();
		assertEquals("ym", t.list("ym", 0, "asd"));
	}

}
	