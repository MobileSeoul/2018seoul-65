package team.gajigo.irang.information;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import team.gajigo.irang.R;

public class TabFragment04 extends Fragment {

    private WebView mWebView;
    private WebSettings mWebSettings;

    WebView myWebView;
    final static String myBlogAddr = "file:///android_asset/onnuri.html";
    String myUrl;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_4, container, false);
        myWebView = (WebView)view.findViewById(R.id.onnuriWebview);

        myWebView.getSettings();
        myWebView.setWebViewClient(new MyWebViewClient());

        if(myUrl == null){
            myUrl = myBlogAddr;
        }
        myWebView.loadUrl(myUrl);

        return view;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            myUrl = url;
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}

