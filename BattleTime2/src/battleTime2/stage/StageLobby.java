package battleTime2.stage;

import battleTime2.manager.GameManager;

public class StageLobby extends Stage {

	private final int GUILD = 1;
	private final int VILLAGE = 2;
	private final int BATTLE = 3;
	private final int SAVE = 4;
	private final int EXIT = 5;
	private boolean isSave;

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
		int sel = GameManager.sc.nextInt();

		while (!((sel == GUILD) || (sel == VILLAGE) || (sel == BATTLE) || (sel == SAVE) || (sel == EXIT))) {
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();
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
		else if(sel == BATTLE) {
			
		}
		// 저장 
		else if(sel == SAVE) {
			
		}
		// 종료
		else if(sel == EXIT) {
			System.out.println("종료하실건가요? (y/n)");
			System.out.print("👉 ");
			if (GameManager.sc.next().equals("y")) {
				System.out.println("아 참, 저장은 하셨나요? (y/n)");
				System.out.print("👉 ");
				if (GameManager.sc.next().equals("y")) {
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

}
