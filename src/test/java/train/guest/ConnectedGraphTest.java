package train.guest;

import org.junit.Assert;
import org.junit.Test;

import train.map.Usa;

public class ConnectedGraphTest {

	@Test
	public void ensure_init() {
		Usa usa = new Usa();
		ConnectedGraph graph = new ConnectedGraph(usa);
		Assert.assertTrue(graph.isConnected(usa.atlanta, usa.atlanta));
		Assert.assertFalse(graph.isConnected(usa.atlanta, usa.boston));
	}

	@Test
	public void ensure_connected_when_add_road() {
		Usa usa = new Usa();
		ConnectedGraph graph = new ConnectedGraph(usa);
		Assert.assertFalse(graph.isConnected(usa.atlanta, usa.newOrleans));
		graph.addRoad(usa.atlanta.getRoad(usa.newOrleans));
		Assert.assertTrue(graph.isConnected(usa.atlanta, usa.newOrleans));
		Assert.assertTrue(graph.isConnected(usa.newOrleans, usa.atlanta));
	}

	@Test
	public void ensure_connected_is_transitive() {
		Usa usa = new Usa();
		ConnectedGraph graph = new ConnectedGraph(usa);
		graph.addRoad(usa.atlanta.getRoad(usa.newOrleans));
		graph.addRoad(usa.newOrleans.getRoad(usa.houston));
		Assert.assertTrue(graph.isConnected(usa.atlanta, usa.houston));
		Assert.assertTrue(graph.isConnected(usa.houston, usa.atlanta));
	}

	@Test
	public void ensure_connected_is_mission_done() {
		Usa usa = new Usa();
		ConnectedGraph graph = new ConnectedGraph(usa);
		Assert.assertFalse(graph.isDone(usa.bos_mia));
		graph.addRoad(usa.atlanta.getRoad(usa.newOrleans));
		graph.addRoad(usa.newOrleans.getRoad(usa.houston));
		graph.addRoad(usa.boston.getRoad(usa.newYork));
		graph.addRoad(usa.miami.getRoad(usa.atlanta));
		graph.addRoad(usa.atlanta.getRoad(usa.nashVille));
		graph.addRoad(usa.nashVille.getRoad(usa.pitsburg));
		graph.addRoad(usa.newYork.getRoad(usa.pitsburg));
		Assert.assertTrue(graph.isDone(usa.bos_mia));
	}

}
