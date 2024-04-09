package battleTime2.stage;

import battleTime2.manager.GameManager;
import battleTime2.unit.player.Player;

public class StageHideout extends Stage {

	private final int WAREHOUSE = 1;
	private final int WALLET = 2;
	private final int SLEEP = 3;
	private final int VILLAGE = 4;

	private final int EQUIP = 1;
	private final int UNEQUIP = 2;
	private final int SORT = 3;

	@Override
	public boolean update() {
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 창고");
		System.out.println("    ❷ 지갑");
		System.out.println("    ❸ 취침");
		System.out.println("    ❹ 마을");
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
		int sel = GameManager.inputNumber();

		while (!((sel == WAREHOUSE) || (sel == SLEEP) || (sel == WALLET) || (sel == VILLAGE))) {
			System.out.print("👉 ");
			sel = GameManager.inputNumber();
		}

		if (sel == WAREHOUSE) {
			boolean haveItems = GameManager.inventoryManager.readInventory();

			System.out.println("┌──────────────┐");
			System.out.println("    ❶ 장착");
			System.out.println("    ❷ 해제");
			System.out.println("    ❸ 정렬");
			System.out.println("└──────────────┘");

			System.out.print("👉 ");
			int menu = GameManager.inputNumber();

			while (!((menu == EQUIP) || (menu == UNEQUIP) || (menu == SORT))) {
				System.out.print("👉 ");
				menu = GameManager.inputNumber();
			}

			// 장착
			if (menu == EQUIP) {
				if (haveItems) {
					System.out.println("장착할 아이템 번호");
					System.out.print("👉 ");
					int index = GameManager.inputNumber() - 1;
					if (index < 0 || index >= GameManager.inventoryManager.getSize()) {
						System.out.println("존재하지 않는 아이템입니다.");
						return false;
					}

					if (!GameManager.inventoryManager.equip(index))
						return false;

					System.out.println("장착완료");
				} else {
					System.out.println("장착할 아이템이 없다.");
				}
			}

			// 해제
			else if (menu == UNEQUIP) {
				if (!GameManager.inventoryManager.unequip()) {
					return false;
				}

				System.out.println("해제완료");
			} 
			
			//  정렬
			else if(menu == SORT) {
				GameManager.inventoryManager.sortItems();
			}

		} else if (sel == WALLET) {
			System.out.println("┌────────────────────────────┐");
			System.out.println("   보유 코인: " + GameManager.guildManager.readCoin() + " coin");
			System.out.println("└────────────────────────────┘");
		} else if (sel == SLEEP) {
			System.out.println("잠에 들건가요? (y/n)");
			System.out.print("👉 ");
			if(GameManager.sc.next().equals("y")) {
				int size = GameManager.guildManager.guildSize();
				for(int i=0; i<size; i++) {
					Player player = GameManager.guildManager.readPlayer(i);
					player.setHp(player.getMaxHp());
					player.setSp();
				}
				StageBattle.allDead = false;
				System.out.println("모든 길드원들이 체력과 스킬포인트를 회복했다!!!");
			}
		} else if (sel == VILLAGE) {
			GameManager.nextStage = "VILLAGE";
		}

		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
