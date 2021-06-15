package com.example.carrera_mobileappstakehomeexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

//I Learned how to use MPAndroidChart api by watching this tutorial: https://www.youtube.com/watch?v=vhKtbECeazQ&t=518s
public class BarChartActivity extends AppCompatActivity {
    SharedPreferences preferences;
    float storedTotal, storedAs, storedBs, storedCs, storedDs, storedFs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        preferences = getSharedPreferences("studentsInfo", MODE_PRIVATE);
        storedTotal = preferences.getFloat("total", 0);
        storedAs = preferences.getFloat("numA", 0);
        storedBs = preferences.getFloat("numB", 0);
        storedCs = preferences.getFloat("numC", 0);
        storedDs = preferences.getFloat("numD", 0);
        storedFs = preferences.getFloat("numF", 0);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> students = new ArrayList<>();
        students.add(new BarEntry(1, storedAs));
        students.add(new BarEntry(2, storedBs));
        students.add(new BarEntry(3, storedCs));
        students.add(new BarEntry(4, storedDs));
        students.add(new BarEntry(5, storedFs));

        BarDataSet barDataSet = new BarDataSet(students, "Students");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.setBackgroundColor(Color.YELLOW);
        barChart.getDescription().setText("Students Chart");
        barChart.animateY(2000);


        //Learned how to use ValueFormatter by using these resources:
        //https://weeklycoding.com/mpandroidchart-documentation/formatting-data-values/
        //https://stackoverflow.com/questions/39945375/how-to-set-the-x-axis-label-with-mpandroidchart
        ValueFormatter xAxisFormatter = new StudentAxisValueFormatter(barChart);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(24f);
        xAxis.setValueFormatter(xAxisFormatter);
    }
}