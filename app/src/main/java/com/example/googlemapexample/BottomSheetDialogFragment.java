package com.example.googlemapexample;

import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class BottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {

    private View view;

    public static BottomSheetDialogFragment newInstance() {
        return new BottomSheetDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
            SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    // Add a marker in Kanpur and move the camera
                    LatLng sydney = new LatLng(26.47674, 80.334466);
                    googleMap.addMarker(new MarkerOptions().position(sydney).draggable(true).title("Marker in Kanpur India"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                    // Enable the zoom controls for the map
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.getUiSettings().setScrollGesturesEnabled(true);
                    googleMap.getUiSettings().setZoomGesturesEnabled(true);
                }
            });
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }

        // get the views and attach the listener
        return view;
    }
}
