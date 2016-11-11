package train.map;

import java.util.ArrayList;
import java.util.List;

import train.model.City;
import train.model.Color;
import train.model.Path;
import train.model.Road;

public class Usa {

	List<City> cities;
	
	public  Usa() {
		cities = new ArrayList<>();
		City vancouver = new City("Vancouver");
		City seattle = new City("seattle");
		City calgay = new City("Calgary");
		City helena = new City("Helena");
		City portLand = new City("Port-land");
		City saltLakeCity = new City("Salt Lake City");
		City winnipeg = new City("Winnipeg");
		City denver = new City("Denver");
		City omaha = new City("omaha");
		
		vancouver.addPath(new Path(new Road(calgay, Color.GRAY, 3)));
		vancouver.addPath(new Path(new Road(seattle, Color.GRAY, 1), new Road(seattle, Color.GRAY, 1)));
		
		seattle.addPath(new Path(new Road(portLand, Color.GRAY, 1), new Road(portLand, Color.GRAY, 1)));
		seattle.addPath(new Path(new Road(helena, Color.YELLOW, 6)));
		
		calgay.addPath(new Path(new Road(helena, Color.GRAY, 4)));
		calgay.addPath(new Path(new Road(winnipeg, Color.WHITE, 6)));
	
		helena.addPath(new Path(new Road(saltLakeCity, Color.PINK, 3)));
		helena.addPath(new Path(new Road(denver, Color.GREEN, 4)));
		helena.addPath(new Path(new Road(omaha, Color.RED, 6)));
		
		saltLakeCity.addPath(new Path(new Road(denver, Color.RED, 3), new Road(denver, Color.YELLOW, 3)));
		
		denver.addPath(new Path(new Road(omaha, Color.PINK, 4)));
		
	}
	
}
