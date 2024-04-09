package battleTime2.item;

public class Weapon extends Item{
	public static final int TYPE_SIZE = 3;
	
	public Weapon(int subType) {
		super(Item.WEAPON, subType);
		
		String name = "초급무기";
		int price = 300;
		double ability = 1.2;
		String info = bWeapon();
		
		switch(subType) {
		case Item.BRONZE :
			break;
		case Item.SILVER :
			name = "중급무기";
			price = 600;
			ability = 1.3;
			info = sWeapon();
			break;
		case Item.GOLD :
			name = "고급무기";
			price = 1000;
			ability = 1.5;
			info = gWeapon();
			break;
		}
		
		super.setInfo(info);
		super.setName(name);
		super.setPrice(price);
		super.setAbility(ability);
	}

	private String bWeapon() {
		String info = "┌─────────────────────────────────┐\n";
		info += "         [초급 무기]\n";
		info += "   플레이어 공격력 20% 상승\n";
		info += "└─────────────────────────────────┘";
		
		return info;
	}
	
	private String sWeapon() {
		String info = "┌─────────────────────────────────┐\n";
		info += "         [중급 무기]\n";
		info += "   플레이어 공격력 30% 상승\n";
		info += "└─────────────────────────────────┘";
		
		return info;
	}
	private String gWeapon() {
		String info = "┌─────────────────────────────────┐\n";
		info += "         [고급 무기]\n";
		info += "   플레이어 공격력 50% 상승\n";
		info += "└─────────────────────────────────┘";
		
		return info;
	}
	
}
