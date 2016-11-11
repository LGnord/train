package train.model;

public class Road {

	private final City from;
	private final City to;
	private final Color[] colors;
	private final int lenght;

	public Road(City from, City to, int lenght, Color... colors) {
		super();
		this.from = from;
		this.to = to;
		this.colors = colors;
		this.lenght = lenght;
		from.addRoad(this);
		to.addRoad(this);
	}

	public City getDestination() {
		return to;
	}

	public int getLenght() {
		return lenght;
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

}
