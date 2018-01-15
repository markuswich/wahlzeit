/*
 * Classname: BookManager
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

import org.wahlzeit.services.ObjectManager;

import java.util.*;



public class BookManager extends ObjectManager {
	
	private static BookManager instance;
	
	private static final Map<String, BookType> bookTypeMap = new HashMap<String, BookType>();
	private static final Map<String, Book> bookMap = new HashMap<String, Book>();
	
	private BookManager() {
		
	}
	
	public static synchronized BookManager getInstance() {
	    if (instance == null) {
	        instance = new BookManager();
	    }
	
	    return instance;
	}
	
	public Book createBook(BookType type, String title) {
		if (type == null) {
			throw new IllegalArgumentException("The type can not be null");
		}
        if (existsBook(title)) {
        	throw new IllegalArgumentException("A book with that name already exists");
        }
        if (!(existsBookType(type.getName()))) {
            createBookType(type.getName());
        }
        Book res = new Book(type, title);
        bookMap.put(title, res);
        return res;
    }

    public BookType createBookType (String typeName) {
       if (existsBookType(typeName)) {
           throw new IllegalArgumentException("A type with that name already exists");
       }
       BookType res = new BookType(typeName);
       bookTypeMap.put(typeName, res);
       return res;
	}
    
    public BookType getBookType(String name) {
    	return bookTypeMap.get(name);
    }
	
	private boolean existsBookType(String name) {
		return bookTypeMap.containsKey(name);
	}
	
	private boolean existsBook(String name) {
		return bookMap.containsKey(name);
	}
	
	
	
}