package it.crescenziandrea.jaranofmpchartsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//lista di elementi di tipo dati
public class buildChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();


        /*YourData[] dataObjects = data;
        List<Entry> entries = new ArrayList<Entry>();
        for (YourData data : dataObjects) {
// turn your data into Entry objects
            entries.add(new Entry(data.getValueX(), data.getValueY()));
        }*/

        String selection = data.getString("selection");
        ArrayList<Integer> num = data.getIntegerArrayList("dati");
        Toast.makeText(getApplicationContext(), selection, Toast.LENGTH_LONG).show();
        switch (selection) {
            case "a Barre":
                List<BarEntry> NmOfEmp = new ArrayList<>();
                for (int i = 0; i < num.size(); i++) {
                    NmOfEmp.add(new BarEntry(i, num.get(i)));
                    Log.w("CB", "for "+i+":"+num.size());
                }
                setContentView(R.layout.activity_bar_chart);

                Toast.makeText(this, "barrrr", Toast.LENGTH_LONG).show();
                BarChart chart = findViewById(R.id.chart);

                chart.setTouchEnabled(true);
                chart.setDragEnabled(true);
                chart.setPinchZoom(true);
                BarDataSet bardataset = new BarDataSet(NmOfEmp, "No Of Employee");
                BarData barData = new BarData(bardataset);
                barData.setBarWidth(0.9f);
                chart.setFitBars(true);
                chart.setData(barData);
                chart.invalidate();
                break;
            case "a Linee":
                List<Entry> NoOfEmp = new ArrayList<Entry>();
                for (int i = 0; i < num.size(); i++) {
                    NoOfEmp.add(new Entry(i, num.get(i)));
                }

                setContentView(R.layout.activity_line_chart);
                LineChart lineChart = findViewById(R.id.chart);
                lineChart.setTouchEnabled(true);
                lineChart.setDragEnabled(true);
                lineChart.setPinchZoom(true);
                LineDataSet dataSet = new LineDataSet(NoOfEmp, "Label");
                lineChart.getHighlighted();
                dataSet.setDrawValues(false);
                dataSet.setDrawCircles(false);
                dataSet.setDrawHighlightIndicators(true);
                LineData lineData = new LineData(dataSet);
                lineChart.setData(lineData);
                lineChart.invalidate(); // refresh

                break;
            case "a Torta":
                List<PieEntry> NOfEmp = new ArrayList<PieEntry>();
                for (int i = 1; i < num.size(); i++) {
                    NOfEmp.add(new PieEntry(i, num.get(i)));
                }
                Toast.makeText(this, "pieeee", Toast.LENGTH_LONG).show();

                setContentView(R.layout.activity_pie_chart);
                PieChart pieChart = findViewById(R.id.chart);

                PieDataSet dataSett = new PieDataSet(NOfEmp, "Number Of Employees");
                PieData pieData = new PieData(dataSett);
                pieChart.setData(pieData);
                pieChart.invalidate(); // refresh

                break;

            default:

                //TODO: boh
        }
    }

}
