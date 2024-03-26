package classes;

import java.util.Arrays;

public class FullHouse extends Score {

	FullHouse(String description, int scorecardIndex) {
		super(description, scorecardIndex);
	}
	
	@Override
	public void setValue(int[] dice) {
		Arrays.sort(dice);
		if (dice[0] == dice[1] && dice[3] == dice[4] && (dice[2] == dice[0] || dice[2] == dice[4]) && dice[0] != dice[4]) {
			this.value = 25;
		} else {
			this.value = 0;
		}
		this.filledIn = true;
	}
}