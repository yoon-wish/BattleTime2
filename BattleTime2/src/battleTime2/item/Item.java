package battleTime2.item;

public class Item {
	
	// 종류
	public static final int POTION = 1;
	public static final int WEAPON = 2;
	public static final int ARMOR = 3;
	
	// 등급
	public static final int BRONZE = 1;
	public static final int SILVER = 2;
	public static final int GOLD = 3;
	
	private int type;
	private int subType;
	
	private String name;
	private int price;
	private String info;
	private double ability;
	private boolean isWear;
	
	public Item(int type, int subType) {
		this.type = type;
		this.subType = subType;
	}
	
	public int getType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

	public double getAbility() {
		return this.ability;
	}
	
	public void setAbility(double ability) {
		this.ability = ability;
	}
	
	public boolean getWear() {
		return this.isWear;
	}
	
	public void setWear() {
		this.isWear = !isWear;
	}

}
