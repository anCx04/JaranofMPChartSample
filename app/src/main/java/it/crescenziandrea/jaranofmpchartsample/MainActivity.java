package it.crescenziandrea.jaranofmpchartsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ciao3

        new Holder();

    }

    abstract class  Volley implements Response.ErrorListener, Response.Listener<String>{
        abstract void fill(String string);

       public void search() {
            String url = "https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-andamento-nazionale-latest.json";
           //String url ="https://www.google.it";

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
            fill(response);

        }
    }



    class Holder implements View.OnClickListener{

        final TextView tx_search;
        final Button bt_search;
        final Volley model ;

        Holder(){


            tx_search = findViewById(R.id.tx_serach);
            bt_search = findViewById(R.id.bt_search);
            bt_search.setOnClickListener(this);

            this.model = new Volley() {
                @Override
                void fill(String string) {
                    filltext(string);
                }
            };

        }

        private  void filltext(String str){
            tx_search.setText(str);
        }

        @Override
        public void onClick(View v) {
            model.search();
            Log.w("CA", "onclick");
        }
    }
}
