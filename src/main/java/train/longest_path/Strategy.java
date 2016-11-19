package train.longest_path;

import java.util.List;

import train.model.Mission;

public class Strategy extends Paths {

	final Mission[] missions;

	public Strategy(List<Path> paths, Mission[] missions) {
		super(paths);
		this.missions = missions;
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

}
