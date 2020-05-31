package it.crescenziandrea.jaranofmpchartsample;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    TextView tx_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> data = extras.getStringArrayList("prova1");
        ArrayList<String> num = extras.getStringArrayList("prova2");


        tx_view = findViewById(R.id.textView);
       tx_view.setText(data.get(data.size()-1)+"   "+String.valueOf(num.get(num.size()-1)));



    }

}
