#after
# http 통신 관련
import requests

# picam 관련
import os
import BeforeAfter 
   
# 이미지를 찍고 전송해는 함수
def imageTransfer(filename,text):
    url = "http://59.0.234.211:8087/after"
# 카메라를 동작을 위한 설정

    # 파일 경로
    route = "/home/yg/webapp/static/after.jpg" 
   
    f = open(route,"rb") # 이미지 파일 열기
    img = f.read() # 이미지파일 변수에 저장 

    print(img)
    print('here', end='')
    print(text)
    
    
    
    response = requests.post(url, files={ 
            "img": img,
            "type": "file",
            "name":filename}, data={'text':text})
    f.close()
   
    
    if response.status_code == 200:
            #os.system("rm after.jpg")
        return 'File uploaded successfully'
    else:
        return 'File upload failed'
  

print("imageTransfer")