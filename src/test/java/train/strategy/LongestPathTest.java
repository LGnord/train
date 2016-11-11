package train.strategy;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import train.map.Usa;

public class LongestPathTest {

	@Test
	public void ensure_longest_path_from_calgary() {
		LongestPathStrategy longestPath = new LongestPathStrategy();
		Usa usa = new Usa();
		Set<Path> actual = longestPath.run(usa.calgary, 10, 12);
		Set<Path> expected = new HashSet<>();

		Path lp = new Path(usa.calgary, usa.helena, usa.seattle, usa.portLand);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.helena, usa.seattle, usa.vancouver);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.vancouver, usa.seattle, usa.helena);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.helena, usa.saltLakeCity, usa.denver);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.helena, usa.denver, usa.saltLakeCity);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.helena, usa.seattle);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.helena, usa.denver, usa.omaha);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.vancouver, usa.seattle, usa.portLand, usa.saltLakeCity);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.winnipeg, usa.duluth);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.helena, usa.duluth);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.winnipeg, usa.duluth, usa.omaha);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.helena, usa.omaha, usa.duluth);
		expected.add(lp);

		lp = new Path(usa.calgary, usa.helena, usa.duluth, usa.omaha);
		expected.add(lp);

		Assert.assertEquals(expected.size(), actual.size());

		Assert.assertEquals(expected, actual);
	}

}
