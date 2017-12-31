package caribbean;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Player {

	static final int[][][] NEIGHBOUR = { { { 1, 1 }, { 0, 1 }, { -1, 0 }, { -1, -1 }, { -1, 0 }, { 0, 1 } }, //
			{ { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 1 } } };

	static int goalX = 0;
	static int goalY = 0;

	// devant, milieu, arrière
	static int[][] myShip = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
	static int[][] enemiesShip = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
	static int[][] myShipMoved = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
	static int mySpeed = 0;
	static int myOrientation = 0;
	static int myLastFire = -1;
	static int currentMoveId = 0;

	static int dangerNum = 0;
	static int[][] dangers = { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 },
			{ 0, 0 }, };

	public static void main(String args[]) {
		InputStream in = System.in;

		// game loop
		while (true) {
			System.out.println(play(in));
		}
	}

	static String play(InputStream is) {
		currentMoveId++;
		Scanner in = new Scanner(is);

		dangerNum = 0;
		long minDist = 10_000;

		StringBuffer buffer = new StringBuffer();

		int myShipCount = in.nextInt();
		append(buffer, myShipCount);
		int entityCount = in.nextInt();
		append(buffer, entityCount);

		for (int i = 0; i < entityCount; i++) {
			int entityId = in.nextInt();
			String entityType = in.next();
			int x = in.nextInt();
			int y = in.nextInt();
			int arg1 = in.nextInt();
			int arg2 = in.nextInt();
			int arg3 = in.nextInt();
			int arg4 = in.nextInt();
			append(buffer, entityId, entityType, x, y, arg1, arg2, arg3, arg4);

			if ("SHIP".equals(entityType)) {
				if (arg4 == 1) {
					myOrientation = arg1;
					mySpeed = arg2;
					myShip[1][0] = x;
					myShip[1][1] = y;
					myShip[0][0] = x + NEIGHBOUR[x % 2][myOrientation][0];
					myShip[0][1] = y + NEIGHBOUR[x % 2][myOrientation][1];
					myShip[2][0] = x - NEIGHBOUR[x % 2][myOrientation][0];
					myShip[2][1] = y - NEIGHBOUR[x % 2][myOrientation][1];
				} else {
					int enemiesOrientation = arg1;
					enemiesShip[1][0] = x;
					enemiesShip[1][1] = y;
					enemiesShip[0][0] = x + NEIGHBOUR[x % 2][enemiesOrientation][0];
					enemiesShip[0][1] = y + NEIGHBOUR[x % 2][enemiesOrientation][1];
					enemiesShip[2][0] = x - NEIGHBOUR[x % 2][enemiesOrientation][0];
					enemiesShip[2][1] = y - NEIGHBOUR[x % 2][enemiesOrientation][1];
				}
			}

			if ("MINE".equals(entityType)) {
				dangers[dangerNum][0] = x;
				dangers[dangerNum][1] = y;
				dangerNum++;
			}

			if ("CANNONBALL".equals(entityType)) {
				dangers[dangerNum][0] = x;
				dangers[dangerNum][1] = y;
				dangerNum++;
			}

			if ("BARREL".equals(entityType)) {
				long dist = distance(myShip[1], new int[] { x, y });
				if (dist < minDist) {
					goalX = x;
					goalY = y;
					minDist = dist;
				}
			}

		}

		if (minDist == 10_000) {
			System.err.println("No more barrel");
			goalX = enemiesShip[1][0];
			goalY = enemiesShip[1][1];
		}

		myShipMoved[2] = myShip[1];
		myShipMoved[1] = myShip[0];
		myShipMoved[0][0] = myShip[0][0] + NEIGHBOUR[myShip[0][0] % 2][myOrientation][0];
		myShipMoved[0][0] = myShip[0][1] + NEIGHBOUR[myShip[0][0] % 2][myOrientation][0];

		boolean isDanger = false;
		for (int i = 0; i < dangerNum; i++) {
			for (int[] shipPos : myShipMoved) {
				if (shipPos[0] == dangers[i][0] && shipPos[1] == dangers[i][1]) {
					isDanger = true;
				}
			}
		}

		System.err.println("Is :" + buffer);
		buffer.setLength(0);
		System.err.println("My ship :" + Arrays.deepToString(myShip));
		System.err.println("My speed :" + mySpeed);
		System.err.println("My orientation :" + myOrientation);
		System.err.println("My dangers :" + Arrays.deepToString(dangers));
		System.err.println("Is danger :" + isDanger);
		System.err.println("myLastFire,currentMoveId :" + myLastFire + "," + currentMoveId);
		System.err.println("\n");

		String res = "MOVE 0 0";

		for (int i = 0; i < myShipCount; i++) {
			if (!isDanger) {
				res = ("MOVE " + goalX + " " + goalY);
			} else {
				int newOrientation = (myOrientation + 1) % 6;
				res = ("MOVE " + (myShip[1][0] + 2 * NEIGHBOUR[myShip[1][0] % 2][newOrientation][0]) + " "
						+ (myShip[1][1] + 2 * NEIGHBOUR[myShip[1][1] % 2][newOrientation][1]));
			}
		}

		return res;
	}

	private static void append(StringBuffer buffer, Object... values) {
		boolean isFirst = true;
		for (Object value : values) {
			if (!isFirst) {
				buffer.append(" ");
			}
			buffer.append(value);
			isFirst = false;
		}
		buffer.append(" ");

	}

	static int distance(int[] a, int[] b) {
		return (Math.abs(a[0] - b[0]) + Math.abs(a[0] + a[1] - b[0] - b[1]) + Math.abs(a[1] - b[1])) / 2;
	}
}
