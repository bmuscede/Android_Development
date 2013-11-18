package bryansworld.com.baccalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class loadingScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warning);
    }
    
    public void disagreePress(View view){
    	//the user has pressed disagree.
    	//closes the program.
    	finish();
    }
    
    public void agreePress(View view){
    	//the user has pressed agree.
    	//starts the main page.
    	startActivity(new Intent(loadingScreen.this,mainScreen.class)); 
    }
}