package yuki.control.extended;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.webkit.JavascriptInterface;

/**
 * YUKI GeoLocation JS Bridge
 */

public class YukiJSBridge {

    private Context mContext;

    public YukiJSBridge(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * JSApi Test Method
     *
     * @return Integer 1234
     */
    @JavascriptInterface
    public int test() {
        return 1234;
    }

    /**
     * JSApi GeoLocation via GPS
     *
     * @return GeoLocation
     */
    @JavascriptInterface
    public JSGeoObject geoGPS() {
        LocationManager alm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Location loc = getLocation(LocationManager.GPS_PROVIDER);
            boolean locok;
            locok = updateToNewLocation(loc);
            if (locok) {
                return new JSGeoObject(true, locok, lat, lon);
            } else {
                return new JSGeoObject(true, locok, 0, 0);
            }
        } else {
            return new JSGeoObject(false, false, 0, 0);
        }
    }

    /**
     * JSApi GeoLocation via Network
     *
     * @return GepLocation
     */
    @JavascriptInterface
    public JSGeoObject geoNetwork() {
        LocationManager alm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            Location loc = getLocation(LocationManager.NETWORK_PROVIDER);
            boolean locok;
            locok = updateToNewLocation(loc);
            if (locok) {
                return new JSGeoObject(true, locok, lat, lon);
            } else {
                return new JSGeoObject(true, locok, 0, 0);
            }
        } else {
            return new JSGeoObject(false, false, 0, 0);
        }
    }

    private double lat, lon;

    private boolean updateToNewLocation(Location location) {

        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            return true;
        } else {
            return false;
        }
    }

    private Location getLocation(String provider) {

        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);

        Location location = locationManager.getLastKnownLocation(provider);
        return location;
    }
}
