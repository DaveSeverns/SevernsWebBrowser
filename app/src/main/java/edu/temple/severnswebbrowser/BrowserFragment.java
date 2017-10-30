package edu.temple.severnswebbrowser;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class BrowserFragment extends android.support.v4.app.Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    
    private static final String ARG_PARAM1 = "https:/reddit.com";

    
    private String url;
    Button searchButton;
    View view;
    WebView webView;
    WebViewClient webViewClient;




    public BrowserFragment() {
        // Required empty public constructor
    }


   

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            
            url = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_browser, container, false);
        webView = view.findViewById(R.id.browser_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        searchButton = (Button) view.findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getStringFromURIText());
                //webView.loadUrl(getStringFromURIText());
            }
        });

        return view;

    }

    /**
     * use this method to read the url from the search bar, have it in browser fragment so its specific to each fragment
     * @return
     */
    public String getStringFromURIText(){
        EditText searchText = (EditText)view.findViewById(R.id.url_search);
        url = searchText.getText().toString();
        return url;

    }

}
