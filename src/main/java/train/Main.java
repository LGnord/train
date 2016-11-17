package train;

import java.util.List;

import train.strategy.LongestPathStrategy;
import train.strategy.Path;
import train.strategy.PathStatistic;

public class Main {

	public static void main(String[] args) {
		LongestPathStrategy longestPathStrategy = new LongestPathStrategy();
		List<Path> actual = longestPathStrategy.getOrderedByPoints(longestPathStrategy.run(40, 45, 85));
		PathStatistic pathStatistic = new PathStatistic(actual);
		System.out.println(pathStatistic.orderByFreq());
	}

}
