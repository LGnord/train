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
	public City portLand = new City("Portland");
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

	public Mission sea_nyo = new Mission(1, seattle, newYork, 22);
	public Mission lan_nyo = new Mission(2, losAngeles, newYork, 21);
	public Mission van_mon = new Mission(3, vancouver, montreal, 20);
	public Mission lan_mia = new Mission(4, losAngeles, miami, 20);
	public Mission por_nah = new Mission(5, portLand, nashVille, 17);
	public Mission san_atl = new Mission(6, sanFrancisco, atlanta, 17);
	public Mission lan_chi = new Mission(7, losAngeles, chicaco, 16);
	public Mission mon_nyo = new Mission(8, montreal, newYork, 13);
	public Mission van_san = new Mission(9, vancouver, santaFe, 13);
	public Mission cal_pho = new Mission(10, calgary, phoenix, 13);
	public Mission mon_nor = new Mission(11, montreal, newOrleans, 13);
	public Mission bos_mia = new Mission(12, boston, miami, 12);
	public Mission tor_mia = new Mission(13, toronto, miami, 12);
	public Mission win_hou = new Mission(14, winnipeg, houston, 12);
	public Mission dal_nyo = new Mission(15, dallas, newYork, 11);
	public Mission den_pit = new Mission(16, denver, pitsburg, 11);
	public Mission por_phe = new Mission(17, portLand, phoenix, 11);
	public Mission win_lro = new Mission(18, winnipeg, littleRock, 11);
	public Mission dul_elp = new Mission(19, duluth, elPaso, 10);
	public Mission sea_lan = new Mission(20, seattle, losAngeles, 9);
	public Mission chi_san = new Mission(21, chicaco, santaFe, 9);
	public Mission mon_atl = new Mission(22, montreal, atlanta, 9);
	public Mission sal_okl = new Mission(23, saultStMarie, oklahomaCity, 9);
	// A valider
	public Mission sal_nas = new Mission(24, saultStMarie, nashVille, 9);
	public Mission hel_lan = new Mission(25, helena, losAngeles, 8);
	public Mission dul_hou = new Mission(26, duluth, houston, 8);
	public Mission chi_nor = new Mission(27, chicaco, newOrleans, 7);
	public Mission cal_slc = new Mission(28, calgary, saltLakeCity, 7);
	public Mission nyo_atl = new Mission(29, newYork, atlanta, 6);
	public Mission kcy_hou = new Mission(30, kansasCity, houston, 5);
	public Mission den_elp = new Mission(31, denver, elPaso, 4);

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

		new Road(1, vancouver, calgary, RoadScore.THREE, Color.GRAY);
		new Road(2, vancouver, seattle, RoadScore.ONE, Color.GRAY, Color.GRAY);

		new Road(3, seattle, portLand, RoadScore.ONE, Color.GRAY);
		new Road(4, seattle, helena, RoadScore.SIX, Color.YELLOW);

		new Road(5, calgary, helena, RoadScore.FOUR, Color.GRAY);
		new Road(6, calgary, winnipeg, RoadScore.SIX, Color.WHITE);

		new Road(7, helena, saltLakeCity, RoadScore.THREE, Color.PINK);
		new Road(8, helena, denver, RoadScore.FOUR, Color.GREEN);
		new Road(9, helena, omaha, RoadScore.FIVE, Color.RED);
		new Road(10, helena, duluth, RoadScore.SIX, Color.ORANGE);
		new Road(11, helena, winnipeg, RoadScore.FOUR, Color.BLUE);

		new Road(12, saltLakeCity, denver, RoadScore.THREE, Color.RED, Color.YELLOW);
		new Road(13, saltLakeCity, lasVegas, RoadScore.THREE, Color.ORANGE);

		new Road(14, denver, omaha, RoadScore.FOUR, Color.PINK);
		new Road(15, denver, kansasCity, RoadScore.FOUR, Color.BLACK, Color.ORANGE);
		new Road(16, denver, phoenix, RoadScore.FIVE, Color.WHITE);
		new Road(17, denver, santaFe, RoadScore.TWO, Color.GRAY);
		new Road(18, denver, oklahomaCity, RoadScore.FOUR, Color.RED);

		new Road(19, portLand, saltLakeCity, RoadScore.SIX, Color.BLUE);
		new Road(20, portLand, sanFrancisco, RoadScore.FIVE, Color.GREEN, Color.PINK);

		new Road(21, winnipeg, duluth, RoadScore.FOUR, Color.BLACK);
		new Road(22, winnipeg, saultStMarie, RoadScore.SIX, Color.GRAY);

		new Road(23, duluth, omaha, RoadScore.TWO, Color.GRAY, Color.GRAY);
		new Road(24, duluth, saultStMarie, RoadScore.THREE, Color.GRAY);
		new Road(25, duluth, toronto, RoadScore.SIX, Color.PINK);
		new Road(26, duluth, chicaco, RoadScore.THREE, Color.RED);

		new Road(27, omaha, kansasCity, RoadScore.ONE, Color.GRAY, Color.GRAY);
		new Road(28, omaha, chicaco, RoadScore.FOUR, Color.BLUE);

		new Road(28, lasVegas, sanFrancisco, RoadScore.TWO, Color.ORANGE);

		new Road(29, saultStMarie, montreal, RoadScore.FIVE, Color.BLACK);
		new Road(30, saultStMarie, toronto, RoadScore.TWO, Color.GRAY);

		new Road(31, montreal, toronto, RoadScore.THREE, Color.GRAY);
		new Road(32, montreal, newYork, RoadScore.THREE, Color.BLUE);
		new Road(33, montreal, boston, RoadScore.TWO, Color.GRAY, Color.GRAY);

		new Road(34, boston, newYork, RoadScore.TWO, Color.YELLOW, Color.RED);

		new Road(35, toronto, pitsburg, RoadScore.TWO, Color.GRAY);

		new Road(36, sanFrancisco, losAngeles, RoadScore.THREE, Color.YELLOW, Color.PINK);

		new Road(37, pitsburg, newYork, RoadScore.TWO, Color.WHITE, Color.GREEN);
		new Road(38, pitsburg, chicaco, RoadScore.THREE, Color.BLACK, Color.ORANGE);
		new Road(39, pitsburg, washington, RoadScore.TWO, Color.GRAY);
		new Road(40, pitsburg, nashVille, RoadScore.FOUR, Color.YELLOW);
		new Road(41, pitsburg, saintLouis, RoadScore.FIVE, Color.GREEN);

		new Road(42, dallas, houston, RoadScore.ONE, Color.GRAY, Color.GRAY);
		new Road(43, dallas, littleRock, RoadScore.TWO, Color.GRAY);

		new Road(44, elPaso, houston, RoadScore.SIX, Color.GREEN);
		new Road(45, elPaso, dallas, RoadScore.FOUR, Color.RED);

		new Road(46, losAngeles, phoenix, RoadScore.THREE, Color.GRAY);
		new Road(47, losAngeles, elPaso, RoadScore.SIX, Color.BLACK);

		new Road(48, phoenix, elPaso, RoadScore.THREE, Color.GRAY);
		new Road(49, phoenix, santaFe, RoadScore.THREE, Color.GRAY);

		new Road(50, santaFe, elPaso, RoadScore.TWO, Color.GRAY);
		new Road(51, santaFe, oklahomaCity, RoadScore.THREE, Color.BLUE);

		new Road(52, kansasCity, oklahomaCity, RoadScore.TWO, Color.GRAY, Color.GRAY);
		new Road(53, kansasCity, saintLouis, RoadScore.TWO, Color.BLUE, Color.PINK);

		new Road(54, oklahomaCity, dallas, RoadScore.TWO, Color.GRAY, Color.GRAY);
		new Road(55, oklahomaCity, elPaso, RoadScore.FIVE, Color.YELLOW);
		new Road(56, oklahomaCity, littleRock, RoadScore.TWO, Color.GRAY);

		new Road(57, newYork, washington, RoadScore.TWO, Color.BLACK); // XXX

		new Road(58, chicaco, saintLouis, RoadScore.TWO, Color.GREEN, Color.WHITE);

		new Road(59, houston, newOrleans, RoadScore.TWO, Color.GRAY);

		new Road(60, littleRock, saintLouis, RoadScore.TWO, Color.GRAY);
		new Road(61, littleRock, newOrleans, RoadScore.THREE, Color.GREEN);
		new Road(62, littleRock, nashVille, RoadScore.THREE, Color.WHITE);

		new Road(63, newOrleans, miami, RoadScore.SIX, Color.RED);
		new Road(64, newOrleans, atlanta, RoadScore.FOUR, Color.YELLOW, Color.ORANGE);

		new Road(65, nashVille, atlanta, RoadScore.ONE, Color.GRAY);
		new Road(66, nashVille, raleign, RoadScore.THREE, Color.BLACK);

		new Road(67, washington, raleign, RoadScore.TWO, Color.GRAY, Color.GRAY);

		new Road(68, raleign, atlanta, RoadScore.TWO, Color.GRAY, Color.GRAY);
		new Road(69, raleign, charleston, RoadScore.TWO, Color.GRAY, Color.GRAY);

		new Road(70, atlanta, miami, RoadScore.FIVE, Color.BLUE);
		new Road(71, atlanta, charleston, RoadScore.TWO, Color.GRAY);

		new Road(72, charleston, miami, RoadScore.FOUR, Color.PINK);

	}

}
