package train.map;

import java.util.ArrayList;
import java.util.List;

import train.model.City;
import train.model.Color;
import train.model.Road;
import train.model.RoadScore;

public class Usa {

	public City vancouver = new City("Vancouver");
	public City seattle = new City("Seattle");
	public City calgary = new City("Calgary");
	public City helena = new City("Helena");
	public City portLand = new City("Port-land");
	public City saltLakeCity = new City("Salt Lake City");
	public City winnipeg = new City("Winnipeg");
	public City denver = new City("Denver");
	public City omaha = new City("Omaha");
	public City duluth = new City("Duluth");
	public City sanFrancisco = new City("San Francisco");
	public City kansasCity = new City("Kansas City");
	public City lasVegas = new City("Las Vegas");
	public City saultStMarie = new City("Sault St Marie");
	public City montreal = new City("Montreal");
	public City boston = new City("Boston");
	public City toronto = new City("Toronto");
	public City pitsburg = new City("pitsburg");
	public City chicaco = new City("Chicaco");
	public City newYork = new City("New York");

	List<City> cities;

	public Usa() {
		cities = new ArrayList<>();

		new Road(vancouver, calgary, RoadScore.THREE, Color.GRAY);
		new Road(vancouver, seattle, RoadScore.ONE, Color.GRAY, Color.GRAY);

		new Road(seattle, portLand, RoadScore.ONE, Color.GRAY);
		new Road(seattle, helena, RoadScore.SIX, Color.YELLOW);

		new Road(calgary, helena, RoadScore.FOUR, Color.GRAY);
		new Road(calgary, winnipeg, RoadScore.SIX, Color.WHITE);

		new Road(helena, saltLakeCity, RoadScore.THREE, Color.PINK);
		new Road(helena, denver, RoadScore.FOUR, Color.GREEN);
		new Road(helena, omaha, RoadScore.FIVE, Color.RED);
		new Road(helena, duluth, RoadScore.SIX, Color.ORANGE);

		new Road(saltLakeCity, denver, RoadScore.THREE, Color.RED, Color.YELLOW);
		new Road(saltLakeCity, lasVegas, RoadScore.THREE, Color.ORANGE);

		new Road(denver, omaha, RoadScore.FOUR, Color.PINK);
		new Road(denver, kansasCity, RoadScore.FOUR, Color.BLACK, Color.ORANGE);

		new Road(portLand, saltLakeCity, RoadScore.SIX, Color.BLUE);
		new Road(portLand, sanFrancisco, RoadScore.FIVE, Color.GREEN, Color.PINK);

		new Road(winnipeg, duluth, RoadScore.FOUR, Color.BLACK);
		new Road(winnipeg, saultStMarie, RoadScore.SIX, Color.GRAY);

		new Road(duluth, omaha, RoadScore.TWO, Color.GRAY, Color.GRAY);
		new Road(duluth, saultStMarie, RoadScore.THREE, Color.GRAY);
		new Road(duluth, toronto, RoadScore.SIX, Color.PINK);

		new Road(omaha, kansasCity, RoadScore.ONE, Color.GRAY, Color.GRAY);
		new Road(omaha, chicaco, RoadScore.FOUR, Color.BLUE);

		new Road(lasVegas, sanFrancisco, RoadScore.TWO, Color.ORANGE);

		new Road(saultStMarie, montreal, RoadScore.FIVE, Color.BLACK);
		new Road(saultStMarie, toronto, RoadScore.TWO, Color.GRAY);

		new Road(montreal, toronto, RoadScore.THREE, Color.GRAY);
		new Road(montreal, newYork, RoadScore.THREE, Color.BLUE);
		new Road(montreal, boston, RoadScore.TWO, Color.GRAY, Color.GRAY);

		new Road(boston, newYork, RoadScore.TWO, Color.YELLOW, Color.RED);

		new Road(toronto, pitsburg, RoadScore.TWO, Color.GRAY);

		new Road(pitsburg, newYork, RoadScore.TWO, Color.WHITE, Color.GREEN);
		new Road(pitsburg, chicaco, RoadScore.THREE, Color.BLACK, Color.ORANGE);
	}

}
