package battleTime2.manager;

import battleTime2.guild.Guild;

public class GuildManager {
	
	private Guild guild = new Guild();
	
	private static GuildManager instance = new GuildManager();

	public static GuildManager getInstance() {
		return instance;
	}
	
	// 코인관리
	public int readCoin() {
		return guild.getCoin();
	}
	
	public void AddCoin(int coin) {
		guild.setInCoin(coin);
	}
	
	public void SubCoin(int coin) {
		guild.setOutCoin(coin);
	}
	
	
	
	
}
