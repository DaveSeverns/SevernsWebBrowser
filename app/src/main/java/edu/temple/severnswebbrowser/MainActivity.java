package edu.temple.severnswebbrowser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //member vars
    ViewPager viewPager;
    int currentIndex, lastIndex;
    EditText urlSearchBar;
    BrowserAdapter browserAdapter;
    FragmentManager fm;
    BrowserFragment fragment;
    Button searchButton;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        urlSearchBar = (EditText) findViewById(R.id.url_search);
        viewPager = (ViewPager) findViewById(R.id.web_pager);
        //button to search the url given
        searchButton = findViewById(R.id.button_search);
        browserAdapter = new BrowserAdapter(fm);
        viewPager.setAdapter(browserAdapter);
        browserAdapter.addUrlToList(url);

        currentIndex=0;
        viewPager.setCurrentItem(currentIndex);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = urlSearchBar.getText().toString();

                WebView webView = (WebView)findViewById(R.id.browser_web_view);
                webView.loadUrl(url);

                browserAdapter.setUrlPositionInList(currentIndex,url);
                browserAdapter.notifyDataSetChanged();

            }
        });



    }



    //inflate and draw menu resource
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.browser_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_tab_button:{



                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

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

        public String getUrlAtPositionAsString(int posisiton){
            return urlsList.get(posisiton).toString();

        }

        public void setUrlPositionInList(int positionInList, String url){
            urlsList.set(positionInList, url);
        }

        public void addUrlToList(String url){
            urlsList.add(url);
        }
    }

}
