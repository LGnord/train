package train.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import train.model.City;
import train.model.Road;

public class Path implements Comparable<Path> {

	private final City from;
	private final City to;
	private final List<Road> roads;
	private final List<Road> orderRoads;
	private final int points;

	public Path(City from, List<Road> roads) {
		super();
		this.roads = roads;
		this.from = from;
		this.orderRoads = orderRoads(roads);
		points = points(roads);
		this.to = checkRoads();
	}

	public City getDestination() {
		return to;
	}

	private City checkRoads() {
		City current = from;
		for (Road r : roads) {
			assert current.equals(r.getSource()) || current.equals(r.getDestination()) : this.toString();
			current = r.getOther(current);
		}
		return current;
	}

	private int points(List<Road> roads) {
		int points = 0;
		for (Road r : roads) {
			points += r.getPoints();
		}
		return points;
	}

	private List<Road> orderRoads(List<Road> roads) {
		List<Road> res = new ArrayList<Road>();
		res.addAll(roads);
		Collections.sort(res);
		return res;
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

	public int getPoints() {
		return points;
	}

	public int getLenght() {
		int lenght = 0;
		for (Road r : roads) {
			lenght += r.getLenght();
		}
		return lenght;
	}

	public List<Road> getRoads() {
		return roads;
	}

	@Override
	public int compareTo(Path o) {
		return o.points - points;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(from.toString());
		City current = from;
		for (Road r : roads) {
			buffer.append("->");
			current = r.getOther(current);
			buffer.append(current);
		}
		buffer.append("(" + points + "p)");
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderRoads == null) ? 0 : orderRoads.hashCode());
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
		if (orderRoads == null) {
			if (other.orderRoads != null)
				return false;
		} else if (!orderRoads.equals(other.orderRoads))
			return false;
		return true;
	}

}
