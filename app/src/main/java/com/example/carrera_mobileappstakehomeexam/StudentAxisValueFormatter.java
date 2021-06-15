package com.example.carrera_mobileappstakehomeexam;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

//Learned how to use ValueFormatter by using these resources:
//https://weeklycoding.com/mpandroidchart-documentation/formatting-data-values/
//https://stackoverflow.com/questions/39945375/how-to-set-the-x-axis-label-with-mpandroidchart
public class StudentAxisValueFormatter extends ValueFormatter {

    private final BarLineChartBase<?> chart;

    public StudentAxisValueFormatter(BarLineChartBase<?> chart){
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value) {
        String s = "";
        switch ((int) value){
            case 1:
                s = "A%";
                break;
            case 2:
                s = "B%";
                break;
            case 3:
                s = "C%";
                break;
            case 4:
                s = "D%";
                break;
            case 5:
                s = "F%";
                break;
        }

        return s;
    }
}
