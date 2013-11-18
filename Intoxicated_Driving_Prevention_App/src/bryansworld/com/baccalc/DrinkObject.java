package bryansworld.com.baccalc;

import java.text.DecimalFormat;

public class DrinkObject {
	private int numberDrinks;
	private String[] drinkName;
	private double[] alchVolume;
	private double[] alchPercentage;
	private Time[] timeConsumed;

	public DrinkObject(){
		numberDrinks = 0;
		drinkName = new String[20];
		alchVolume = new double[20];
		alchPercentage = new double[20];
		timeConsumed = new Time[20];
	}
	
	public void addDrink(String name, double volume, double percentage, Time timeConsumed){
		drinkName[numberDrinks] = name;
		alchVolume[numberDrinks] = volume;
		alchPercentage[numberDrinks] = percentage;
		this.timeConsumed[numberDrinks] = timeConsumed;
		
		numberDrinks++;
	}
	
	public void removeDrink(int index){
		for (int i = (index + 1); i < numberDrinks; i++){
			drinkName[i-1] = drinkName[i];
			alchVolume[i-1] = alchVolume[i];
			alchPercentage[i-1] = alchPercentage[i];
			timeConsumed[i-1] = timeConsumed[i];
		}
		
		numberDrinks--;
	}
	
	public double getBAC(String genderName, int weight, Time calculateTime){
		double volumeDistribution = 0;
		double totalBAC = 0;
		
		//first, sets up the variable for volume of distribution.
		if (genderName.equals("Male")){
			volumeDistribution = 0.73;
		} else {
			volumeDistribution = 0.66;
		}
		
		//now we need to loop through each of the drinks.
		for (int i = 0; i < numberDrinks; i++){
			totalBAC = totalBAC + individualBAC(i, volumeDistribution, weight, calculateTime);
		}
		
		//rounds and returns
		totalBAC = roundDecimals(totalBAC);
		return totalBAC;
	}
	
	public double individualBAC(int index, double volumeDistribution, int weight, Time calculateTime){
		//first gets all the variables required.
		double totalAlcohol = alchVolume[index] * (alchPercentage[index] / 100);
		double timeDiff = (calculateTime.convertMins() - timeConsumed[index].convertMins())/60;
		final double CONVERSION_FACTOR = 5.14;
		final double ELIMINATION_RATE = 0.015;
		double BAC = 0;
		
		//gets the BAC.
		BAC = ((CONVERSION_FACTOR * totalAlcohol) / (weight * volumeDistribution)) 
				- (ELIMINATION_RATE * timeDiff);
		
		//sees if the alcohol is out of the person's system.
		if (BAC < 0){
			BAC = 0;
		}
		
		return BAC;
	}
	
	public String[] drinksString(){
		String[] output = new String[numberDrinks];
		
		for (int i = 0; i < numberDrinks; i++){
			output[i] = drinkName[i] + " - " + alchVolume[i] + "fl oz (" + alchPercentage[i] + "%) at " 
					+ timeConsumed[i].toString();
		}
		
		return output;
	}
	
    private double roundDecimals(double d) {
        DecimalFormat fourDForm = new DecimalFormat("#.####");
        return Double.valueOf(fourDForm.format(d));
    }
}