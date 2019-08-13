package com.example.lm.count_people.Fragemnt;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lm.count_people.R;
import com.example.lm.count_people.dto.VideoCount;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import static com.example.lm.count_people.Fragemnt.FragmentCountList.list;

public class FragmentLineChart extends Fragment {
    private static final String TAG ="----777---" ;
    private LineChart lineChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private LimitLine limitLine;

    private List<String> xData = new ArrayList<>();
    private List<Integer> yData = new ArrayList<>();
    public FragmentLineChart() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.people_linechart, container, false);
        lineChart = view.findViewById(R.id.lineChart);
        initChart(lineChart);
        showLineChart(list, "人数统计情况", Color.CYAN);
        return view;
    }


    private void initChart(LineChart lineChart) {
        //图表设置//
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(true);
        lineChart.setDragDecelerationFrictionCoef(0.9f);


        //是否有触摸事件
        lineChart.setTouchEnabled(true);

        //设置一页最大显示个数为6，超出部分就滑动
        float ratio = (float) 77/(float) 6;

        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
        lineChart.zoom(ratio,1f,0,0);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);

        lineChart.setNoDataText("暂无数据");
        //设置背景
        lineChart.setBackgroundColor(Color.WHITE);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //XY轴的设置//
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        //一个界面显示6个Lable
        xAxis.setLabelCount(6);
        //设置最小间隔，防止当放大时出现重复标签
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        //设置为true当一个页面显示条目过多，X轴值隔一个显示一个
        xAxis.setGranularityEnabled(true);

        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(true);

        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);

        leftYAxis.enableGridDashedLine(10f, 10f, 0f);
        rightYaxis.setEnabled(false);
        //折线图例 标签 设置//
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);

        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                //对X轴上的值进行Format格式化，转成相应的值
                int intValue = (int) value;

                //筛选出自己需要的值，一般都是这样写没问题，并且一定要加上这个判断，不然会出错
                if (xData.size() > intValue && intValue >= 0) {
                    return xData.get(intValue);
                } else {
                    return "";
                }
            }
        });

    }

    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }


    public void showLineChart(List<VideoCount> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            VideoCount data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            // Entry entry = new Entry(Float.parseFloat(data.getCurrentTime()), (float) data.getCurrentPeople());
            //entries.add(entry);
            xData.add(data.getCurrentTime());
            yData.add(data.getCurrentPeople());
            entries.add(new Entry(i, Float.valueOf(yData.get(i)), xData.get(i)));
        }

        Log.i(TAG, "x轴的最大个数:"+xData.size());

        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);

        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);


    }
}
