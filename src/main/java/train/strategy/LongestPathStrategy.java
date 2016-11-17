package train.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import train.map.Usa;
import train.model.City;
import train.model.Road;

public class LongestPathStrategy {

	Cache cache = new Cache();
	final int[] maxPointsByTrain;

	public LongestPathStrategy() {
		maxPointsByTrain = new int[46];
		maxPointsByTrain[0] = 0;
		maxPointsByTrain[1] = 1;
		maxPointsByTrain[2] = 2;
		maxPointsByTrain[3] = 4;
		maxPointsByTrain[4] = 7;
		maxPointsByTrain[5] = 10;
		maxPointsByTrain[6] = 15;
		for (int i = 7; i <= 45; i++) {
			maxPointsByTrain[i] = 15 * (i / 6) + maxPointsByTrain[i % 6];
		}
		System.out.println(Arrays.toString(maxPointsByTrain));
	}

	public Set<Path> run(int minGoal, int maxGoal, int minPoints) {
		Set<Path> longestPaths = new HashSet<>();
		for (City city : new Usa().getCities())
			run(city, city, minGoal, maxGoal, 0, 0, new ArrayList<Road>(), new HashSet<Road>(), longestPaths, minPoints,
					true);
		return longestPaths;
	}

	public Set<Path> run(City from, int minGoal, int maxGoal, int minPoints) {
		Set<Path> longestPaths = new HashSet<>();
		run(from, from, minGoal, maxGoal, 0, 0, new ArrayList<Road>(), new HashSet<Road>(), longestPaths, minPoints,
				true);
		return longestPaths;
	}

	public List<Path> getOrderedByPoints(Set<Path> longestPaths) {
		List<Path> res = new ArrayList<>();
		res.addAll(longestPaths);
		Collections.sort(res);
		return res;
	}

	int nbPrune = 0;

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
				System.out.println("Res contains " + res.size() + " elements. " + nbPrune + " paths have been pruned.");
			}
		}

		if (currentLenght == maxLenght) {
			return;
		}

		int remainingTrain = maxLenght - currentLenght;
		if (currentPoints + maxPointsByTrain[remainingTrain] < minPoints) {
			nbPrune++;
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