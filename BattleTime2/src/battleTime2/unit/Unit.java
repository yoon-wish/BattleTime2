package battleTime2.unit;

import battleTime2.item.Item;

public class Unit {
	
	private final int MY_HP = 1;
	private int[] hpBar;
	
	private String name;
	private String job;
	private int maxHp;
	private int hp;
	private int power;
	private int defense;
	private int level;
	private int exp;
	
	private Item weapon;
	private Item armor;
	
	private boolean isParty;
	
	// 플레이어
	public Unit(String name, String job, int maxHp, int power, int defense) {
		this.name = name;
		this.job = job;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
		this.defense = defense;
		this.level = 1;
		this.exp = 0;
		this.hpBar = new int[hp/50];
	}
	
	// 몬스터
	public Unit() {

	}
	
	// 몬스터
	public void init(int maxHp, int power, int defense, int level) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
		this.defense = defense;
		this.level = level;
		this.hpBar = new int[hp/50];
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getJop() {
		return this.job;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getMaxHp() {
		return maxHp;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public int getDefense() {
		return this.defense;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public boolean getParty() {
		return this.isParty;
	}
	
	public void setParty() {
		this.isParty = !isParty;
	}
	
	public Item getWeapon() {
		return this.weapon;
	}
	
	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}
	
	public void setWeapon() {
		this.weapon = null;
	}
	
	public Item getArmor() {
		return this.armor;
	}
	
	public void setArmor(Item armor) {
		this.armor = armor;
	}
	
	public void setArmor() {
		this.armor = null;
	}
	
	public void printData() {
		double temp = hp;
		temp = Math.floor(temp / 50);
		
		for(int i=0; i<hpBar.length; i++) {
			hpBar[i] = 0;
		}
		
		for(int i=0; i<temp; i++) {
			hpBar[i] = MY_HP;
		}
		
		if(job == null) {
			System.out.printf("[%3s] ", this.name);
		} else {
		System.out.printf("[%3s](%3s) ", this.name, this.job);
		}
		
		for(int i=0; i<hpBar.length; i++) {
			if(hpBar[i] == MY_HP)
				System.out.print("■");
			else 
				System.out.print("□");
		}
		
		
		System.out.printf("[♥%d/%d][🗡️%d/🛡️%d]\n", this.hp, this.maxHp, this.power, this.defense);
		
	}
	
	public void attack(Unit target) {
		target.hp -= power;
		System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.", this.name, target.name, power);
		if(target.hp <= 0) {
			target.hp = 0;
			System.out.printf("\n[%s]를 처치했습니다.\n", target.name);
		}
	}
	
}
