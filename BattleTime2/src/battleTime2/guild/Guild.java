package battleTime2.guild;

public class Guild {
	private int coin;

	// 코인
	public int getCoin() {
		return this.coin;
	}
	
	public void setCoin() {
		this.coin = 0;
	}
	
	public void setInCoin(int coin) {
		this.coin += coin;
	}	

	public void setOutCoin(int coin) {
		this.coin -= coin;
	}

}
