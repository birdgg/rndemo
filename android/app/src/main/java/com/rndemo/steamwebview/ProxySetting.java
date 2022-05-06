package com.rndemo.steamwebview;

import android.content.Context;
import android.content.Intent;
import android.net.Proxy;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;
import android.webkit.WebView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ProxySetting {
    private static final String TAG = "RNCWebViewManager";
    private static final String APPLICATION_NAME = "android.app.Application";

    public static boolean setProxy(WebView webView, String str, int i) {
        Log.d(TAG, "set proxy: " + str + ":" + i);
        if (Build.VERSION.SDK_INT <= 15) {
            return setProxyICS(webView, str, i);
        } else if (Build.VERSION.SDK_INT <= 18) {
            return setProxyJB(webView, str, i);
        } else {
            return setProxyKKPlus(webView, str, i);
        }
    }

    public static boolean revertProxy(WebView webview) {
        Log.d(TAG, "revert proxy");
        if (Build.VERSION.SDK_INT <= 15) {
            return revertProxyICS(webview);
        } else if (Build.VERSION.SDK_INT <= 18) {
            return revertProxyJB(webview);
        } else {
            return revertProxyKKPlus(webview);
        }
    }

    private static boolean setProxyICS(WebView webView, String str, int i) {
        try {
            Class.forName("android.webkit.JWebCoreJavaBridge").getDeclaredMethod("updateProxy", Class.forName("android.net.ProxyProperties")).invoke(getFieldValueSafely(Class.forName("android.webkit.BrowserFrame").getDeclaredField("sJavaBridge"), getFieldValueSafely(Class.forName("android.webkit.WebViewCore").getDeclaredField("mBrowserFrame"), getFieldValueSafely(Class.forName("android.webkit.WebView").getDeclaredField("mWebViewCore"), webView))), Class.forName("android.net.ProxyProperties").getConstructor(String.class, Integer.TYPE, String.class).newInstance(str, Integer.valueOf(i), null));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean revertProxyICS(WebView webview) {
        try {
            Log.d(TAG, "Setting proxy with 4.0 API.");

            Class jwcjb = Class.forName("android.webkit.JWebCoreJavaBridge");
            Class params[] = new Class[1];
            params[0] = Class.forName("android.net.ProxyProperties");
            Method updateProxyInstance = jwcjb.getDeclaredMethod("updateProxy", params);

            Class wv = Class.forName("android.webkit.WebView");
            Field mWebViewCoreField = wv.getDeclaredField("mWebViewCore");
            Object mWebViewCoreFieldInstance = getFieldValueSafely(mWebViewCoreField, webview);

            Class wvc = Class.forName("android.webkit.WebViewCore");
            Field mBrowserFrameField = wvc.getDeclaredField("mBrowserFrame");
            Object mBrowserFrame = getFieldValueSafely(mBrowserFrameField, mWebViewCoreFieldInstance);

            Class bf = Class.forName("android.webkit.BrowserFrame");
            Field sJavaBridgeField = bf.getDeclaredField("sJavaBridge");
            Object sJavaBridge = getFieldValueSafely(sJavaBridgeField, mBrowserFrame);

            Class ppclass = Class.forName("android.net.ProxyProperties");
            Class pparams[] = new Class[3];
            pparams[0] = String.class;
            pparams[1] = int.class;
            pparams[2] = String.class;
            Constructor ppcont = ppclass.getConstructor(pparams);

            Object o = null;
            updateProxyInstance.invoke(sJavaBridge, o);

            Log.d(TAG, "Setting proxy with 4.0 API successful!");
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "failed to set HTTP proxy: " + ex);
            return false;
        }
    }

    private static boolean setProxyJB(WebView webView, String str, int i) {
        try {
            Object fieldValueSafely = getFieldValueSafely(Class.forName("android.webkit.BrowserFrame").getDeclaredField("sJavaBridge"), getFieldValueSafely(Class.forName("android.webkit.WebViewCore").getDeclaredField("mBrowserFrame"), getFieldValueSafely(Class.forName("android.webkit.WebViewClassic").getDeclaredField("mWebViewCore"), Class.forName("android.webkit.WebViewClassic").getDeclaredMethod("fromWebView", Class.forName("android.webkit.WebView")).invoke(null, webView))));
            Constructor<?> constructor = Class.forName("android.net.ProxyProperties").getConstructor(String.class, Integer.TYPE, String.class);
            Class.forName("android.webkit.JWebCoreJavaBridge").getDeclaredMethod("updateProxy", Class.forName("android.net.ProxyProperties")).invoke(fieldValueSafely, constructor.newInstance(str, Integer.valueOf(i), null));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean revertProxyJB(WebView webview) {
        Log.d(TAG, "revert proxy with 4.1 - 4.3 API.");
        try {
            Class wvcClass = Class.forName("android.webkit.WebViewClassic");
            Class wvParams[] = new Class[1];
            wvParams[0] = Class.forName("android.webkit.WebView");
            Method fromWebView = wvcClass.getDeclaredMethod("fromWebView", wvParams);
            Object webViewClassic = fromWebView.invoke(null, webview);

            Class wv = Class.forName("android.webkit.WebViewClassic");
            Field mWebViewCoreField = wv.getDeclaredField("mWebViewCore");
            Object mWebViewCoreFieldInstance = getFieldValueSafely(mWebViewCoreField, webViewClassic);

            Class wvc = Class.forName("android.webkit.WebViewCore");
            Field mBrowserFrameField = wvc.getDeclaredField("mBrowserFrame");
            Object mBrowserFrame = getFieldValueSafely(mBrowserFrameField, mWebViewCoreFieldInstance);

            Class bf = Class.forName("android.webkit.BrowserFrame");
            Field sJavaBridgeField = bf.getDeclaredField("sJavaBridge");
            Object sJavaBridge = getFieldValueSafely(sJavaBridgeField, mBrowserFrame);

            Class ppclass = Class.forName("android.net.ProxyProperties");
            Class pparams[] = new Class[3];
            pparams[0] = String.class;
            pparams[1] = int.class;
            pparams[2] = String.class;
            Constructor ppcont = ppclass.getConstructor(pparams);

            Class jwcjb = Class.forName("android.webkit.JWebCoreJavaBridge");
            Class params[] = new Class[1];
            params[0] = Class.forName("android.net.ProxyProperties");
            Method updateProxyInstance = jwcjb.getDeclaredMethod("updateProxy", params);

            Object o = null;
            updateProxyInstance.invoke(sJavaBridge, o);
        } catch (Exception ex) {
            Log.e(TAG, "Setting proxy with >= 4.1 API failed with error: " + ex.getMessage());
            return false;
        }

        Log.d(TAG, "revert proxy with 4.1 - 4.3 API successful!");
        return true;
    }

    private static boolean setProxyKKPlus(WebView webView, String host, int port) {
        Context appContext = webView.getContext().getApplicationContext();
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port + "");
        System.setProperty("https.proxyHost", host);
        System.setProperty("https.proxyPort", port + "");
        try {
            Field loadedApkField = Class.forName(APPLICATION_NAME).getField("mLoadedApk");
            loadedApkField.setAccessible(true);
            Object loadedApk = loadedApkField.get(appContext);

            Field receiversField = Class.forName("android.app.LoadedApk").getDeclaredField("mReceivers");
            receiversField.setAccessible(true);
            ArrayMap receivers = (ArrayMap) receiversField.get(loadedApk);
            for (Object receiverMap : receivers.values()) {
                for (Object rec : ((ArrayMap) receiverMap).keySet()) {
                    Class clazz = rec.getClass();
                    if (clazz != null && clazz.getName().contains("ProxyChangeListener")) {
                        Method onReceiveMethod = clazz.getDeclaredMethod("onReceive", Context.class, Intent.class);
                        Intent intent = new Intent(Proxy.PROXY_CHANGE_ACTION);
                        onReceiveMethod.invoke(rec, appContext, intent);
                    }
                }
            }
            return true;
        } catch (ClassNotFoundException e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            stringWriter.toString();
            return false;
        } catch (NoSuchFieldException e2) {
            StringWriter stringWriter2 = new StringWriter();
            e2.printStackTrace(new PrintWriter(stringWriter2));
            stringWriter2.toString();
            return false;
        } catch (IllegalAccessException e3) {
            StringWriter stringWriter3 = new StringWriter();
            e3.printStackTrace(new PrintWriter(stringWriter3));
            stringWriter3.toString();
            return false;
        } catch (IllegalArgumentException e4) {
            StringWriter stringWriter4 = new StringWriter();
            e4.printStackTrace(new PrintWriter(stringWriter4));
            stringWriter4.toString();
            return false;
        } catch (NoSuchMethodException e5) {
            StringWriter stringWriter5 = new StringWriter();
            e5.printStackTrace(new PrintWriter(stringWriter5));
            stringWriter5.toString();
            return false;
        } catch (InvocationTargetException e6) {
            StringWriter stringWriter6 = new StringWriter();
            e6.printStackTrace(new PrintWriter(stringWriter6));
            stringWriter6.toString();
            return false;
        }
    }

    public static boolean revertProxyKKPlus(WebView webView) {
        Context appContext = webView.getContext().getApplicationContext();
        Properties properties = System.getProperties();
        properties.remove("http.proxyHost");
        properties.remove("http.proxyPort");
        properties.remove("https.proxyHost");
        properties.remove("https.proxyPort");
        try {
            Field loadedApkField = Class.forName(APPLICATION_NAME).getField("mLoadedApk");
            loadedApkField.setAccessible(true);
            Object loadedApk = loadedApkField.get(appContext);
            Field receiversField = Class.forName("android.app.LoadedApk").getDeclaredField("mReceivers");
            receiversField.setAccessible(true);
            ArrayMap receivers = (ArrayMap) receiversField.get(loadedApk);
            for (Object receiverMap : receivers.values()) {
                for (Object rec : ((ArrayMap) receiverMap).keySet()) {
                    Class clazz = rec.getClass();
                    if (clazz != null && clazz.getName().contains("ProxyChangeListener")) {
                        Method onReceiveMethod = clazz.getDeclaredMethod("onReceive", Context.class, Intent.class);
                        Intent intent = new Intent(Proxy.PROXY_CHANGE_ACTION);
                        onReceiveMethod.invoke(rec, appContext, intent);
                    }
                }
            }
            return true;
        } catch (ClassNotFoundException e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            stringWriter.toString();
            return false;
        } catch (NoSuchFieldException e2) {
            StringWriter stringWriter2 = new StringWriter();
            e2.printStackTrace(new PrintWriter(stringWriter2));
            stringWriter2.toString();
            return false;
        } catch (IllegalAccessException e3) {
            StringWriter stringWriter3 = new StringWriter();
            e3.printStackTrace(new PrintWriter(stringWriter3));
            stringWriter3.toString();
            return false;
        } catch (IllegalArgumentException e4) {
            StringWriter stringWriter4 = new StringWriter();
            e4.printStackTrace(new PrintWriter(stringWriter4));
            stringWriter4.toString();
            return false;
        } catch (NoSuchMethodException e5) {
            StringWriter stringWriter5 = new StringWriter();
            e5.printStackTrace(new PrintWriter(stringWriter5));
            stringWriter5.toString();
            return false;
        } catch (InvocationTargetException e6) {
            StringWriter stringWriter6 = new StringWriter();
            e6.printStackTrace(new PrintWriter(stringWriter6));
            stringWriter6.toString();
            return false;
        }
    }

    private static Object getFieldValueSafely(Field field, Object obj) throws IllegalArgumentException, IllegalAccessException {
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        Object obj2 = field.get(obj);
        field.setAccessible(isAccessible);
        return obj2;
    }
}
