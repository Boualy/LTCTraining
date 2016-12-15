package com.laotel.tpv.boualy.ltctraining;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.security.ProviderInstaller;

public class ServiceActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Criteria criteria;
    private double latADouble, lngADouble, updateLatADouble, updateLngADouble;
    private String[] loginStrings;
    private TextView TV_name;

    private EditText nameEditText;
    private ImageView imageView, takPhotoImageView;
    private String nameImageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        // SetUp
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // no need to find length (z)
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

        loginStrings = getIntent().getStringArrayExtra("Login");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        nameEditText = (EditText) findViewById(R.id.ET_Name);
        TV_name = (TextView) findViewById(R.id.TV_Name);
        imageView = (ImageView) findViewById(R.id.img_photo);
        takPhotoImageView = (ImageView) findViewById(R.id.Img_take_photo);
        TV_name.setText(loginStrings[1]);

    } // Main Method


    public void clickSave(View view) {
    nameImageString = nameEditText.getText().toString().trim();

        if (nameImageString.equals("")) {

            MyAlert myAlert = new MyAlert(ServiceActivity.this,
                    getResources().getString(R.string.title_space),
                    getResources().getString(R.string.message_space),
                    R.drawable.doremon48);
            myAlert.myDialog();

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        latADouble = 17.969943;
        lngADouble = 102.612896;
        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);

        if (networkLocation !=null) {
            latADouble = networkLocation.getLatitude();
            lngADouble = networkLocation.getLongitude();
        }

        Location gspLocation = myFindLocation(LocationManager.GPS_PROVIDER);
        if (gspLocation != null) {
            latADouble = gspLocation.getLatitude();
            lngADouble = gspLocation.getLongitude();
        }

        Log.d("15DecV1", "lat ==>" + latADouble);
        Log.d("15DecV1", "lng ==>" + lngADouble);
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    public Location myFindLocation(String strProvider) {
        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            locationManager.requestLocationUpdates(strProvider,1000,10,locationListener);
        }
        return location;
    }

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latADouble = location.getLatitude();
            lngADouble = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        updateLatADouble = latADouble;
        updateLngADouble = lngADouble;

        LatLng latLng = new LatLng(latADouble, lngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        myAddMarker(latADouble, lngADouble);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                myAddMarker(latLng.latitude, latLng.longitude);
            }
        });

    } // Map

    private void myAddMarker(double latADouble, double lngADouble) {
        LatLng latLng = new LatLng(latADouble, lngADouble);
        mMap.addMarker(new MarkerOptions().position(latLng)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.build4)));

        updateLatADouble = latADouble;
        updateLngADouble = lngADouble;

        Log.d("15DecV2", "update Lat ==>" + updateLatADouble);
        Log.d(("15DecV2"), "update Lng ==>" + updateLngADouble);
    }

}  // Main class
