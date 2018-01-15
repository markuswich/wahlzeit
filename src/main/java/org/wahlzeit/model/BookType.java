/*
 * Classname: BookType
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



public class BookType extends DataObject {
	
	private String name;
	private BookType superType = null;
	private Set<BookType> subTypes = new HashSet<BookType>();
	
	public BookType(String name) {
		this.name = name;
	}
		
	
	
	public void addSubType(BookType bookType) {
		assert (bookType != null) : "tried to set null sub-type";
		bookType.setSuperType(this);
		subTypes.add(bookType);
	}
	
	public boolean isSubtype(BookType bookType) {
		assert (bookType != null) : "asked about null type";
		
		if(bookType == this) {
			return true;
		}
		
		for(BookType bookSubType : subTypes) {
			if(bookSubType.isSubtype(bookType)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasInstance(Book book) {
		assert (book != null) : "asked about null object";
		
		if (book.getType() == this) {
			return true;
		}
		
		for (BookType type : subTypes) {
			if (type.hasInstance(book)) {
				return true;
			}
		}
		return false;
	}
	
	public Book createInstance() {
		return new Book(this);
	}
	
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name can not be null");
		}
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public Set<BookType> getSubTypes() {
		return subTypes;
	}

 
	public void setSuperType(BookType superType) {
		this.superType = superType;
	}
	
	public BookType getSuperType() {
		return superType;
	}
	
	public Iterator<BookType> getSubTypeIterator() {
		return subTypes.iterator();
	}

}