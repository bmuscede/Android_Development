package bryansworld.com.baccalc;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class addDrinkActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
    }
    
    //sends back the data that the user added to the previous page.
    public void addPress(View view){
    	//gets the name of the drink.
    	EditText currentData = (EditText) findViewById(R.id.txtName);
    	String name = currentData.getText().toString();
    	
    	//now looks to see the units of the drink.
    	Spinner unitDrink = (Spinner) findViewById(R.id.spnVolume);
    	String units = unitDrink.getSelectedItem().toString();
    	
    	//gets the volume of the drink.
    	currentData = (EditText) findViewById(R.id.txtVolume);
    	double volume = Double.parseDouble(currentData.getText().toString());
    	if (units.equals("mL")){
    		//we need to convert.
    		final double CONVERSION = 29.5735296875;
    		volume = volume / CONVERSION;
    		volume = roundTwoDecimals(volume);
    	}
    	
    	//gets the percentage of the drink.
    	currentData = (EditText) findViewById(R.id.txtPercentage);
    	double percentage = Double.parseDouble(currentData.getText().toString());
    	
    	//gets the time entered
    	TimePicker selectedTime = (TimePicker) findViewById(R.id.tmrTime);
    	int hour = selectedTime.getCurrentHour();
    	int minute = selectedTime.getCurrentMinute();
    	
    	//tells the program to go to the second tab.
    	final int TAB_MASTER = 1;
    	
    	//finally, moves everything back to the previous class.
		Intent myIntent = new Intent(addDrinkActivity.this, mainScreen.class);
		
		Bundle dataBundle = new Bundle();
		dataBundle.putString("name", name);
		dataBundle.putDouble("volume", volume);
		dataBundle.putDouble("percentage", percentage);
		dataBundle.putInt("hour", hour);
		dataBundle.putInt("minute", minute);
		dataBundle.putInt("maintab", TAB_MASTER);
		
		myIntent.putExtras(dataBundle);
		
		addDrinkActivity.this.startActivity(myIntent);
    }
    
    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
}