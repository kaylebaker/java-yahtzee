package classes;

import java.util.Arrays;

public class SmStraight extends Score {

	SmStraight(String description, int scorecardIndex) {
		super(description, scorecardIndex);
	}

	@Override
	public void setValue(int[] dice) {
		Arrays.sort(dice);
		boolean check1 = false;
		boolean check2 = false;
		
		// Check sequence of first four dice
		for (int i=0; i < dice.length - 2; i++) {
			if (dice[i] != dice[i+1] - 1) {
				check1 = false;
			} else {
				check1 = true;
			}
		}
		
		// Check sequence of last four dice
		for (int i=1; i < dice.length - 1; i++) {
			if (dice[i] != dice[i+1] - 1) {
				check2 = false;
			} else {
				check2 = true;
			}
		}
		
		if (check1 || check2) {
			this.value = 30;
		} else {
			this.value = 0;
		}
		
		this.filledIn = true;
	}
}
