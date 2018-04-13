package best.obmankazino.jara;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.facebook.applinks.AppLinkData;

public class MainActivity extends AppCompatActivity {

    private String url = "http://vicklopo.ru/VWJh6h";
    private String key = "http://sadtewrfxczxf.com";
    private Handler handler;
    private ImageView btnPlay;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.btn_play);
        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.contains(key)) {
                    btnPlay.setVisibility(View.VISIBLE);
                } else {
                    btnPlay.setVisibility(View.GONE);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        handler = new Handler(Looper.getMainLooper());
        AppLinkData.fetchDeferredAppLinkData(this,
                appLinkData -> {
                    Runnable myRunnable = () -> {
                        if (appLinkData != null) {
                            getTransformUrl(appLinkData.getTargetUri());
                        }
                        webView.loadUrl(url);
                    };
                    handler.post(myRunnable);
                }
        );
        btnPlay.setOnClickListener(__ -> play());
    }

    private void play() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void getTransformUrl(Uri data) {
        if (data != null) {
            String QUERY_1 = "sub_id_1";
            String QUERY_2 = "sub_id_2";
            String QUERY_3 = "sub_id_3";
            if (data.getEncodedQuery().contains(QUERY_1)) {
                String queryValueFirst = "?sub_id_1=" + data.getQueryParameter(QUERY_1);
                url = url + queryValueFirst;
            }
            if (data.getEncodedQuery().contains(QUERY_2)) {
                String queryValueSecond = "&sub_id_2=" + data.getQueryParameter(QUERY_2);
                url = url + queryValueSecond;
            }
            if (data.getEncodedQuery().contains(QUERY_3)) {
                String queryValueSecond = "&sub_id_3=" + data.getQueryParameter(QUERY_3);
                url = url + queryValueSecond;
            }
        }
    }
}
