

# Pop-Up-MapView

[![enter image description here][1]][1]


  [1]: https://i.stack.imgur.com/S1SFG.gif

<b>Android Studio: <i>3.5.2</i></b>

<b>Target SDK Version: <i>29</i></b>

<b>Min SDK Version: <i>16</i></b>


## Setup

Setup is very straight forward and well documentation by google.

1. Git Clone the repo and open it in Android Studios

            git clone https://github.com/MitchTODO/Pop-Up-MapView.git

2. Create your MapView API key through the [Google Developer Console][3].

   [Google documentation on create your API key for a MapView][2]

3. Apply your map API key

   Once you have your key (it starts with "AIza"), replace the "google_maps_key"
    string in this file.

    res -> values -> google_maps_api.xml

           <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">google_maps_key</string>

4. Run the project


## Code Run Down

Layout of the BottomSheetDialogFragment.

<i>Note:</i> `android:nestedScrollingEnabled="true"` must be added before the MapView fragment otherwise vertical touch movements will trigger the BottomSheetDialogFragment to be dismissed.

<b>FILE: /res/layout/bottom_sheet_layout.xml</b>

```xml
<?xml version="1.0" encoding="utf-8"?>

<!--grabber bar above the mapView keeps functionality of popup-->
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingTop="@dimen/dp_56"
    android:background="@color/colorAccent"
    android:orientation="vertical">
    <!--prevents touch events within the mapView triggering BottomDialog-->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/colorPrimary"
        android:nestedScrollingEnabled="true">

        <fragment
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:tag="MapFragment"
            android:id="@+id/map"
            tools:context=".MapsActivity"
            android:name="com.google.android.gms.maps.SupportMapFragment" />
    </LinearLayout>

</LinearLayout>
```

Creation of the map view within the BottomSheetDialogFragment class

<b>FILE: /java/com.example.googlemapexample/BottomSheetDialogFragment.java</b>
```java    

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
  ```



  [2]: https://developers.google.com/maps/documentation/android/start#get-key
  [3]:https://console.developers.google.com
