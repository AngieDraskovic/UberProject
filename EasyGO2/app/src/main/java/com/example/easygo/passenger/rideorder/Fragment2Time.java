package com.example.easygo.passenger.rideorder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.easygo.R;
import com.example.easygo.model.Ride;

import java.time.LocalDateTime;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2Time#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2Time extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Ride ride;
    private View nowFutureButtons;
    private View futureOrderDetails;

    public Fragment2Time() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment2Future.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2Time newInstance(Ride ride) {
        Fragment2Time fragment = new Fragment2Time();
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
        View view = inflater.inflate(R.layout.fragment_fragment2_time, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            this.ride = (Ride) bundle.getSerializable("ride");
        }

        nowFutureButtons = view.findViewById(R.id.nowFutureButtons);
        futureOrderDetails = view.findViewById(R.id.futureOrderDetails);
        futureOrderDetails.setVisibility(View.GONE); // Inicijalno postavljamo na invisible

        Button orderForFutureBtn = view.findViewById(R.id.orderForFutureBtn);
        orderForFutureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                futureOrderDetails.setVisibility(View.VISIBLE);
            }
        });

        DatePicker datePicker = view.findViewById(R.id.date_picker);
        TimePicker timePicker = view.findViewById(R.id.time_picker);
        Button submitButton = view.findViewById(R.id.get_datetime_button);

        Button orderForNowBtn = view.findViewById(R.id.orderForNowBtn);
        orderForNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocalDateTime startTime = LocalDateTime.now();
                ride.setStartTime(startTime);
                Toast.makeText(getContext(), "Ordered for now.", Toast.LENGTH_SHORT).show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                LocalDateTime startTime = LocalDateTime.of(year, month + 1, day, hour, minute);
                if (validTime(startTime)) {
                    ride.setStartTime(startTime);
                    Toast.makeText(getContext(), "Date and time selected.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    private boolean validTime(LocalDateTime startTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextFiveHours = now.plusHours(5);

        if (startTime.isBefore(now) || startTime.isAfter(nextFiveHours)) {
            Toast.makeText(getContext(), "Please choose a date and time that is within the next 5 hours.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}