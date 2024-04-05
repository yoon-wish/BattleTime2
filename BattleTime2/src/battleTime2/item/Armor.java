package battleTime2.item;

public class Armor extends Item{
	public static final int TYPE_SIZE = 3;

	public Armor(int subType) {
		super(Item.ARMOR, subType);
		
		String name = "초급갑옷";
		int price = 200;
		double ability = 1.3;
		String info = bArmor();
		
		switch(subType) {
		case Item.BRONZE :
			break;
		case Item.SILVER :
			name = "중급갑옷";
			price = 500;
			ability = 1.5;
			info = sArmor();
			break;
		case Item.GOLD :
			name = "고급갑옷";
			price = 900;
			ability = 1.8;
			info = gArmor();
			break;
		}
		
		super.setInfo(info);
		super.setName(name);
		super.setPrice(price);
		super.setAbility(ability);
	}
	
	private String bArmor() {
		String info = "┌─────────────────────────────────┐";
		info += "         [초급 갑옷]";
		info += "   플레이어 방여력 30% 상승";
		info += "└─────────────────────────────────┘";
		
		return info;
	}
	private String sArmor() {
		String info = "┌─────────────────────────────────┐";
		info += "         [중급 갑옷]";
		info += "    플레이어 방여력 50% 상승";
		info += "└─────────────────────────────────┘";
		
		return info;
	}
	private String gArmor() {
		String info = "┌─────────────────────────────────┐";
		info += "         [고급 갑옷]";
		info += "    플레이어 방여력 80% 상승";
		info += "└─────────────────────────────────┘";
		
		return info;
	}

}
