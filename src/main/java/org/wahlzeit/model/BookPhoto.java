/*
 * Classname: BookPhoto
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



public class BookPhoto extends Photo {
	
	private Book book;
	
	public BookPhoto() {
		super();
		book = null;
	}
	
	public BookPhoto(Book book) {
		super();
		this.book = book;
	}
	
	public BookPhoto(PhotoId myId) {
		super(myId);
	}
	
	public Book getBook() {
		return this.book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
}