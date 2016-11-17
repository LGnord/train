package train.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import train.model.Road;

public class PathStatistic {

	final List<Path> paths;

	public PathStatistic(List<Path> paths) {
		super();
		this.paths = paths;
	}

	public List<RoadFreq> orderByFreq() {
		Map<Road, Integer> map = new HashMap<>();
		for (Path p : paths) {
			for (Road r : p.getRoads()) {
				if (map.get(r) == null) {
					map.put(r, 0);
				}
				map.put(r, map.get(r) + 1);
			}
		}
		List<RoadFreq> res = new ArrayList<>();
		for (Road r : map.keySet()) {
			res.add(new RoadFreq(r, map.get(r), (100 * map.get(r)) / paths.size()));
		}
		Collections.sort(res);
		return res;
	}

}
