package com.example.easygo.passenger.rideorder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easygo.R;
import com.example.easygo.mockup.MockupDrivers;
import com.example.easygo.model.Ride;
import com.example.easygo.model.users.Driver;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment4Confirm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment4Confirm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Ride ride;

    public Fragment4Confirm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment4Success.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment4Confirm newInstance(Ride ride) {
        Fragment4Confirm fragment = new Fragment4Confirm();
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
        View view = inflater.inflate(R.layout.fragment_fragment4_confirm, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            this.ride = (Ride) bundle.getSerializable("ride");
            setRideValues(view);
        }

        Button confirmOrderButton = view.findViewById(R.id.confirmOrderBtn);
        confirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Driver driver = findAvailableDriver(ride);
                if (driver == null) {
                    Toast.makeText(getContext(), "There is no available driver.", Toast.LENGTH_SHORT).show();
                } else {
                    ride.setDriver(driver);
                    driver.getRides().add(ride);
                    Toast.makeText(getContext(), "Successfully ordered ride.\n\n" + driver + " will be your driver.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private Driver findAvailableDriver(Ride ride) {
        HashMap<Integer, Driver> allDrivers = MockupDrivers.getDrivers();
        for (Driver driver : allDrivers.values()) {
            if (!driver.compatibileVehicle(ride))
                continue;
            if (driver.isAvailable(ride))
                return driver;
        }
        return null;
    }

    private void setRideValues(View view) {
        TextView rideDeparture = view.findViewById(R.id.rideDeparture);
        TextView rideDestination = view.findViewById(R.id.rideDestination);
        TextView rideTime = view.findViewById(R.id.rideTime);
        TextView rideVehicleName = view.findViewById(R.id.rideVehicleName);
        TextView rideBabyTransport = view.findViewById(R.id.rideBabyTransport);
        TextView ridePetTransport = view.findViewById(R.id.ridePetTransport);

        rideDeparture.setText("Departure: " + ride.getRoutes().get(0).getDeparture().getAddress());
        rideDestination.setText("Destination: " + ride.getRoutes().get(0).getDeparture().getAddress());
        rideTime.setText("Time: " + ride.getStartTime().toString());
        rideVehicleName.setText("Vehicle name: " + ride.getVehicleName().toString());
        rideBabyTransport.setText("Baby transport: " + ride.getBabyProofString());
        ridePetTransport.setText("Pet transport: " + ride.getPetsAllowedString());
    }
}