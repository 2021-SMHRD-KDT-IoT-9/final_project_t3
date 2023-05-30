from flask import Flask ,request ,jsonify ,render_template 

#해당 코드는 Flask에서 필요한 모듈을 가져옵니다.
#Flask 자체인 Flask
#HTTP 요청을 처리하기 위한 request,
#그리고 Python 객체를 JSON 형식으로 변환하기 위한 jsonify 모듈입니다.
import requests
import os
from flask_cors import CORS, cross_origin

import beforeServer as bj
import afterServer as aj
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
        os.system("libcamera-still --vflip --hflip --width 1000 --height 1200 -t 3000 -f -o /home/yg/webapp/static/before.jpg")
        filename="static/before.jpg"
        # 이미지를 촬영하고 before.jpg로 저장합니다. 
        bj.imageTransfer()
        
        #to.imageTransfer()를 호출하여 이미지 전송 작업을 수행합니다.
        #그런 다음 requests.post()를 사용하여 요청을 보내고,
        #응답을 response 변수에 저장합니다.   if response.status_code == 200:
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
       
        # 이미지를 촬영하고 after.jpg로 저장합니다.   
        
        
        text = request.json['text']
        print('data :' ,text)
        
        os.system("libcamera-still --vflip --hflip -f -o /home/yg/webapp/static/after.jpg")
        filename="static/after.jpg"
        
        aj.imageTransfer(filename,text)
        #response = requests.post(url, files={""files,})
        #to.imageTransfer()를 호출하여 이미지 전송 작업을 수행합니다.
        #그런 다음 requests.post()를 사용하여 요청을 보내고,
        #응답을 response 변수에 저장합니다.
        return 'Success'

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=False)
