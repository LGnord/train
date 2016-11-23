package train.longest_path;

import java.util.List;

import train.model.Mission;

public class Strategy extends Paths implements Comparable<Strategy> {

	final Mission[] missions;

	public Strategy(List<Path> paths, Mission[] missions) {
		super(paths);
		this.missions = missions;
	}

	public Mission[] getMissions() {
		return missions;
	}

	public String prettyPrint() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("-------------------------------\n\n");
		buffer.append(orderByFreq());
		buffer.append("\n");
		int pos = 1;
		for (Path path : paths) {
			buffer.append(pos + " ");
			buffer.append(path.toString());
			buffer.append("\n");
			pos++;
		}
		return buffer.toString();
	}

	@Override
	public int compareTo(Strategy o) {
		return (int) Math.round(o.esperance() - this.esperance());
	}

	public double esperance() {
		return (1.0 * paths.size() * getPoints()) / 100;
	}

	int getPoints() {
		int res = 0;
		for (Mission m : missions) {
			res += m.getPoints();
		}
		return res;
	}

	public String explainEsperance() {
		return esperance() + " = " + paths.size() + "% * " + getPoints();
	}

}
