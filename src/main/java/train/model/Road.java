package train.model;

import train.map.Usa;

public class Road implements Comparable<Road> {

	private final City from;
	private final City to;
	private final Color[] colors;
	private final RoadScore score;
	private final int id;

	public Road(int id, City from, City to, RoadScore score, Color... colors) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.colors = colors;
		this.score = score;
		from.addRoad(this);
		to.addRoad(this);
	}

	public City getDestination() {
		return to;
	}

	public int getLenght() {
		return score.lenght;
	}

	public int getPoints() {
		return score.points;
	}

	public City getOther(City from) {
		if (this.from.equals(from)) {
			return to;
		}
		return this.from;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return from + "->" + to;
	}

	public City getSource() {
		return from;
	}

	@Override
	public int compareTo(Road other) {
		return this.toString().compareTo(other.toString());
	}

	public boolean match(Color c) {
		for (Color myC : colors) {
			if (myC.equals(c)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDepth(int depth) {
		return colors.length == depth;
	}

	public boolean isLenght(int lenght) {
		return score.lenght == lenght;
	}

	public static Road parseRoad(String road, Usa usa) {
		if (road.length() != 7) {
			throw new IllegalArgumentException("Uknown " + road);
		}
		String[] citiesString = road.split("-");
		if (citiesString.length != 2) {
			throw new IllegalArgumentException("Uknown " + road);
		}
		City from = usa.getCity(citiesString[0]);
		City to = usa.getCity(citiesString[1]);
		for (Road r : usa.getRoads()) {
			if (r.from.equals(from) && r.to.equals(to)) {
				return r;
			}
			if (r.from.equals(to) && r.to.equals(from)) {
				return r;
			}
		}
		throw new IllegalArgumentException("Uknown " + road);
	}

	public static String lookLikeRoad(String road, Usa usa) {
		StringBuffer buffer = new StringBuffer();
		String[] citiesString = road.split("-");
		City from = usa.getCity(citiesString[0]);
		City to = usa.getCity(citiesString[1]);
		for (Road r : usa.getRoads()) {
			if (r.from.equals(from) || r.from.equals(to) || r.to.equals(from) || r.to.equals(to)) {
				buffer.append(r.prettyPrint() + "  ");
			}
		}
		return buffer.toString();

	}

	private String prettyPrint() {
		return from.getId() + "-" + to.getId();
	}

}
