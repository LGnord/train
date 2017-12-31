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
		Assert.assertFalse("MOVE 19 17".equals(actual));
		Assert.assertTrue(actual.startsWith("MOVE"));
	}

}
