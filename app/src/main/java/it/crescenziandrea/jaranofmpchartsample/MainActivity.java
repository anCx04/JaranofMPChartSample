package it.crescenziandrea.jaranofmpchartsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    Holder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ciao3

        holder = new Holder();

    }

    abstract class  Volley implements Response.ErrorListener, Response.Listener<String>{
        abstract void fill(List<dati> cnt);

       public void search() {
           // String url = "https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-andamento-nazionale-latest.json";
           String url ="https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-andamento-nazionale.json";
           apiCall(url);
           Log.w("CA", "search");
        }

        private void apiCall(String url) {
            RequestQueue requestQueue;
            requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(MainActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    url,
                    this,
                    this);
            requestQueue.add(stringRequest);
            Log.w("CA", "apicall");
        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }

        @Override
        public void onResponse(String response) {

            Gson gson = new Gson();
            try {

                Type listType = new TypeToken<List<dati>>() {
                }.getType();
                List<dati> cnt = gson.fromJson(response, listType);
                if (cnt != null && cnt.size() > 0) {
                    Log.w("CA", "" + cnt.size());
                    fill(cnt);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            holder.getAll();

        }
    }


    class Holder implements View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

        final Button btGenerate;
        final Volley model;
        final Spinner spChartType;
        final Spinner spChartArgument;
        final Switch swEnabledLegend;
        final SeekBar sbHole;
        final TextView tvProgress;
        private int selectionData = 99;
        private Bundle bundle = new Bundle();
        private Intent intent = new Intent(getApplicationContext(),buildChart.class);

        Holder() {

            swEnabledLegend = findViewById(R.id.swEnableLegend);
            spChartType = findViewById(R.id.spChartSelection);
            spChartArgument = findViewById(R.id.spChartArgument);
            tvProgress = findViewById(R.id.tvProgress);
            btGenerate = findViewById(R.id.btGenerate);
            sbHole = findViewById(R.id.sbHole);
            btGenerate.setOnClickListener(this);

            spinnerAdapter(spChartType, R.array.ChartType);
            spinnerAdapter(spChartArgument, R.array.ChartArgument);
            sbHole.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    tvProgress.setText(""+progress+"%");
                    bundle.putInt("percentage", progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            swEnabledLegend.setOnCheckedChangeListener(this);

            spChartArgument.setOnItemSelectedListener(this);
            spChartType.setOnItemSelectedListener(this);

            this.model = new Volley() {
                @Override
                void fill(List<dati> cnt) {
                    filltext(cnt);
                    //data = cnt;

                }
            };
        }

        private void spinnerAdapter(Spinner spChart, int ItemType) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), ItemType, R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            spChart.setAdapter(adapter);
        }

        private void filltext(List<dati> cnt) {
            ArrayList<Integer> dati = new ArrayList<>();
            switch(selectionData) {
                case 0:

                    dati.add(cnt.get(0).getTamponi());
                    for (int i = 1; i < cnt.size(); i++) {
                        dati.add(cnt.get(i).getTamponi()-cnt.get(i-1).getTamponi());
                        Log.w("CA", "for "+i+":"+cnt.get(i).getTamponi());
                    }
                    setData(dati);
                    break;
                case 1:
                    for (int i = 0; i < cnt.size(); i++) {
                        dati.add(cnt.get(i).getTotale_positivi());
                    }
                    setData(dati);
                    break;
                case 2:
                    dati.add(cnt.get(0).getDimessi_guariti());
                    for (int i = 1; i < cnt.size()-1; i++) {
                        dati.add(cnt.get(i).getDimessi_guariti()-cnt.get(i-1).getDimessi_guariti());
                    }
                    setData(dati);
                    break;
                default:
                    break;
            }

        }



        @Override
        public void onClick(View v) {

            model.search();
            Log.w("CA", "onclick");
        }
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selection = parent.getItemAtPosition(position).toString();

            if(selection.equals("Tamponi effettuati")) {
                selectionData = 0;

            }
            else if(selection.equals("Contagiati")){
                selectionData = 1;
            }
            else if(selection.equals("Guariti")) {
                selectionData = 2;
            }

            if (selection.equals("a Barre") || selection.equals("a Linee")) {
                setSelection(selection);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (!swEnabledLegend.isChecked()) {
                setSwLegend(isChecked);
            } else {
                setSwLegend(isChecked);
            }
        }

        private void setSelection(String selection) {
            bundle.putString("selection", selection);
        }


        private void setSwLegend(Boolean isChecked) { bundle.putBoolean("swLegend", isChecked); }

        private void setData(ArrayList<Integer> data) {
            bundle.putIntegerArrayList("dati",data);
        }

        public void getAll() {
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }


}
