package train.guest;

import java.util.HashMap;
import java.util.Map;

import train.map.Usa;
import train.model.City;
import train.model.Mission;
import train.model.Road;

public class ConnectedGraph {

	Map<City, Map<City, Integer>> graph;
	Usa usa;

	int NOT_CONNECTED = -1;
	int CONNECTED = 0;

	public ConnectedGraph(Usa usa) {
		this.usa = usa;
		graph = new HashMap<>();
		for (City left : usa.getCities()) {
			for (City right : usa.getCities()) {
				if (isOrdered(left, right)) {
					add(left, right);
				} else {
					add(right, left);
				}
			}
		}

	}

	private boolean isOrdered(City left, City right) {
		return left.getName().compareTo(right.getName()) <= 0;
	}

	private void add(City right, City left) {
		Map<City, Integer> map = graph.get(right);
		if (map == null) {
			map = new HashMap<>();
			graph.put(right, map);
		}
		if (right == left) {
			map.put(left, CONNECTED);
		} else {
			map.put(left, NOT_CONNECTED);
		}

	}

	public boolean isConnected(City left, City right) {
		if (isOrdered(left, right)) {
			return graph.get(left).get(right) == CONNECTED;
		}
		return isOrdered(right, left);
	}

	public void addRoad(Road road) {
		addPath(road.getSource(), road.getDestination());
	}

	private void addPath(City left, City right) {
		for (City city : usa.getCities()) {
			if (isConnected(left, city)) {
				_addPath(city, right);
			}
			if (isConnected(right, city)) {
				_addPath(city, left);
			}
		}
	}

	private void _addPath(City left, City right) {
		if (isOrdered(left, right)) {
			graph.get(left).put(right, CONNECTED);
		} else {
			_addPath(right, left);
		}

	}

	public boolean isDone(Mission mission) {
		return isConnected(mission.getFrom(), mission.getTo());
	}

}
