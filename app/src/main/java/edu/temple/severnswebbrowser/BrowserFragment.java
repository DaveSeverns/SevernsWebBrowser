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
    
    private static final String ARG_PARAM1 = "url";

    
    private String url;
    Button searchButton;
    View view;
    WebView webView;
    WebViewClient webViewClient;




    public BrowserFragment() {
        // Required empty public constructor
    }


    public static BrowserFragment newInstance(String url){
        BrowserFragment browserFragment = new BrowserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,url);
        browserFragment.setArguments(args);


        return  browserFragment;
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
        EditText searchText = (EditText)view.findViewById(R.id.url_search);
        searchText.setText(url);
        webView = view.findViewById(R.id.browser_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        searchButton = (Button) view.findViewById(R.id.button_search);
        webView.setWebViewClient(new WebViewClient());




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //added this so app would load url in my webview and not use default app from phone

                webView.loadUrl(urlFromEditText());
                //webView.loadUrl(getStringFromURIText());
            }
        });

        webView.loadUrl(url);

        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("url",url);
        super.onSaveInstanceState(outState);
    }

    public String urlFromEditText(){
        EditText searchText = (EditText) view.findViewById(R.id.url_search);
        url = searchText.getText().toString();
        return url;
    }
}
