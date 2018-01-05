package caribbean;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		new Main().main();
	}

	PipedOutputStream player2RefereeOut = new PipedOutputStream();
	PipedOutputStream referee2Player1Out = new PipedOutputStream();
	PipedOutputStream referee2Player2Out = new PipedOutputStream();

	void main() throws IOException, InterruptedException {
		Player player1 = new Player();
		Player player2 = new Player();

		PipedInputStream player2RefereeIn = new PipedInputStream(player2RefereeOut);

		PipedInputStream referee2Player1In = new PipedInputStream(referee2Player1Out);
		PipedInputStream referee2Player2In = new PipedInputStream(referee2Player2Out);

		player2RefereeOut.write("[[INIT] 1]\n2\n".getBytes());
		player2RefereeOut.flush();

		RefereeThread refereeThread = new RefereeThread(player2RefereeIn);
		refereeThread.start();
		Thread.sleep(500);

		try {

			while (!refereeThread.referee.gameOver()) {
				play(player1, referee2Player1In, referee2Player1Out, refereeThread, 0);
				play(player2, referee2Player2In, referee2Player2Out, refereeThread, 1);
			}
		} catch (IOException e) {
			// how to catch game over
			e.printStackTrace();
		}
		System.out.println("score 0:" + refereeThread.referee.getScore(0));
		System.out.println("score 1:" + refereeThread.referee.getScore(1));
	}

	private void play(Player player, PipedInputStream referee2PlayerIn, PipedOutputStream referee2PlayerOut,
			RefereeThread refereeThread, int playerIdx) throws IOException, InterruptedException {
		player2RefereeOut.write("[[GET_GAME_INFO] 0]\n".getBytes());
		player2RefereeOut.flush();
		Thread.sleep(20);
		for (String s : refereeThread.referee.getInputForPlayer(0, playerIdx)) {
			referee2PlayerOut.write((s + "\n").getBytes());
		}
		String move = player.play(referee2PlayerIn);
		int count = 1 + move.length() - move.replace("\n", "").length();
		player2RefereeOut.write(("[[SET_PLAYER_OUTPUT] " + count + "]\n" + move + "\n").getBytes());
		player2RefereeOut.flush();
	}

}

class RefereeThread extends Thread {
	final InputStream in;
	Referee referee;

	RefereeThread(InputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		try {
			referee = new Referee(in, System.out, System.err);
			referee.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}