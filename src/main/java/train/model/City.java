package train.model;

import java.util.HashSet;
import java.util.Set;

public class City {

	private final String name;
	private final Set<Road> roads;

	public City(String name) {
		super();
		this.name = name;
		this.roads = new HashSet<>();
	}

	public String getName() {
		return name;
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
