package it.crescenziandrea.jaranofmpchartsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//lista di elementi di tipo dati
public class buildChart extends AppCompatActivity {

    MyMarkerView mv;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // passaggio dati con il bundle dalla mainActivity
        Bundle data = getIntent().getExtras();



        ArrayList EntryPie = new ArrayList();
        PieDataSet PiedataSet;
        PieData pieData;
        PieChart pieChart;
        Legend legend;

        // creazione colori per i grafici
        int colorAccent = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
        int labelColor = ContextCompat.getColor(getApplicationContext(), R.color.gridcolor);
        int background = ContextCompat.getColor(getApplicationContext(), R.color.backgroundc);
        Description description = new Description();
        description.setText("");


        // salviamo i dati provenienti dal bundle nelle variabili

        String selection = data.getString("selection");                     // var selezione primo grafico dell'activity
        int percentage = data.getInt("percentage");                         // var percentuale foro del grafico a torta
        Boolean swLegend = data.getBoolean("swLegend");                     // var booleana che attiva o disattiva le legende del grafico
        ArrayList<Integer> num = data.getIntegerArrayList("dati");          // ArrayList dove vengono salvati i dati da plottare sui grafici

        // switch di selezione del grafico
        switch (selection) {
            case "a Barre":

                //passaggio di dati alla lista del grafico proveninte dall'Arraylist num
                List<BarEntry> BarEntryList = new ArrayList<>();
                for (int i = 0; i < num.size(); i++) {
                    BarEntryList.add(new BarEntry(i, num.get(i)));
                    Log.w("CB", "for "+i+":"+num.size());
                }

                // generiamo la view del grafico a barre
                setContentView(R.layout.activity_bar_chart);

                //definisce il grafico
                final BarChart chart = findViewById(R.id.barChart);

                //generiamo i marker e li associamo al grafico
                mv = new MyMarkerView(this, R.layout.marker_view);
                chart.setMarker(mv);

                //metodo che gestisce l'interazione del grafico con le gesture
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


                //generiamo le legende
                legend = chart.getLegend();
                legend.setEnabled(swLegend);
                legend.setTextColor(labelColor);

                //generazione del dataset
                BarDataSet bardataset = new BarDataSet(BarEntryList, "selezione");
                BarData barData = new BarData(bardataset);
                bardataset.setColor(colorAccent);                                                   // imposta il colore delle barre del grafico
                barData.setBarWidth(0.9f);                                                          // imposta  la larghezza  delle barre del grafico

                // associa i dati (barData) nel grafico
                chart.setFitBars(true);                                                             // adatta la dimensione delle barre per rientrare nei limiti del grafico
                chart.setData(barData);
                chart.setDescription(description);

                // imposta dimensione , colore e testo dell'asse x
                XAxis x1 = chart.getXAxis();
                x1.setAxisLineWidth(2f);
                x1.setGridColor(labelColor);
                x1.setTextColor(labelColor);

                // imposta dimensione , colore e testo dell'asse y
                YAxis left1 = chart.getAxisLeft();
                left1.setAxisLineWidth(2f);
                left1.setGridColor(labelColor);
                left1.setTextColor(labelColor);

                // imposta dimensione , colore e testo dell'asse sulla destra del grafico
                YAxis right1 = chart.getAxisRight();
                right1.setAxisLineWidth(2f);
                right1.setGridColor(labelColor);
                right1.setDrawLabels(false);

                // aggiorna la view
                chart.invalidate();

                //Creazione del pieChart
                pieChart = findViewById(R.id.pieChart);

                for (int i = 0; i < num.size(); i++) {                                              //Calcolo il totale della selezione fatta
                    sum += num.get(i);
                }
               EntryPie.add(new PieEntry(sum, "Selezione"));                                  //Creazione della entry con il totale della selezione
               EntryPie.add(new PieEntry(60360000, "popolazione italiana"));            //Entry in cui viene inserita la popolazione totale

                PiedataSet = new PieDataSet(EntryPie, "");                                    //Genero il dataSet

                //Genero la Legenda e la personalizzo
                legend = pieChart.getLegend();
                legend.setEnabled(swLegend);
                legend.setTextColor(labelColor);
                pieData = new PieData(PiedataSet);

                //Personalizzazione delle etichette del PieChart
                pieChart.setUsePercentValues(true);                                                 //Calcolo dati in percentuale
                pieData.setValueFormatter(new MyValueFormatter(pieChart));                          //Mostra la percentuale
                pieData.setDataSet(PiedataSet);
                pieData.setValueTextSize(18f);
                pieChart.setEntryLabelTextSize(18f);

                //Personalizzazione del grafico
                pieChart.setHoleRadius(percentage/2);                                               //Imposto larghezza foro centrale
                pieChart.setData(pieData);
                PiedataSet.setColors(new int[] {R.color.colorAccent, R.color.colorPrimary}, getApplicationContext()); //Personalizzazione colori
                pieChart.setHoleColor(background);
                pieChart.setCenterText("JARANOF\ncovid19 MPchart");
                pieChart.setCenterTextSize(20f);
                pieChart.setCenterTextColor(labelColor);

                pieChart.invalidate(); // refresh
//.0
                break;
            case "a Linee":

                //Genero e popolo le Entry tramite i dati scaricati
                List<Entry> LineEntryList = new ArrayList<>();
                for (int i = 0; i < num.size(); i++) {
                    LineEntryList.add(new Entry(i, num.get(i)));                                    //Aggiungo Entry sequenzialmente
                }

                //Genero il Grafico a Linee
                setContentView(R.layout.activity_line_chart);
                final LineChart lineChart = findViewById(R.id.lineChart);

                //genero il marker
                mv = new MyMarkerView(this, R.layout.marker_view);
                lineChart.setMarker(mv);

                //Metodo che gestisce le gesture
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

                //Abilito e imposto la legenda
                legend = lineChart.getLegend();
                legend.setEnabled(swLegend);
                legend.setTextColor(labelColor);
                //Imposto le interazioni possibili col grafico
                lineChart.setTouchEnabled(true);
                lineChart.setDragEnabled(true);
                lineChart.setPinchZoom(true);
                //Genero il dataSet
                LineDataSet dataSet = new LineDataSet(LineEntryList, "Selezione");
                dataSet.setColors(colorAccent);                                                     //Personalizzo il colore delle linee
                dataSet.setDrawValues(false);                                                       //Disabilito le etichette
                dataSet.setDrawCircles(false);                                                      //Disabilito i punti tra le linee
                dataSet.setDrawHighlightIndicators(true);                                           //Evidenzio la entry selezionata
                //Passo i dati al grafico
                LineData lineData = new LineData(dataSet);
                lineChart.setData(lineData);
                lineChart.setDescription(description);
                lineChart.getHighlighted();

                // imposta dimensione , colore e testo dell'asse x
                XAxis x = lineChart.getXAxis();
                x.setAxisLineWidth(2f);
                x.setGridColor(labelColor);
                x.setTextColor(labelColor);

                // imposta dimensione , colore e testo dell'asse y
                YAxis left = lineChart.getAxisLeft();
                left.setAxisLineWidth(2f);
                left.setGridColor(labelColor);
                left.setTextColor(labelColor);

                // imposta dimensione , colore e testo dell'asse sulla destra del grafico
                YAxis right = lineChart.getAxisRight();
                right.setAxisLineWidth(2f);
                right.setGridColor(labelColor);
                right.setDrawLabels(false);

                lineChart.invalidate(); // refresh

                //Creazione pieChart
                pieChart = findViewById(R.id.pieChart);

                for (int i = 0; i < num.size(); i++) {                                              //Calcolo il totale della selezione fatta
                    sum += num.get(i);
                }
                EntryPie.add(new PieEntry(sum, "selezione"));                                 //Creazione della entry con il totale della selezione
                EntryPie.add(new PieEntry(60360000, "popolazione italiana"));           //Entry in cui viene inserita la popolazione totale

                PiedataSet = new PieDataSet(EntryPie, "");                                    //Genero il dataSet

                //Genero la Legenda e la personalizzo
                legend = pieChart.getLegend();
                legend.setEnabled(swLegend);
                legend.setTextColor(labelColor);
                pieData = new PieData(PiedataSet);

                //Personalizzazione delle etichette del PieChart
                pieChart.setUsePercentValues(true);                                                 //Calcolo dati in percentuale
                pieData.setValueFormatter(new MyValueFormatter(pieChart));                          //Mostra la percentuale
                pieData.setDataSet(PiedataSet);
                pieData.setValueTextSize(18f);
                pieChart.setEntryLabelTextSize(18f);

                //Personalizzazione del grafico
                pieChart.setHoleRadius(percentage/2);                                               //Imposto larghezza foro centrale
                pieChart.setData(pieData);
                PiedataSet.setColors(new int[] {R.color.colorPrimary, R.color.colorAccent}, getApplicationContext());
                pieChart.setHoleColor(background);
                pieChart.setCenterText("JARANOF\ncovid19 MPchart");
                pieChart.setCenterTextSize(20f);
                pieChart.setCenterTextColor(labelColor);

                pieChart.invalidate(); // refresh
//.0
            break;
            default:
                //TODO
        }
    }

    //Classe per formattare le Entry del pieChart in percentuale
    public class MyValueFormatter extends PercentFormatter {
        DecimalFormat mFormat;
        PieChart mPieChart;

        public MyValueFormatter(PieChart pieChart){
            mFormat = new DecimalFormat("###,###,##0.0");
            mPieChart = pieChart;
        }


        @Override
        public String getFormattedValue(float value) {
            return mFormat.format(value) + "%";
        }

        @Override
        public String getPieLabel(float value, PieEntry pieEntry) {
            if (mPieChart != null && mPieChart.isUsePercentValuesEnabled()) {
                // Converto in percentuale
                return getFormattedValue(value);
            } else {
                // Valore grezzo, sposto il valore della percentuale
                return mFormat.format(value);
            }
        }
    }

    //Classe per genereare il marker
    public class MyMarkerView extends MarkerView {

        private TextView tvContent;

        public MyMarkerView(Context context, int layoutResource) {
            super(context, layoutResource);
            tvContent = findViewById(R.id.tvContent);
        }

        //Adatta i dati disegnati dal marker
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
            return new MPPointF(-(getWidth() /2), -getHeight());
        }


    }

}
