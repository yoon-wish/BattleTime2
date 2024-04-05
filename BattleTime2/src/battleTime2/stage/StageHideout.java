package battleTime2.stage;

import battleTime2.manager.GameManager;

public class StageHideout extends Stage{

	private final int WAREHOUSE = 1;
	private final int WALLET = 2;
	private final int SLEEP = 3;
	private final int VILLAGE = 4;
	
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
		
		if(sel == WAREHOUSE) {
			
		} else if(sel == WALLET) {
			System.out.println("┌────────────────────────────┐");
//			System.out.println("   보유 코인: " + GameManager.coin + " coin");
			System.out.println("└────────────────────────────┘");
		} else if(sel == SLEEP) {
			
		} else if(sel == VILLAGE) {
			GameManager.nextStage = "VILLAGE";
		}
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
