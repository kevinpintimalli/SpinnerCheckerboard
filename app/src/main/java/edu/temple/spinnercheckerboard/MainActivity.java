package edu.temple.spinnercheckerboard;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements Checkerboard.OnFragmentInteractionListener, spinner_fragment.OnFragmentInteractionListener{

    boolean twoPanes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoPanes = (findViewById(R.id.checkfragment) != null);;


        Fragment spin = new spinner_fragment();

        loadFragment(R.id.spinfragment, spin, false);

        if(twoPanes) {
            Checkerboard board = new Checkerboard();
            Bundle bundle = new Bundle();
            bundle.putInt(Checkerboard.dataKey, 0);
            board.setArguments(bundle);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onButtonPressed(int colValue) {
        Checkerboard board = new Checkerboard();

        Bundle bundle = new Bundle();
        bundle.putInt(Checkerboard.dataKey,colValue);
        board.setArguments(bundle);

        loadFragment(twoPanes ? R.id.checkfragment : R.id.spinfragment,board,!twoPanes);
    }


    public void loadFragment(int paneId,Fragment fragment,boolean placeOnBackStack){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(paneId,fragment);

        if(placeOnBackStack){
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }
}
