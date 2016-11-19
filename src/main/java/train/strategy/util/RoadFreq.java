package train.strategy.util;

import train.model.Road;

public class RoadFreq implements Comparable<RoadFreq> {

	final Road road;
	final int occurence;
	final int freq;

	public RoadFreq(Road road, int occurence, int freq) {
		super();
		this.road = road;
		this.occurence = occurence;
		this.freq = freq;
	}

	@Override
	public int compareTo(RoadFreq o) {
		return o.occurence - this.occurence;
	}

	@Override
	public String toString() {
		return road + "(" + occurence + ", " + freq + "%)";
	}

}
