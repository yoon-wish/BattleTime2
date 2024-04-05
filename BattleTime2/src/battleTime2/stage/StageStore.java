package battleTime2.stage;

import battleTime2.item.Armor;
import battleTime2.item.Item;
import battleTime2.item.Potion;
import battleTime2.item.Weapon;
import battleTime2.manager.GameManager;

public class StageStore extends Stage {

	private final int BUY = 1;
	private final int SELL = 2;
	private final int VILLAGE = 3;

	private final int INFO = 2;

	private int hpPotion;
	private int spPotion;

	public StageStore() {
		this.hpPotion = GameManager.rand.nextInt(3);
		this.spPotion = GameManager.rand.nextInt(3);
	}

	@Override
	public boolean update() {
		printEntry();
		if (printMenu())
			return true;

		return false;
	}

	@Override
	public void init() {

	}

	private boolean printMenu() {
		int sel = 0;

		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 구매");
		System.out.println("    ❷ 판매");
		System.out.println("    ❸ 마을");
		System.out.println("└──────────────┘");

		System.out.print("👉 ");

		sel = GameManager.sc.nextInt();
		while (!((sel == BUY) || (sel == SELL) || (sel == VILLAGE))) {
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();
		}

		// 구매
		if (sel == BUY) {
			System.out.println("┌──────────────┐");
			System.out.println("    ❶ 포션");
			System.out.println("    ❷ 무기");
			System.out.println("    ❸ 갑옷");
			System.out.println("└──────────────┘");
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();

			while (!((sel == Item.POTION) || (sel == Item.WEAPON) || (sel == Item.ARMOR))) {
				System.out.print("👉 ");
				sel = GameManager.sc.nextInt();
			}

			// 포션
			if (sel == Item.POTION) {
				System.out.println("┌──────────────┐");
				System.out.println("    ❶ HP");
				System.out.println("    ❷ SP");
				System.out.println("└──────────────┘");

				System.out.print("👉 ");
				sel = GameManager.sc.nextInt();

				while (!((sel == Potion.HP) || (sel == Potion.SP))) {
					System.out.print("👉 ");
					sel = GameManager.sc.nextInt();
				}

				if (sel == Potion.HP) {
					if (!printPotion("HP", this.hpPotion)) {
						Back();
						return false;
					}
				} else if (sel == Potion.SP) {
					if (!printPotion("SP", this.spPotion)) {
						Back();
						return false;
					}
				}

			} 
			
			// 무기 or 갑옷
			else {
				int type = sel == Item.WEAPON ? Item.WEAPON : Item.ARMOR;
				System.out.println("┌──────────────┐");
				System.out.println("    ❶ 초급");
				System.out.println("    ❷ 중급");
				System.out.println("    ❸ 고급");
				System.out.println("└──────────────┘");
				System.out.print("👉 ");
				sel = GameManager.sc.nextInt();

				while (!((sel == Item.BRONZE) || (sel == Item.SILVER) || (sel == Item.GOLD))) {
					System.out.print("👉 ");
					sel = GameManager.sc.nextInt();
				}

				if (!printItem(type, sel)) {
					Back();
					return false;
				}
			}
		}
		// 판매
		else if (sel == SELL) {
			if (!sellItem())
				return false;

		} 
		// 마을
		else if (sel == VILLAGE) {
			Back();
		}

		return false;
	}

	private void printEntry() {
		System.out.println("┌──────┐");
		System.out.println("  👤💭 ");
		System.out.println("└──────┘");
		System.out.println("인자한 미소의 주인이 길드원들을 맞이한다!");

		try {
			System.out.println("┌───────────────────────────┐");
			Thread.sleep(500);
			System.out.println("   허허허.. 반갑네 모두들");
			Thread.sleep(500);
			System.out.println("   오늘은 무엇을 하겠는가?");
			Thread.sleep(500);
			System.out.println("└───────────────────────────┘");
			Thread.sleep(500);

		} catch (Exception e) {
		}
	}

	private boolean printItem(int type, int subType) {
		Item item = null;
		if (type == Item.WEAPON)
			item = new Weapon(subType);
		else
			item = new Armor(subType);

		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 구매");
		System.out.println("    ❷ 정보");
		System.out.println("└──────────────┘");

		System.out.print("👉 ");
		int sel = GameManager.sc.nextInt();

		while (!((sel == BUY) || (sel == INFO))) {
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();
		}

		if (sel == BUY) {
			if (!buyItem(item))
				return false;
		} else if (sel == INFO) {
			System.out.println(item.getInfo());

			System.out.println("구매할까? (y/n)");
			System.out.print("👉 ");
			if (GameManager.sc.next().equals("y")) {
				if (!buyItem(item))
					return false;
			} else {
				printNotTrade("buy");
				return false;
			}
		}
		return true;
	}

	private boolean buyItem(Item item) {
		int price = item.getPrice();

		System.out.println("┌──────────────────────┐");
		System.out.println("  몇 개 구매 하겠는가?");
		System.out.printf("  개 당 %d원일세\n", price);
		System.out.println("└──────────────────────┘");

		System.out.print("👉 ");
		int number = GameManager.sc.nextInt();

		if (number <= 0) {
			printNotTrade("buy");
			return false;
		}

		if (number * price > GameManager.guildManager.readCoin()) {
			try {
				System.out.println("┌───────────────────────────────┐");
				Thread.sleep(500);
				System.out.println("  허허.... 돈이 부족한 것 같네");
				Thread.sleep(500);
				System.out.println("  다음에 다시오게나");
				Thread.sleep(500);
				System.out.println("└───────────────────────────────┘");
				Thread.sleep(500);
			} catch (Exception e) {
			}
			System.out.println("쫓겨나듯이 상점을 빠져나왔다");
			GameManager.nextStage = "VILLAGE";
			return false;
		}

		GameManager.inventoryManager.createItem(item);
		GameManager.guildManager.SubCoin(number * price);

		String name = item.getName();
		System.out.printf("%s를 %d개 구매했다!\n", name, number);

		System.out.println("┌─────────────────────────────┐");
		System.out.println("  고맙네! 더 둘러보겠는가?");
		System.out.println("└─────────────────────────────┘");
		printMenu();

		return true;
	}

	private boolean printPotion(String name, int potion) {
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 구매");
		System.out.println("    ❷ 정보");
		System.out.println("└──────────────┘");

		System.out.print("👉 ");
		int sel = GameManager.sc.nextInt();

		while (!((sel == BUY) || (sel == INFO))) {
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();
		}

		if (sel == BUY) {
			if (!buyPotion(name, potion)) {
				return false;
			}

		} else if (sel == INFO) {
			int subType = potion == hpPotion ? Potion.HP : Potion.SP;
			Potion item = new Potion(subType);
			System.out.println(item.getInfo());

			System.out.println("구매할까? (y/n)");
			System.out.print("👉 ");
			if (GameManager.sc.next().equals("y")) {
				if (!buyPotion(name, potion)) {
					return false;
				}
			} else {
				printNotTrade("buy");
				return false;
			}
		}
		return true;
	}

	private boolean buyPotion(String name, int potion) {
		int price = name == "HP" ? Potion.HP_PRICE : Potion.SP_PRICE;

		try {
			System.out.println("┌──────────────────────────────────────┐");
			if (potion == 0) {
				Thread.sleep(500);
				System.out.println("   아쉽게도 오늘은 들어온 물건이 없어");
				Thread.sleep(500);
				System.out.println("└──────────────────────────────────────┘");
				Back();
				return false;
			} else {
				System.out.printf("   마침 오늘 %s포션이 %d개 들어왔지\n", name, potion);
				Thread.sleep(500);
				System.out.println("   몇 개 구매하겠는가?");
				Thread.sleep(500);
				System.out.printf("   개 당 %d원일세\n", price);
				Thread.sleep(500);
				System.out.println("└──────────────────────────────────────┘");
			}
		} catch (Exception e) {
		}

		System.out.print("👉 ");
		int number = GameManager.sc.nextInt();

		while (number < 0 || number > potion) {
			System.out.println("┌─────────────────────────────┐");
			System.out.println("  아냐아냐 다시 말해보게");
			System.out.printf("  %d개 구매할 수 있네\n", potion);
			System.out.println("└─────────────────────────────┘");
			System.out.print("👉 ");
			number = GameManager.sc.nextInt();
		}

		if (number * price > GameManager.guildManager.readCoin()) {
			try {
				System.out.println("┌───────────────────────────────┐");
				Thread.sleep(500);
				System.out.println("  허허.... 돈이 부족한 것 같네");
				Thread.sleep(500);
				System.out.println("  다음에 다시오게나");
				Thread.sleep(500);
				System.out.println("└───────────────────────────────┘");
				Thread.sleep(500);
			} catch (Exception e) {
			}
			System.out.println("쫓겨나듯이 상점을 빠져나왔다");
			GameManager.nextStage = "VILLAGE";
			return false;
		}

		int subType = potion == hpPotion ? Potion.HP : Potion.SP;
		Item item = new Item(Item.POTION, subType);
		GameManager.inventoryManager.createItem(item);

		potion -= number;
		GameManager.guildManager.SubCoin(number * price);

		System.out.println("┌─────────────────────────────┐");
		System.out.println("  고맙네! 더 둘러보겠는가?");
		System.out.println("└─────────────────────────────┘");
		printMenu();

		return true;
	}

	private boolean sellItem() {
		// 판매
		// 인벤토리 보여주고
		// 선택해서 판매하기 (반값)
		if (GameManager.inventoryManager.getSize() == 0) {
			System.out.println("┌──────────────────────────────────┐");
			System.out.println("   어? 판매할 물건이 없어보이는군.");
			System.out.println("└──────────────────────────────────┘");
			printMenu();
		}
		GameManager.inventoryManager.readInventory();
		System.out.println("┌─────────────────────────┐");
		System.out.println("   무엇을 판매하겠는가?");
		System.out.println("└─────────────────────────┘");

		System.out.print("👉 ");
		int index = GameManager.sc.nextInt() - 1;
		int size = GameManager.inventoryManager.getSize();
		if (index < 0 || index >= size) {
			printNotTrade("sell");
			return false;
		}

		int price = GameManager.inventoryManager.getPrice(index);
		GameManager.inventoryManager.removeItem(index);

		System.out.println("┌─────────────────────────┐");
		System.out.printf("   %d코인 주겠네\n", price);
		System.out.println("└─────────────────────────┘");

		GameManager.guildManager.AddCoin(price);
		System.out.println("보유 코인: " + GameManager.guildManager.readCoin());

		System.out.println("┌─────────────────────────────┐");
		System.out.println("  더 둘러보겠는가?");
		System.out.println("└─────────────────────────────┘");
		printMenu();

		return true;
	}

	private void Back() {
		System.out.println("마을로 돌아가야겠다.....");
		System.out.println("┌─────────────────────────────┐");
		System.out.println("  조심히 가게!");
		System.out.println("└─────────────────────────────┘");
		GameManager.nextStage = "VILLAGE";
	}

	private void printNotTrade(String input) {
		String kind = input == "buy" ? "구매" : "판매";
		try {
			System.out.println("┌─────────────────────────────┐");
			Thread.sleep(500);
			System.out.printf("  어? %s하지 않는건가\n", kind);
			Thread.sleep(500);
			System.out.println("  알겠네");
			Thread.sleep(500);
			System.out.println("  조심히 돌아가게");
			Thread.sleep(500);
			System.out.println("└─────────────────────────────┘");
			Back();
		} catch (Exception e) {
		}
	}

}
