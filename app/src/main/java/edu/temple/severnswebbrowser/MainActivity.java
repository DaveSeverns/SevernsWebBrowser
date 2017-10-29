package edu.temple.severnswebbrowser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    int currentIndex, lastIndex;
    EditText urlSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.web_pager);

    }



    //inflate and draw menu resource
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.browser_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public class BrowserAdapter extends FragmentStatePagerAdapter{
        /*to keep track of the urls for each new fragment and to
         pass string url to new browser fragment*/
        ArrayList urlsList = new ArrayList();

        //construct a new adapter using support fragment manager
        public BrowserAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            //create new instance of the fragment at the position specified with the url specified
            return BrowserFragment.newInstance(position, urlsList.get(position).toString());
        }

        @Override
        public int getCount() {
            return lastIndex;
        }
    }

}
