package com.example.luckypdv.fragments;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.luckypdv.R;
import com.example.luckypdv.models.Shop;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.luckypdv.utilities.Constants.DEFAULT_LOCATION_LATITUDE;
import static com.example.luckypdv.utilities.Constants.DEFAULT_LOCATION_LONGITUDE;
import static com.example.luckypdv.utilities.Constants.DEFAULT_ZOOM;

public class ShopsMapFragment extends Fragment {
    private List<Shop> shopList;
    private SupportMapFragment mapFragment;
    private GoogleMap gMap;
    private EditText etSearch;
    private ImageView ivSearch;
    private Button btBackToList;
    private Fragment self = this;

    public ShopsMapFragment() {
    }

    public static ShopsMapFragment newInstance() {
        ShopsMapFragment fragment = new ShopsMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_shops_map, container, false);
        initData();
        initViews(root);
        return root;
    }

    private void initViews(View root) {
        initMap();

        etSearch = root.findViewById(R.id.et_search);
        ivSearch = root.findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng location = geoLocate();
                if (location != null){
                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, DEFAULT_ZOOM));
                }

            }
        });

        btBackToList = root.findViewById(R.id.bt_back_to_list);
        btBackToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationView navigationView = self.getActivity().findViewById(R.id.nav_view);
                navigationView.getMenu().performIdentifierAction(R.id.nav_shop_list, 0);
            }
        });

    }

    private LatLng geoLocate(){
        String searchString = etSearch.getText().toString();
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addressList = new ArrayList<>();
        try {
            addressList = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addressList.size() > 0){
            Address address = addressList.get(0);
            return new LatLng(address.getLatitude(), address.getLongitude());
        }
        return null;
    }

    private void initData() {
        shopList = new ArrayList<>();
        Shop shop = new Shop();

        shop.setName("Bodega Paquita");
        shop.setAddress("Av. Peru #1350 - San Martin de Porres");
        shop.setLatitude(-12.0326843);
        shop.setLongitude(-77.0615803);
        shopList.add(shop);

        shop = new Shop();
        shop.setName("Metro Izaguirre");
        shop.setAddress("Av Peru #2660 - San Martin de Porres");
        shop.setLatitude(-12.0316481);
        shop.setLongitude(-77.0771404);
        shopList.add(shop);

        shop = new Shop();
        shop.setName("Ernesto Tito Galvez / Mrdo. Las Frutas");
        shop.setAddress("Av.Peru #1360 - San Martin de Porres");
        shop.setLatitude(-12.0326626);
        shop.setLongitude(-77.0617391);
        shopList.add(shop);

        shop = new Shop();
        shop.setName("Ferreteria Carsina SAC");
        shop.setAddress("Av.Peru #1740 - San Martin de Porres");
        shop.setLatitude(-12.0321406);
        shop.setLongitude(-77.0663581);
        shopList.add(shop);
    }

    private void initMap() {
        mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gMap = googleMap;
                LatLng locationLatLng = new LatLng(DEFAULT_LOCATION_LATITUDE, DEFAULT_LOCATION_LONGITUDE);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng, DEFAULT_ZOOM));

                Drawable circleDrawable = getResources().getDrawable(R.drawable.shopping, getContext().getTheme());
                BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);

                LatLng shopLatLng = new LatLng(-12.0326843, -77.0615803);
                MarkerOptions markerOptions = new MarkerOptions().
                        position(shopLatLng).
                        title("Bodega Paquita").
                        icon(markerIcon).
                        draggable(false);
                gMap.addMarker(markerOptions);

                shopLatLng = new LatLng(-12.0316481, -77.0771404);
                markerOptions = new MarkerOptions().
                        position(shopLatLng).
                        title("Metro Izaguirre").
                        icon(markerIcon).
                        draggable(false);
                gMap.addMarker(markerOptions);

                shopLatLng = new LatLng(-12.0326626, -77.0617391);
                markerOptions = new MarkerOptions().
                        position(shopLatLng).
                        title("Ernesto Tito Galvez / Mrdo. Las Frutas").
                        icon(markerIcon).
                        draggable(false);
                gMap.addMarker(markerOptions);

                shopLatLng = new LatLng(-12.0321406, -77.0663581);
                markerOptions = new MarkerOptions().
                        position(shopLatLng).
                        title("Ferreteria Carsina SAC").
                        icon(markerIcon).
                        draggable(false);
                gMap.addMarker(markerOptions);
            }
        });
    }

    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
