package battleTime2.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import battleTime2.stage.Stage;
import battleTime2.stage.StageBattle;
import battleTime2.stage.StageGuild;
import battleTime2.stage.StageHideout;
import battleTime2.stage.StageLobby;
import battleTime2.stage.StageStore;
import battleTime2.stage.StageTitle;
import battleTime2.stage.StageVillage;
import battleTime2.unit.Unit;

public class GameManager {
	public static Scanner sc = new Scanner(System.in);
	public static Random rand = new Random();
	public static String nextStage = "";

	public static GuildManager guildManager = GuildManager.getInstance();
	public static InventoryManager inventoryManager = InventoryManager.getInstance();
	public static MonsterManager monsterManager = MonsterManager.getInstance();
	
	public static ArrayList<Unit> monsterList;
	public static ArrayList<Unit> pList;
	
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
}
