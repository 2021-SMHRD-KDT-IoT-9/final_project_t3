Hi To All 
If you want to know to run Pi Wide Camera Module V3 
I can help you in this branch 
And also You can know How to Raspberry Pi 4 Bullseye System preferences setting 

안녕하세요~  
이연규라는 30살 초보개발자입니다.

이번 프로젝트에서 라즈베리파이 Camera Module V3를 사용했습니다! 


이번 프로젝트에서 고민했던점
1)
Camera Module V3를 사용하기위해선
과거 buster였던 라파os를 밀고 bullseye로 업데이트시켜줘야했습니다!
고로 라파4 환경설정 (Bullseye)과 한글화 과정을 올렸습니다. 


2)
Thonny Python과 Camera Module V3를 Flask를 통해 웹에서 버튼을 누르면 사진이 찍히는 방식을 하였습니다.
CameraModule V3와 Thonny Python 연동과정
고로 Thonny Python에서 OS를 IMPORT해서 CAMERA MODULE V3 명령어를 적어주는 식으로 만들었습니다.
또한 Flask를 Import와 관련 사항 Import 하는 방식을 올려뒀습니다!



3) 
이번프로젝트 IOT과정 순서

WEB(NODE JS) ==> Rasp ==> Spring boots 
연동과정







1-1 
Raspberry Pi 4 환경설정 및 한글화

1. 포매터로으로 SD카드를 먼저 비워주시고
2. 라즈비안 이미지 다운로드 https://downloads.raspberrypi.org/imager/imager_latest.exe (불스아이)
3. Micro SD카드로 라즈비안 이미지 라이팅
4. 라파 이미저 라파를 넣어줍니다! 
5. 환경설정 1.아이디 및 2.WiFi 설정 3. locale Time Zone// Wifi country // Time Zone// Keyboard layout 설정 
6. 라파에 sd카드를 넣어줍니다

그리고 그후 라파에 들어가서 화면에 한글이 깨져있을겁니다... 


라파 cmd에서 
sudo apt-get update
업데이트
sudo apt-get upgrade
업그레이드

CMD 나와서


GUI 좌측 위 설정에서
Raspberry PI Configuration --> locaisation / setTimezone 설정… 
여기선 그냥 다 한국관련된걸로 바꿔줍시다! 

다시 
CMD에서
sudo apt-get install ibus
(ibus)
sudo apt-get install ibus-hangul
(ibus-hangul)
sudo apt-get install fonts-unfonts-core
(font)

리부트한번
sudo reboot

한글키보드용
sudo apt-get install nabi


라파 언어환경설정 들어가서 

inputMethod Ok

yes 

hangul 라디오버튼 체크하면 완료! 

reboot한번 진행해주시면 키보드 또한 한글화된 라파를 사용하실수있습니다!





3-1




