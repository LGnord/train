package train.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import train.model.City;
import train.model.Road;

public class LongestPathStrategy {

	Set<Path> longestPaths;
	Cache cache = new Cache();

	public Set<Path> run(City from, int minGoal, int maxGoal, int minPoints) {
		longestPaths = new HashSet<>();
		run(from, from, minGoal, maxGoal, 0, 0, new ArrayList<Road>(), new HashSet<Road>(), longestPaths, minPoints,
				true);
		return longestPaths;
	}

	public List<Path> getOrderedByPoints() {
		List<Path> res = new ArrayList<>();
		res.addAll(longestPaths);
		Collections.sort(res);
		return res;
	}

	private void run(City fromHead, City from, int minLenght, int maxLenght, int currentLenght, int currentPoints,
			List<Road> currentPath, Set<Road> used, Set<Path> res, int minPoints, boolean useCache) {
		if (currentLenght > maxLenght) {
			return;
		}
		if (currentLenght >= minLenght) {
			Path path = new Path(fromHead, currentPath);
			if (path.getPoints() >= minPoints) {
				res.add(path);
			}
			if (res.size() % 1_000 == 0) {
				System.out.println("Res contains " + res.size() + " elements.");
			}
		}

		if (currentLenght == maxLenght) {
			return;
		}

		if (useCache && currentLenght + cache.CACHE_LENGHT >= maxLenght) {
			for (int remainLenght = maxLenght - currentLenght; remainLenght > 0; remainLenght--) {
				Set<Path> paths = cache.getPaths(from, remainLenght);
				for (Path p : paths) {
					assert p.getLenght() == remainLenght;
					// XXX duplicate code
					if (isEmptyIntersection(p.getRoads(), used)) {
						List<Road> newCurrentPath = new ArrayList<>();
						newCurrentPath.addAll(currentPath);
						newCurrentPath.addAll(p.getRoads());
						Set<Road> newUsed = new HashSet<>();
						newUsed.addAll(used);
						newUsed.addAll(p.getRoads());
						run(fromHead, p.getDestination(), minLenght, maxLenght, currentLenght + p.getLenght(),
								currentPoints + p.getPoints(), newCurrentPath, newUsed, res, minPoints, useCache);
					}
				}
			}
			return;
		}
		runOverAllRoad(from.getRoads(), fromHead, from, minLenght, maxLenght, currentLenght, currentPoints, currentPath,
				used, res, minPoints, useCache);

	}

	private boolean isEmptyIntersection(List<Road> roads, Set<Road> used) {
		for (Road r : roads) {
			if (used.contains(r)) {
				return false;
			}
		}
		return true;
	}

	void runOverAllRoad(Iterable<Road> roads, City fromHead, City from, int minLenght, int maxLenght, int currentLenght,
			int currentPoints, List<Road> currentPath, Set<Road> used, Set<Path> res, int minPoints, boolean useCache) {
		for (Road road : roads) {
			if (!used.contains(road)) {
				List<Road> newCurrentPath = new ArrayList<>();
				newCurrentPath.addAll(currentPath);
				newCurrentPath.add(road);
				Set<Road> newUsed = new HashSet<>();
				newUsed.addAll(used);
				newUsed.add(road);
				run(fromHead, road.getOther(from), minLenght, maxLenght, currentLenght + road.getLenght(),
						currentPoints + road.getPoints(), newCurrentPath, newUsed, res, minPoints, useCache);
			}
		}
	}

	class Cache {
		int CACHE_LENGHT = 6;

		Map<City, Set<Path>[]> cache = new HashMap<>();

		public Set<Path> getPaths(City city, int lenght) {
			assert lenght > 0;
			assert lenght <= CACHE_LENGHT;
			Set<Path>[] array = cache.get(city);
			if (array == null) {
				array = new Set[CACHE_LENGHT];
				cache.put(city, array);
			}
			Set<Path> paths = array[lenght - 1];
			if (paths == null) {
				paths = new HashSet<>();
				run(city, city, lenght, lenght, 0, 0, new ArrayList<Road>(), new HashSet<Road>(), paths, 0, false);
				array[lenght - 1] = paths;
			}
			return paths;
		}

	}

}