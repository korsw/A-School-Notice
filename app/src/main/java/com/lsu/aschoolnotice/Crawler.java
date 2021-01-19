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

    private static ArrayList Covidlist;
    private static ArrayList Employmentlist;
    private static ArrayList Bachelorlist;
    private static ArrayList CovidTextlist;
    private static ArrayList EmploymentTextlist;
    private static ArrayList BachelorTextlist;

    public static void Covidstart() throws IOException {
        Covidlist = new ArrayList<>();
        //Bachelorlist = new ArrayList<>();
        //Employmentlist = new ArrayList<>();
        CovidTextlist = new ArrayList<>();
        //Bachelorlist = new ArrayList<>();
        //EmploymentTextlist = new ArrayList<>();

        crawling("https://homepage.sch.ac.kr/sch/06/06050000.jsp", Covidlist, CovidTextlist);

        //crawling("https://homepage.sch.ac.kr/sch/05/05040100.jsp", Bachelorlist, BachelorTextlist);

        //crawling("https://homepage.sch.ac.kr/sch/06/06010000.jsp", Employmentlist, EmploymentTextlist);

    }

    public static void Bachelorstart() throws IOException{
        Bachelorlist = new ArrayList<>();
        BachelorTextlist = new ArrayList<>();
        crawling("https://homepage.sch.ac.kr/sch/05/05040100.jsp", Bachelorlist, BachelorTextlist);
    }

    public static void Employmentstart() throws IOException{
        Employmentlist = new ArrayList<>();
        EmploymentTextlist = new ArrayList<>();
        crawling("https://homepage.sch.ac.kr/sch/06/06010000.jsp", Employmentlist, EmploymentTextlist);
    }



    public static void crawling(String url, ArrayList Titlist, ArrayList Textlist) throws IOException {
        Document Notice = Jsoup.connect(url).get();
        Elements NoticeEL = Notice.getElementsByAttributeValue("class","lmode");
        Iterator<Element> El1 = NoticeEL.select("td").iterator();
        Iterator<Element> El2 = NoticeEL.select("a").iterator();

        while (El1.hasNext()){
            Titlist.add(El1.next().text());
        }
        makeURLList(url, El2, Textlist);

    }

    public static void makeURLList(String url, Iterator<Element> El, ArrayList list) throws IOException{
        int i = 0;
        while (El.hasNext()) {
            Document Text = Jsoup.connect(url + El.next().attr("href")).get();
            Text.outputSettings().prettyPrint(false);
            Elements TexteEL = Text.getElementsByAttributeValue("class", "text");
            Iterator<Element> El1 = TexteEL.select("div").iterator();

            while (El1.hasNext()) {
                list.add(i, El1.next().html().replace("<br>", "\n").replace("&nbsp;&nbsp;&nbsp;", "").replace("&nbsp;", " "));
            }
            i++;
        }
    }
    public static ArrayList getCovidlist(){
        return Covidlist;
    }
    public static ArrayList getCovidTextlist(){
        return CovidTextlist;
    }
    public static ArrayList getEmploymentlist(){
        return Employmentlist;
    }
    public static ArrayList getEmploymentTextlist(){
        return EmploymentTextlist;
    }
    public static ArrayList getBachelorlist(){
        return Bachelorlist;
    }
    public static ArrayList getBachelorTextlist(){
        return BachelorTextlist;
    }
}

/*package com.lsu.aschoolnotice;

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
}*/