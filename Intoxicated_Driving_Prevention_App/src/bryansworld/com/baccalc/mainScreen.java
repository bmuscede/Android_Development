package bryansworld.com.baccalc;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;


public class mainScreen extends TabActivity{
	public static DrinkObject drinkList = new DrinkObject();
	public static PersonalInfo userInfo = new PersonalInfo();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, personalActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("personalinfo").setIndicator("Personal Information",
                          res.getDrawable(R.drawable.ic_tab_calculator))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, drinkActivity.class);
        spec = tabHost.newTabSpec("drinks").setIndicator("Drink Manager",
                          res.getDrawable(R.drawable.ic_tab_drink))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, infoActivity.class);
        spec = tabHost.newTabSpec("baccalc").setIndicator("Calculate BAC",
                          res.getDrawable(R.drawable.ic_tab_info))
                      .setContent(intent);
        tabHost.addTab(spec);

        //sees if the user has added a drink.
        Bundle extras = getIntent().getExtras();
        if (extras != null){
        	//they did!
        	String name = extras.getString("name");
        	double volume = extras.getDouble("volume");
        	double percentage = extras.getDouble("percentage");
        	Time selectedTime = new Time(extras.getInt("hour"),extras.getInt("minute"));
        	
        	//adds in the new drink.
        	drinkList.addDrink(name, volume, percentage, selectedTime);
        	
        	tabHost.setCurrentTab(extras.getInt("maintab"));
        } else {
        	tabHost.setCurrentTab(0);
        }
    }
}
