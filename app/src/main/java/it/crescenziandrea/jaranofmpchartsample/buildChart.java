package it.crescenziandrea.jaranofmpchartsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//lista di elementi di tipo dati
public class buildChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_chart);
        //Intent data = getIntent();

        LineChart lineChart;

        lineChart = findViewById(R.id.lineChart);

        /*YourData[] dataObjects = data;
        List<Entry> entries = new ArrayList<Entry>();
        for (YourData data : dataObjects) {
// turn your data into Entry objects
            entries.add(new Entry(data.getValueX(), data.getValueY()));
        }*/
        ArrayList<Entry> NoOfEmp = new ArrayList<Entry>();

        NoOfEmp.add(new Entry(0, 945f));
        NoOfEmp.add(new Entry(1, 1040f));
        NoOfEmp.add(new Entry(2, 1133f));
        NoOfEmp.add(new Entry(3, 1240f));
        //gfchrghvhgcgjvgc

        LineDataSet dataSet = new LineDataSet(NoOfEmp, "Label");
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh
    }

}
