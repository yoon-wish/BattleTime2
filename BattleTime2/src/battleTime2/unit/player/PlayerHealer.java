package battleTime2.unit.player;

import battleTime2.manager.GuildManager;

public class PlayerHealer extends Player{

	public PlayerHealer(String name) {
		super(name, "힐러", 200, 30, 20);
	}
	
	@Override
	public void skill() {
		// 안정의 선율(팀원 전체 치유 [플레이어 maxHp의 30% 치유])
		System.out.println("🌿💫✨~༺༄ 안정의 선율 ༄༻~✨💫🌿");
		for(int i=0; i<GuildManager.partyList.size(); i++) {
			Player player = GuildManager.partyList.get(i);
			double heal = player.getMaxHp() * 0.3;
			if(player.getHp() == 0) {
				return;
			}
			
			if(player.getHp() + heal > player.getMaxHp()) {
				heal = player.getMaxHp() - player.getHp();
			}
			
			player.setHp(player.getHp() + (int) heal);
			System.out.printf("❤️ [%s]가 %d의 HP를 회복합니다\n", player.getName(), (int) heal);
		}
		
	}

}
