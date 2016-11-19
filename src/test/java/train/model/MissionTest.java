package train.model;

import org.junit.Assert;
import org.junit.Test;

import train.map.Usa;

public class MissionTest {

	@Test
	public void ensure_() {
		Usa usa = new Usa();
		Mission actual = Mission.parseMission("CHI-SFE", usa);
		Assert.assertEquals(usa.chi_san, actual);
	}
}
