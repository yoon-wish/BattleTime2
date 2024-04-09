package battleTime2.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import battleTime2.item.Armor;
import battleTime2.item.Item;
import battleTime2.item.Potion;
import battleTime2.item.Weapon;
import battleTime2.stage.Stage;
import battleTime2.stage.StageBattle;
import battleTime2.stage.StageGuild;
import battleTime2.stage.StageHideout;
import battleTime2.stage.StageLobby;
import battleTime2.stage.StageStore;
import battleTime2.stage.StageTitle;
import battleTime2.stage.StageVillage;
import battleTime2.unit.Unit;
import battleTime2.unit.player.Player;
import battleTime2.unit.player.PlayerArcher;
import battleTime2.unit.player.PlayerHealer;
import battleTime2.unit.player.PlayerWarrior;
import battleTime2.unit.player.PlayerWizard;

public class GameManager {
	public static Scanner sc = new Scanner(System.in);
	public static Random rand = new Random();
	public static String nextStage = "";

	public static GuildManager guildManager = GuildManager.getInstance();
	public static InventoryManager inventoryManager = InventoryManager.getInstance();
	public static MonsterManager monsterManager = MonsterManager.getInstance();
	public static FileManager fileManager = FileManager.getInstance();

	public static ArrayList<Unit> monsterList;

	public static int day = 1; // 날짜
	public static int battleNum = 3; // 하루 배틀 횟수 제한

	private Map<String, Stage> stageList = new HashMap<String, Stage>();

	public GameManager() {
		stageList.put("TITLE", new StageTitle());
		stageList.put("BATTLE", new StageBattle());
		stageList.put("LOBBY", new StageLobby());
		stageList.put("VILLAGE", new StageVillage());
		stageList.put("STORE", new StageStore());
		stageList.put("HIDEOUT", new StageHideout());
		stageList.put("GUILD", new StageGuild());

		nextStage = "TITLE";

		setGame();
	}

	public boolean changeStage() {
		if (nextStage != "" && nextStage != "TITLE") {
			System.out.printf("»»═════ [%s] ═════««\n", nextStage);
		}

		Stage stage = stageList.get(nextStage);

		boolean run = true;
		while (true) {
			run = stage.update();
			if (run == false)
				break;
		}

		if (nextStage.equals(""))
			return false;
		else
			return true;
	}

	public static int inputNumber() {
		int number = -1;
		try {
			String input = sc.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
		}
		return number;
	}

	public static String inputString() {
		return sc.next();
	}

	private void setGame() {
		String info = fileManager.load();
		if (info == "") {
			guildManager.start();
			return;
		}

		String[] allInfo = info.split("\n");
		String[] gameInfo = allInfo[0].split("/");

		// 날짜 + 1
		day = Integer.parseInt(gameInfo[0]) + 1;
		// 소지금 넣어주기
		int coin = Integer.parseInt(gameInfo[1]);
		guildManager.AddCoin(coin);
		// hp 포션 넣어주기
		int hpPotion = Integer.parseInt(gameInfo[2]);
		for (int i = 0; i < hpPotion; i++) {
			Potion potion = new Potion(Potion.HP);
			inventoryManager.createItem(potion);
		}
		// sp 포션 넣어주기
		int spPotion = Integer.parseInt(gameInfo[3]);
		for (int i = 0; i < spPotion; i++) {
			Potion potion = new Potion(Potion.SP);
			inventoryManager.createItem(potion);
		}

		if (allInfo[1] != "") {
			String[] invenInfo = allInfo[1].split("/");
			for (int i = 0; i < invenInfo.length; i += 2) {
				int type = Integer.parseInt(invenInfo[i]);
				int subType = Integer.parseInt(invenInfo[i + 1]);

				Item item = null;
				if (type == Item.WEAPON) {
					item = new Weapon(subType);
				} else if (type == Item.ARMOR) {
					item = new Armor(subType);
				}

				inventoryManager.createItem(item);
			}
		}
		setPlayer(allInfo);
	}

	private void setPlayer(String[] info) {
		// 플레이어1 (닉네임/직업/경험치/무기/갑옷/파티참여여부?)
		for (int i = 2; i < info.length; i++) {
			String[] playerInfo = info[i].split("/");
			Player player = null;
			String job = playerInfo[1];

			// 직업별 플레이어 생성
			if (job.equals("전사")) {
				player = new PlayerWarrior(playerInfo[0]);
			} else if (job.equals("마법사")) {
				player = new PlayerWizard(playerInfo[0]);
			} else if (job.equals("궁수")) {
				player = new PlayerArcher(playerInfo[0]);
			} else if (job.equals("힐러")) {
				player = new PlayerHealer(playerInfo[0]);
			}

			// 레벨 설정
			int exp = Integer.parseInt(playerInfo[2]);
			player.levelUp(exp);

			// 무기 설정
			if (playerInfo[3] != "") {
				Weapon item = new Weapon(Integer.parseInt(playerInfo[3]));
				player.setWeapon(item);
				// 공격력 상승
				double power = player.getPower() * item.getAbility();
				player.setPower((int) power);
			}

			// 갑옷 설정
			if (playerInfo[4] != "") {
				Armor item = new Armor(Integer.parseInt(playerInfo[4]));
				player.setArmor(item);
				// 방어력 상승
				double defense = player.getDefense() * item.getAbility();
				player.setDefense((int) defense);
			}

			// 파티 추가
			if (playerInfo[5].equals("true")) {
				guildManager.addParty(player);
			}

			guildManager.addPlayer(player);
		}
	}
}
