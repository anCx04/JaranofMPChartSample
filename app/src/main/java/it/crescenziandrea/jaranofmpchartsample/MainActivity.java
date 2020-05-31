package it.crescenziandrea.jaranofmpchartsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ciao3

        new Holder();

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
            //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
            //Log.w("CA", "onrespons");

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

            //fill(response);

        }
    }


    class Holder implements View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

        final Button btGenerate;
        final Volley model ;
        private List<dati> data;
        final Spinner spChartType;
        final Spinner spChartArgument;
        final Switch swEnabledLegend;
        final Switch swEnabledLabel;

        final Bundle bundle = new Bundle();
        putData putdata = new putData();
        Intent intent;

        Holder(){

            swEnabledLabel = findViewById(R.id.swLabelEnable);
            swEnabledLegend = findViewById(R.id.swEnableLegend);
            spChartType = findViewById(R.id.spChartSelection);
            spChartArgument = findViewById(R.id.spChartArgument);
            btGenerate = findViewById(R.id.btGenerate);
            btGenerate.setOnClickListener(this);

            spinnerAdapter(spChartType, R.array.ChartType);
            spinnerAdapter(spChartArgument, R.array.ChartArgument);

            swEnabledLegend.setOnCheckedChangeListener(this);
            swEnabledLabel.setOnCheckedChangeListener(this);

            spChartArgument.setOnItemSelectedListener(this);
            spChartType.setOnItemSelectedListener(this);

            this.model = new Volley() {
                @Override
                void fill(List<dati> cnt) {
                    filltext(cnt);
                    data = cnt;
                    Log.w("CA", String.valueOf(data));
                }
            };
        }

        private void spinnerAdapter(Spinner spChart, int ItemType) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), ItemType, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spChart.setAdapter(adapter);
        }

        private  void filltext(List<dati> cnt) {

        }



        int i= 0;

        @Override
        public void onClick(View v) {

            i = i+1;

            model.search();
            Log.w("CA", "onclick");
            /*
            if(i == 2) {
            i=0;


            ArrayList<String> prova1 = new ArrayList<>();
            prova1.add(data.get(0).getData());
            prova1.add(data.get(1).getData());
            prova1.add(data.get(2).getData());

            ArrayList<Integer> prova2 = new ArrayList<Integer>();
            prova2.add(data.get(0).getTotale_ospedalizzati());
            prova2.add(data.get(1).getTotale_ospedalizzati());
            prova2.add(data.get(2).getTotale_ospedalizzati());

             Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("prova1",prova1);
            bundle.putIntegerArrayList("prova2",prova2);
            intent.putExtras(bundle);


                startActivity(intent);
            }
*/
            if(v == btGenerate) {
                putdata.getAll();
            }
        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selection = parent.getItemAtPosition(position).toString();
            putdata.setSelection(selection);
            Toast.makeText(parent.getContext(), selection, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(!swEnabledLabel.isChecked()) {
                Toast.makeText(getApplicationContext(), "label on", Toast.LENGTH_LONG).show();
                putdata.setSwLabel(isChecked);
            }
            else {
                Toast.makeText(getApplicationContext(), "label off", Toast.LENGTH_LONG).show();
                putdata.setSwLabel(isChecked);
            }
            if(!swEnabledLegend.isChecked()) {
                Toast.makeText(getApplicationContext(), "legend on", Toast.LENGTH_LONG).show();
                putdata.setSwLegend(isChecked);
            }
            else {
                Toast.makeText(getApplicationContext(), "legend off", Toast.LENGTH_LONG).show();
                putdata.setSwLegend(isChecked);
            }
        }

    }




    public class putData{
        Intent intent = new Intent(getApplicationContext(), buildChart.class);
        public void setSelection(String selection){ intent.putExtra("selection", selection);}
        public void setSwLabel(Boolean isChecked){ intent.putExtra("swLabel", isChecked);}
        public void setSwLegend(Boolean isChecked){ intent.putExtra("swLegend", isChecked);}

        public void getAll(){ startActivity(intent);}
    }
}
