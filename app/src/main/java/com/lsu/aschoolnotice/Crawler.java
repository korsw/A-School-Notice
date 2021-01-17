package com.lsu.aschoolnotice;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Crawler {

    public static List Covidlist = new ArrayList();
    public static List Employmentlist = new ArrayList();
    public static List Bachelorlist = new ArrayList();

    public static void start() throws IOException {


        Covidlist = crawling("https://homepage.sch.ac.kr/sch/06/06050000.jsp");

        Employmentlist = crawling("https://homepage.sch.ac.kr/sch/06/06010000.jsp");

        Bachelorlist = crawling("https://homepage.sch.ac.kr/sch/05/05040100.jsp");
    }

    public static List crawling(String url) throws IOException {
        List list = new ArrayList();
        Document Notice = Jsoup.connect(url).get();
        Elements NoticeEL = Notice.getElementsByAttributeValue("class","lmode");
        Iterator<Element> El1 = NoticeEL.select("td").iterator();

        while (El1.hasNext()){
            list.add(El1.next().text());
        }

        return list;
    }
}
