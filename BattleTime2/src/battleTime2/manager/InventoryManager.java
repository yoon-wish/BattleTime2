package battleTime2.manager;

import java.util.ArrayList;

import battleTime2.item.Armor;
import battleTime2.item.Item;
import battleTime2.item.Potion;
import battleTime2.item.Weapon;

public class InventoryManager {
	private ArrayList<Item> itemList = new ArrayList<>();

	private static InventoryManager instance = new InventoryManager();

	public static InventoryManager getInstance() {
		return instance;
	}
	
	public void createItem(Item item) {
		itemList.add(item);
	}
	
	public void readInventory() {
		for(int i=0; i<itemList.size(); i++) {
			String name = itemList.get(i).getName();
			int price = itemList.get(i).getPrice();
			System.out.printf("%d) %s (판매가: %d원)\n", i+1, name, price / 2);
		}
	}
	
	public int getPrice(int index) {
		return itemList.get(index).getPrice() / 2;
	}
	
	public void removeItem(int index) {
		itemList.remove(index);
	}
	
	public int getSize() {
		return itemList.size();
	}
}
