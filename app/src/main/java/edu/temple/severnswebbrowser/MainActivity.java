package edu.temple.severnswebbrowser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //member variable to keep reference through out activity and inner class
    ViewPager viewPager;
    int currentIndex, lastIndex;

    BrowserAdapter browserAdapter;
    FragmentManager fm;
    ArrayList<BrowserFragment> browserFragmentsList;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();


        browserFragmentsList = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.web_pager);

        browserAdapter = new BrowserAdapter(fm);
        viewPager.setAdapter(browserAdapter);


        currentIndex=0;
        //viewPager.setCurrentItem(currentIndex);

    }



    //inflate and draw menu resource
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.browser_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * defines the behavior of each interaction of the menu buttons
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //set the index to current fragment in pager so if you swiped to a diff one you have a r
        //reference to where you are in the list
        currentIndex = viewPager.getCurrentItem();
        switch (item.getItemId()){
            case R.id.new_tab_button:{
                //adds a browser fragment to the view pager
                BrowserFragment browserFragment = new BrowserFragment();
                browserFragmentsList.add(browserFragment);
                //update the pager adapter to load new fragment
                browserAdapter.notifyDataSetChanged();
                //set current position to end of list
                currentIndex = browserFragmentsList.size() -1;
                viewPager.setCurrentItem(currentIndex);
                return true;
            }
            case R.id.previous_tab_button:{
                if(currentIndex >0){
                    viewPager.setCurrentItem(--currentIndex);
                }

                return true;
            }
            case R.id.next_tab_button:{
                if(currentIndex < browserFragmentsList.size()-1){
                    viewPager.setCurrentItem(++currentIndex);
                }
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public class BrowserAdapter extends FragmentStatePagerAdapter{
        /*to keep track of the urls for each new fragment and to
         pass string url to new browser fragment*
        ArrayList urlsList = new ArrayList();
        TODO went a differnt way, made the urls local to each browser fragment*/

        //construct a new adapter using support fragment manager
        public BrowserAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            //create new instance of the fragment at the position specified with the url specified
            return browserFragmentsList.get(position);
        }

        @Override
        public int getCount() {
            return browserFragmentsList.size();
        }

        /**public String getUrlAtPositionAsString(int posisiton){
            return urlsList.get(posisiton).toString();

        }

        public void setUrlPositionInList(int positionInList, String url){
            urlsList.set(positionInList, url);
        }

        public void addUrlToList(String url){
            urlsList.add(url);
        }**/
    }

}
