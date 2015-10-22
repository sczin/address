
package com.bayviewglen.book;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class TesterMapAdd {

	@Test
	public void creation() {
		Contact empty = new Contact();
		Contact full = new Contact("Czinner", "Shon", "647-460-0061");
		
		String emptyFname = empty.getFname();
		String fullFname = full.getFname();
		
		assertTrue(emptyFname.equals(""));
		assertTrue(fullFname.equals("Shon"));
		
		AddressMap map = new AddressMap();
		
		map.addContact(empty);
		map.addContact(full);
		
		assertTrue(map.size()==2);
		try {
			map.printWrite(map);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void loadFile() throws FileNotFoundException{
			File file = new File("notnamed.txt");
			AddressMap loaded = new AddressMap(file);
			assertTrue(loaded.size()==2);
			assertTrue(loaded.searchContacts("Shon").size()==1);
			loaded.searchAndDelete("Shon");
			assertTrue(loaded.searchContacts("Shon").size()==0);
			try {
				loaded.printWrite(loaded);
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file = new File("notnamed.txt");
			loaded = new AddressMap(file);
			assertTrue(loaded.searchContacts("Shon").size()==0);
	}
	

}
