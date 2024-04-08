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
	
	public void monster_rand_set(int size) {
		monster_list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			int num = GameManager.rand.nextInt(monNames.length);
			try {
				Class<?> clazz = Class.forName(path + monNames[num]);
				Object obj = clazz.getDeclaredConstructor().newInstance();

				Unit temp = (Unit) obj;
				int level = GameManager.rand.nextInt(GameManager.day) + GameManager.day;
				temp.setLevel(level);
				int hp = (GameManager.rand.nextInt(level * 100)) + 100;
				int power = GameManager.rand.nextInt(level * 10) + (level * 10);
				int defense = power / 2;
				
				temp.init(hp, power, defense, level);
				monster_list.add(temp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
