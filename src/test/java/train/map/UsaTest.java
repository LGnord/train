package train.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import train.model.City;
import train.model.Color;

public class UsaTest {

	@Test
	public void ensure_number_of_cities() {
		Usa usa = new Usa();
		Assert.assertEquals(36, usa.getCities().size());
	}

	private void assertNumberOfRoadByColor(int expected, Color c) {
		Usa usa = new Usa();
		Assert.assertEquals(expected, usa.getRoadsByColor(c).size());
	}

	@Test
	public void ensure_number_of_read_roads() {
		assertNumberOfRoadByColor(7, Color.RED);
	}

	@Test
	public void ensure_number_of_pink_roads() {
		assertNumberOfRoadByColor(7, Color.PINK);
	}

	@Test
	public void ensure_number_of_green_roads() {
		assertNumberOfRoadByColor(7, Color.GREEN);
	}

	@Test
	public void ensure_number_of_orange_roads() {
		assertNumberOfRoadByColor(6, Color.ORANGE);
	}

	@Test
	public void ensure_number_of_yellow_roads() {
		assertNumberOfRoadByColor(7, Color.YELLOW);
	}

	@Test
	public void ensure_number_of_blue_roads() {
		assertNumberOfRoadByColor(7, Color.BLUE);
	}

	@Test
	public void ensure_number_of_black_roads() {
		assertNumberOfRoadByColor(7, Color.BLACK);
	}

	@Test
	public void ensure_number_of_gray_roads() {
		assertNumberOfRoadByColor(30, Color.GRAY);
	}

	@Test
	public void ensure_number_of_double_roads() {
		Usa usa = new Usa();
		Assert.assertEquals(20 /* 21 */, usa.getRoadsByDepth(2).size());
	}

	@Test
	public void ensure_number_of_l5_roads() {
		Usa usa = new Usa();
		Assert.assertEquals(7, usa.getRoadsByLenght(5).size());
	}

	@Test
	public void ensure_all_id_are_distinct() {
		Usa usa = new Usa();
		List<String> ids = new ArrayList<>();
		for (City city : usa.getCities()) {
			ids.add(city.getId());
		}
		Collections.sort(ids);
		Set<String> removeDuplicate = new HashSet<>();
		removeDuplicate.addAll(ids);
		Assert.assertEquals(ids.toString(), removeDuplicate.size(), ids.size());
	}

	@Test
	public void ensure_get_santa_fe_by_id() {
		Usa usa = new Usa();
		Assert.assertEquals(usa.santaFe, usa.getCity("Sfe"));
	}

}
