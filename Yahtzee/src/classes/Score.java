package classes;

public abstract class Score {
	
	public int scorecardIndex;
	public String description;
	int value;
	public boolean filledIn = false;
	
	Score(String description, int scorecardIndex) {
		this.description = description;
		this.scorecardIndex = scorecardIndex;
	}
	
	public void printDescription() {
		System.out.println(this.scorecardIndex + ".\t" + this.description);
	}

	public abstract void setValue(int[] dice);
	
	public int getIndex() {
		return this.scorecardIndex;
	}
	
	public int getValue() {
		return this.value;
	}
}
