package com.example.easygo.passenger.rideorder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.easygo.R;
import com.example.easygo.model.Ride;
import com.example.easygo.model.enumerations.VehicleName;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3Details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Ride ride;
    private Spinner vehicleSpinner;
    private CheckBox babyTransportCheckbox;
    private CheckBox petTransportCheckbox;

    public Fragment3Details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment3Details.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment3Details newInstance(Ride ride) {
        Fragment3Details fragment = new Fragment3Details();
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
        View view =  inflater.inflate(R.layout.fragment_fragment3_details, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            this.ride = (Ride) bundle.getSerializable("ride");
        }

        this.vehicleSpinner = view.findViewById(R.id.vehicle_spinner);
        this.babyTransportCheckbox = view.findViewById(R.id.baby_transport_checkbox);
        this.petTransportCheckbox = view.findViewById(R.id.pet_transport_checkbox);

        Button submitButton = view.findViewById(R.id.submitBtnDetails);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRideValues();
                Toast.makeText(getContext(), "Vehicle details selected.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setRideValues() {
        String selectedVehicle = vehicleSpinner.getSelectedItem().toString();
        if(selectedVehicle.equals("STANDARD"))
            ride.setVehicleType(VehicleName.STANDARD);
        else if(selectedVehicle.equals("LUXURY"))
            ride.setVehicleType(VehicleName.LUXURY);
        else if(selectedVehicle.equals("VAN"))
            ride.setVehicleType(VehicleName.VAN);
        else

        ride.setBabyTransport(babyTransportCheckbox.isChecked());
        ride.setPetTransport(petTransportCheckbox.isChecked());
    }
}