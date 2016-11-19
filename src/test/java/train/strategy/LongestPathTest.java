package train.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import train.longest_path.LongestPathHelper;
import train.longest_path.Path;
import train.map.Usa;

public class LongestPathTest {

	@Test
	public void ensure_longest_path_10_12_from_calgary() {
		LongestPathHelper longestPath = new LongestPathHelper();
		Usa usa = new Usa();
		Set<Path> actual = longestPath.run(usa.calgary, 10, 12, 0);
		Set<Path> expected = new HashSet<>();

		Path lp = new Path(usa.calgary, usa.helena, usa.seattle, usa.portLand);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.seattle, usa.vancouver);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.vancouver, usa.seattle, usa.helena);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.saltLakeCity, usa.denver);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.denver, usa.saltLakeCity);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.seattle);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.denver, usa.omaha);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.vancouver, usa.seattle, usa.portLand, usa.saltLakeCity);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.winnipeg, usa.duluth);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.duluth);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.winnipeg, usa.duluth, usa.omaha);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.omaha, usa.duluth);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.duluth, usa.omaha);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.vancouver, usa.seattle, usa.portLand, usa.sanFrancisco);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.omaha, usa.kansasCity);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.denver, usa.kansasCity);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.saltLakeCity, usa.lasVegas);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.saltLakeCity, usa.lasVegas, usa.sanFrancisco);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.vancouver, usa.seattle, usa.portLand, usa.sanFrancisco, usa.lasVegas);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.winnipeg, usa.saultStMarie);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.denver, usa.santaFe);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.saltLakeCity, usa.denver, usa.santaFe);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.denver, usa.oklahomaCity);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.denver, usa.santaFe, usa.elPaso);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.omaha, usa.kansasCity, usa.oklahomaCity);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.winnipeg, usa.helena);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.winnipeg, usa.duluth);
		add(expected, lp);

		lp = new Path(usa.calgary, usa.helena, usa.omaha, usa.kansasCity, usa.saintLouis);
		add(expected, lp);

		for (Path p : expected) {
			Assert.assertTrue(p.getLenght() >= 10);
			Assert.assertTrue(p.getLenght() <= 12);
		}

		assertSetEquals(actual, expected);
	}

	@Test
	public void ensure_longest_path_6_8_from_dallas() {
		LongestPathHelper longestPath = new LongestPathHelper();
		Usa usa = new Usa();
		Set<Path> actual = longestPath.run(usa.dallas, 6, 8, 0);
		Set<Path> expected = new HashSet<>();

		Path lp = new Path(usa.dallas, usa.houston, usa.elPaso);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.elPaso, usa.phoenix);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.elPaso, usa.santaFe);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.elPaso, usa.santaFe, usa.denver);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.kansasCity, usa.omaha, usa.duluth);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.santaFe, usa.denver);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.denver);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.santaFe, usa.phoenix);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.kansasCity, usa.denver);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.santaFe, usa.elPaso);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.denver, usa.santaFe);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.elPaso);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.oklahomaCity, usa.kansasCity);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.oklahomaCity, usa.kansasCity, usa.omaha);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.oklahomaCity, usa.denver);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.oklahomaCity, usa.dallas);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.oklahomaCity, usa.dallas, usa.houston);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.oklahomaCity, usa.santaFe);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.littleRock, usa.saintLouis);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.houston, usa.newOrleans, usa.littleRock, usa.oklahomaCity);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.littleRock, usa.newOrleans);
		add(expected, lp);

		// lp = new Path(usa.dallas, usa.oklahomaCity, usa.littleRock,
		// usa.newOrleans, usa.houston);
		// expected.add(lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.newOrleans, usa.houston, usa.dallas);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.houston, usa.newOrleans, usa.littleRock, usa.saintLouis);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.newOrleans, usa.houston);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.houston, usa.newOrleans, usa.atlanta);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.nashVille, usa.atlanta);
		add(expected, lp);

		// lp = new Path(usa.dallas, usa.houston, usa.newOrleans,
		// usa.littleRock, usa.nashVille);
		// expected.add(lp);

		lp = new Path(usa.dallas, usa.houston, usa.newOrleans, usa.atlanta, usa.nashVille);
		add(expected, lp);

		// lp = new Path(usa.dallas, usa.littleRock, usa.newOrleans,
		// usa.atlanta);
		// expected.add(lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.littleRock, usa.nashVille, usa.atlanta);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.nashVille, usa.atlanta, usa.charleston);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.littleRock, usa.nashVille);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.nashVille, usa.atlanta, usa.raleign);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.oklahomaCity, usa.kansasCity, usa.saintLouis);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.saintLouis, usa.kansasCity);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.saintLouis, usa.chicaco);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.saintLouis, usa.kansasCity, usa.oklahomaCity);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.littleRock, usa.saintLouis, usa.chicaco);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.houston, usa.newOrleans, usa.littleRock);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.kansasCity, usa.saintLouis, usa.littleRock);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.nashVille, usa.raleign);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.littleRock, usa.saintLouis, usa.kansasCity);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.littleRock, usa.saintLouis, usa.kansasCity, usa.omaha);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.kansasCity, usa.saintLouis, usa.chicaco);
		add(expected, lp);

		lp = new Path(usa.dallas, usa.oklahomaCity, usa.kansasCity, usa.saintLouis);
		add(expected, lp);

		for (Path p : expected) {
			Assert.assertTrue(p.getLenght() >= 6);
			Assert.assertTrue(p.getLenght() <= 8);
		}

		assertSetEquals(actual, expected);
	}

	private void assertSetEquals(Set<Path> actual, Set<Path> expected) {
		for (Path p : expected) {
			Assert.assertTrue("*[" + actual.size() + "," + expected.size() + "]*" + p.toString(), actual.contains(p));
		}

		for (Path p : actual) {
			Assert.assertTrue("[" + actual.size() + "," + expected.size() + "]" + p.toString(), expected.contains(p));
		}

		Assert.assertEquals(expected.size(), actual.size());
	}

	private void add(Set<Path> expected, Path lp) {
		if (expected.contains(lp)) {
			System.out.println("Duplicate: " + lp);
		}
		expected.add(lp);
	}

	@Test
	public void ensure_longest_path_45_from_calgary() {
		LongestPathHelper longestPath = new LongestPathHelper();
		Usa usa = new Usa();

		List<Path> actual = longestPath.getOrderedByPoints(longestPath.run(usa.calgary, 45, 45, 80));

		System.out.println(actual.get(0));
		System.out.println(actual.get(37_000));

		Assert.assertEquals(113_712, actual.size());
		Assert.assertEquals(105, actual.get(0).getPoints());
	}
}
