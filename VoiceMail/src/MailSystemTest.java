package com.voicemail.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.voicemail.Class.MailSystem;
import com.voicemail.Class.Mailbox;

class MailSystemTest {
	private MailSystem mailSystem;

	@BeforeEach
	public void setUp() throws Exception {
		mailSystem = new MailSystem(5);
	}

	@Test
	public void testFindMailboxWithExistentMailbox() {
		Mailbox actual = mailSystem.findMailbox("1");
		Mailbox expected = new Mailbox("1", "You have reached mailbox " + 1 + ". \nPlease leave a message now.");
		
		assertEquals(expected.getGreeting(), actual.getGreeting());
	}
	
	@Test
	public void testFindMailboxWithNonExistentMailboxGreaterThanMailboxesListSize() {
		assertNull(mailSystem.findMailbox("6"));
	}
	
	@Test
	public void testFindMailboxWithNonExistentMailboxLessThanOne() {
		assertNull(mailSystem.findMailbox("0"));
	}
}
