package com.voicemail.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.voicemail.Class.Message;

class MessageTest {
	private Message messageEmpty;
	private Message messageNotEmpty;

	@BeforeEach
	public void setUp() throws Exception {
		messageEmpty = new Message("");
		messageNotEmpty = new Message("Test for messageText");
	}

	@Test
	public void testGetTextWithEmptyMessage() {
		assertEquals("", messageEmpty.getText());
	}
	
	@Test
	public void testGetTextWithNotEmptyMessage() {
		assertEquals("Test for messageText", messageNotEmpty.getText());
	}

}
