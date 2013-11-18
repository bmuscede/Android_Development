package bryansworld.com.baccalc;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class drinkActivity extends ListActivity {
	private int positionItem; //the item that the user long pressed
	
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  //creates a menu to add a drink.
	  String[] drinks = mainScreen.drinkList.drinksString();
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.drink, drinks));
	  
	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);
	  
	  registerForContextMenu(lv);
	  
	  lv.setOnItemClickListener(new OnItemClickListener() {
		  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			  //when the item is clicked here, bring up the edit drink menu.
			}
		  });
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add(1,1,1,"Add New Drink...").setIcon(R.drawable.ic_menu_add);
     	return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
		switch(item.getItemId())
		{
			case 1:
				Intent myIntent = new Intent(drinkActivity.this, addDrinkActivity.class);
				drinkActivity.this.startActivity(myIntent);
				return true;
     }
     return super.onOptionsItemSelected(item);

    }
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);

	    // Get the info on which item was selected
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

	    // Get the Adapter behind ListView
	    Adapter adapter = getListAdapter();

	    // Retrieve the item that was clicked on
	    positionItem = info.position;
	    
	    // Creates the menu.
	    menu.setHeaderTitle("Drink Manager");
	    menu.add(0, 0, 0, "Remove Drink");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	    //sees if remove was pressed.
		if (item.getItemId() == 0){
			mainScreen.drinkList.removeDrink(positionItem);
	    
			//updates the list.
			String[] drinks = mainScreen.drinkList.drinksString();
			setListAdapter(new ArrayAdapter<String>(this, R.layout.drink, drinks));
		}
		return true;
	}
 }