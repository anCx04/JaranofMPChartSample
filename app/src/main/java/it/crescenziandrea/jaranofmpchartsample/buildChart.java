package it.crescenziandrea.jaranofmpchartsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
                for (int i = 0; i < num.size(); i++) {
                    Log.w("CB", "for "+i+":"+num.size());
                }
                break;
            case "a Linee":
               // LineChart lineChart;

                //lineChart = findViewById(R.id.lineChart);
               // ArrayList<Entry> NoOfEmp = new ArrayList<Entry>();
                for (int i = 0; i < num.size(); i++) {
                    Log.w("CB", "for "+i+":"+num.size());
                }
              /*  NoOfEmp.add(new Entry(0, num.get(1)));
                NoOfEmp.add(new Entry(1, num.get(1)));
                NoOfEmp.add(new Entry(2, num.get(2)));
                NoOfEmp.add(new Entry(3, num.get(3)));
                */
                /*
                //gfchrghvhgcgjvgc
                LineDataSet dataSet = new LineDataSet(NoOfEmp, "Label");
                LineData lineData = new LineData(dataSet);

                lineChart.setData(lineData);
                lineChart.invalidate(); // refresh
                    */
                break;
            case "a Torta":
                break;

            default:

                //TODO: boh
        }
    }

}
