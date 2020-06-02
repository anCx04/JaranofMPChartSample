package it.crescenziandrea.jaranofmpchartsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//lista di elementi di tipo dati
public class buildChart extends AppCompatActivity {

    MyMarkerView mv;

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
                final BarChart chart = findViewById(R.id.chart);




                mv = new MyMarkerView(this, R.layout.marker_view);
                chart.setMarker(mv);

                chart.setOnChartGestureListener(new OnChartGestureListener() {

                    @Override
                    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                    }

                    @Override
                    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                        // un-highlight values after the gesture is finished and no single-tap
                        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP) {
                                chart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)

                            }
                    }

                    @Override
                    public void onChartLongPressed(MotionEvent me) {
                    }

                    @Override
                    public void onChartDoubleTapped(MotionEvent me) {
                    }

                    @Override
                    public void onChartSingleTapped(MotionEvent me) {
                    }

                    @Override
                    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
                    }

                    @Override
                    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
                    }

                    @Override
                    public void onChartTranslate(MotionEvent me, float dX, float dY) {
                    }
                });

                chart.setTouchEnabled(true);
                chart.setDragEnabled(true);
                chart.setPinchZoom(true);
                BarDataSet bardataset = new BarDataSet(NmOfEmp, "No Of Employee");
                BarData barData = new BarData(bardataset);
                barData.setBarWidth(0.9f);
                chart.setFitBars(true);
                chart.setData(barData);
                chart.invalidate(); // refresh
//.0
                break;
            case "a Linee":
                List<Entry> NoOfEmp = new ArrayList<Entry>();
                for (int i = 0; i < num.size(); i++) {
                    NoOfEmp.add(new Entry(i, num.get(i)));
                }

                setContentView(R.layout.activity_line_chart);
                final LineChart lineChart = findViewById(R.id.chart);

                mv = new MyMarkerView(this, R.layout.marker_view);
                lineChart.setMarker(mv);

                lineChart.setOnChartGestureListener(new OnChartGestureListener() {

                    @Override
                    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                    }

                    @Override
                    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                        // un-highlight values after the gesture is finished and no single-tap
                        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP) {
                            lineChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)

                        }
                    }

                    @Override
                    public void onChartLongPressed(MotionEvent me) {
                    }

                    @Override
                    public void onChartDoubleTapped(MotionEvent me) {
                    }

                    @Override
                    public void onChartSingleTapped(MotionEvent me) {
                    }

                    @Override
                    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
                    }

                    @Override
                    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
                    }

                    @Override
                    public void onChartTranslate(MotionEvent me, float dX, float dY) {
                    }
                });

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

    public class MyMarkerView extends MarkerView {

        private TextView tvContent;

        public MyMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
            // this markerview only displays a textview
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

// callbacks everytime the MarkerView is redrawn, can be used to update the
// content (user-interface)

        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            //tvContent.setText("day: " + e.getX() + "\n"+"Value: " + e.getY()); // set the entry-value as the display text

            if (e instanceof CandleEntry) {

                CandleEntry ce = (CandleEntry) e;

                tvContent.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
            } else {

                tvContent.setText("" + Utils.formatNumber(e.getY(), 0, true));
            }

            super.refreshContent(e, highlight);


        }

        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight());
        }


    }

}
