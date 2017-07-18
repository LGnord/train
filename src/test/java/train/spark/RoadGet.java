package train.spark;

import java.util.Scanner;

import train.map.Usa;
import train.model.Road;

public class RoadGet {

	public static void main(String[] args) {
		String roads;
		try (Scanner keybordScanner = new Scanner(System.in);) {
			roads = new RoadGet().readRoadFromKeyboard(0, keybordScanner);
			roads += new RoadGet().readRoadFromKeyboard(10, keybordScanner);
			System.out.println(roads);
		}

	}

	private String readRoadFromKeyboard(int player, Scanner keybordScanner) {
		Usa usa = new Usa();
		int movMax = 10;
		StringBuffer buffer = new StringBuffer();
		try {
			for (int i = 1; i <= movMax; i++) {
				Road road = null;
				String str = null;
				while (road == null) {
					System.out.println((player == 0 ? "My " : "Other ") + "Road " + i);
					str = keybordScanner.next();
					System.out.println("Vous avez saisi : " + str);
					if (!"q".equals(str)) {
						try {
							road = Road.parseRoad(str, usa);
						} catch (IllegalArgumentException e) {
							System.err.println(e.getMessage());
						}
						if (road != null) {
							System.out.println("Vous avez saisi : " + road);
						} else {
							System.out.println("Voulez-vous dire : " + Road.lookLikeRoad(str, usa));
						}
					} else {
						return buffer.toString();
					}
				}
				buffer.append((player + i) + ":" + road.getId() + " ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
