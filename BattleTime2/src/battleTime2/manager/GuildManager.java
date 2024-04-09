package battleTime2.manager;

import java.util.ArrayList;

import battleTime2.guild.Guild;
import battleTime2.unit.player.Player;
import battleTime2.unit.player.PlayerArcher;
import battleTime2.unit.player.PlayerHealer;
import battleTime2.unit.player.PlayerWarrior;
import battleTime2.unit.player.PlayerWizard;

public class GuildManager {
	private final int SIZE = 3;
	private final int MAX = 10;

	private final int WARRIOR = 1;
	private final int WIZARD = 2;
	private final int ARCHER = 3;
	private final int HEALER = 4;

	private Guild guild = new Guild();
	private ArrayList<Player> player_list;
	
	public static ArrayList<Player> partyList;

	private static GuildManager instance = new GuildManager();

	public static GuildManager getInstance() {
		return instance;
	}

	public GuildManager() {
		player_list = new ArrayList<>();
		partyList = new ArrayList<>();
	}

	public void start() {
		try {
			Thread.sleep(700);
			System.out.println("╔══════════════ ⚔️ 𝓑𝓪𝓽𝓽𝓵𝓮 𝓣𝓲𝓶𝓮 ⚔️ ══════════════╗");
			Thread.sleep(700);
			System.out.println("          배틀타임에 온 것을 환영하네!            ");
			Thread.sleep(700);
			System.out.println("      오늘은 최고의 길드원 3명을 모집해야 해.       ");
			Thread.sleep(700);
			System.out.println("  함께라면 모든 도전을 이겨낼 힘을 가질 것이야.    ");
			Thread.sleep(700);
			System.out.println("                지금 선택하게.                  ");
			Thread.sleep(700);
			System.out.println("  우리 팀의 승리를 이끌어 낼 최고의 동료들을!!   ");
			Thread.sleep(700);
			System.out.println("          함께 우리의 모험을 시작하자!            ");
			Thread.sleep(700);
			System.out.println("╚══════════════════════════════════════════════╝");
			Thread.sleep(700);
		} catch (Exception e) {
		}

		System.out.println("┌────────────────────────────────────────┐");
		System.out.println("  지금부터 3명의 길드원을 모집합니다");
		System.out.println("└────────────────────────────────────────┘");

		for (int i = 0; i < SIZE; i++) {
			playerInfo();
			System.out.printf("%d번째 길드원 👉 ", i + 1);
			int sel = GameManager.inputNumber();
			while (sel != ARCHER && sel != WARRIOR && sel != WIZARD && sel != HEALER) {
				System.out.printf("%d번째 길드원 👉 ", i + 1);
				sel = GameManager.inputNumber();
			}
			addPlayer(sel);
		}

		System.out.println("┌─────────────────────────┐");
		System.out.println("   🛡️길드원 모집 완료🛡️");
		System.out.println("└─────────────────────────┘");
	}

	private void playerInfo() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("    ❶ 전사 (♥600/🗡️80/🛡️50)");
		System.out.println("    ❷ 마법사 (♥400/🗡️50/🛡️40)");
		System.out.println("    ❸ 궁수 (♥400/🗡️60/🛡️30)");
		System.out.println("    ❹ 힐러 (♥200/🗡️30/🛡️20)");
		System.out.println("└──────────────────────────────┘");
	}

	private void addPlayer(int job) {
		System.out.print("닉네임(2~4글자) 👉 ");
		String name = GameManager.inputString();
		while (name.length() < 2 || name.length() > 4 || duplName(name)) {
			System.out.print("닉네임(2~4글자) 👉 ");
			name = GameManager.inputString();
		}

		if (job == WARRIOR)
			player_list.add(new PlayerWarrior(name));
		else if (job == WIZARD)
			player_list.add(new PlayerWizard(name));
		else if (job == ARCHER)
			player_list.add(new PlayerArcher(name));
		else if (job == HEALER)
			player_list.add(new PlayerHealer(name));
	}

	// 로드용
	public void addPlayer(Player player) {
		player_list.add(player);
	}
	
	public boolean duplName(String name) {
		for (int i = 0; i < player_list.size(); i++) {
			String temp = player_list.get(i).getName();
			if (temp.equals(name)) {
				System.out.println("이미 존재하는 아이디 입니다.");
				return true;
			}
		}
		return false;
	}

	public boolean createPlayer() {
		if(player_list.size() == MAX) {
			System.out.println("가입 정원이 마감되었습니다.");
			return false;
		}
		System.out.println("┌───────────────────────┐");
		System.out.println("  반갑네 용사여 ......");
		System.out.println("└───────────────────────┘");

		playerInfo();
		System.out.printf("👉 ");
		int sel = GameManager.inputNumber();
		while (sel != ARCHER && sel != WARRIOR && sel != WIZARD && sel != HEALER) {
			System.out.printf("👉 ");
			sel = GameManager.inputNumber();
		}
		addPlayer(sel);
		
		return true;
	}

	public void readAllPalyer() {
		System.out.println("┌─────────────────────────────────────┐");
		for (int i = 0; i < player_list.size(); i++) {
			Player player = player_list.get(i);
			System.out.printf("   %d) ", i+1);
			System.out.println(player);
		}
		System.out.println("└─────────────────────────────────────┘");
	}
	
	public Player readPlayer(int index) {
		return player_list.get(index);
	}
	
	public Player findPlayerWithItems() {
		// 아이템 장착 중인 플레이어 전부 출력
		ArrayList<Player> temp = new ArrayList<>();
		System.out.println("┌─────────────────────────────────────┐");
		int number = 1;
		for(int i=0; i<player_list.size(); i++) {
			Player player = player_list.get(i);
			if(player.getWeapon() != null || player.getArmor() != null) {
				temp.add(player);
				System.out.printf("   %d) ", number ++);
				System.out.println(player);
				
				System.out.print("      ");
				if(player.getWeapon() != null) {
					System.out.printf("%s ", player.getWeapon().getName());
				}
				
				if(player.getArmor() != null) {
					System.out.printf("%s ", player.getArmor().getName());
				}
				
				System.out.println();
			}
		}
		System.out.println("└─────────────────────────────────────┘");
		
		if(temp.size() == 0) {
			return null;
		}
		
		// 플레이어 선택
		System.out.print("👉 ");
		int index = GameManager.inputNumber() - 1;
		while(index < 0 || index >= temp.size()) {
			System.out.print("👉 ");
			index = GameManager.inputNumber() - 1;
		}
		
		// player_list에서 플레이어 찾기
		int idx = -1;
		for(int i=0; i<player_list.size(); i++) {
			if(temp.get(index).getName().equals(player_list.get(i).getName()))
				idx = i;
		}
		
		return player_list.get(idx);
	}
	
	public boolean addParty(int index) {
		Player player = player_list.get(index);
		
		if(player.getParty()) {
			System.out.println("이미 파티 참여 중입니다.");
			return false;
		}
		
		if(partyList.size() == SIZE) {
			System.out.println("정원이 마감되었습니다.");
			return false;
		}
		
		partyList.add(player);
		player.setParty();
		System.out.println("파티에 추가되었습니다.");
		return true;
	}
	
	public void addParty(Player player) {
		partyList.add(player);
		player.setParty();
	}
	
	public void subParty(int index) {
		String temp = partyList.get(index).getName();
		int idx = findIndexByName(temp);
		
		player_list.get(idx).setParty(); // 파티 참여 여부 해제
		partyList.remove(index);		 // 파티 제외
		
		System.out.printf("[%s]길드원이 파티에서 나왔습니다.\n", temp);
	}
	
	public int findIndexByName(String name) {
		int index = -1;
		for(int i=0; i<player_list.size(); i++) {
			String temp = player_list.get(i).getName();
			if(temp.equals(name))
				index = i;
		}
			
		return index;
	}
	
	public void readParty() {
		System.out.println("┌──────────────────────────────────┐");
		for (int i = 0; i < partyList.size(); i++) {
			Player player = partyList.get(i);
			System.out.printf("   %d) ", i+1);
			System.out.println(player);
		}
		System.out.println("└──────────────────────────────────┘");
	}
	

	public int guildSize() {
		return player_list.size();
	}
	
	public int partySize() {
		return partyList.size();
	}

	public void deletePlayer(int index) {
		if(player_list.get(index).getParty()) {
			System.out.println("파티에 참여 중인 길드원은 추방할 수 없습니다.");
			return;
		}
		player_list.remove(index);
		System.out.println("바이바이 !");
	}

	// 코인관리
	public int readCoin() {
		return guild.getCoin();
	}

	public void AddCoin(int coin) {
		guild.setInCoin(coin);
	}

	public void SubCoin(int coin) {
		guild.setOutCoin(coin);
	}
	
	public void setCoin() {
		guild.setCoin();
	}

}
