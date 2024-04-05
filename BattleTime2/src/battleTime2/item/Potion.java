package battleTime2.item;

public class Potion extends Item {
	public static final int TYPE_SIZE = 2;
	public static final int HP = 1;
	public static final int SP = 2;

	public static final int HP_PRICE = 100;
	public static final int SP_PRICE = 200;

	public Potion(int subType) {
		super(Item.POTION, subType);

		String name = "HP포션";
		int price = 100;
		String info = hpPotion();

		switch (subType) {
		case HP:
			break;
		case SP:
			name = "SP포션";
			price = 200;
			info = spPotion();
		}
		
		super.setInfo(info);
		super.setName(name);
		super.setPrice(price);
	}

	private String hpPotion() {
		String info = "┌───────────────────────────────────┐\n";
		info += "             [HP 포션]\n";
		info += "   플레이어 최대 체력의 50% 회복\n";
		info += "└───────────────────────────────────┘\n";

		return info;
	}

	private String spPotion() {
		String info = "┌───────────────────────────────────┐\n";
		info += "             [SP 포션]\n";
		info += "   플레이어의 스킬포인트 1 회복\n";
		info += "└───────────────────────────────────┘\n";

		return info;
	}
}
