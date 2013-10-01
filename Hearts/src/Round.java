import java.util.ArrayList;

//holds the points for a round for use at the end of the game
public class Round {
	// stores the points
	private ArrayList<Integer> points; // 0 --> player 1 score etc.

	// initializes the class
	public Round() {
		points = new ArrayList<Integer>();
	}

	// adds a point
	public void addPoints(int point) {
		points.add((Integer) point);
	}

	// returns the points
	public ArrayList<Integer> getPoints() {
		return points;
	}

	// sets a certain point value already initialized
	public void setPoints(int c, int change) {
		points.set(c, ((Integer) change));
	}

	// gets a single point value at a given index
	public int getPoint(int c) {
		return points.get(c);
	}
}
