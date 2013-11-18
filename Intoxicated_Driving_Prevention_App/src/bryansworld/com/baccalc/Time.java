package bryansworld.com.baccalc;

public class Time {
	private int hour;
	private int minute;
	
	public Time(int hour, int minute){
		this.hour = hour;
		this.minute = minute;
	}
	
	public void setHour(int newHour){
		this.hour = newHour;
	}
	public void setMinute(int newMinute){
		this.minute = newMinute;
	}
	
	public int getHour(){
		return hour;
	}
	public int getMinute(){
		return minute;
	}
	
	public String toString(){
		if (minute < 10){
			return hour + ":0" + minute;
		}
		
		return hour + ":" + minute;
	}
	
	public int convertMins(){
		int mins;
		mins = (hour*60) + minute;
		
		return mins;
	}
}
