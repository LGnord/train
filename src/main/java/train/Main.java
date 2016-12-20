package train;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import train.longest_path.LongestPathHelper;
import train.longest_path.Path;
import train.longest_path.Paths;
import train.longest_path.Strategy;
import train.map.Usa;
import train.model.Mission;

public class Main {

	Usa usa = new Usa();

	public static void main(String[] args) {
		new Main().run();

	}

	void run() {
		LongestPathHelper longestPathStrategy = new LongestPathHelper();
		int minGoal = 40;
		int maxGoal = 45;
		int minPoints = 90;
		List<Path> paths = readFromFile(minGoal, maxGoal, minPoints, usa);
		if (paths.isEmpty()) {
			paths = longestPathStrategy.getOrderedByPoints(longestPathStrategy.run(minGoal, maxGoal, minPoints, usa));
			writeToFile(minGoal, maxGoal, minPoints, paths);
		}
		Paths longestPaths = new Paths(paths);

		try (Scanner keybordScanner = new Scanner(System.in);) {
			Mission[] missions = readMissionFromKeyboard(keybordScanner);

			List<Strategy> strategies = longestPaths.prepareStrategies(100, missions);
			int pos = 1;
			for (Strategy s : strategies) {
				System.out.println(pos + ". " + Arrays.toString(s.getMissions()) + " -> " + s.explainEsperance());
				pos++;
			}
			int strategyIndex = readIndexFromKeyboard(keybordScanner);
			System.out.println(strategies.get(strategyIndex).prettyPrint());
		}
	}

	private int readIndexFromKeyboard(Scanner keybordScanner) {
		try {
			int index = 1;

			System.out.println("Strategy :");
			String str = keybordScanner.next();
			index = Integer.parseInt(str);

			return index - 1;
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return readIndexFromKeyboard(keybordScanner);
		}
	}

	private void writeToFile(int minGoal, int maxGoal, int minPoints, List<Path> paths) {
		String filename = fileName(minGoal, maxGoal, minPoints);
		File file = new File(filename);
		try (FileWriter fw = new FileWriter(file);) {
			for (Path path : paths) {
				fw.write(path.toFileString());
				fw.write("\n");
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	private String fileName(int minGoal, int maxGoal, int minPoints) {
		return "C:\\Users\\UTILISATEUR\\workspace_train\\train\\src\\main\\resources\\usa\\" + minGoal + "_" + maxGoal
				+ "_" + minPoints + ".train";
	}

	private List<Path> readFromFile(int minGoal, int maxGoal, int minPoints, Usa usa) {
		List<Path> paths = new ArrayList<Path>();

		String filename = fileName(minGoal, maxGoal, minPoints);
		String line;

		try (BufferedReader br = new BufferedReader(new FileReader(filename));) {

			while ((line = br.readLine()) != null) {
				paths.add(Path.parsePath(line, usa));
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return paths;
	}

	private Mission[] readMissionFromKeyboard(Scanner keybordScanner) {
		Mission[] missions = new Mission[3];
		try {
			for (int i = 0; i < missions.length; i++) {
				while (missions[i] == null) {
					System.out.println("Mission :");
					String str = keybordScanner.next();
					System.out.println("Vous avez saisi : " + str);
					try {
						missions[i] = Mission.parseMission(str, usa);
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
					if (missions[i] != null) {
						System.out.println("Vous avez saisi : " + missions[i]);
					} else {
						System.out.println("Voulez-vous dire : " + Mission.lookLikeMission(str, usa));
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return missions;
	}

}
