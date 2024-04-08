package battleTime2.unit.player;

import battleTime2.manager.GameManager;
import battleTime2.unit.Unit;

public class PlayerWizard extends Player{

	public PlayerWizard(String name) {
		super(name, "마법사", 400, 50, 40);
	}

	@Override
	public void skill() {
		// 마법의 태풍(여러 마리 한 번에 공격) 
		System.out.println("🔮✨🌌~༺༄ 마법의 태풍 ༄༻~🌌✨🔮");
		for(int i=0; i<GameManager.monsterList.size(); i++) {
			Unit monster = GameManager.monsterList.get(i);
			monster.setHp(monster.getHp() - this.getPower() / 2);
			System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.\n", this.getName(), monster.getName(), this.getPower() / 2);
			if(monster.getHp() <= 0) {
				monster.setHp(0);
				System.out.printf("[%s]를 처치했습니다.\n", monster.getName());
				levelUp(monster);
			}
		}
		
	}
	
}
