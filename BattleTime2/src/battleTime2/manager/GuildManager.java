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
	
	private final int WARRIOR = 1;
	private final int WIZARD = 2;
	private final int ARCHER = 3;
	private final int HEALER = 4;
	
	private Guild guild = new Guild();
	private ArrayList<Player> player_list;
	
	private static GuildManager instance = new GuildManager();

	public static GuildManager getInstance() {
		return instance;
	}
	
	public GuildManager() {
		player_list = new ArrayList<>();
		start();
	}
	
	private void start() {
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
		
		for(int i=0; i<SIZE; i++) {
			playerInfo();
			System.out.printf("%d번째 길드원 👉 ", i + 1);
			int sel = GameManager.sc.nextInt();
			while (sel != ARCHER && sel != WARRIOR && sel != WIZARD && sel != HEALER) {
				System.out.printf("%d번째 길드원 👉 ", i + 1);
				sel = GameManager.sc.nextInt();
			}
			
			System.out.print("닉네임(2~4글자) 👉 ");
			String name = GameManager.sc.next();
			while(name.length() < 2 || name.length() > 4 || duplName(name)) {
				System.out.print("닉네임(2~4글자) 👉 ");
				name = GameManager.sc.next();
			}
			
			if(sel == WARRIOR) 
				player_list.add(new PlayerWarrior(name));
			else if(sel == WIZARD) 
				player_list.add(new PlayerWizard(name));
			else if(sel == ARCHER) 
				player_list.add(new PlayerArcher(name));
			else if(sel == HEALER) 
				player_list.add(new PlayerHealer(name));
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
	
	public boolean duplName(String name) {
		for(int i=0; i<player_list.size(); i++) {
			String temp = player_list.get(i).getName();
			if(temp.equals(name)) {
				System.out.println("이미 존재하는 아이디 입니다.");
				return true;
			}
		}
		return false;
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
	
	
	
	
}
