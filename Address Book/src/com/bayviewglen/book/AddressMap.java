package com.bayviewglen.book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

public class AddressMap {
		private String name;
		private HashMap<String, Contact> map;
		
		public AddressMap(){
			map = new HashMap<String, Contact>();
			name = "notnamed";
		}
		public AddressMap(String name){
			map = new HashMap<String, Contact>();
			this.name.toLowerCase().replaceAll("\\s+","");//st.replaceAll("\\s+","")  removes all spaces or formatting, http://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
		}
		public AddressMap(File file) throws FileNotFoundException{
			name = file.getName().substring(0, file.getName().indexOf("."));
			Scanner input = new Scanner(file);
			map = new HashMap<String, Contact>();
			while(input.hasNextLine()){
				Contact toAdd = new Contact(input.nextLine());
				addContact(toAdd);
			}
		}
		public int size(){
			return map.size();
		}
		public void addContact(Contact contact){
			map.put(contact.getLname()+contact.getFname(), contact);
		}
		
		public void addContacts(AddressMap contacts){
			for(String key: contacts.map.keySet())
				addContact(contacts.map.get(key));
		}
		public String toString(){
			String s = "";
			for(String key: map.keySet()){
				s+=map.get(key).toString()+"\n";
			}
			return s;
		}
		public AddressMap searchContacts(String s){
			AddressMap containedSearch = new AddressMap();
			for(String key: map.keySet()){
				if(map.get(key).getFname().toLowerCase().startsWith(s.toLowerCase())){
					containedSearch.addContact(map.get(key));
				}
				else if(map.get(key).getLname().toLowerCase().startsWith(s.toLowerCase())){
					containedSearch.addContact(map.get(key));
				}
				else if(map.get(key).getPhone().startsWith(s)){
					containedSearch.addContact(map.get(key));
				}
			}
			return containedSearch;
		}
		public void deleteContacts(AddressMap contacts){
			for(String key: contacts.map.keySet())
				deleteContact(contacts.map.get(key).getLname()+contacts.map.get(key).getFname());
		}

		private void deleteContact(String key) {
			map.remove(key);
		}
		public void searchAndDisplay(String s){
			System.out.print(searchContacts(s).toString());
		}
		public void searchAndDelete(String s){
			deleteContacts(searchContacts(s));
		}
		public void printWrite(AddressMap map) throws FileNotFoundException, UnsupportedEncodingException{
			String fileName = map.name.toLowerCase().replaceAll("\\s+","");
			PrintWriter printWriter = new PrintWriter(fileName+".txt","UTF-8");
			printWriter.print(map.toString());
			printWriter.close();
		}
}
