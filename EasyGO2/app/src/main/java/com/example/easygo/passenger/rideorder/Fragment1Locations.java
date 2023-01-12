package com.example.easygo.passenger.rideorder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easygo.R;
import com.example.easygo.model.Ride;
import com.example.easygo.model.Route;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1Locations#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1Locations extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Ride ride;

    public Fragment1Locations() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment1Locations.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1Locations newInstance(Ride ride) {
        Fragment1Locations fragment = new Fragment1Locations();
        Bundle args = new Bundle();
        args.putSerializable("ride", ride);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1_locations, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            this.ride = (Ride) bundle.getSerializable("ride");
        }

        EditText departureEditText = view.findViewById(R.id.departureEdit);
        EditText destinationEditText = view.findViewById(R.id.destinationEdit);
        Button submitButton = view.findViewById(R.id.submitBtn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String departure = departureEditText.getText().toString();
                String destination = destinationEditText.getText().toString();

                if (departure.equals("") || destination.equals("")) {
                    Toast.makeText(getContext(), "Departure and destination cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    ride.getRoutes().add(new Route());
                    ride.getRoutes().get(0).getDeparture().setAddress(departure);
                    ride.getRoutes().get(0).getDestination().setAddress(destination);
                    Toast.makeText(getContext(), "Departure and destination selected.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}