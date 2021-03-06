package com.example.snackler.snackler;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.example.snackler.snackler.ToolBarSetup;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

/**
 * Created by magneta94 on 12/5/16.
 */


//CODE THAT SUPPORTS THE GRAPH;

public class SnackStats extends Fragment {

    private static String TAG = "MainActivity";
    public FragmentActivity mainStats;
    private float[] yData = {30f, 70f};
    private String[] xData = {"Remaining", "Used"};
    PieChart pieChart ;
    SnackDay data;
    SnackEntry testEntry;

    public SnackStats() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.activity_stats, container, false);
        pieChart = (PieChart) view.findViewById(R.id.idPieChart);
        init();
        return view;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

    }


    public void init(){


        testEntry = new SnackEntry("Grape");
        testEntry.setCalories(500);

        data = new SnackDay("Tuesday,December 6th,");
        data.setDailyCalories(800);
        data.countCalories();




        Log.d(TAG, "onCreate: starting to create chart");




        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Remaining Snack Calories");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawSliceText(false);
        pieChart.setTransparentCircleRadius(20f);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("(sum): ");
                String sales = e.toString().substring(pos1 + 7);

                for(int i = 0; i < yData.length; i++){
                    if(yData[i] == Float.parseFloat(sales)){
                        pos1 = i;
                        break;
                    }
                }
                String employee = xData[pos1 + 1];

            }

            @Override
            public void onNothingSelected() {

            }
        });





    }


    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , xData[i]));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(4);
        pieDataSet.setValueTextSize(12);


        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.argb(255,81,149,72));
        colors.add(Color.argb(255,136,196,37));


        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);


    }

}

