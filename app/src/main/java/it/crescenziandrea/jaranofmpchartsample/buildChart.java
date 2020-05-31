package it.crescenziandrea.jaranofmpchartsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

//lista di elementi di tipo dati
public class buildChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent data = getIntent();

        /*YourData[] dataObjects = data;
        List<Entry> entries = new ArrayList<Entry>();
        for (YourData data : dataObjects) {
// turn your data into Entry objects
            entries.add(new Entry(data.getValueX(), data.getValueY()));
        }*/

        String selection = data.getStringExtra("selection");
        Toast.makeText(getApplicationContext(), selection, Toast.LENGTH_LONG).show();
        switch (selection) {
            case "a Barre":
                setContentView(R.layout.activity_bar_chart);

                Toast.makeText(this, "barrrr", Toast.LENGTH_LONG).show();
                BarChart chart = findViewById(R.id.chart);

                List<BarEntry> NmOfEmp = new ArrayList<>();

                NmOfEmp.add(new BarEntry(945f, 0));
                NmOfEmp.add(new BarEntry(1040f, 1));
                NmOfEmp.add(new BarEntry(1133f, 2));
                NmOfEmp.add(new BarEntry(1240f, 3));
                NmOfEmp.add(new BarEntry(1369f, 4));
                NmOfEmp.add(new BarEntry(1487f, 5));
                NmOfEmp.add(new BarEntry(1501f, 6));
                NmOfEmp.add(new BarEntry(1645f, 7));
                NmOfEmp.add(new BarEntry(1578f, 8));
                NmOfEmp.add(new BarEntry(1695f, 9));

                BarDataSet bardataset = new BarDataSet(NmOfEmp, "No Of Employee");
                BarData barData = new BarData(bardataset);
                chart.setData(barData);
                chart.invalidate(); // refresh

                break;
            case "a Linee":
                setContentView(R.layout.activity_line_chart);

                Toast.makeText(this, "LINEEEE", Toast.LENGTH_LONG).show();
                LineChart lineChart;

                lineChart = findViewById(R.id.chart);
                ArrayList<Entry> NoOfEmp = new ArrayList<Entry>();

                NoOfEmp.add(new Entry(0, 945f));
                NoOfEmp.add(new Entry(1, 1040f));
                NoOfEmp.add(new Entry(2, 1133f));
                NoOfEmp.add(new Entry(3, 1240f));

                LineDataSet dataSet = new LineDataSet(NoOfEmp, "Label");
                LineData lineData = new LineData(dataSet);
                lineChart.setData(lineData);
                lineChart.invalidate(); // refresh

                break;
            case "a Torta":
                setContentView(R.layout.activity_pie_chart);

                Toast.makeText(this, "pieeee", Toast.LENGTH_LONG).show();

                PieChart pieChart = findViewById(R.id.chart);
                List<PieEntry> NOfEmp = new ArrayList<>();

                NOfEmp.add(new PieEntry(945f, 0));
                NOfEmp.add(new PieEntry(1040f, 1));
                NOfEmp.add(new PieEntry(1133f, 2));
                NOfEmp.add(new PieEntry(1240f, 3));
                NOfEmp.add(new PieEntry(1369f, 4));
                NOfEmp.add(new PieEntry(1487f, 5));
                NOfEmp.add(new PieEntry(1501f, 6));
                NOfEmp.add(new PieEntry(1645f, 7));
                NOfEmp.add(new PieEntry(1578f, 8));
                NOfEmp.add(new PieEntry(1695f, 9));
                PieDataSet dataSett = new PieDataSet(NOfEmp, "Number Of Employees");

                ArrayList<String> years = new ArrayList<String>();

                years.add("2008");
                years.add("2009");
                years.add("2010");
                years.add("2011");
                years.add("2012");
                years.add("2013");
                years.add("2014");
                years.add("2015");
                years.add("2016");
                years.add("2017");
                PieData pieData = new PieData(dataSett);
                pieChart.setData(pieData);
                pieChart.invalidate(); // refresh

                break;

            default:
                //TODO: boh
        }
    }

}
