package com.example.greeknews.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greeknews.R;
import com.example.greeknews.base.BaseFragment;
import com.example.greeknews.bean.V2exTabBean;
import com.example.greeknews.ptrsenter.V2exPersenter;
import com.example.greeknews.view.V2exView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class V2exFragment extends BaseFragment<V2exView,V2exPersenter> implements V2exView {
    private static final String TAG = "V2exFragment";
    private String mUrl = "https://www.v2ex.com/";

    @BindView(R.id.v2tab)
    TabLayout v2tab;

    @BindView(R.id.v2vp)
    ViewPager v2vp;
    private ArrayList<String> title;
    private ArrayList<Fragment> list;

    @Override
    protected V2exPersenter initPresenter() {
        return new V2exPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        title = new ArrayList<>();

    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<V2exTabBean> tabsList = new ArrayList<>();
                    Document doc = Jsoup.connect(mUrl).get();
                    //查找id是Tabs的div元素,因为只有一个,直接调用了first()
                    Element tabs = doc.select("div#Tabs").first();
                    //查找带有href属性的a元素
                    Elements allTabs = tabs.select("a[href]");
                    for (Element element :allTabs) {
                        String linkHref = element.attr("href");
                        String tab = element.text();
                        //Log.d(TAG, "linkHref: "+linkHref+",tab:"+tab);
                        V2exTabBean bean = new V2exTabBean(linkHref, tab);
                        tabsList.add(bean);

                    }

                    Log.d(TAG, "tabsList: "+tabsList.toString());

                    //新闻的子条目数据
                    Elements items = doc.select("div.cell.item");
                    for (Element element:items) {
                        //图片
                        Element image = element.select("table tbody tr td > a >img.avatar").first();
                        String src = image.attr("src");
                        //Log.d(TAG, "图片: "+src);

                        //评论,有可能没有,需要判空
                        Element comment = element.select("table tbody tr td >a.count_livid").first();
                        if (comment != null){
                            String commentCount = comment.text();
                            String href = comment.attr("href");
                            //Log.d(TAG, "评论数量: "+commentCount+",href:"+href);
                        }

                        //新闻的主体信息
                        Element titleElement = element.select("table tbody tr td span.item_title > a").first();
                        String text = titleElement.text();
                        Log.d(TAG, "标题: "+text);

                        //评论的信息
                        Elements topicElement = element.select("table tbody tr td span.topic_info");
                        String topic = topicElement.text();
                        Log.d(TAG, "topic: "+topic);

                        Element secondTab  = topicElement.select("a.node").first();
                        String text1 = secondTab.text();
                        Log.d(TAG, "二类的Tab:"+text1);

                        Elements people = topicElement.select("strong > a");
                        if (people.size()>0){
                            Element author = people.get(0);
                            Log.d(TAG, "作者: "+author.text());
                        }

                        if (people.size()>1){
                            Element commentPeople = people.get(1);
                            Log.d(TAG, "评论人: "+commentPeople.text());
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
