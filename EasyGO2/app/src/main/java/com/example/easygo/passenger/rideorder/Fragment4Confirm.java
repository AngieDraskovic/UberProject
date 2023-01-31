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
import com.example.easygo.dto.LocationDTO;
import com.example.easygo.mockup.MockupDrivers;
import com.example.easygo.mockup.MockupRides;
import com.example.easygo.model.Ride;
import com.example.easygo.model.WorkingHours;
import com.example.easygo.model.users.Driver;
import com.example.easygo.service.ServiceUtilis;

import java.time.LocalDateTime;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                createRide(ride);

                /*
                Driver driver = findAvailableDriver(ride);
                if (driver == null) {
                    Toast.makeText(getContext(), "There is no available driver.", Toast.LENGTH_SHORT).show();
                } else {
                    ride.setStatus(RideStatus.ACTIVE);
                    ride.setDriver(driver);
                    driver.getRides().add(ride);
                    saveRide(ride);
                    createRideExample(ride);
                    Toast.makeText(getContext(), "Successfully ordered ride.\n\n" + driver + " will be your driver.", Toast.LENGTH_SHORT).show();
                }
                 */
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

        rideDeparture.setText("Departure: " + ride.getLocations().get(0).getDeparture().getAddress());
        rideDestination.setText("Destination: " + ride.getLocations().get(0).getDeparture().getAddress());
        rideTime.setText("Time: " + ride.getStartTime().toString());
        rideVehicleName.setText("Vehicle name: " + ride.getVehicleType().toString());
        rideBabyTransport.setText("Baby transport: " + ride.getBabyProofString());
        ridePetTransport.setText("Pet transport: " + ride.getPetsAllowedString());
    }

    public void saveRide(Ride ride) {
        MockupRides.getRides().put(4, ride);
    }


    public void createRide(Ride ride){
        ride.setEstimatedTimeInMinutes(10.0);   // Ovo mokuejmo :(
        Call<Ride> call = ServiceUtilis.rideService.createRide(ride);
        call.enqueue(new Callback<Ride>() {
            @Override
            public void onResponse(Call<Ride> call, Response<Ride> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Ride newRide = response.body();
                    Toast.makeText(getContext(),"Create ride", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Ride> call, Throwable t) {
                Toast.makeText(getContext(),"Failovao working-hour", Toast.LENGTH_SHORT).show();
            }
        });
    }

/*
    public void createRideExample(Ride ride) {
        RideDTORequest rideDTORequest = new RideDTORequest(ride);
        print(rideDTORequest.getStartTime().toString());
        Call<RideDTORequest> call = ServiceUtilis.rideService.createExampleRide(rideDTORequest);
        print("pozvao ride service");

        call.enqueue(new Callback<RideDTORequest>() {
            @Override
            public void onResponse(Call<RideDTORequest> call, Response<RideDTORequest> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(),"Usao u response", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
//                                Toast.makeText(DriverMainActivity.this, activeRideHTTP.setStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RideDTORequest> call, Throwable t) {
                Toast.makeText(getContext(),"Failovao ride service", Toast.LENGTH_SHORT).show();
                print(t.toString());
            }
        });
    }
*/

    public void createLocation(){
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setAddress("Android adresa");
        locationDTO.setLatitude(22.0);
        locationDTO.setLongitude(22.0);

        Call<LocationDTO> call = ServiceUtilis.rideService.createLocation(locationDTO);

        call.enqueue(new Callback<LocationDTO>() {
            @Override
            public void onResponse(Call<LocationDTO> call, Response<LocationDTO> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(),"Usao u response", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
//                    LocationDTO locationDTO1 = new LocationDTO(response.body());
                }
            }

            @Override
            public void onFailure(Call<LocationDTO> call, Throwable t) {
                Toast.makeText(getContext(),"Failovao ride service", Toast.LENGTH_SHORT).show();
                print(t.toString());
            }
        });
    }

    public void createWorkingHour(){
        WorkingHours workingHours = new WorkingHours();
//        workingHourDTOResponse.setId(92);
        workingHours.setStart(LocalDateTime.now());
        workingHours.setEnd(LocalDateTime.now());

        Call<WorkingHours> call = ServiceUtilis.rideService.createWorkingHour(workingHours);
        call.enqueue(new Callback<WorkingHours>() {
            @Override
            public void onResponse(Call<WorkingHours> call, Response<WorkingHours> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(),"Create working-hour", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
//                    LocationDTO locationDTO1 = new LocationDTO(response.body());
                }
            }

            @Override
            public void onFailure(Call<WorkingHours> call, Throwable t) {
                Toast.makeText(getContext(),"Failovao working-hour", Toast.LENGTH_SHORT).show();
                print(t.toString());
            }
        });
    }

    public void getActiveWorkingHour(){
        WorkingHours workingHours = new WorkingHours();
        workingHours.setStart(LocalDateTime.now());
        workingHours.setEnd(LocalDateTime.now());

        Call<WorkingHours> call = ServiceUtilis.rideService.createWorkingHour(workingHours);
        call.enqueue(new Callback<WorkingHours>() {
            @Override
            public void onResponse(Call<WorkingHours> call, Response<WorkingHours> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(),"Get active working-hour", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
                    WorkingHours workingHours = new WorkingHours(response.body());
                    print("Izvrsio");
//                    LocationDTO locationDTO1 = new LocationDTO(response.body());
                }
            }

            @Override
            public void onFailure(Call<WorkingHours> call, Throwable t) {
                Toast.makeText(getContext(),"Failovao working-hour", Toast.LENGTH_SHORT).show();
                print(t.toString());
            }
        });
    }

    public void print(String what) {
        System.out.println("ISPIS: " + what);
    }
}