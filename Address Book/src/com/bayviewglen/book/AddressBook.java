package com.bayviewglen.book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;



public class AddressBook {
	private String name;
	private ArrayList<Contact> list;
	
	public AddressBook(){
		list = new ArrayList<Contact>();
		name = "notnamed";
	}
	public AddressBook(String name){
		list = new ArrayList<Contact>();
		this.name.toLowerCase().replaceAll("\\s+","");//st.replaceAll("\\s+","")  removes all spaces or formatting, http://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
	}
	public AddressBook(File file) throws FileNotFoundException{
		name = file.getName().substring(0, file.getName().indexOf("."));
		Scanner input = new Scanner(file);
		list = new ArrayList<Contact>();
		while(input.hasNextLine()){
			Contact toAdd = new Contact(input.nextLine());
			addContact(toAdd);
		}
	}
	public int size(){
		return list.size();
	}
	public void addContact(Contact contact){
		list.add(contact);
	}
	
	public void addContacts(AddressBook contacts){
		for(int i = 0; i<contacts.list.size(); i++)
			addContact(contacts.list.get(i));
	}
	public String toString(){
		String s = "";
		for(int i = 0; i<list.size(); i++){
			s+=list.get(i).toString()+"\n";
		}
		return s;
	}
	public AddressBook searchContacts(String s){
		AddressBook containedSearch = new AddressBook();
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getFname().toLowerCase().startsWith(s.toLowerCase())){
				containedSearch.addContact(list.get(i));
			}
			else if(list.get(i).getLname().toLowerCase().startsWith(s.toLowerCase())){
				containedSearch.addContact(list.get(i));
			}
			else if(list.get(i).getPhone().startsWith(s)){
				containedSearch.addContact(list.get(i));
			}
		}
		return containedSearch;
	}
	public void deleteContacts(AddressBook contacts){
		for(int i = 0; i<contacts.list.size(); i++)
			deleteContact(contacts.list.get(i));
	}

	private void deleteContact(Contact contact) {
		list.remove(contact);
	}
	public void searchAndDisplay(String s){
		System.out.print(searchContacts(s).toString());
	}
	public void searchAndDelete(String s){
		deleteContacts(searchContacts(s));
	}
	public void printWrite(AddressBook book) throws FileNotFoundException, UnsupportedEncodingException{
		String fileName = book.name.toLowerCase().replaceAll("\\s+","");
		PrintWriter printWriter = new PrintWriter(fileName+".txt","UTF-8");
		printWriter.print(book.toString());
		printWriter.close();
	}
}

