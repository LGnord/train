package train.map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import train.model.City;
import train.model.Color;
import train.model.Mission;
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
	public City pitsburg = new City("Pitsburg");
	public City chicaco = new City("Chicaco");
	public City newYork = new City("New York");
	public City phoenix = new City("Phoenix");
	public City elPaso = new City("El Paso");
	public City santaFe = new City("Santa Fe");
	public City dallas = new City("Dallas");
	public City houston = new City("Houston");
	public City oklahomaCity = new City("Oklahoma City");
	public City losAngeles = new City("Los Angeles");
	public City littleRock = new City("Little Rock");
	public City newOrleans = new City("New Orleans");
	public City nashVille = new City("NashVille");
	public City atlanta = new City("Atlanta");
	public City miami = new City("Miami");
	public City charleston = new City("Charleston");
	public City raleign = new City("Raleign");
	public City washington = new City("Washington");
	public City saintLouis = new City("Saint Louis");

	private Set<City> cities;

	public Set<City> getCities() {
		return cities;
	}

	public Mission sea_nyo = new Mission(seattle, newYork, 22);
	public Mission lan_nyo = new Mission(losAngeles, newYork, 21);
	public Mission van_mon = new Mission(vancouver, montreal, 20);
	public Mission lan_mia = new Mission(losAngeles, miami, 20);
	public Mission por_nah = new Mission(portLand, nashVille, 17);
	public Mission san_atl = new Mission(sanFrancisco, atlanta, 17);
	public Mission lan_chi = new Mission(losAngeles, chicaco, 16);
	public Mission mon_nyo = new Mission(montreal, newYork, 13);
	public Mission van_san = new Mission(vancouver, santaFe, 13);
	public Mission cal_pho = new Mission(calgary, phoenix, 13);
	public Mission mon_nor = new Mission(montreal, newOrleans, 13);
	public Mission bos_mia = new Mission(boston, miami, 12);
	public Mission dal_nyo = new Mission(dallas, newYork, 11);
	public Mission den_pit = new Mission(denver, pitsburg, 11);
	public Mission por_phe = new Mission(portLand, phoenix, 11);
	public Mission win_lro = new Mission(winnipeg, littleRock, 11);
	public Mission dul_elp = new Mission(duluth, elPaso, 10);
	public Mission sea_lan = new Mission(seattle, losAngeles, 9);
	public Mission chi_san = new Mission(chicaco, santaFe, 9);
	public Mission mon_atl = new Mission(montreal, atlanta, 9);
	public Mission sal_okl = new Mission(saultStMarie, oklahomaCity, 9);
	public Mission hel_lan = new Mission(helena, losAngeles, 8);
	public Mission dul_hou = new Mission(duluth, houston, 8);
	public Mission chi_nor = new Mission(chicaco, newOrleans, 7);
	public Mission cal_sea = new Mission(calgary, seattle, 7);
	public Mission nyo_atl = new Mission(newYork, atlanta, 6);
	public Mission den_elp = new Mission(denver, elPaso, 4);

	private Set<Mission> missions;

	public Set<Mission> getMissions() {
		return missions;
	}

	public Set<Road> getRoads() {
		Set<Road> res = new HashSet<>();
		for (City c : cities) {
			res.addAll(c.getRoads());
		}
		return res;
	}

	public City getCity(String id) {
		for (City city : cities) {
			if (city.getId().equals(id.toUpperCase())) {
				return city;
			}
		}
		return null;
	}

	public Set<Road> getRoadsByColor(Color c) {
		Set<Road> res = new HashSet<>();
		for (Road r : getRoads()) {
			if (r.match(c)) {
				res.add(r);
			}
		}
		return res;
	}

	public Set<Road> getRoadsByDepth(int depth) {
		Set<Road> res = new HashSet<>();
		for (Road r : getRoads()) {
			if (r.isDepth(depth)) {
				res.add(r);
			}
		}
		return res;
	}

	public Set<Road> getRoadsByLenght(int lenght) {
		Set<Road> res = new HashSet<>();
		for (Road r : getRoads()) {
			if (r.isLenght(lenght)) {
				res.add(r);
			}
		}
		return res;
	}

	public Usa() {

		cities = new HashSet<>();
		cities.addAll(Arrays.asList(vancouver, seattle, calgary, //
				helena, portLand, saltLakeCity, //
				winnipeg, denver, omaha, //
				duluth, sanFrancisco, kansasCity, //
				lasVegas, saultStMarie, montreal, //
				boston, toronto, pitsburg, //
				chicaco, newYork, phoenix, //
				elPaso, santaFe, dallas, //
				houston, oklahomaCity, losAngeles, //
				littleRock, newOrleans, nashVille, //
				atlanta, miami, charleston, //
				raleign, washington, saintLouis));

		missions = Mission.ALL;

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
		new Road(helena, winnipeg, RoadScore.FOUR, Color.BLUE);

		new Road(saltLakeCity, denver, RoadScore.THREE, Color.RED, Color.YELLOW);
		new Road(saltLakeCity, lasVegas, RoadScore.THREE, Color.ORANGE);

		new Road(denver, omaha, RoadScore.FOUR, Color.PINK);
		new Road(denver, kansasCity, RoadScore.FOUR, Color.BLACK, Color.ORANGE);
		new Road(denver, phoenix, RoadScore.FIVE, Color.WHITE);
		new Road(denver, santaFe, RoadScore.TWO, Color.GRAY);
		new Road(denver, oklahomaCity, RoadScore.FOUR, Color.RED);

		new Road(portLand, saltLakeCity, RoadScore.SIX, Color.BLUE);
		new Road(portLand, sanFrancisco, RoadScore.FIVE, Color.GREEN, Color.PINK);

		new Road(winnipeg, duluth, RoadScore.FOUR, Color.BLACK);
		new Road(winnipeg, saultStMarie, RoadScore.SIX, Color.GRAY);

		new Road(duluth, omaha, RoadScore.TWO, Color.GRAY, Color.GRAY);
		new Road(duluth, saultStMarie, RoadScore.THREE, Color.GRAY);
		new Road(duluth, toronto, RoadScore.SIX, Color.PINK);
		new Road(duluth, chicaco, RoadScore.THREE, Color.RED);

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

		new Road(sanFrancisco, losAngeles, RoadScore.THREE, Color.YELLOW, Color.PINK);

		new Road(pitsburg, newYork, RoadScore.TWO, Color.WHITE, Color.GREEN);
		new Road(pitsburg, chicaco, RoadScore.THREE, Color.BLACK, Color.ORANGE);
		new Road(pitsburg, washington, RoadScore.TWO, Color.GRAY);
		new Road(pitsburg, nashVille, RoadScore.FOUR, Color.YELLOW);
		new Road(pitsburg, saintLouis, RoadScore.FIVE, Color.GREEN);

		new Road(dallas, houston, RoadScore.ONE, Color.GRAY, Color.GRAY);
		new Road(dallas, littleRock, RoadScore.TWO, Color.GRAY);

		new Road(elPaso, houston, RoadScore.SIX, Color.GREEN);
		new Road(elPaso, dallas, RoadScore.FOUR, Color.RED);

		new Road(losAngeles, phoenix, RoadScore.THREE, Color.GRAY);
		new Road(losAngeles, elPaso, RoadScore.SIX, Color.BLACK);

		new Road(phoenix, elPaso, RoadScore.THREE, Color.GRAY);
		new Road(phoenix, santaFe, RoadScore.THREE, Color.GRAY);

		new Road(santaFe, elPaso, RoadScore.TWO, Color.GRAY);
		new Road(santaFe, oklahomaCity, RoadScore.THREE, Color.BLUE);

		new Road(kansasCity, oklahomaCity, RoadScore.TWO, Color.GRAY, Color.GRAY);
		new Road(kansasCity, saintLouis, RoadScore.TWO, Color.BLUE, Color.PINK);

		new Road(oklahomaCity, dallas, RoadScore.TWO, Color.GRAY, Color.GRAY);
		new Road(oklahomaCity, elPaso, RoadScore.FIVE, Color.YELLOW);
		new Road(oklahomaCity, littleRock, RoadScore.TWO, Color.GRAY);

		new Road(newYork, washington, RoadScore.TWO, Color.BLACK); // XXX

		new Road(chicaco, saintLouis, RoadScore.TWO, Color.GREEN, Color.WHITE);

		new Road(houston, newOrleans, RoadScore.TWO, Color.GRAY);

		new Road(littleRock, saintLouis, RoadScore.TWO, Color.GRAY);
		new Road(littleRock, newOrleans, RoadScore.THREE, Color.GREEN);
		new Road(littleRock, nashVille, RoadScore.THREE, Color.WHITE);

		new Road(newOrleans, miami, RoadScore.SIX, Color.RED);
		new Road(newOrleans, atlanta, RoadScore.FOUR, Color.YELLOW, Color.ORANGE);

		new Road(nashVille, atlanta, RoadScore.ONE, Color.GRAY);
		new Road(nashVille, raleign, RoadScore.THREE, Color.BLACK);

		new Road(washington, raleign, RoadScore.TWO, Color.GRAY, Color.GRAY);

		new Road(raleign, atlanta, RoadScore.TWO, Color.GRAY, Color.GRAY);
		new Road(raleign, charleston, RoadScore.TWO, Color.GRAY, Color.GRAY);

		new Road(atlanta, miami, RoadScore.FIVE, Color.BLUE);
		new Road(atlanta, charleston, RoadScore.TWO, Color.GRAY);

		new Road(charleston, miami, RoadScore.FOUR, Color.PINK);

	}

}
