package caribbean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

	@Test
	public void test_move_when_close_danger_and_close_barrel() {
		String in = "1 7 0 SHIP 19 17 2 0 28 1 1 SHIP 18 13 5 1 18 0 59 CANNONBALL 19 17 1 1 0 0 20 BARREL 18 16 19 0 0 0 19 BARREL 18 4 19 0 0 0 28 BARREL 18 2 19 0 0 0 32 BARREL 19 4 17 0 0 0";
		InputStream is = new ByteArrayInputStream(in.getBytes());
		String actual = Player.play(is);
		System.out.println(actual);
		Assert.assertFalse(actual, "MOVE 19 17".equals(actual));
		Assert.assertTrue(actual, actual.startsWith("MOVE"));
	}

	@Test
	public void test_no_exception_when_close_border() {
		String in = "2 16 0 SHIP 8 9 5 0 84 1 2 SHIP 20 19 4 1 86 1 1 SHIP 7 11 0 0 83 0 3 SHIP 14 14 3 0 99 0 4 MINE 4 8 0 0 0 0 30 CANNONBALL 20 15 3 0 0 0 11 BARREL 4 15 19 0 0 0 10 BARREL 4 5 19 0 0 0 13 BARREL 2 18 20 0 0 0 12 BARREL 2 2 20 0 0 0 14 BARREL 6 8 14 0 0 0 18 BARREL 15 6 16 0 0 0 21 BARREL 16 7 17 0 0 0 23 BARREL 8 10 17 0 0 0 25 BARREL 8 12 12 0 0 0 26 BARREL 13 7 16 0 0 0";
		InputStream is = new ByteArrayInputStream(in.getBytes());
		String actual = Player.play(is);
		Assert.assertTrue(actual, actual.contains("\n"));
	}

	@Test
	public void test_move_restart_close_border() {
		String in = "2 8 0 SHIP 0 4 4 0 24 1 2 SHIP 1 18 2 1 4 1 1 SHIP 9 20 4 0 25 0 3 SHIP 7 19 4 1 30 0 56 CANNONBALL 2 19 1 0 0 0 57 CANNONBALL 2 19 3 1 0 0 58 CANNONBALL 1 19 1 3 0 0 59 CANNONBALL 1 19 3 2 0 0";
		InputStream is = new ByteArrayInputStream(in.getBytes());
		String actual = Player.play(is);
		Assert.assertFalse(actual, actual.contains("MOVE 0 4"));
	}

	@Test
	public void test_move_to_closest_barrel() {
		String in = "3 26 0 SHIP 6 7 4 1 100 1 2 SHIP 9 12 4 1 94 1 4 SHIP 19 13 5 1 98 1 1 SHIP 4 14 2 1 94 0 3 SHIP 5 11 1 1 98 0 5 SHIP 17 12 4 0 97 0 7 MINE 10 14 0 0 0 0 6 MINE 10 6 0 0 0 0 9 MINE 15 12 0 0 0 0 38 CANNONBALL 18 11 5 1 0 0 17 BARREL 21 17 15 0 0 0 16 BARREL 21 3 15 0 0 0 19 BARREL 18 15 13 0 0 0 18 BARREL 18 5 13 0 0 0 22 BARREL 2 13 12 0 0 0 21 BARREL 2 7 12 0 0 0 26 BARREL 13 15 18 0 0 0 25 BARREL 13 5 18 0 0 0 28 BARREL 19 19 16 0 0 0 27 BARREL 19 1 16 0 0 0 30 BARREL 10 15 10 0 0 0 29 BARREL 10 5 10 0 0 0 31 BARREL 17 7 19 0 0 0 34 BARREL 14 12 16 0 0 0 33 BARREL 14 8 16 0 0 0 35 BARREL 19 8 10 0 0 0";
		InputStream is = new ByteArrayInputStream(in.getBytes());
		String actual = Player.play(is);
		Assert.assertFalse(actual, actual.contains("MOVE 8 9"));
	}

	@Test
	public void test_event_if_all_neightbours_are_negative() {
		String in = "3 17 0 SHIP 7 10 5 0 86 1 2 SHIP 7 9 2 0 86 1 4 SHIP 12 3 3 1 63 1 1 SHIP 5 14 1 1 94 0 3 SHIP 8 9 2 0 98 0 5 SHIP 14 16 3 1 66 0 9 MINE 11 1 0 0 0 0 31 CANNONBALL 14 3 3 2 0 0 14 BARREL 3 1 15 0 0 0 19 BARREL 11 15 12 0 0 0 18 BARREL 11 5 12 0 0 0 21 BARREL 12 15 16 0 0 0 20 BARREL 12 5 16 0 0 0 22 BARREL 5 10 19 0 0 0 23 BARREL 9 5 14 0 0 0 25 BARREL 21 1 14 0 0 0 27 BARREL 3 3 17 0 0 0";
		InputStream is = new ByteArrayInputStream(in.getBytes());
		String actual = Player.play(is);
		Assert.assertFalse(actual, actual.contains("MOVE 0 0"));
	}

	@Test
	public void test_to_center_when_block_close_border() {
		String in = "3 13 0 SHIP 4 0 2 0 69 1 2 SHIP 9 0 3 0 65 1 4 SHIP 6 0 3 0 15 1 1 SHIP 3 6 1 1 84 0 3 SHIP 0 4 2 0 68 0 5 SHIP 3 9 1 1 15 0 10 MINE 8 5 0 0 0 0 25 CANNONBALL 6 0 3 1 0 0 26 CANNONBALL 6 0 1 2 0 0 27 CANNONBALL 6 0 3 3 0 0 14 BARREL 1 3 11 0 0 0 16 BARREL 6 2 12 0 0 0 22 BARREL 10 1 19 0 0 0 ";
		InputStream is = new ByteArrayInputStream(in.getBytes());
		String actual = Player.play(is);
		Assert.assertFalse(actual, actual.contains("MOVE 0 0"));
	}

	@Test
	public void test_take_barrel_when_distance_1() {
		String in = "1 2 0 SHIP 4 4 4 0 69 1 1 BARREL 4 3 11 0 0 0 16";
		InputStream is = new ByteArrayInputStream(in.getBytes());
		String actual = Player.play(is);
		Assert.assertTrue(actual, actual.contains("MOVE 4 2"));
	}

	@Test
	public void test_no_collosion_with_my_own_ships() {
		String in = "3 15 0 SHIP 7 5 0 0 88 1 2 SHIP 11 5 3 0 85 1 4 SHIP 16 5 0 0 35 1 1 SHIP 3 6 0 1 98 0 3 SHIP 8 17 5 1 100 0 5 SHIP 21 12 2 1 94 0 6 MINE 16 10 0 0 0 0 7 MINE 21 5 0 0 0 0 9 MINE 11 9 0 0 0 0 11 MINE 13 10 0 0 0 0 12 MINE 15 3 0 0 0 0 31 CANNONBALL 16 5 5 4 0 0 16 BARREL 21 4 17 0 0 0 20 BARREL 9 5 13 0 0 0 22 BARREL 8 2 17 0 0 0 ";
		InputStream is = new ByteArrayInputStream(in.getBytes());
		String actual = Player.play(is);
		// second ship sould move
		Assert.assertFalse(actual, actual.contains("MOVE 10 5"));
	}

}
