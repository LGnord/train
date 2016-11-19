package train.model;

import java.util.HashSet;
import java.util.Set;

import train.map.Usa;

public class Mission {

	public static final Set<Mission> ALL = new HashSet<>();
	final City from;
	final City to;
	final int points;

	public Mission(City from, City to, int points) {
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

	public City getTo() {
		return to;
	}

	public City getFrom() {
		return from;
	}

	@Override
	public String toString() {
		return from + "->" + to + "(" + points + "p)";
	}

}
