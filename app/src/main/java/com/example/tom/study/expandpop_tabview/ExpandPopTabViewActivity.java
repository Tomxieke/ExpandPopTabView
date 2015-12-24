package com.example.tom.study.expandpop_tabview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tom.study.R;
import com.warmtel.expandtab.ExpandPopTabView;
import com.warmtel.expandtab.KeyValueBean;
import com.warmtel.expandtab.PopOneListView;
import com.warmtel.expandtab.PopTwoListView;

import java.util.ArrayList;
import java.util.List;

public class ExpandPopTabViewActivity extends Activity {
    private ExpandPopTabView mPopTabView;
    ArrayList<KeyValueBean> list ;
    ArrayList<KeyValueBean> parentsList;
    ArrayList<ArrayList<KeyValueBean>> childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_pop_tab_view);
        mPopTabView = (ExpandPopTabView) findViewById(R.id.expand_tab_view);

        setData();  //一级菜单数据源。
        setSecondMenuData();//二级菜单数据源

        PopOneListView popOneListView = new PopOneListView(this);
        popOneListView.setCallBackAndData(list, mPopTabView, new PopOneListView.OnSelectListener() {
            @Override
            public void getValue(String key, String value) {
                Toast.makeText(ExpandPopTabViewActivity.this,value,Toast.LENGTH_SHORT).show();
            }
        });
        mPopTabView.addItemToExpandTab("价格",popOneListView); //添加上去


        PopTwoListView popTwoListView = new PopTwoListView(this);
        popTwoListView.setDefaultSelectByValue("四川","成都"); //二级菜单的默认选项必须设置，不然报数组越位
        popTwoListView.setCallBackAndData(mPopTabView, parentsList, childList, new PopTwoListView.OnSelectListener() {
            @Override
            public void getValue(String showText, String parentKey, String childrenKey) {
                Toast.makeText(ExpandPopTabViewActivity.this,showText,Toast.LENGTH_SHORT).show();
            }
        });
        mPopTabView.addItemToExpandTab("区域",popTwoListView);

        //当然也可以封装直接添加的方法：
        addItem(mPopTabView,list,"默认","排序");
        addItem(mPopTabView,parentsList,childList,"重庆","渝北","城市");

    }

    /**
     * 一级菜单添加数据的方法封装
     * @param expandTabView  自定义的View
     * @param lists     数据源
     * @param defaultSelect     默认选中项
     * @param defaultShowText   没人显示项
     */
    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> lists, String defaultSelect, String defaultShowText) {
        PopOneListView popOneListView = new PopOneListView(this);
        popOneListView.setDefaultSelectByValue(defaultSelect);
        //popViewOne.setDefaultSelectByKey(defaultSelect);
        popOneListView.setCallBackAndData(lists, expandTabView, new PopOneListView.OnSelectListener() {
            @Override
            public void getValue(String key, String value) {
                //弹出框选项点击选中回调方法
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popOneListView);
    }

    /**
     * 二级菜单
     * @param expandTabView
     * @param parentLists  主数据集
     * @param childrenListLists  子数据集
     * @param defaultParentSelect
     * @param defaultChildSelect
     * @param defaultShowText
     */
    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> parentLists,
                        List<ArrayList<KeyValueBean>> childrenListLists, String defaultParentSelect, String defaultChildSelect, String defaultShowText) {
        PopTwoListView popTwoListView = new PopTwoListView(this);
        popTwoListView.setDefaultSelectByValue(defaultParentSelect, defaultChildSelect);
        //distanceView.setDefaultSelectByKey(defaultParent, defaultChild);
        popTwoListView.setCallBackAndData(expandTabView, parentLists, childrenListLists, new PopTwoListView.OnSelectListener() {
            @Override
            public void getValue(String showText, String parentKey, String childrenKey) {
                //弹出框选项点击选中回调方法
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popTwoListView);
    }

    /**
     * 二级菜单数据源
     */
    void setSecondMenuData(){
        parentsList = new ArrayList<>();
        KeyValueBean parentBean = new KeyValueBean();
        parentBean.setKey("1");
        parentBean.setValue("四川");
        parentsList.add(parentBean);

        parentBean = new KeyValueBean();
        parentBean.setKey("2");
        parentBean.setValue("重庆");
        parentsList.add(parentBean);

        parentBean = new KeyValueBean();
        parentBean.setKey("3");
        parentBean.setValue("云南");
        parentsList.add(parentBean);
        //==================================================

        childList = new ArrayList<>();
        ArrayList<KeyValueBean>  sclist = new ArrayList<>();
        KeyValueBean bean = new KeyValueBean();
        bean.setKey("11");
        bean.setValue("成都");
        sclist.add(bean);

        bean = new KeyValueBean();
        bean.setKey("12");
        bean.setValue("绵阳");
        sclist.add(bean);

        bean = new KeyValueBean();
        bean.setKey("13");
        bean.setValue("德阳");
        sclist.add(bean);

        bean = new KeyValueBean();
        bean.setKey("14");
        bean.setValue("宜宾");
        sclist.add(bean);
        childList.add(sclist);


        ArrayList<KeyValueBean>  cqlist = new ArrayList<>();
        bean = new KeyValueBean();
        bean.setKey("21");
        bean.setValue("渝北");
        cqlist.add(bean);

        bean = new KeyValueBean();
        bean.setKey("22");
        bean.setValue("渝中");
        cqlist.add(bean);

        bean = new KeyValueBean();
        bean.setKey("23");
        bean.setValue("江北");
        cqlist.add(bean);

        bean = new KeyValueBean();
        bean.setKey("24");
        bean.setValue("沙坪坝");
        cqlist.add(bean);
        childList.add(cqlist);

        ArrayList<KeyValueBean>  shlist = new ArrayList<>();
        bean = new KeyValueBean();
        bean.setKey("31");
        bean.setValue("昆明");
        shlist.add(bean);

        bean = new KeyValueBean();
        bean.setKey("32");
        bean.setValue("丽江");
        shlist.add(bean);

        bean = new KeyValueBean();
        bean.setKey("33");
        bean.setValue("香格里拉");
        shlist.add(bean);

        bean = new KeyValueBean();
        bean.setKey("34");
        bean.setValue("凯里");
        shlist.add(bean);
        childList.add(shlist);

   //     childList.add(shlist);





    }

    /**
     * 一级菜单数据源
     */
    void setData(){
        list = new ArrayList<>();
        KeyValueBean bean = new KeyValueBean();
        bean.setKey("A");
        bean.setValue("200元");
        list.add(bean);

        bean = new KeyValueBean();
        bean.setKey("B");
        bean.setValue("300元");
        list.add(bean);

        bean = new KeyValueBean();
        bean.setKey("C");
        bean.setValue("400元");
        list.add(bean);

        bean = new KeyValueBean();
        bean.setKey("D");
        bean.setValue("500元");
        list.add(bean);
    }
}
