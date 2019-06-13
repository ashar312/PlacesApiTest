package com.example.placestest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PlacesClient placesClient;
    List<Place.Field> placeField;
    AutocompleteSupportFragment places_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeField = Arrays.asList(Place.Field.ID,
                Place.Field.NAME,
                Place.Field.ADDRESS);
        initPlaces();
        setupPLacesAutoComplete();
    }

    private void setupPLacesAutoComplete() {

        places_fragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.places_autocmp);
        places_fragment.setPlaceFields(placeField);
        places_fragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Toast.makeText(MainActivity.this,""+place.getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Status status) {

                Toast.makeText(MainActivity.this,""+status.getStatusMessage(),Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void initPlaces() {

        Places.initialize(this,getString(R.string.places_api_key));
        placesClient = Places.createClient(this);

    }
}
