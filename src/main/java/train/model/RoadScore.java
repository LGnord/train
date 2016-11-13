package train.model;

public enum RoadScore {

	ONE(1, 1), TWO(2, 2), THREE(3, 4), FOUR(4, 7), FIVE(5, 10), SIX(6, 15);

	final int lenght;
	final int points;

	private RoadScore(int lenght, int points) {
		this.lenght = lenght;
		this.points = points;
	}

}
