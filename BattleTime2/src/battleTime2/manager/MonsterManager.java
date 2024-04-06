package battleTime2.manager;

import java.util.ArrayList;

import battleTime2.unit.Unit;
import battleTime2.unit.monster.Monster;

public class MonsterManager {

	public static ArrayList<Unit> monster_list;
	
	private String path = "battleTime2.unit.monster.Monster";
	private String monNames[] = { "Bat", "Orc", "Troll" };
	
	private static MonsterManager instance = new MonsterManager();

	public static MonsterManager getInstance() {
		return instance;
	}
	
	public MonsterManager() {
		monster_list = new ArrayList<>();
	}
	
	public void monster_rand_set(int size) {
		for (int i = 0; i < size; i++) {
			int num = GameManager.rand.nextInt(monNames.length);
			try {
				Class<?> clazz = Class.forName(path + monNames[num]);
				Object obj = clazz.getDeclaredConstructor().newInstance();

				Unit temp = (Unit) obj;
				int level = GameManager.rand.nextInt(GameManager.day) + 1;
				int hp = (GameManager.rand.nextInt(level * 100)) + 100;
				int power = GameManager.rand.nextInt(level * 50) + (level * 50 / 2);
				int defense = power / 4;
				
				temp.init(hp, power, defense, level);
				monster_list.add(temp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
