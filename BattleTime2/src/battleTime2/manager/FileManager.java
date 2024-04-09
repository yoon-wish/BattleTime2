package battleTime2.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	private File file;
	private String fileName;

	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	private static FileManager instance = new FileManager();

	public static FileManager getInstance() {
		return instance;
	}
	
	public FileManager() {
		fileName = "battleTime2.txt";
		file = new File(fileName);
	}

	public void save(String info) {
		try {
			fw = new FileWriter(file);
			fw.write(info);
			fw.close();

			System.out.println("GAME SAVED");
		} catch (IOException e) {
			System.err.println("GAME SAVE FAILED");
		}
	}

	public String load() {
		String info = "";
		if (file.exists()) {
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);

				while (br.ready()) {
					info += br.readLine();
					info += "\n";
				}

				fr.close();
				br.close();

				System.out.println("GAME LOADED.");
			} catch (IOException e) {
				System.err.println("GAME LOAD FAILED");
			}
		}

		return info;
	}
}
