# A-School-Notice

# Server_py

import requests
import time
from bs4 import BeautifulSoup
from pyfcm import FCMNotification
import threading

APIKEY = "server_key"

push_service = FCMNotification(APIKEY)

keyword = "06050000"
url = "https://homepage.sch.ac.kr/sch/06/{}.jsp".format(keyword)
#url = "https://homepage.sch.ac.kr/sch/06/06010000.jsp"


Cr = requests.get(url)

Cbs = BeautifulSoup(Cr.content, "lxml")
CRecentTitleList = Cbs.select("td.title")

CpreTitleList = [0]


Br = requests.get(url)

Bbs = BeautifulSoup(Br.content, "lxml")
BRecentTitleList = Bbs.select("td.title")

BpreTitleList = [0]


Er = requests.get(url)

Ebs = BeautifulSoup(Er.content, "lxml")
ERecentTitleList = Ebs.select("td.title")

EpreTitleList = [0]


def sendMessage(body, title):
    data_message = {
        "body": body,
        "title": title
    }

    result = push_service.notify_topic_subscribers(topic_name="NewNotice", data_message=data_message)

    print(result)

def execute(Notice, RecentTitleList, preTitleList):
    if (RecentTitleList == preTitleList):
        #sendMessage("A School Notice", "새로운 " + Notice + " 공지가 없습니다.")
        time.sleep(20)
        return RecentTitleList
    else:
        if (preTitleList[0] == 0):
            preTitleList = RecentTitleList
            #sendMessage("A School Notice", "초기화해볼게요")
            return RecentTitleList
        else:
            preTitleList = RecentTitleList
            sendMessage("A School Notice", "새로운 " + Notice + " 공지가 올라왔습니다")
            time.sleep(20)
            return RecentTitleList

while True:
    CpreTitleList = execute("코로나", CRecentTitleList, CpreTitleList)
    BpreTitleList = execute("학사", BRecentTitleList, BpreTitleList)
    EpreTitleList = execute("취업", ERecentTitleList, EpreTitleList)


