package train;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

		Mission[] missions = readMissionFromKeyboard();

		Strategy strategy = longestPaths.prepareStrategy(20, missions);
		System.out.println(strategy.prettyPrint());

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

	private Mission[] readMissionFromKeyboard() {
		Mission[] missions = new Mission[2];
		try (Scanner sc = new Scanner(System.in);) {
			for (int i = 0; i < 2; i++) {
				while (missions[i] == null) {

					System.out.println("Mission :");
					String str = sc.next();
					System.out.println("Vous avez saisi : " + str);
					try {
						missions[i] = Mission.parseMission(str, usa);
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
					System.out.println("Vous avez saisi : " + missions[i]);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return missions;
	}

}
