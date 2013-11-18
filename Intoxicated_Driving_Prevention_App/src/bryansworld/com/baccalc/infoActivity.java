package bryansworld.com.baccalc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

public class infoActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
    }
    
    //method that executes on press of calculate button.
    public void calculatePress(View view){
    	CheckBox warningOne = (CheckBox) findViewById(R.id.chkWarning1);
    	CheckBox warningTwo = (CheckBox) findViewById(R.id.chkWarning2);
    	
    	//now determines if they are checked or not.
    	if (warningOne.isChecked() && warningTwo.isChecked()){
    		calculateBAC();
    	} else {
    		//warning messages were disregarded.
    		Context context = getApplicationContext();
    		CharSequence text = "You cannot continue without accepting the following warnings!";
    		int duration = Toast.LENGTH_SHORT;

    		Toast toastAlert = Toast.makeText(context, text, duration);
    		toastAlert.show();
    	}
    }
    
    //method responsible for calculating bac and going to the results page.
    public void calculateBAC(){
    	double bacLevel;
    	
    	//gets the time the user wants to determine their bac.
    	TimePicker timeCalc = (TimePicker) findViewById(R.id.tmrCalculateTime);
    	Time calculateTime = new Time(timeCalc.getCurrentHour(), timeCalc.getCurrentMinute());
    	
    	//sees what units the user selected for weight
    	int weight = mainScreen.userInfo.weight;
    	if (mainScreen.userInfo.weightUnitName.equals("kg")){
    		//need to convert to lbs.
    		final double CONVERSION = 2.20462262;
    		weight = (int)(weight * CONVERSION);
    	}
    	
    	//now gets the bac.
    	bacLevel = mainScreen.drinkList.getBAC(mainScreen.userInfo.genderName, weight, 
    			calculateTime);
    	
    	Intent myIntent = new Intent(infoActivity.this, resultsPage.class);
		
		Bundle dataBundle = new Bundle();
		dataBundle.putDouble("bac", bacLevel);
		dataBundle.putString("stateProv", mainScreen.userInfo.stateProvName);
		myIntent.putExtras(dataBundle);
		
		infoActivity.this.startActivity(myIntent);
    }
}