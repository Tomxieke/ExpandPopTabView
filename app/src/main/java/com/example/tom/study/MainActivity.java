package com.example.tom.study;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.tom.study.expandpop_tabview.ExpandPopTabViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Map<String, Object>> data = this.getData(); // 调用方法得到资源文件
        setListAdapter(new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_1, // 实列化适配器
                new String[] { "title" }, new int[] { android.R.id.text1 }));

        setSelection(data.size()-1);  //设置进入时光标处于最底部
    }


    protected List<Map<String, Object>> getData() { // 封装添加资源方法
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>(); // 用来保存数据源
        addItem(data,"ExpandPopTabViewActivity", ExpandPopTabViewActivity.class);
        return data;
    }

    protected void addItem(List<Map<String, Object>> data, String name,
                           Class<?> c) { // 封装Intent
        addItem(data, name, new Intent(this, c));
    }

    protected void addItem(List<Map<String, Object>> data, String name,
                           Intent intent) { // 向数据源中添加内容的方法
        Map<String, Object> temp = new HashMap<String, Object>(); // 实列化集合对象
        temp.put("title", name); // 向集合中添加元素
        temp.put("intent", intent);
        data.add(temp); // 向数据源中添加元素
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) l
                .getItemAtPosition(position);
        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
