/*
 * Classname: Coordinate
 *
 * Date: 05.11.17
 * 
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */


package org.wahlzeit.services.mailing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.services.EmailAddress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailServiceTest {

	EmailService emailService = null;
	EmailAddress validAddress = null;
	EmailAddress validAddress2 = null;
	
	
	
	@Before
	public void setup() throws Exception {
		emailService = EmailServiceManager.getDefaultService();
		validAddress = EmailAddress.getFromString("test@test.de");
		validAddress2 = EmailAddress.getFromString("test2@test.de");
	}

	@Test
	public void testSendInvalidAddressEmail() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "lol", "hi"));
			assertFalse(emailService.sendEmailIgnoreException(null, validAddress, null, "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, null, "hi", "       "));
			assertFalse(emailService.sendEmailIgnoreException(null, null, "hi", "       "));
			assertFalse(emailService.sendEmailIgnoreException(null, null, null, "body"));

		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendValidEmail() {
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress, "hi", "test"));
			assertTrue(emailService.sendEmailIgnoreException(validAddress, validAddress2, "hi", "test"));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}
	
	
	@Test
	public void testSendInvalidBodyEmail() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, validAddress, "subject", ""));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, validAddress, "subject", null));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}
	
	@Test
	public void testSendInvalidSubjectEmail() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddress, validAddress, "", "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddress, validAddress, null, "body"));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}
}