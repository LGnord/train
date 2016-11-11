package train.strategy;

import java.util.ArrayList;
import java.util.List;

import train.model.City;
import train.model.Road;

public class Path {

	private final City from;
	private final City to;
	private final List<Road> roads;

	public Path(City from, List<Road> roads) {
		super();
		this.roads = roads;
		this.from = from;
		this.to = roads.get(roads.size() - 1).getDestination();
	}

	public Path(City... cities) {
		this(cities[0], roads(cities));
	}

	private static List<Road> roads(City[] cities) {
		List<Road> roads = new ArrayList<>();
		for (int i = 1; i < cities.length; i++) {
			roads.add(cities[i - 1].getRoad(cities[i]));
		}
		return roads;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(from.toString());
		City current = from;
		for (Road r : roads) {
			buffer.append("->");
			if (r.getSource().equals(current)) {
				current = r.getDestination();
			} else {
				current = r.getSource();
			}
			buffer.append(current);
		}
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((roads == null) ? 0 : roads.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Path other = (Path) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (roads == null) {
			if (other.roads != null)
				return false;
		} else if (!roads.equals(other.roads))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

}
