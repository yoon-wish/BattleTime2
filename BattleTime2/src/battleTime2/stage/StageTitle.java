package battleTime2.stage;

import battleTime2.manager.GameManager;

public class StageTitle extends Stage{

	@Override
	public boolean update() {
		System.out.println("═══════ ＢＡＴＴＬＥ ＴＩＭＥ ² ═══════");
		System.out.println("[시작]을 입력하세요.");
		System.out.print("👉 ");
		String start = GameManager.inputString();

		while (!start.equals("시작")) {
			System.out.print("👉 ");
			start = GameManager.inputString();
		}

		System.out.printf("︵‿︵‿୨ << %d일차 >> ୧‿︵‿︵\n", GameManager.day);
		GameManager.nextStage = "LOBBY";

		return false;
	}

	@Override
	public void init() {
		
	}
}
