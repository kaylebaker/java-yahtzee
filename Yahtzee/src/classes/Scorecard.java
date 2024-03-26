package classes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Scorecard {
	
	private String playerName;
	
	// Define attributes for UPPER SECTION
	public Aces aces = new Aces("ACES\t| Count and add only aces.", 1);
	public Twos twos = new Twos("TWOS\t| Count and add only twos.", 2);
	public Threes threes = new Threes("THREES\t| Count and add only threes.", 3);
	public Fours fours = new Fours("FOURS\t| Count and add only fours.", 4);
	public Fives fives = new Fives("FIVES\t| Count and add only fives.", 5);
	public Sixes sixes = new Sixes("SIXES\t| Count and add only sixes.", 6);

	private int subTotalUpperScore;
	private boolean upperScoreBonus;
	private int totalUpperScore;
	
	
	// Define attributes for LOWER SECTION
	public ThreeOfAKind threeOfAKind = new ThreeOfAKind("THREE OF A KIND | Three of the same dice.", 7);
	public FourOfAKind fourOfAKind = new FourOfAKind("FOUR OF A KIND\t| Four of the same dice.", 8);
	public FullHouse fullHouse = new FullHouse("FULL HOUSE\t| Two of one, three of another.", 9);
	public SmStraight smStraight = new SmStraight("SMALL STRAIGHT\t| Sequence of four dice.", 10);
	public LgStraight lgStraight = new LgStraight("LARGE STRAIGHT\t| Sequence of five dice.", 11);
	public Yahtzee yahtzee = new Yahtzee("YAHTZEE !!!\t| Five of a kind.\t", 12);
	public Chance chance = new Chance("CHANCE\t\t| Score total of all five dice.", 13);

	private int totalLowerScore;
	private int grandTotal;
	
	// Class constructor method
	public Scorecard(String playerName) {
		this.playerName = playerName;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	// Print list of scoring options
	public void getOptions() throws Exception {
		// Use Java Reflection to iterate through class fields
		Class<?> c = this.getClass();
        Field[] fields = c.getFields();
        
        System.out.println("UPPER SECTION");
        System.out.println("---------------");
        // Iterate through each field
        for (Field field : fields) {
        	// Get the value of this field instance
        	Object fieldValue = field.get(this);
        	
        	if (fieldValue != null) {
        		// Get the class of the field's value
        		Class<?> fieldValueClass = fieldValue.getClass();
        		
        		try {
        			// Get the 'printDescription' method and 'filledIn' field from the field's value class
        			Method printDescriptionMethod = fieldValueClass.getMethod("printDescription");
        			Field filledInField = fieldValueClass.getField("filledIn");
        			Field scorecardIndexField = fieldValueClass.getField("scorecardIndex");

        			boolean filledInValue = (boolean) filledInField.get(fieldValue);
        			int scorecardIndexValue = (int) scorecardIndexField.getInt(fieldValue);
        			
        			if (!filledInValue && scorecardIndexValue < 6) {
        				printDescriptionMethod.invoke(fieldValue);
        			}
        			
        			if (!filledInValue && scorecardIndexValue == 6) {
        				printDescriptionMethod.invoke(fieldValue);
        				System.out.println("\nLOWER SECTION");
        				System.out.println("---------------");
        			}
        			
        			if (!filledInValue && scorecardIndexValue > 6) {
        				printDescriptionMethod.invoke(fieldValue);
        			}
        			
        		} catch (NoSuchMethodException e) {
        			System.out.println("Method printDescription not found in " + fieldValueClass.getName());
        		} catch (NoSuchFieldException e) {
        			System.out.println("Field filledIn not found in " + fieldValueClass.getName());
        		}
        	}
        }
	}
	
	// Method that uses the scorecardIndex to call the setValue method of a class instance
	public void setScore(int index, int[] array) throws Exception {
		// Use Java Reflection to iterate through class fields
		Class<?> c = this.getClass();
		Field[] fields = c.getFields();
		
        // Iterate through each field
        for (Field field : fields) {
        	// Get the value of this field instance
        	Object fieldValue = field.get(this);
        	
        	if (fieldValue != null) {
        		// Get the class of the field's value
        		Class<?> fieldValueClass = fieldValue.getClass();
        		
        		try {
        			Method setValueMethod = fieldValueClass.getMethod("setValue", int[].class);
        			Field scoreboardIndexField = fieldValueClass.getField("scorecardIndex");
        			int scoreboardIndexValue = (int) scoreboardIndexField.get(fieldValue);
        			
        			if (scoreboardIndexValue == index) {
        				setValueMethod.invoke(fieldValue, array);
        			}
        			
        		} catch (NoSuchFieldException e) {
        			System.out.println("Field scorecardIndex not found in " + fieldValueClass.getName());
        		}
        	}
        }
	}
	
	public void printScorecard() throws Exception {
		// Use Java Reflection to iterate through class fields
		Class<?> c = this.getClass();
        Field[] fields = c.getFields();
        
        System.out.println("### UPPER SECTION ###\t\t\t\t\tSCORE");
        System.out.println("-----------------------------------------------------------------");
        
        // Iterate through each field
        for (Field field : fields) {
        	// Get the value of this field instance
        	Object fieldValue = field.get(this);
        	
        	if (fieldValue != null) {
        		// Get the class of the field's value
        		Class<?> fieldValueClass = fieldValue.getClass();
        		
        		try {
        			Method getValueMethod = fieldValueClass.getMethod("getValue");
        			Field descriptionField = fieldValueClass.getField("description");
        			Field scoreboardIndexField = fieldValueClass.getField("scorecardIndex");
        			
        			int scoreboardIndexValue = (int) scoreboardIndexField.get(fieldValue);
        			int score = (int) getValueMethod.invoke(fieldValue);
        			String desc = (String) descriptionField.get(fieldValue);
        			
        			if (scoreboardIndexValue < 6) {
        				System.out.println(" " + desc + "\t\t\t  " + score + " ");
        				System.out.println("-----------------------------------------------------------------");        				
        			}
        			
        			if (scoreboardIndexValue == 6) {
        				System.out.println(" " + desc + "\t\t\t  " + score + " ");
        				System.out.println("\n### LOWER SECTION ###\t\t\t\t\tSCORE");
        		        System.out.println("-----------------------------------------------------------------");        				
        			}
        			
        			if (scoreboardIndexValue > 6) {
        				System.out.println(" " + desc + "\t\t  " + score + " ");
        				System.out.println("-----------------------------------------------------------------");        				
        			}
        			
        			
        		} catch (NoSuchMethodException e) {
        			System.out.println("Method getValue not found in " + fieldValueClass.getName());
        		} catch (NoSuchFieldException e) {
        			System.out.println("Field description not found in " + fieldValueClass.getName());
        		}
        	}
        }
        System.out.println("-----------------------------------------------------------------");
	}
        
	// Define setter methods
	public void setSubtotalUpper() {
		this.subTotalUpperScore = this.sumUpperSection();
		if (subTotalUpperScore > 63) {
			this.upperScoreBonus = true;
		}
	}
	
	public void setTotalUpper() {
		if (upperScoreBonus) {
			this.totalUpperScore = this.subTotalUpperScore + 35;
		} else {
			this.totalUpperScore = this.subTotalUpperScore;
		}
	}
	
	public void setTotalLower() {
		this.totalLowerScore = this.sumLowerSection();
	}
	
	public void setGrandTotal() {
		this.grandTotal = this.totalUpperScore + this.totalLowerScore;
	}
	
	
	// Define getter methods
	public int getSubtotalUpper() {
		return this.subTotalUpperScore;
	}
	
	public boolean getUpperBonus() {
		return this.upperScoreBonus;
	}
	
	public int getTotalUpper() {
		return this.totalUpperScore;
	}
	
	public int getTotalLower() {
		return this.totalLowerScore;
	}
	
	public int getGrandTotal() {
		return this.grandTotal;
	}
	
	
	// Define other methods
	private int sumUpperSection() {
		int sum = aces.value + twos.value + threes.value + fours.value + fives.value + sixes.value;
		return sum;
	}
	
	private int sumLowerSection() {
		int sum = threeOfAKind.value + fourOfAKind.value + fullHouse.value + smStraight.value + lgStraight.value + yahtzee.value + chance.value;
		return sum;
	}
}
