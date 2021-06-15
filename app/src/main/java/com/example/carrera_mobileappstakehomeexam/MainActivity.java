package com.example.carrera_mobileappstakehomeexam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText totalStudents, As, Bs, Cs, Ds, Fs;
    Button btn1;
    Float total, aStudents, bStudents, cStudents, dStudents, fStudents, percentA, percentB,
            percentC, percentD, percentF;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("studentsInfo", MODE_PRIVATE);

        totalStudents = (EditText) findViewById(R.id.totalNumberOfStudents);
        As = (EditText) findViewById(R.id.numberOfAs);
        Bs = (EditText) findViewById(R.id.numberOfBs);
        Cs = (EditText) findViewById(R.id.numberOfCs);
        Ds = (EditText) findViewById(R.id.numberOfDs);
        Fs = (EditText) findViewById(R.id.numberOfFs);
        btn1 = (Button) findViewById(R.id.computeBtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (totalStudents.getText().toString().isEmpty() || As.getText().toString().isEmpty() || Bs.getText().toString().isEmpty() ||
                        Cs.getText().toString().isEmpty() || Ds.getText().toString().isEmpty() || Fs.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please do not leave anything blank", Toast.LENGTH_SHORT).show();
                }else{
                    total = Float.parseFloat(totalStudents.getText().toString());
                    aStudents = Float.parseFloat(As.getText().toString());
                    bStudents = Float.parseFloat(Bs.getText().toString());
                    cStudents = Float.parseFloat(Cs.getText().toString());
                    dStudents = Float.parseFloat(Ds.getText().toString());
                    fStudents = Float.parseFloat(Fs.getText().toString());

                    if (total != aStudents + bStudents + cStudents + dStudents + fStudents){
                        Toast.makeText(MainActivity.this, "Please check your numbers", Toast.LENGTH_SHORT).show();
                    }else{
                        percentA = getPercentage(total, aStudents);
                        percentB = getPercentage(total, bStudents);
                        percentC = getPercentage(total, cStudents);
                        percentD = getPercentage(total, dStudents);
                        percentF = getPercentage(total, fStudents);

                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putFloat("total", total);
                        editor.putFloat("numA", percentA);
                        editor.putFloat("numB", percentB);
                        editor.putFloat("numC", percentC);
                        editor.putFloat("numD", percentD);
                        editor.putFloat("numF", percentF);
                        editor.apply();

                        final ViewGroup subView = (ViewGroup) getLayoutInflater().// inflater view
                                inflate(R.layout.costum_alert_view, null, false);
                        ((TextView) subView.findViewById(R.id.percentOfA)).setText("As: " + percentA + "%");
                        ((TextView) subView.findViewById(R.id.percentOfB)).setText("Bs: " + percentB + "%");
                        ((TextView) subView.findViewById(R.id.percentOfC)).setText("Cs: " + percentC + "%");
                        ((TextView) subView.findViewById(R.id.percentOfD)).setText("Ds: " + percentD + "%");
                        ((TextView) subView.findViewById(R.id.percentOfF)).setText("Fs: " + percentF + "%");
                        AlertDialog dialog = new AlertDialog.Builder( MainActivity.this)
                                .setTitle("The Percentages of the grade distributions are:")
                                .setView(subView)
                                .create();
                        dialog.show();

                        startActivity(new Intent(MainActivity.this, BarChartActivity.class));
                        finish();
                    }
                }
            }
        });


    }

    public float getPercentage(float total, float num)
    {
        return (num / total) * 100;

    }
}