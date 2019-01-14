package application;

public class Fish {
	
	//fields to hold values for fish
	private double weight;
	private int length;
	private String Species;
	private int fishNum;	
	
	//getter and setters for fish fields
	public int getFishNum() {
		return fishNum;
	}

	public void setFishNum(int fishNum) {
		this.fishNum = fishNum;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSpecies() {
		return Species;
	}

	public void setSpecies(String species) {
		this.Species = species;
	}

	//constructor for fish, requires all properties to be created
	public Fish(int number, double weight, int length, String species) {
		this.fishNum = number;
		this.weight = weight;
		this.length = length;
		this.Species = species;
	}
}
