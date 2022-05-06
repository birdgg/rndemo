package com.rndemo.steamwebview;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.webkit.ProxyConfig;
import androidx.webkit.ProxyController;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.concurrent.Executor;

public class SteamWebViewManager extends SimpleViewManager<WebView> {
  public static final String REACT_CLASS = "SteamWebView";

  ReactApplicationContext mCallerContext;

  public SteamWebViewManager(ReactApplicationContext reactContext) {
    mCallerContext = reactContext;
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public WebView createViewInstance(ThemedReactContext context) {
    WebView webView = new WebView(context);
    WebSettings webSettings = webView.getSettings();
    webSettings.getJavaScriptEnabled();
    webView.setWebViewClient(new SteamWebViewClient());
//    setProxy();
//    ProxySetting.setProxy(webView, "fpn-hk.vpc.kr", 20452);
    return webView;
  }

  @ReactProp(name = "url")
  public void setUrl(WebView view, @Nullable String url) {
    Log.d(REACT_CLASS, "load " + url);
    view.loadUrl(url);
  }

  private void setProxy() {
    ProxyConfig proxyConfig = new ProxyConfig.Builder()
      .addProxyRule("fpn-hk.vpc.kr:20452")
      .addDirect().build();
    ProxyController.getInstance().setProxyOverride(proxyConfig, new Executor() {
      @Override
      public void execute(Runnable command) {
        //do nothing
      }
    }, new Runnable() {
      @Override
      public void run() {

      }
    });
  }


}
