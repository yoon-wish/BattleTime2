package battleTime2.unit.player;

import battleTime2.manager.GameManager;
import battleTime2.unit.Unit;

public class PlayerArcher extends Player{

	public PlayerArcher(String name) {
		super(name, "궁수", 400, 60, 30);
	}

	@Override
	public void skill(Unit target) {
		// 파괴의 화살(방어력 무시 타겟에게 100퍼의 피해, 나머지에게 50퍼의 피해)
		System.out.println("🏹🔥🎯~༺༄ 파괴의 화살 ༄༻~🎯🔥🏹");
		target.setHp(target.getHp() - this.getPower());
		System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.\n", this.getName(), target.getName(), this.getPower());
		if(target.getHp() <= 0) {
			target.setHp(0);
			System.out.printf("[%s]를 처치했습니다.\n", target.getName());
			levelUp(target);
		}
		
		for(int i=0; i<GameManager.monsterList.size(); i++) {
			Unit monster = GameManager.monsterList.get(i);
			if(monster == target) {
				continue;
			}
			monster.setHp(monster.getHp() - this.getPower() / 2);
			System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.\n", this.getName(), monster.getName(), this.getPower() / 2);
			if(monster.getHp() <= 0) {
				monster.setHp(0);
				System.out.printf("[%s]를 처치했습니다.\n", monster.getName());
				levelUp(target);
			}
		}
		
	}
	
}
