package com.example.bilalsalman.waterics;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private FirebaseAuth mAuth;
    private DatabaseReference userdata;
    private List<NewComplaintDataForFireBase> ll;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maps, null);

        userdata = FirebaseDatabase.getInstance().getReference();
        //userdata.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();

        ll  = new ArrayList<>();

        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        if (mapFragment==null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map,mapFragment).commit();
        }

        mapFragment.getMapAsync(this);



        return root;
    }

    /*public void onViewCreated(View view, Bundle savedInstanceState){
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }*/

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }*/


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(25, 67);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        mMap.addMarker(new MarkerOptions().position(new LatLng(22,65)).title("close to sydney").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        LatLng karachi = getLocationFromAddress("Karachi");

        //ll=getAddresses();

        /*if(ll.isEmpty())
            Toast.makeText(getContext(), "list is empty", Toast.LENGTH_SHORT).show();

        for(NewComplaintDataForFireBase l : ll)
        {
            //Toast.makeText(getContext(), l.getAddress(), Toast.LENGTH_SHORT).show();
            if(l.getAddress()!="") {
                mMap.addMarker(new MarkerOptions().position(getLocationFromAddress(l.getAddress())).title(l.getType()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                Toast.makeText(getContext(), l.getAddress(), Toast.LENGTH_SHORT).show();
            }
        }*/

        userdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DataSnapshot complaintSnapshot = dataSnapshot.child("Complaints");
                Iterable<DataSnapshot> complaintChildren = complaintSnapshot.getChildren();

                for (DataSnapshot complaint : complaintChildren) {

                    NewComplaintDataForFireBase l = complaint.getValue(NewComplaintDataForFireBase.class);

                    if(!(l.getAddress()==null)) {
                        mMap.addMarker(new MarkerOptions().position(getLocationFromAddress(l.getAddress())).title(l.getType()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                        Toast.makeText(getContext(), l.getAddress(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLng(karachi));
    }

    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this.getActivity().getApplicationContext(), Locale.getDefault());
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

            return p1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }

    private List<NewComplaintDataForFireBase> getAddresses()
    {
        final List<NewComplaintDataForFireBase> cl = new ArrayList<>();

        userdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DataSnapshot complaintSnapshot = dataSnapshot.child("Complaints");
                Iterable<DataSnapshot> complaintChildren = complaintSnapshot.getChildren();

                for (DataSnapshot complaint : complaintChildren) {

                    NewComplaintDataForFireBase c = complaint.getValue(NewComplaintDataForFireBase.class);
                    //Log.d("hello123",c.getAdd());

                    //Toast.makeText(getContext(), "Adding to list", Toast.LENGTH_SHORT).show();
                    cl.add(c);
                    //cl.notify();
                    if(cl.isEmpty())
                        Toast.makeText(getContext(), "C L list is empty", Toast.LENGTH_SHORT).show();

                    ll.add(c);
                    if(ll.isEmpty())
                        Toast.makeText(getContext(), "L L list is empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        });

        return cl;
    }
}
