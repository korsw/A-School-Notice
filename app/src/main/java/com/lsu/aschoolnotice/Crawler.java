package com.lsu.aschoolnotice;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class Crawler {
    public static void main(String[] args) throws IOException {
        System.out.println("======우한 공지사항======");
        crawling("https://homepage.sch.ac.kr/sch/06/06050000.jsp");

        System.out.println("======취업 공지사항======");
        crawling("https://homepage.sch.ac.kr/sch/06/06010000.jsp");

        System.out.println("======학사 공지사항======");
        crawling("https://homepage.sch.ac.kr/sch/05/05040100.jsp");
    }
    public static String covidNotice() throws IOException{
        String string = crawling("https://homepage.sch.ac.kr/sch/06/06050000.jsp");
        return string;
    }

    public static String crawling(String url) throws IOException {
        Document Notice = Jsoup.connect(url).get();
        Elements NoticeEL = Notice.getElementsByAttributeValue("class","lmode");
        Iterator<Element> El1 = NoticeEL.select("td.title").iterator();

        String reurl = El1.next().text();
        /*while (El1.hasNext()){
            System.out.println(El1.next().text());
        }*/

        return reurl;
    }
}
