#before
# http 통신 관련
import requests

# picam 관련
import os

   
# 이미지를 찍고 전송해는 함수
def imageTransfer():
    url = "http://59.0.234.211:8087/before"
# 카메라를 동작을 위한 설정

    # 파일 경로
    route = "/home/yg/webapp/static/before.jpg" 
   
    f = open(route,"rb") # 이미지 파일 열기
    img = f.read() # 이미지파일 변수에 저장

    print(img)
   
    response = requests.post(url, files={ # 이미지를 전송하기 위한 설정
        "img" : img,
        "type" : "file",
        "name" : "before.jpg"
        })
    f.close()
    print(response.status_code)
  

imageTransfer()
print("imageTransfer")