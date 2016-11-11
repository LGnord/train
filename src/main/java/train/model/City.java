package train.model;

import java.util.HashSet;
import java.util.Set;

public class City {
	
	private final String name;
	private final Set<Path> paths;
	
	public City(String name) {
		super();
		this.name = name;
		this.paths = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public void addPath(Path path) {
		paths.add(path);
	}
	
	
}
