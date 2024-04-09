package battleTime2.stage;

import battleTime2.item.Item;
import battleTime2.manager.GameManager;
import battleTime2.unit.player.Player;

public class StageLobby extends Stage {

	private final int GUILD = 1;
	private final int VILLAGE = 2;
	private final int BATTLE = 3;
	private final int SAVE = 4;
	private final int EXIT = 5;

	@Override
	public boolean update() {
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 길드");
		System.out.println("    ❷ 마을");
		System.out.println("    ❸ 전투");
		System.out.println("    ❹ 저장");
		System.out.println("    ❺ 종료");
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
		int sel = GameManager.inputNumber();

		while (!((sel == GUILD) || (sel == VILLAGE) || (sel == BATTLE) || (sel == SAVE) || (sel == EXIT))) {
			System.out.print("👉 ");
			sel = GameManager.inputNumber();
		}

		// 길드
		if (sel == GUILD) {
			GameManager.nextStage = "GUILD";
		}
		// 마을
		else if (sel == VILLAGE) {
			GameManager.nextStage = "VILLAGE";
		}
		// 전투
		else if (sel == BATTLE) {
			if (GameManager.battleNum == 0) {
				System.out.println("┌────────────────────────────────────┐");
				System.out.println("   오늘 이미 3회의 전투를 치뤘다");
				System.out.println("   다들 지친 상태이다");
				System.out.println("   휴식을 취하고 다음 날 다시 오자");
				System.out.println("└────────────────────────────────────┘");
				GameManager.nextStage = "LOBBY";
			} else {
				if (StageBattle.allDead) {
					System.out.println("┌────────────────────────────────────┐");
					System.out.println("   다들 지친 상태이다");
					System.out.println("   휴식을 취하고 다시 도전하자");
					System.out.println("└────────────────────────────────────┘");
					GameManager.nextStage = "LOBBY";
				} else
					GameManager.nextStage = "BATTLE";
			}
		}
		// 저장
		else if (sel == SAVE) {
			GameManager.fileManager.save(saveInfo());
		}

		// 종료
		else if (sel == EXIT) {
			System.out.println("종료하실건가요? (y/n)");
			System.out.print("👉 ");
			if (GameManager.inputString().equals("y")) {
				System.out.println("아 참, 저장은 하셨나요? (y/n)");
				System.out.print("👉 ");
				if (GameManager.inputString().equals("y")) {
					System.out.println("다음에 다시 만나요 ~");
					GameManager.nextStage = "";
				} else {
					System.out.println("저장부터 하자....");
					GameManager.nextStage = "LOBBY";
				}
			} else {
				System.out.println("조금 더 둘러보자...");
				GameManager.nextStage = "LOBBY";
			}
		}
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	private String saveInfo() {
		// Day / 소지금 / HP물약 / SP물약
		// 아이템1타입/아이템1서브타입/아이템2타입/아이템2서브타입 ...
		// 플레이어1 (닉네임/직업/경험치/무기/갑옷/파티참여여부?)
		// 플레이어2
		// ...

		String info = GameManager.day + "/" + GameManager.guildManager.readCoin() + "/"
				+ GameManager.inventoryManager.countHpPotion() + "/" + GameManager.inventoryManager.countSpPotion();

		info += "\n";

		int size = GameManager.inventoryManager.getSize();
		for(int i=0; i<size; i++) {
			Item item = GameManager.inventoryManager.readItem(i);
			if(item.getType() != Item.POTION) {
				info += item.getType() +"/" + item.getSubType();
				if(i<size-1) {
					info += "/";
				}
			}
		}
		
		info += "\n";
	
		size = GameManager.guildManager.guildSize();
		for (int i = 0; i < size; i++) {
			Player player = GameManager.guildManager.readPlayer(i);
			int level = player.getLevel() - 1;
			int exp = (level * 50) + ((level - 1) * 20) + player.getExp();
			if(player.getLevel() == 1) {
				exp = player.getExp();
			}

			// 무기, 갑옷 둘 다 없을 때
			if (player.getWeapon() == null && player.getArmor() == null) {
				info += player.getName() + "/" + player.getJop() + "/" + exp + "/" + "/" + "/" + player.getParty();
			} 
			// 갑옷만 없을 때
			else if (player.getArmor() == null) {
				info += player.getName() + "/" + player.getJop() + "/" + exp + "/" + player.getWeapon().getSubType()
						+ "/" + "/" + player.getParty();
			} 
			// 무기만 없을 때
			else if (player.getWeapon() == null) {
				info += player.getName() + "/" + player.getJop() + "/" + exp + "/" + "/"
						+ player.getArmor().getSubType() + "/" + player.getParty();
			} 
			// 둘 다 있을 때
			else {
				info += player.getName() + "/" + player.getJop() + "/" + exp + "/" + player.getWeapon().getSubType()
						+ "/" + player.getArmor().getSubType() + "/" + player.getParty();
			}
			
			if (i < size - 1) {
				info += "\n";
			}
		}

		return info;
	}

}
