package bryansworld.com.baccalc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class resultsPage extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        
        //gets the BAC passed to it.
        Bundle extras = getIntent().getExtras();
        
        double bacLevel = extras.getDouble("bac");
        
        //changes the bac on the main screen.
        TextView lblBAC = (TextView) findViewById(R.id.lblBACResult);
        lblBAC.setText(String.valueOf(bacLevel));
        
        //now finds the correct province/state and displays the information on driving.
        String stateProv = extras.getString("stateProv");
        provinceStateMessage(stateProv, bacLevel);
        
        //finally, gets the effects of alcohol at certain blood alcohol levels.
        getBloodAlcoholEffects(bacLevel);
    }
    
    public void provinceStateMessage(String stateProv, double bacLevel){
    	String message = "In the";
    	if (stateProv.equals("British Columbia") || stateProv.equals("Ontario") || 
    			stateProv.equals("Newfoundland and Labrador") || stateProv.equals("Nova Scotia") ||
    			stateProv.equals("New Brunswick")){
    		//provincial offense and federal offence.
    		double provincialOff = 0.05;
    		double federalOff = 0.08;
    		
    		message = message + " province of " + stateProv
    				+ " it is a provincial offence to operate a motor vehicle with a blood alcohol level above "
    				+ provincialOff + " and a federal offence to do so with a blood alcohol level above "
    				+ federalOff + ".";
    		TextView lblDriving = (TextView) findViewById(R.id.lblDrivingResults);
    		lblDriving.setText(message);
    		
    		//now gives a visual alert.
    		if (bacLevel >= federalOff){
    			//very bad.
    			lblDriving.setTextColor(0xffff0000);
    		} else if ((bacLevel >= provincialOff) && (bacLevel < federalOff)){
    			//bad.
    			lblDriving.setTextColor(0xffffff00);
    		}
    	} else if (stateProv.equals("Alberta") || stateProv.equals("Manitoba") || stateProv.equals("Northwest Territories") || 
    			stateProv.equals("Nunavut") || stateProv.equals("Prince Edward Island") || stateProv.equals("Quebec") || 
    			stateProv.equals("Saskatchewan") || stateProv.equals("Yukon")){
    		//federaloffence.
    		double federalOff = 0.08;
    		
    		message = message + " province of " + stateProv
    				+ " it is a federal offence to operate a motor vehicle with a blood alcohol level above "
    				+ federalOff + ".";
    		TextView lblDriving = (TextView) findViewById(R.id.lblDrivingResults);
    		lblDriving.setText(message);
    		
    		//now gives a visual alert.
    		if (bacLevel >= federalOff){
    			//very bad.
    			lblDriving.setTextColor(0xffff0000);
    		}
    	} else {
    		//federaloffence.
    		double federalOff = 0.08;
    		
    		message = message + " state of " + stateProv
    				+ " it is a federal offence to operate a motor vehicle with a blood alcohol level above "
    				+ federalOff + ".";
    		TextView lblDriving = (TextView) findViewById(R.id.lblDrivingResults);
    		lblDriving.setText(message);
    		
    		//now gives a visual alert.
    		if (bacLevel >= federalOff){
    			//very bad.
    			lblDriving.setTextColor(0xffff0000);
    		}
    	}
    }
    
    private void getBloodAlcoholEffects(double bacLevel){
    	//now determines the blood alcohol level.
    	TextView lblDriving = (TextView) findViewById(R.id.lblAlcoholResultsDes);
    	TextView lblDrivingTitle = (TextView) findViewById(R.id.lblAlcoholResults);
    	
    	if (bacLevel > 0.30){
    		lblDrivingTitle.setText((String) getString(R.string.bac_0_30toOnwardstitle));
    		lblDriving.setText((String) getString(R.string.bac_0_30toOnwards));
    	} else if (bacLevel > 0.20){
    		lblDrivingTitle.setText((String) getString(R.string.bac_0_20to0_30title));
    		lblDriving.setText((String) getString(R.string.bac_0_20to0_30));
    	} else if (bacLevel > 0.19){
    		lblDrivingTitle.setText((String) getString(R.string.bac_0_19to0_20title));
    		lblDriving.setText((String) getString(R.string.bac_0_19to0_20));
    	} else if (bacLevel > 0.15){
    		lblDrivingTitle.setText((String) getString(R.string.bac_0_15to0_19title));
    		lblDriving.setText((String) getString(R.string.bac_0_15to0_19));
    	} else if (bacLevel > 0.125){
    		lblDrivingTitle.setText((String) getString(R.string.bac_0_125to0_15title));
    		lblDriving.setText((String) getString(R.string.bac_0_125to0_15));
    	} else if (bacLevel > 0.09){
    		lblDrivingTitle.setText((String) getString(R.string.bac_0_09to0_125title));
    		lblDriving.setText((String) getString(R.string.bac_0_09to0_125));
    	} else if (bacLevel > 0.06){
    		lblDrivingTitle.setText((String) getString(R.string.bac_0_06to0_09title));
    		lblDriving.setText((String) getString(R.string.bac_0_06to0_09));
    	} else if (bacLevel > 0.03){
    		lblDrivingTitle.setText((String) getString(R.string.bac_0_03to0_06title));
    		lblDriving.setText((String) getString(R.string.bac_0_03to0_06));
    	} else if (bacLevel > 0.01){
    		lblDrivingTitle.setText((String) getString(R.string.bac_0_01to0_03title));
    		lblDriving.setText((String) getString(R.string.bac_0_01to0_03));
    	} else {
    		lblDrivingTitle.setText((String) getString(R.string.bac_0to0_01title));
    		lblDriving.setText((String) getString(R.string.bac_0to0_01));
    	}
    }
}