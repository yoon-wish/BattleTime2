package battleTime2.unit.player;

import battleTime2.unit.Unit;

public class Player extends Unit implements SkillAble{
	
	// 게임 승리 시, 10% 확률로 +1
	public static int maxSp = 1;
	private int sp;
	
	public Player(String name, String job, int maxHp, int power, int defense) {
		super(name, job, maxHp, power, defense);
		this.sp =  maxSp;
	}

	@Override
	public void skill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skill(Unit unit) {
		// TODO Auto-generated method stub
		
	}
	
	public int getMaxSp() {
		return this.maxSp;
	}
	
	public int getSp() {
		return this.sp;
	}
	
	public void setSp() {
		this.sp = maxSp;
	}
	
	public void setSubSp() {
		this.sp --;
	}
	
	public void setAddSp() {
		this.sp ++;
	}
	
	@Override
	public String toString() {
		String info = "";
		
		return String.format("[%s] %s/♥%d/🗡️%d/🛡️%d", getName(), getJob(), getHp(), getPower(), getDefense());
	}

}
