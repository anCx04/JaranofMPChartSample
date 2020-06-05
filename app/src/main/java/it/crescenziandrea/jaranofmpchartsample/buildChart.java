package it.crescenziandrea.jaranofmpchartsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

//lista di elementi di tipo dati
public class buildChart extends AppCompatActivity {

    MyMarkerView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        int sum = 0;
        ArrayList EntryPie = new ArrayList();
        PieDataSet PiedataSet;
        PieData pieData;
        PieChart pieChart;
        Legend legend;
        int colorAccent = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);

        String selection = data.getString("selection");
        int percenctage = data.getInt("percenctage");
        Boolean swLegend = data.getBoolean("swLegend");
        ArrayList<Integer> num = data.getIntegerArrayList("dati");
        switch (selection) {
            case "a Barre":
                List<BarEntry> NmOfEmp = new ArrayList<>();
                for (int i = 0; i < num.size(); i++) {
                    NmOfEmp.add(new BarEntry(i, num.get(i)));
                    Log.w("CB", "for "+i+":"+num.size());
                }
                setContentView(R.layout.activity_bar_chart);

                final BarChart chart = findViewById(R.id.barChart);




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

                legend = chart.getLegend();

                legend.setEnabled(swLegend);
                chart.setTouchEnabled(true);
                chart.setDragEnabled(true);
                chart.setPinchZoom(true);
                BarDataSet bardataset = new BarDataSet(NmOfEmp, "selezione");
                BarData barData = new BarData(bardataset);
                bardataset.setColor(colorAccent);
                barData.setBarWidth(0.9f);
                chart.setFitBars(true);
                chart.setData(barData);
                chart.invalidate(); // refresh

                //pie chart
                pieChart = findViewById(R.id.pieChart);

                for (int i = 0; i < num.size(); i++) {
                    sum += num.get(i);
                }
               EntryPie.add(new PieEntry(sum, "selzione"));
               EntryPie.add(new PieEntry(60360000, "popolazione italiana"));

                PiedataSet = new PieDataSet(EntryPie, "");

                legend = pieChart.getLegend();
                legend.setEnabled(swLegend);
                pieData = new PieData(PiedataSet);
                pieChart.setHoleRadius(percenctage/2);
                pieChart.setData(pieData);
                PiedataSet.setColors(new int[] {R.color.colorAccent, R.color.colorPrimary}, getApplicationContext());
                pieChart.invalidate(); // refresh
//.0
                break;
            case "a Linee":
                List<Entry> NoOfEmp = new ArrayList<Entry>();
                for (int i = 0; i < num.size(); i++) {
                    NoOfEmp.add(new Entry(i, num.get(i)));
                }

                setContentView(R.layout.activity_line_chart);
                final LineChart lineChart = findViewById(R.id.lineChart);

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

                legend = lineChart.getLegend();
                legend.setEnabled(swLegend);
                lineChart.setTouchEnabled(true);
                lineChart.setDragEnabled(true);
                lineChart.setPinchZoom(true);
                LineDataSet dataSet = new LineDataSet(NoOfEmp, "Label");
                dataSet.setColors(colorAccent);
                lineChart.getHighlighted();
                dataSet.setDrawValues(false);
                dataSet.setDrawCircles(false);
                dataSet.setDrawHighlightIndicators(true);
                LineData lineData = new LineData(dataSet);
                lineChart.setData(lineData);
                lineChart.invalidate(); // refresh

                //pie chart
                pieChart = findViewById(R.id.pieChart);

                for (int i = 0; i < num.size(); i++) {
                    sum += num.get(i);
                }
                EntryPie.add(new PieEntry(sum, "selezione"));
                EntryPie.add(new PieEntry(60360000, "popolazione italiana"));

                PiedataSet = new PieDataSet(EntryPie, "");

                legend = pieChart.getLegend();
                legend.setEnabled(swLegend);
                pieData = new PieData(PiedataSet);
                pieChart.setData(pieData);
                pieChart.setHoleRadius(percenctage/2);
                PiedataSet.setColors(new int[] {R.color.colorPrimary, R.color.colorAccent}, getApplicationContext());
                pieChart.invalidate(); // refresh
//.0


                break;


            default:

                //TODO: boh
        }
    }

    public class MyMarkerView extends MarkerView {

        private TextView tvContent;

        public MyMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

        @Override
        public void refreshContent(Entry e, Highlight highlight) {

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
