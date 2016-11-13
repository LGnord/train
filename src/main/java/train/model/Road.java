package train.model;

public class Road implements Comparable<Road> {

	private final City from;
	private final City to;
	private final Color[] colors;
	private final RoadScore score;

	public Road(City from, City to, RoadScore score, Color... colors) {
		super();
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

}
