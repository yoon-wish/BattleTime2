package battleTime2.stage;

import battleTime2.manager.GameManager;

public class StageGuild extends Stage{

	private final int ADD = 1;
	private final int EXILE = 2;
	private final int PARTY = 3;
	private final int GUILD_INFO = 4;
	private final int PARTY_INFO = 5;
	
	private final int ADD_PARTY = 1;
	private final int SUB_PARTY = 2;

	@Override
	public boolean update() {
		System.out.println("┌───────────────────┐");
		System.out.println("    ❶ 길드원 추가");
		System.out.println("    ❷ 길드원 추방");
		System.out.println("    ❸ 파티 설정");
		System.out.println("    ❹ 길드원 정보");
		System.out.println("    ❺ 파티원 전보");
		System.out.println("└───────────────────┘");
		System.out.print("👉 ");
		int sel = GameManager.inputNumber();

		while (!((sel == ADD) || (sel == EXILE) || (sel == PARTY) || (sel == GUILD_INFO) || (sel == PARTY_INFO))) {
			System.out.print("👉 ");
			sel = GameManager.inputNumber();
		}
		
		// 길드원 추가
		if(sel == ADD) {
			if(!GameManager.guildManager.createPlayer()) {
				return false;
			}
			
			System.out.println("환영회가 성대하게 열렸다!");
		}
		
		// 길드원 추방
		else if(sel == EXILE) {
			GameManager.guildManager.readPalyer();
			System.out.print("👉 ");
			int index = GameManager.inputNumber() - 1;
			if(index < 0 || index >= GameManager.guildManager.guildSize()) {
				System.out.println("존재하지 않는 길드원입니다.");
				return false;
			}
			
			System.out.println("정말 추방하시겠습니까? (y/n)");
			System.out.print("👉 ");
			if(GameManager.inputString().equals("y")) {
				GameManager.guildManager.deletePlayer(index);
				System.out.println("바이바이 !");
			} else {
				System.out.println("추방하지 않았다.");
				return false;
			}
		}
		
		// 파티 설정
		else if(sel == PARTY) {
			System.out.println("┌──────────────┐");
			System.out.println("    ❶ 추가");
			System.out.println("    ❷ 제외");
			System.out.println("└──────────────┘");
			System.out.print("👉 ");
			int menu = GameManager.inputNumber();

			while (!((menu == ADD_PARTY) || (menu == SUB_PARTY))) {
				System.out.print("👉 ");
				menu = GameManager.inputNumber();
			}
			
			// 파티 추가
			if(menu == ADD_PARTY) {
				GameManager.guildManager.readPalyer();
				System.out.print("👉 ");
				int index = GameManager.inputNumber() - 1;
				if(index < 0 || index >= GameManager.guildManager.guildSize()) {
					System.out.println("존재하지 않는 길드원입니다.");
					return false;
				}
				
				if(!GameManager.guildManager.addParty(index)) {
					return false;
				} 
			}
			
			// 파티 제외
			if(menu == SUB_PARTY) {
				GameManager.guildManager.readParty();
				System.out.print("👉 ");
				int index = GameManager.inputNumber() - 1;
				if(index < 0 || index >= GameManager.guildManager.partySize()) {
					System.out.println("존재하지 않는 파티원입니다.");
					return false;
				}
				
				GameManager.guildManager.subParty(index);
			}
		}
		
		// 길드원 정보
		else if(sel == GUILD_INFO) {
			GameManager.guildManager.readPalyer();
			System.out.println("👉 닫기 (아무키나 누르세요)");
			String input = GameManager.inputString();
			
			return false;
		}
		
		// 파티원 정보
		else if(sel == PARTY_INFO) {
			GameManager.guildManager.readParty();
			System.out.println("👉 닫기 (아무키나 누르세요)");
			String input = GameManager.inputString();
			
			return false;
		}
		
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
