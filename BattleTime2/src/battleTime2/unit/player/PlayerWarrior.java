package battleTime2.unit.player;

import battleTime2.unit.Unit;

public class PlayerWarrior extends Player{

	public PlayerWarrior(String name) {
		super(name, "전사", 600, 80, 50);
		
	}

	@Override
	public void skill(Unit target) {
		// 죽음의 일격(2배 공격)
		System.out.println("🗡️💥🔥~༺༄ 죽음의 일격 ༄༻~🔥💥🗡️");
		int attack = this.getPower() * 2 - target.getDefense();
		if(attack <= 0) {
			attack = 0;
		}
		
		target.setHp(target.getHp() - attack);
		
		if(attack == 0) {
			System.out.printf("[%s](이)가 [%s]에게 MISS !!!\n", this.getName(), target.getName(), attack);				
		}else 
			System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.\n", this.getName(), target.getName(), attack);
		
		if(target.getHp() <= 0) {
			target.setHp(0);
			System.out.printf("[%s]를 처치했습니다.\n", target.getName());
			levelUp(target);
		}
	}
	
}
