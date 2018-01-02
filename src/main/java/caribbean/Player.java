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

	static int[][] goal = { { 0, 0 }, { 0, 0 }, { 0, 0 } };

	// bateau id -> devant, milieu, arrière -> {x,y}
	static int[][][] myShip = { { { 0, 0 }, { 0, 0 }, { 0, 0 } }, { { 0, 0 }, { 0, 0 }, { 0, 0 } },
			{ { 0, 0 }, { 0, 0 }, { 0, 0 } } };
	static int[][][] enemiesShip = { { { 0, 0 }, { 0, 0 }, { 0, 0 } }, { { 0, 0 }, { 0, 0 }, { 0, 0 } },
			{ { 0, 0 }, { 0, 0 }, { 0, 0 } } };
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
		StringBuffer buffer = new StringBuffer();
		try {
			currentMoveId++;
			Scanner in = new Scanner(is);

			dangerNum = 0;
			long minDist = 10_000;

			int myShipCount = in.nextInt();
			append(buffer, myShipCount);
			int entityCount = in.nextInt();
			append(buffer, entityCount);

			int myShipId = 0;
			int enemiesShipId = 0;
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
						myShip[myShipId][1][0] = x;
						myShip[myShipId][1][1] = y;
						myShip[myShipId][0][0] = x + NEIGHBOUR[x % 2][myOrientation][0];
						myShip[myShipId][0][1] = y + NEIGHBOUR[x % 2][myOrientation][1];
						myShip[myShipId][2][0] = x - NEIGHBOUR[x % 2][myOrientation][0];
						myShip[myShipId][2][1] = y - NEIGHBOUR[x % 2][myOrientation][1];
						myShipId++;
					} else {
						int enemiesOrientation = arg1;
						enemiesShip[enemiesShipId][1][0] = x;
						enemiesShip[enemiesShipId][1][1] = y;
						enemiesShip[enemiesShipId][0][0] = x + NEIGHBOUR[x % 2][enemiesOrientation][0];
						enemiesShip[enemiesShipId][0][1] = y + NEIGHBOUR[x % 2][enemiesOrientation][1];
						enemiesShip[enemiesShipId][2][0] = x - NEIGHBOUR[x % 2][enemiesOrientation][0];
						enemiesShip[enemiesShipId][2][1] = y - NEIGHBOUR[x % 2][enemiesOrientation][1];
						enemiesShipId++;
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
					for (int shipId = 0; shipId < myShipCount; shipId++) {
						int[] xy = new int[] { x, y };
						long dist = distance(myShip[shipId][1], xy);
						if (dist < minDist) {
							goal[shipId] = xy;
							minDist = dist;
						}
					}
				}

			}

			if (minDist == 10_000) {
				for (int shipId = 0; shipId < myShipCount; shipId++) {
					goal[shipId] = enemiesShip[shipId][1];
				}
			}

			String res = "";

			for (int shipId = 0; shipId < myShipCount; shipId++) {
				final String shipRes;

				myShipMoved[2] = myShip[shipId][1];
				myShipMoved[1] = myShip[shipId][0];
				myShipMoved[0][0] = myShip[shipId][0][0] + NEIGHBOUR[myShip[shipId][0][0] % 2][myOrientation][0];
				myShipMoved[0][0] = myShip[shipId][0][1] + NEIGHBOUR[myShip[shipId][0][0] % 2][myOrientation][0];

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
				System.err.println("Enemies ships :" + Arrays.deepToString(enemiesShip));
				System.err.println("My ships :" + Arrays.deepToString(myShip));
				System.err.println("My speed :" + mySpeed);
				System.err.println("My orientation :" + myOrientation);
				System.err.println("My dangers :" + Arrays.deepToString(dangers));
				System.err.println("Is danger :" + isDanger);
				System.err.println("myLastFire, currentMoveId :" + myLastFire + ", " + currentMoveId);
				System.err.println("\n");

				if (!isDanger) {
					if (myLastFire + 2 < currentMoveId) {
						if (distance(myShip[shipId][1], enemiesShip[0][1]) <= 4) {
							myLastFire = currentMoveId;
							shipRes = "FIRE " + enemiesShip[0][1][0] + " " + enemiesShip[0][1][1];
						} else {
							shipRes = ("MOVE " + goal[shipId][0] + " " + goal[shipId][1]);
						}
					} else {
						shipRes = ("MOVE " + goal[shipId][0] + " " + goal[shipId][1]);
					}
				} else {
					int newOrientation = (myOrientation + 1) % 6;
					shipRes = ("MOVE "
							+ (myShip[shipId][1][0] + 2 * NEIGHBOUR[myShip[shipId][1][0] % 2][newOrientation][0]) + " "
							+ (myShip[shipId][1][1] + 2 * NEIGHBOUR[myShip[shipId][1][1] % 2][newOrientation][1]));
				}
				res += shipRes + (shipId == myShipCount - 1 ? "" : "\n");
			}

			return res;
		} catch (Exception e) {
			System.err.println("** Exception **");
			System.err.println("Is :" + buffer);
			buffer.setLength(0);
			System.err.println(e.getMessage());
			return "MOVE 10 10";
		}
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
