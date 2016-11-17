package train.model;

import java.util.HashSet;
import java.util.Set;

public class City {

	private final String name;
	private final String id;
	private final Set<Road> roads;

	public City(String name) {
		super();
		this.name = name;
		this.roads = new HashSet<>();
		this.id = id(name);
	}

	private String id(String name) {
		String[] s = name.split(" ");
		if (s.length == 1) {
			return name.substring(0, 3).toUpperCase();
		}
		String string = "" + s[0].charAt(0) + s[1].charAt(0);
		if (s.length > 2) {
			string += s[2].charAt(0);
		} else {
			string += s[1].charAt(1);
		}
		return string.toUpperCase();
	}

	public String getName() {
		return name;
	}

	public String geId() {
		return id;
	}

	public void addRoad(Road road) {
		roads.add(road);
	}

	public Road getRoad(City city) {
		for (Road r : roads)
			if (city.equals(r.getDestination()) || city.equals(r.getSource())) {
				return r;
			}
		throw new IllegalArgumentException("No road from " + this + " to " + city);
	}

	public Set<Road> getRoads() {
		return roads;
	}

	@Override
	public String toString() {
		return name;
	}

}
