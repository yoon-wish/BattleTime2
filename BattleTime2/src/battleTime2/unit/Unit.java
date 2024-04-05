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
	private int point;
	
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
		this.point = 0;
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
	
}
