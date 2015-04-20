package com.example.nzf.nzfmap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.Parse;
import com.parse.ParseObject;

public class MapsActivity extends FragmentActivity {
    static final LatLng MELBOURNE = new LatLng(-37.8254,144.95410);
    static final LatLng SYDNEY = new LatLng(-33.86916,151.20437);
    //TODO create a database
    //TODO add interface for the data
    //TODO make the Markers more sophisticated
    //TODO create a sign in form
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "TSeJqGQRGo18G0whgiGGsAkOJv8y1Br4vZ3T4jyu", "xjv1Bfyrp7hGeQBYHLz4PEEcPo13N6xBB1ee48VF");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("key1", "value1");
        testObject.saveInBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(MELBOURNE).title("mMarker"));
        MarkerOptions mo = new MarkerOptions();
        mo.draggable(true);
        mo.position(SYDNEY).title("sMarker");
        mo.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
        mMap.addMarker(mo);

        Log.d(Constants.LOG, "on setUpMap");

    }
    public interface Constants {
        String LOG = "com.example.nzf.nzfmap";
    }
}
