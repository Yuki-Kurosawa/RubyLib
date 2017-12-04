package yuki.control.extended;

import android.webkit.JavascriptInterface;

/**
 * Geo Location Object.
 */
public final class JSGeoObject {
    private boolean _result;
    private boolean _GPS;
    private double _x;
    private double _y;

    public JSGeoObject(boolean GPS, boolean result, double lat, double lon) {
        _GPS = GPS;
        _result = result;
        _x = lat;
        _y = lon;
    }

    @JavascriptInterface
    public double get_x() {
        return get_lat();
    }

    @JavascriptInterface
    public double get_y() {
        return get_lon();
    }

    @JavascriptInterface
    public boolean is_location_ok() {
        return _result;
    }

    @JavascriptInterface
    public boolean is_GPS_ok() {
        return _GPS;
    }

    @JavascriptInterface
    public double get_lat() {
        return _x;
    }

    @JavascriptInterface
    public double get_lon() {
        return _y;
    }
}
