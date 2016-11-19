package train.longest_path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import train.model.Mission;
import train.model.Road;
import train.strategy.util.RoadFreq;

public class Paths {

	final List<Path> paths;

	public Paths(List<Path> paths) {
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

	public Strategy prepareStrategy(int qte, Mission... missions) {
		int nb = 0;
		List<Path> res = new ArrayList<>();
		for (Path path : paths) {
			if (path.contains(missions)) {
				res.add(path);
				nb++;
				if (nb == qte) {
					return new Strategy(res, missions);
				}
			}
		}
		return new Strategy(res, missions);
	}

}
