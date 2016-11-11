package train.strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import train.model.City;
import train.model.Road;

public class LongestPathStrategy {

	public Set<Path> run(City from, int minGoal, int maxGoal) {
		Set<Path> res = new HashSet<>();
		run(from, from, minGoal, maxGoal, 0, new ArrayList<Road>(), new HashSet<Road>(), res);
		return res;
	}

	private void run(City fromHead, City from, int minGoal, int maxGoal, int currentLenght, List<Road> currentPath,
			Set<Road> used, Set<Path> res) {
		if (currentLenght > maxGoal) {
			return;
		}
		if (currentLenght >= minGoal) {
			Path path = new Path(fromHead, currentPath);
			res.add(path);
		}
		for (Road road : from.getPaths()) {
			if (!used.contains(road)) {
				List<Road> newCurrentPath = new ArrayList<>();
				newCurrentPath.addAll(currentPath);
				newCurrentPath.add(road);
				Set<Road> newUsed = new HashSet<>();
				newUsed.addAll(used);
				newUsed.add(road);
				run(fromHead, road.getOther(from), minGoal, maxGoal, currentLenght + road.getLenght(), newCurrentPath,
						newUsed, res);
			}
		}
	}

}
