package com.voicemail.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.voicemail.Class.Telephone;

class TelephoneTest {
	private Telephone telephone;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() throws Exception {
		telephone = new Telephone(new Scanner(System.in));

		System.setOut(new PrintStream(outContent));
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		System.setOut(System.out);
		System.setIn(System.in);
	}

	@Test
	public void testSpeak() {
		telephone.speak("Output Test");
		
		assertEquals("Output Test\n", outContent.toString());
	}
	
	/*@Test
	public void testRunWithInputH() {
	}*/
}
