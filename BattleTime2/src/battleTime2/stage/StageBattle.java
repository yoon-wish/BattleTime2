package battleTime2.stage;

import battleTime2.item.Potion;
import battleTime2.manager.GameManager;
import battleTime2.manager.GuildManager;
import battleTime2.manager.MonsterManager;
import battleTime2.unit.Unit;
import battleTime2.unit.player.Player;

public class StageBattle extends Stage {
	private final int SIZE = 3;

	private final int ATTACK = 1;
	private final int SKILL = 2;
	private final int INVENTORY = 3;

	private int playerDead = 0;
	private int monsterDead = 0;

	private int monster_index = 0;

	public static boolean allDead;
	public static boolean isLose;

	
	@Override
	public boolean update() {
		if (GameManager.guildManager.partySize() < SIZE) {
			System.out.println("3명의 파티원이 모여야합니다.");
			GameManager.nextStage = "LOBBY";
			return false;
		}

		// 몬스터 생성
		init();

		// 배틀 시작
		boolean run = true;
		int player_index = 0;
		boolean turn = true;

		while (run) {
			
			try {
				Thread.sleep(700);
			} catch (Exception e) {
			}

			// 플레이어 턴이면
			if (turn) {		
		
				// 플레이어 턴이면
				if (player_index < GuildManager.partyList.size()) {
					if(GuildManager.partyList.get(player_index).getHp() <= 0) {
						player_index += 1;
						continue;
					}
					print_character();
					player_attack(player_index);
					player_index += 1;
				}
				// 플레이어가 3명까지 다 돌면
				else {
					turn = !turn; 		// 몬스터 턴으로 돌려주기
					player_index = 0; 	// 플레이어는 처음으로 돌려주기
				}
			} 
			
			// 몬스터 턴이면 
			else if(!turn) {
				int size = GameManager.monsterList.size();
				if (monster_index < size) {
					System.out.println();
					monster_attack();
					monster_index += 1;
				} else {
					turn = !turn;
					monster_index = 0;
				}
			}
			
			check_live();
			
			if(monsterDead <= 0 || playerDead <= 0)
				break;
		}
		
		if(monsterDead <= 0) {
			monsterDead();
			GameManager.nextStage = "LOBBY";
		}

		if (playerDead <= 0) {
			playerDead();
			GameManager.nextStage = "HIDEOUT";
		}
		
		return false;
	}

	@Override
	public void init() {
		// 몬스터 생성
		if (!isLose) {
			GameManager.monsterManager.monster_rand_set(4);
			GameManager.monsterList = MonsterManager.monster_list;
			monsterDead = GameManager.monsterList.size();
			playerDead = GameManager.guildManager.partySize();
		}
		// 패배 후 재배틀인 경우
		else {
			for (int i = 0; i < GameManager.monsterList.size(); i++) {
				Unit monster = GameManager.monsterList.get(i);
				monster.setHp(monster.getMaxHp());
			}
		}
		
		isLose = false;
	}

	private void playerDead() {
		int rNum = GameManager.guildManager.readCoin() / 2;
		int coin = GameManager.rand.nextInt(rNum) + rNum;
		GameManager.guildManager.SubCoin(coin);

		allDead = true;
		GameManager.battleNum --;
		
		try {
			System.out.println("┌────────────────────────────┐");
			Thread.sleep(500);
			System.out.println("   배틀에서 패배했다");
			Thread.sleep(500);
			System.out.printf("   %d 코인을 빼앗겼다!\n", coin);
			Thread.sleep(500);
			System.out.println("   눈 앞이 캄캄해졌다...!");
			Thread.sleep(500);
			System.out.println("   일단 아지트로 가자...");
			System.out.println("└────────────────────────────┘");
		} catch (Exception e) {
		}
	}

	private void monsterDead() {

		getCoin();

		int ran = GameManager.rand.nextInt(10);
		if (ran == 0) {
			getSp();
		}

		GameManager.battleNum--;
	}
	
	private void getCoin() {
		int day = GameManager.day;
		int coin = GameManager.rand.nextInt(day * 100) + (200 + day * 100);
		System.out.println("┌────────────────────────────┐");
		System.out.println("   배틀에서 승리했다");
		System.out.printf("   보상으로 %d 코인 획득!\n", coin);
		System.out.println("└────────────────────────────┘");

		GameManager.guildManager.AddCoin(coin);
	}
	
	private void getSp() {
		Player.maxSp ++;
		System.out.println("┌───────────────────────────────────────┐");
		System.out.println("   스킬 포인트가 영구히 1 증가했다 !!!");
		System.out.println("   스킬 포인트: " + Player.maxSp);
		System.out.println("└───────────────────────────────────────┘");
	}
	
	private void check_live() {
		int num = 0;
		int size = GameManager.monsterList.size();
		for (int i = 0; i < GuildManager.partyList.size(); i++) {
			if (GuildManager.partyList.get(i).getHp() <= 0)
				num++;
		}
		playerDead =  GuildManager.partyList.size() - num;

		num = 0;
		for (int i = 0; i < size; i++) {
			if (GameManager.monsterList.get(i).getHp() <= 0)
				num++;
		}
		monsterDead = size - num;
	}
	
	private void print_character() {
		System.out.println("\n═══════ [PLAYER] ═══════");
		for (int i = 0; i < GameManager.guildManager.partySize(); i++) {
			GuildManager.partyList.get(i).printData();
		}

		System.out.println("\n═══════ [MONSTER] ═══════");
		for (int i = 0; i < GameManager.monsterList.size(); i++) {
			GameManager.monsterList.get(i).printData();
		}
	}
	
	public void player_attack(int index) {
		Player player = GuildManager.partyList.get(index);
		String job = player.getJop();
		// 쓰러진 플레이어 공격권 넘기기
		if(player.getHp() <= 0)
			return;
		
		printPlayer(player);
		
		int size = GameManager.monsterList.size();
		int sel = GameManager.inputNumber();
		int idx = 0;
		Unit monster = null;
		if(sel == ATTACK) {
			// 죽은 몬스터 예외처리용으로 while 사용
			while(true) {
				idx = GameManager.rand.nextInt(size);
				monster = GameManager.monsterList.get(idx);
				// 살아있는 몬스터면
				if(monster.getHp() > 0) {
					System.out.println();
					player.attack(monster);
					System.out.println();
					break;
				}
			}
		} else if(sel == SKILL) {
			System.out.println();
			if(player.getSp() > 0) {
				while(true) {
					idx = GameManager.rand.nextInt(size);
					monster = GameManager.monsterList.get(idx);
					if(monster.getHp() > 0) {
						System.out.println();
						if(job.equals("전사") || job.equals("궁수"))
							player.skill(monster);
						else 
							player.skill();
						System.out.println();
						System.out.println();
						player.setSubSp();
						break;
					}
				}
				System.out.println("\n남은 스킬 포인트: " + player.getSp());
			} else {
				System.out.println("┌──────────────────────────────┐");
				System.out.println("  더이상 스킬을 쓸 수 없다....");
				System.out.println("  바보같이 차례를 놓쳤다....");
				System.out.println("└──────────────────────────────┘");
			}
		} else if(sel == INVENTORY) {
			inventory();
		}
	}
	
	private void printPlayer(Player player) {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
		System.out.println();
		System.out.println("┌──────────────┐");
		System.out.println("   🫅🏻" + player.getName());
		System.out.println("└──────────────┘");
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 어택");
		System.out.println("    ❷ 스킬");
		System.out.println("    ❸ 가방");
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
	}
	
	private void inventory() {
		System.out.println();
		
		if(!GameManager.inventoryManager.readInventoryVerBattle())
			return;
		
		System.out.println("┌────────────────────┐");
		System.out.println("  무엇을 사용할까?");
		System.out.println("└────────────────────┘");

		System.out.print("👉 ");
		int number = GameManager.inputNumber() - 1;
		// 범위 예외처리
		if (number < 0 || number >= GameManager.inventoryManager.getSize()) {
			System.out.print("👉 ");
			number = GameManager.inputNumber() - 1;
		}
		
		// 포션이 아닐 경우
		if(!GameManager.inventoryManager.isPotion(number)) {
			System.out.println("포션만 사용가능합니다.");
			
			System.out.print("👉 ");
			number = GameManager.inputNumber() - 1;
		} 
		// 포션일 경우
		else {
			int kind = GameManager.inventoryManager.findKindOfPotion(number);

			System.out.println("┌────────────────┐");
			System.out.println("  누구에게 줄까?");
			System.out.println("└────────────────┘");

			int playerIdx = selectPlayer();
			while (playerIdx < 0 || playerIdx >= GuildManager.partyList.size()) {
				playerIdx = selectPlayer();
			}
			
			// HP 포션 사용
			if(kind == Potion.HP) {
				giveHpPotion(playerIdx, number);
			} 
			// SP 포션 사용
			else if(kind == Potion.SP) {
				giveSpPotion(playerIdx, number);
			}
			
		}
	}
	
	private void giveHpPotion(int pIndex, int iIndex) {
		Player healPlayer = GuildManager.partyList.get(pIndex);
		if(healPlayer.getHp() == 0) {
			System.out.println("쓰러진 플레이어에게는 사용할 수 없습니다.");
			return;
		}
		
		int maxHp = healPlayer.getMaxHp();
		int temp = healPlayer.getHp();
		int heal = temp + (maxHp / 2);
		healPlayer.setHp(heal);
		if(healPlayer.getHp() > maxHp )
			healPlayer.setHp(maxHp);
		temp = healPlayer.getHp() - temp;
		
		
		GameManager.inventoryManager.removeItem(iIndex);
		
		System.out.println("┌────────────────────────────────────────┐");
		System.out.printf("   [%s]의 체력이 %d만큼 회복되었습니다.\n", healPlayer.getName(), temp);
		System.out.println("└────────────────────────────────────────┘");
	}
	
	private void giveSpPotion(int pIndex, int iIndex) {
		Player player = GuildManager.partyList.get(pIndex);
		player.setAddSp();
		if(player.getSp() > player.getMaxSp()) {
			player.setSubSp();
			System.out.println("┌───────────────────────────────┐");
			System.out.printf("   스킬포인트가 최대 입니다 (%d).\n", player.getSp());
			System.out.println("└───────────────────────────────┘");
		} else {
			GameManager.inventoryManager.removeItem(iIndex);
			System.out.println("┌────────────────────────────────────────┐");
			System.out.printf("   [%s]의 SP가 1만큼 회복되었습니다. (%d).\n", player.getSp());
			System.out.println("└────────────────────────────────────────┘");
		}
	}
	
	public int selectPlayer() {
		System.out.println("┌─────────────────────────────────────────┐");
		for (int i = 0; i < GuildManager.partyList.size(); i++) {
			System.out.printf("    %d) %s\n", i + 1, GuildManager.partyList.get(i));
		}
		System.out.println("└─────────────────────────────────────────┘");
		System.out.print("👉 ");
		int index = (GameManager.sc.nextInt()) - 1;

		return index;
	}
	
	public void monster_attack() {
		int size = GameManager.monsterList.size();

		while (monster_index < size) {
			if (GameManager.monsterList.get(monster_index).getHp() == 0) {
				monster_index += 1;
				if (monster_index == size) {
					return;
				}
			} else
				break;
		}

		Unit monster = GameManager.monsterList.get(monster_index);
		if (monster.getHp() <= 0)
			return;
		while (true) {
			int idx = GameManager.rand.nextInt(GuildManager.partyList.size());
			if (GuildManager.partyList.get(idx).getHp() > 0) {
				monster.attack(GuildManager.partyList.get(idx));
				break;
			}
		}
	}

}
