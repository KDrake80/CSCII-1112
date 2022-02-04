/*
 * Author Kevin Drake
 * Date 2/3/22
 * This Class defines the Account Object
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.image.ImageView;

public class Account {
	private String accountName = " ";
	private String password = " ";
	private ArrayList<Item> items;
	private int itemCount;
	private Date dateCreated;
	
	public Account() {
		dateCreated = new Date();
		itemCount = 0;
		items = new ArrayList<Item>();
	}
	public Account(String accountName) {
		this.accountName = accountName;
		dateCreated = new Date();
		items = new ArrayList<Item>();
	}
	public Account(String accountName, String password) {
		this.accountName = accountName;
		this.password = password;
		dateCreated = new Date();
		items = new ArrayList<Item>();
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void addItem(Item item) {
		items.add(item);
		itemCount++;
	}
	public void addNewItem(String name, String description, double price) {
		Item item = new Item(name);
		item.setDescription(description);
		item.setPrice(price);
		items.add(item);
		itemCount++;
	}
	public int getItemCount() {
		return itemCount;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public int getItemIndex(String name) {
		int index = 0;
		for (Item i: items) {
			if (name.equalsIgnoreCase(i.getItemName())) {
				index = items.indexOf(i);
			}
		}
		return index;
	}
	public Item getItem(int index) {
		return items.get(index);
	}
	public String getItemName(int index) {
		return items.get(index).getItemName();
	}

	public String getItemDescription(int index) {
		return items.get(index).getDescription();
	}
	public double getItemPrice(int index) {
		return items.get(index).getPrice();
	}
	public String printItem(int index) {
		return items.get(index).toString();
	}
	public void printItemList() {
		for (int i = 0; i < items.size(); i++)
			System.out.println(printItem(i));
	}
	public ImageView getItemImage(int index) {
		return items.get(index).getImage();
	}
	public void loseItem(int index) {
		items.remove(index);
	}
	public void removeItem(Account one, int index) {
		one.addItem(getItem(index));
		items.remove(index);
		itemCount--;
	}

}
