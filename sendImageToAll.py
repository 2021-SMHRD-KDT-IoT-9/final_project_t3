from flask import Flask ,request ,jsonify ,render_template

#해당 코드는 Flask에서 필요한 모듈을 가져옵니다.
#Flask 자체인 Flask
#HTTP 요청을 처리하기 위한 request,
#그리고 Python 객체를 JSON 형식으로 변환하기 위한 jsonify 모듈입니다.
import requests
import os
from flask_cors import CORS, cross_origin

import beforeJSP as bj
import afterJSP as aj

#requests 모듈은 HTTP 요청을 보내기 위해,
#os 모듈은 운영 체제와 상호 작용하기 위해,
#그리고 flask_cors 모듈은 Flask에서 Cross-Origin Resource Sharing (CORS)을 활성화하기 위해 가져옵니다.
#toJSP라는 사용자 정의 모듈을 가져옵니다.


app = Flask(__name__)
CORS(app)
 
#Flask 애플리케이션 인스턴스를 생성하고 app이라는 이름으로 지정합니다.
#CORS 함수를 사용하여 Flask 애플리케이션에 Cross-Origin Resource Sharing (CORS)를 활성화합니다.
@app.route('/')
def index():
    image_path="static/before.jpg"
    return render_template('index.html', image_path=image_path)



#before
@app.route('/execute', methods=['POST'])
@cross_origin()
def execute():
    if request.method == 'POST':
        #요청 메서드가 POST인 경우에만 
        os.system("libcamera-still --vflip --hflip -f -o /home/yg/webapp/static/before.jpg")
        filename="static/before.jpg"
        # 이미지를 촬영하고 before.jpg로 저장합니다. 
      
        
        bj.imageTransfer()
        response = requests.post(url, files=files)
        #to.imageTransfer()를 호출하여 이미지 전송 작업을 수행합니다.
        #그런 다음 requests.post()를 사용하여 요청을 보내고,
        #응답을 response 변수에 저장합니다. .
       
        if response.status_code == 200:
            return 'File uploaded successfully'
        else:
            return 'File upload failed'
#os.system("rm before.jpg")
#마지막으로 before.jpg 파일을 삭제합니다


#after
@app.route('/execute2', methods=['POST'])
@cross_origin()
def execute2():
    if request.method == 'POST':
        #요청 메서드가 POST인 경우에만 
        os.system("libcamera-still --vflip --hflip -f -o /home/yg/webapp/static/after.jpg")
        filename="static/after.jpg"
        # 이미지를 촬영하고 after.jpg로 저장합니다.   
        
        aj.imageTransfer()
        response = requests.post(url, files=files)
        #to.imageTransfer()를 호출하여 이미지 전송 작업을 수행합니다.
        #그런 다음 requests.post()를 사용하여 요청을 보내고,
        #응답을 response 변수에 저장합니다. 
       
        if response.status_code == 200:
            #os.system("rm after.jpg")
            return 'File uploaded successfully'
        else:
            return 'File upload failed'        
        
        

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=False)# http 통신 관련
import requests

# picam 관련
import os

   
# 이미지를 찍고 전송해는 함수
def imageTransfer():
    url = "http://59.0.234.211:8087/after"
# 카메라를 동작을 위한 설정

    # 파일 경로
    route = "/home/yg/webapp/static/after.jpg" 
   
    f = open(route,"rb") # 이미지 파일 열기
    img = f.read() # 이미지파일 변수에 저장

    print(img)
   
    response = requests.post(url, files={ # 이미지를 전송하기 위한 설정
        "img" : img,
        "type" : "file",
        "name" : "after.jpg"
        })
    f.close()
    print(response.status_code)
  

imageTransfer()
print("imageTransfer")# http 통신 관련
import requests

# picam 관련
import os

   
# 이미지를 찍고 전송해는 함수
def imageTransfer():
    url = "http://59.0.234.211:8087/after"
# 카메라를 동작을 위한 설정

    # 파일 경로
    route = "/home/yg/webapp/static/after.jpg" 
   
    f = open(route,"rb") # 이미지 파일 열기
    img = f.read() # 이미지파일 변수에 저장

    print(img)
   
    response = requests.post(url, files={ # 이미지를 전송하기 위한 설정
        "img" : img,
        "type" : "file",
        "name" : "after.jpg"
        })
    f.close()
    print(response.status_code)
  

imageTransfer()
print("imageTransfer")