package battleTime2.unit;

import battleTime2.item.Item;
import battleTime2.manager.GameManager;

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
	private int maxExp;
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
		this.hpBar = new int[hp / 50];
		this.maxExp = 50;
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
		this.hpBar = new int[hp / 50];
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
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
	
	public void setLevel(int level) {
		this.level = level;
	}

	public void setMaxExp() {
		this.exp += 20;
	}

	public int getExp() {
		return this.exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public boolean getParty() {
		return this.isParty;
	}

	public void setParty() {
		this.isParty = !isParty;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setDefense(int defense) {
		this.defense = defense;
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

		for (int i = 0; i < hpBar.length; i++) {
			hpBar[i] = 0;
		}

		for (int i = 0; i < temp; i++) {
			hpBar[i] = MY_HP;
		}

		if (job == null) {
			System.out.printf("[Lv%d][%3s] ", this.level, this.name);
		} else {
			System.out.printf("[Lv%d][%3s](%3s) ", this.level, this.name, this.job);
		}

		for (int i = 0; i < hpBar.length; i++) {
			if (hpBar[i] == MY_HP)
				System.out.print("■");
			else
				System.out.print("□");
		}

		if(job == null) 
			System.out.printf("[♥%d/%d][🗡️%d/🛡️%d]\n", this.hp, this.maxHp, this.power, this.defense);			
		else 
			System.out.printf("[♥%d/%d][🗡️%d/🛡️%d] (Exp%d/%d)\n", this.hp, this.maxHp, this.power, this.defense, this.exp, this.maxExp);

	}

	public void attack(Unit target) {
		int rDefense = GameManager.rand.nextInt(target.defense / 2) + target.defense / 2;
		int attack = power - rDefense;
		if (attack <= 0) {
			System.out.printf("[%s](이)가 [%s]에게 MISS !!!", this.name, target.name);
		} else {
			target.hp -= attack;
			System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.", this.name, target.name, attack);
		}

		if (target.hp <= 0) {
			target.hp = 0;
			System.out.printf("\n[%s]를 처치했습니다.\n", target.name);
			levelUp(target);
		}

	}
	
	public void levelUp(Unit target) {
		// 몬스터면 return
		if(job == null) {
			return;
		}

		exp += target.level * 20;
		while (exp / maxExp > 0) {
			if (exp >= maxExp) {
				System.out.printf("🌟✨ [%s] ＬＥＶＥＬ ＵＰ ✨🌟\n", this.name);
				level++;
				maxHp += 100;
				this.hpBar = new int[maxHp / 50];
				// 무기 장착여부 확인 후 공격력 상승
				if (weapon != null) {
					// 아이템 뺀 기본 공격력
					int tempPower = (int) (power / weapon.getAbility());
					// 기본 공격력 + 10
					tempPower += 10;
					// 아이템 장착 후 공격력
					power = (int) (tempPower * weapon.getAbility());
				} else
					power += 10;

				// 갑옷 장착여부 확인 후 방어력 상승
				if (armor != null) {
					int tempPower = (int) (defense / armor.getAbility());
					tempPower += 10;
					defense = (int) (tempPower * armor.getAbility());
				} else
					defense += 10;
			}
			hp = maxHp;
			exp -= maxExp;
			maxExp += 20;
		}
	}
	
	// 로드용 레벨업
	public void levelUp(int exp) {
		while (exp / maxExp > 0) {
			if (exp >= maxExp) {
				level++;
				maxHp += 100;
				this.hpBar = new int[maxHp / 50];
				// 무기 장착여부 확인 후 공격력 상승
				if (weapon != null) {
					// 아이템 뺀 기본 공격력
					int tempPower = (int) (power / weapon.getAbility());
					// 기본 공격력 + 10
					tempPower += 10;
					// 아이템 장착 후 공격력
					power = (int) (tempPower * weapon.getAbility());
				} else
					power += 10;

				// 갑옷 장착여부 확인 후 방어력 상승
				if (armor != null) {
					int tempPower = (int) (defense / armor.getAbility());
					tempPower += 10;
					defense = (int) (tempPower * armor.getAbility());
				} else
					defense += 10;
			}
			exp -= maxExp;
			maxExp += 20;
		}
		hp = maxHp;
		this.exp = exp;
	}

}
