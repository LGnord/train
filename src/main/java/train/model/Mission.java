package train.model;

import java.util.HashSet;
import java.util.Set;

import train.map.Usa;

public class Mission {

	public static final Set<Mission> ALL = new HashSet<>();
	final City from;
	final City to;
	final int points;

	public Mission(int id, City from, City to, int points) {
		this.from = from;
		this.to = to;
		this.points = points;
		ALL.add(this);
	}

	public static Mission parseMission(String mission, Usa usa) {
		if (mission.length() != 7) {
			throw new IllegalArgumentException("Uknown " + mission);
		}
		String[] citiesString = mission.split("-");
		if (citiesString.length != 2) {
			throw new IllegalArgumentException("Uknown " + mission);
		}
		City from = usa.getCity(citiesString[0]);
		City to = usa.getCity(citiesString[1]);
		for (Mission m : ALL) {
			if (m.from.equals(from) && m.to.equals(to)) {
				return m;
			}
		}
		throw new IllegalArgumentException("Uknown " + mission);
	}

	public static String lookLikeMission(String mission, Usa usa) {
		try {
			String[] citiesString = mission.split("-");
			City from = usa.getCity(citiesString[0]);
			City to = usa.getCity(citiesString[1]);
			for (Mission m : ALL) {
				if (from != null && from.equals(m.from) && isFirstLetterEquals(m.to, citiesString[1])) {
					return helpString(m);
				}
				if (to != null && to.equals(m.to) && isFirstLetterEquals(m.from, citiesString[0])) {
					return helpString(m);
				}
			}
			for (Mission m : ALL) {
				if (isFirstLetterEquals(m.from, citiesString[0]) && isFirstLetterEquals(m.to, citiesString[1])) {
					return helpString(m);
				}
			}
		} catch (Throwable t) {
			return "impossible d'aider";
		}
		return "impossible d'aider";
	}

	private static String helpString(Mission m) {
		return m.from.getId() + "-" + m.to.getId() + "    " + m;
	}

	private static boolean isFirstLetterEquals(City city, String name) {
		return city.getName().charAt(0) == name.charAt(0);
	}

	public City getTo() {
		return to;
	}

	public City getFrom() {
		return from;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return from + "->" + to + "(" + points + "p)";
	}

}
