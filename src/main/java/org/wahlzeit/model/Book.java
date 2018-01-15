/*
 * Classname: Book
 *
 * Date: 15.01.18
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

import org.wahlzeit.services.DataObject;

import java.util.*;



public class Book extends DataObject {
	
	private BookType bookType;
	
	private String title;
	private ArrayList<String> authors = new ArrayList<String>();
	private String publisher = null;
	
	public Book(BookType bookType) {
		super();
		this.bookType = bookType;
	}
	
	public Book(BookType bookType, String title) {
		super();
		this.bookType = bookType;
		this.title = title;
	}
	
	public BookType getType() {
		return this.bookType;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void addAuthor(String author) throws IllegalArgumentException {
		if(author == null || author.isEmpty()) {
			throw new IllegalArgumentException(author + " is an invalid author name");
		}
		this.authors.add(author);
	}
	
	public void removeAuthor(String author) {
		this.authors.remove(author);
	}
	
	public ArrayList<String> getAuthors() {
		return this.authors;
	}
	
	public void setPublisher(String publisher) throws IllegalArgumentException { 
		if(publisher == null || publisher.isEmpty()){
			throw new IllegalArgumentException(publisher + " is an invalid publisher name");
		}
		this.publisher = publisher;
	}
	
	public String getPublisher() {
		return this.publisher;
	}
}