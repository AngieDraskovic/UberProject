package com.example.easygo.passenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.easygo.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PassengerReportActivity extends AppCompatActivity {


    Button selectDate;
    TextView textDate;
    GraphView numOfRidesGraph;
    LineGraphSeries<DataPoint> series;
    Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_report);

        selectDate = findViewById(R.id.btnDate);
        textDate = findViewById(R.id.startDate);
        numOfRidesGraph = findViewById(R.id.numOfRidesGraph);


        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select rang");
        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();


        ArrayList<Long> dates = new ArrayList<>();              // LISTA DATUMA TIPA LONG
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getSupportFragmentManager(), "Material_Range");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>(){
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        // KOD ZA DOBAVLJANJE DATUMA :)
                        /*
                        if (selection.first != null && selection.second != null) {
                            // start date
                            Calendar start = Calendar.getInstance();
                            start.setTimeInMillis(selection.first);
                            // end date
                            Calendar end = Calendar.getInstance();
                            end.setTimeInMillis(selection.second);

                            while (start.before(end)) {
                                start.add(Calendar.DAY_OF_MONTH, 1); // add one day
                                dates.add(start.getTimeInMillis());
                            }
                        }
                        ArrayList<Date> datesInFormat = new ArrayList<>();                // LISTA DATUMA SELECTOVANIH
                        for(Long d : dates){
                            datesInFormat.add(new Date(d));         // PRETVARAMO U DATUM TAKO STO U KONSTRUKTORU PROSLIJEDIMO ODGOVARAJUCI LONG
                        }

                        DataPoint[] dataPoints = new DataPoint[datesInFormat.size()];
                        for(int i = 0; i < datesInFormat.size(); i++){
                            dataPoints[i] = new DataPoint(datesInFormat.get(i), i);     // NAPRAVLJEN NIZ DATAPOINTS SA DATUMIMA I OBICNIM INTOM :D
                        }
                        */


                        String date = datePicker.getHeaderText();
                        String underlinedDate = "<u>" + date + "</u>";
                        textDate.setText(Html.fromHtml(underlinedDate));

                    }
                });
            }
        });


        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d1, 1),
                new DataPoint(d2, 5),
                new DataPoint(d3, 3)
        });
        numOfRidesGraph.addSeries(series2);


        numOfRidesGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(PassengerReportActivity.this));
        numOfRidesGraph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        // set manual x bounds to have nice steps
        //numOfRidesGraph.getViewport().setMinX(datesInFormat.get(0).getTime());
        //numOfRidesGraph.getViewport().setMaxX(datesInFormat.get(dates.size()-1).getTime());


        numOfRidesGraph.getViewport().setMinX(d1.getTime());
        numOfRidesGraph.getViewport().setMaxX(d3.getTime());
        numOfRidesGraph.getViewport().setXAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not necessary
        numOfRidesGraph.getGridLabelRenderer().setHumanRounding(false);

    }

    @Override
    protected void onResume(){
        super.onResume();
        TextView backArrow = (TextView)findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerReportActivity.this, PassengerAccountActivity.class));
            }
        });
    }
}