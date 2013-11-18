package bryansworld.com.baccalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.text.Editable;
import android.text.TextWatcher;

public class personalActivity extends Activity implements TextWatcher {
    boolean blnChanged = false;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);
        
        //sets up listeners for certain functions (including saving the user's info).
        Spinner countrySpinner = (Spinner) findViewById(R.id.spnCountry);
        countrySpinner.setOnItemSelectedListener(new countrySpinnerListener());
        
        Spinner genderSpinner = (Spinner) findViewById(R.id.spnGender);
        genderSpinner.setOnItemSelectedListener(new genderSpinnerListener());
        
        Spinner weightSpinner = (Spinner) findViewById(R.id.spnWeight);
        weightSpinner.setOnItemSelectedListener(new weightSpinnerListener());
        
        EditText weightText = (EditText) findViewById(R.id.txtWeight);
        weightText.addTextChangedListener(this);
        
		Spinner stateProvSpinner = (Spinner) findViewById(R.id.spnStateProvince);
		
        //finally, sets up the previous positions
        countrySpinner.setSelection(mainScreen.userInfo.country);
        ArrayAdapter<CharSequence> adapter;
        if (countrySpinner.getSelectedItemPosition() == 0){
        	//canada
        	adapter = ArrayAdapter.createFromResource(
		            personalActivity.this, R.array.canada_elements, android.R.layout.simple_spinner_item);
        } else {
        	//usa
		    adapter = ArrayAdapter.createFromResource(
		    		personalActivity.this, R.array.usa_elements, android.R.layout.simple_spinner_item);
        }
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		stateProvSpinner.setAdapter(adapter);
		
        genderSpinner.setSelection(mainScreen.userInfo.gender);

        stateProvSpinner.setSelection(mainScreen.userInfo.stateProv);
        stateProvSpinner.setOnItemSelectedListener(new stateProvSpinnerListener());
       
        weightText.setText(String.valueOf(mainScreen.userInfo.weight));
        weightSpinner.setSelection(mainScreen.userInfo.weightUnit);
    }
    
    public void afterTextChanged(Editable s) {
    	EditText calledEditText = (EditText) findViewById(R.id.txtWeight);
    	if ((calledEditText.getText().toString().equals(""))){
    		mainScreen.userInfo.weight = 0;
    	} else {
    		mainScreen.userInfo.weight = Integer.parseInt(calledEditText.getText().toString());
    	}
    }

    public class weightSpinnerListener implements OnItemSelectedListener {
    	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	    	Spinner calledSpinner = (Spinner) findViewById(R.id.spnWeight);
			mainScreen.userInfo.weightUnit = calledSpinner.getSelectedItemPosition();
			mainScreen.userInfo.weightUnitName = (String) calledSpinner.getSelectedItem();
	    }
	
	    public void onNothingSelected(AdapterView<?> parent) {
	    	return;
	    }
    }
    
    public class genderSpinnerListener implements OnItemSelectedListener {
    	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	    	Spinner calledSpinner = (Spinner) findViewById(R.id.spnGender);
			mainScreen.userInfo.gender = calledSpinner.getSelectedItemPosition();
			mainScreen.userInfo.genderName = (String) calledSpinner.getSelectedItem();
	    }
	
	    public void onNothingSelected(AdapterView<?> parent) {
	    	return;
	    }
    }
    
    public class stateProvSpinnerListener implements OnItemSelectedListener {
    	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	    	Spinner calledSpinner = (Spinner) findViewById(R.id.spnStateProvince);
			mainScreen.userInfo.stateProv = calledSpinner.getSelectedItemPosition();
			mainScreen.userInfo.stateProvName = (String)calledSpinner.getSelectedItem();
	    }
	
	    public void onNothingSelected(AdapterView<?> parent) {
	    	return;
	    }
    }
    
    public class countrySpinnerListener implements OnItemSelectedListener {
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	    	Spinner calledSpinner = (Spinner) findViewById(R.id.spnCountry);
	    	String selectedCountry = calledSpinner.getSelectedItem().toString();
			ArrayAdapter<CharSequence> adapter;
			 
			if (selectedCountry.equals("Canada")){
			    adapter = ArrayAdapter.createFromResource(
			            personalActivity.this, R.array.canada_elements, android.R.layout.simple_spinner_item);
			} else {
			    adapter = ArrayAdapter.createFromResource(
			    		personalActivity.this, R.array.usa_elements, android.R.layout.simple_spinner_item);
			}
			 
			Spinner stateProvSpinner = (Spinner) findViewById(R.id.spnStateProvince);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			stateProvSpinner.setAdapter(adapter);
			
			//gets the selected province/state from previous country.
			if (blnChanged == false){
				stateProvSpinner.setSelection(mainScreen.userInfo.stateProv);
				blnChanged = true;
			}
			
			//saves the selected country
			mainScreen.userInfo.country = calledSpinner.getSelectedItemPosition();
	    }
	
	    public void onNothingSelected(AdapterView<?> parent) {
	    	return;
	    }
    }

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}
}