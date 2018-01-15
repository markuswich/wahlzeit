/*
 * Classname: BookPhotoTest
 *
 * Date: 12.11.17
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
package org.wahlzeit.model;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.ClassRule;
import org.junit.rules.RuleChain;

import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;




public class BookPhotoTest {
	
	@ClassRule
	public static TestRule rule = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider());
	
	private Book book;
	private BookPhoto photo;
	
	private BookPhoto photo2;

	
	@Before
	public void setUp() {
		book = new Book(null, "Test Title");
		photo = new BookPhoto(book);
	}

	@Test
	public void testConstructor() {
		assertNotNull(book);
		assertNotNull(photo);
	}
	
	@Test
	public void testAccessorsAndMutators() {
		assertEquals(photo.getBook(), book);
		
		photo2 = new BookPhoto();
		photo2.setBook(book);
		assertEquals(photo2.getBook(), book);
	}
}
