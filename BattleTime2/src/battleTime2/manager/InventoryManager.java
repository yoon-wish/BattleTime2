package battleTime2.manager;

import java.util.ArrayList;

import battleTime2.item.Item;
import battleTime2.unit.player.Player;

public class InventoryManager {
	private ArrayList<Item> itemList = new ArrayList<>();

	private static InventoryManager instance = new InventoryManager();

	public static InventoryManager getInstance() {
		return instance;
	}

	public void createItem(Item item) {
		itemList.add(item);
	}

	public boolean readInventory() {
		if (itemList.size() == 0) {
			System.out.println("┌──────┐");
			System.out.println("  텅 - ");
			System.out.println("└──────┘");
			return false;
		}

		System.out.println("┌─────────────────────────────────┐");
		for (int i = 0; i < itemList.size(); i++) {
			String name = itemList.get(i).getName();
			int price = itemList.get(i).getPrice();
			System.out.printf("   %d) %s (판매가: %d원)\n", i + 1, name, price / 2);
		}
		System.out.println("└─────────────────────────────────┘");

		return true;
	}

	public boolean readInventoryVerBattle() {
		if (itemList.size() == 0) {
			System.out.println("┌──────┐");
			System.out.println("  텅 - ");
			System.out.println("└──────┘");
			return false;
		}

		if (potionCount() == 0) {
			System.out.println("보유한 포션이 없습니다.");
			return false;
		} else {
			readInventory();
		}

		return true;
	}

	public boolean isPotion(int index) {
		Item item = itemList.get(index);
		if (item.getType() == Item.POTION) {
			return true;
		}

		return false;

	}

	public int potionCount() {
		int count = 0;
		for (int i = 0; i < itemList.size(); i++) {
			int type = itemList.get(i).getType();
			if (type == Item.POTION)
				count++;
		}

		return count;
	}
	
	public int findKindOfPotion(int index) {
		Item item = itemList.get(index);
		return item.getSubType();
	}

	public boolean equip(int index) {
		// 아이템 가져오기
		Item item = itemList.get(index);
		// 누구에게 장착할까?
		int idx = selectPlayer();
		Player player = GameManager.guildManager.readPlayer(index);

		// 아이템 타입 확인
		int type = item.getType();

		// 플레이어에 넣어주기
		if (type == Item.WEAPON) {
			player.setWeapon(item);
		} else if (type == Item.ARMOR) {
			player.setArmor(item);
		} else {
			System.out.println("포션은 장착할 수 없다.");
			return false;
		}

		// 창고에서 지워주기
		itemList.remove(index);

		return true;
	}

	public boolean unequip() {
		// 플레이어 선택
		Player player = GameManager.guildManager.findPlayerWithItems();

		if (player == null) {
			System.out.println("아이템을 장착한 플레이어가 없다.");
			return false;
		}

		System.out.println("무엇을 뺄까?");
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 무기");
		System.out.println("    ❷ 갑옷");
		System.out.println("└──────────────┘");

		System.out.print("👉 ");
		int menu = GameManager.inputNumber();

		while (!((menu == 1) || (menu == 2))) {
			System.out.print("👉 ");
			menu = GameManager.inputNumber();
		}

		if (menu == 1) {
			if (player.getWeapon() == null) {
				System.out.println("장착하고 있지 않습니다.");
				return false;
			} else {
				Item item = player.getWeapon();
				itemList.add(item);
				player.setWeapon();
			}
		} else if (menu == 2) {
			if (player.getArmor() == null) {
				System.out.println("장착하고 있지 않습니다.");
				return false;
			} else {
				Item item = player.getArmor();
				itemList.add(item);
				player.setArmor();
			}
		}

		return true;
	}

	private int selectPlayer() {
		GameManager.guildManager.readAllPalyer();
		System.out.println("누구에게 장착할까?");
		System.out.print("👉 ");
		int idx = GameManager.inputNumber() - 1;
		while (idx < 0 || idx >= GameManager.guildManager.guildSize()) {
			System.out.print("👉 ");
			idx = GameManager.inputNumber() - 1;
		}
		return idx;
	}

	public void sortItems() {
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			int idx = i;

			for (int j = i; j < itemList.size(); j++) {
				Item target = itemList.get(j);

				if (item.getName().compareTo(target.getName()) > 0) {
					item = target;
					idx = j;
				}
			}

			if (idx != i) {
				itemList.set(idx, itemList.get(i));
				itemList.set(i, item);
			}
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
