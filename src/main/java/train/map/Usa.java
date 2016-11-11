package train.map;

import java.util.ArrayList;
import java.util.List;

import train.model.City;
import train.model.Color;
import train.model.Road;

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

	List<City> cities;

	public Usa() {
		cities = new ArrayList<>();

		new Road(vancouver, calgary, 3, Color.GRAY);
		new Road(vancouver, seattle, 1, Color.GRAY, Color.GRAY);

		new Road(seattle, portLand, 1, Color.GRAY);
		new Road(seattle, helena, 6, Color.YELLOW);

		new Road(calgary, helena, 4, Color.GRAY);
		new Road(calgary, winnipeg, 6, Color.WHITE);

		new Road(helena, saltLakeCity, 3, Color.PINK);
		new Road(helena, denver, 4, Color.GREEN);
		new Road(helena, omaha, 5, Color.RED);
		new Road(helena, duluth, 6, Color.ORANGE);

		new Road(saltLakeCity, denver, 3, Color.RED, Color.YELLOW);

		new Road(denver, omaha, 4, Color.PINK);

		new Road(portLand, saltLakeCity, 6, Color.BLUE);

		new Road(winnipeg, duluth, 4, Color.BLACK);

		new Road(duluth, omaha, 2, Color.GRAY, Color.GRAY);
	}

}
