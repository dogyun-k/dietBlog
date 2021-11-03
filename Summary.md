# dietblog

### 휴스타 ICT 설계 프로젝트

### 1) 개요

음식 사진을 찍어 업로드하면 칼로리를 계산하여 기록.

추후 칼로리 소모를 위한 운동영상 추천 등 다양한 컨텐츠를 추가할 예정.

> API 서버 구축 및 REST API 호출.

### 2) 요구사항

1. Springboot : 웹서버. UI 제공

    - 글 업로드 : title, content, date
    - 사진 업로드 : image
    - 글 수정 : title, content
    - 글 삭제 : seq
    - 글 조회
    - 전체 업로드 리스트 확인
    - 로그인/ 회원가입 : Oauth 소셜 로그인(Google, Naver..)

2. Flask : API 서버 (욜로v5)

    - 음식 인식 : image file
    - 음식이름 번역 : en food name
    - 음식 칼로리 크롤링 : kr food name


### 3) 시스템 설계

#### 구조

![구조](Summary/images/구조.png)

1. Web Server (Springboot)

   **URL**
    - [GET] / : 메인화면 제공
    - [GET] /posts : 업로드한 글 리스트 제공
    - [GET] /postinfo : 음식 사진 포스트 화면
    - [POST] /post : 글 수정
    - [POST] /deletepost : 글 삭제

   **HTTP Message**

   파일 처리

    - Request Message

      Accept : Application/json

      Content-type : Application/json

   그 외

    - Content-type : Application/json

   API Server와 통신은 RestTemplate 사용

2. API Server (Flask)

   **URL**
    - [POST] /calorie : 음식 사진 받아서 칼로리 값 응답
      ![플라스크](Summary/images/FlaskPost.png)
    - Naver Open API(Papago) 요청
    - 음식칼로리 크롤링	(DB에 저장하기엔 방대함.)

3. DB

   MySQL

   **User Entity**
    - Long | seq
    - String | id
    - String | pw
    - String | name

   **Post Entity**
    - Long | seq
    - String | title
    - String | content
    - String | melaType
    - UploadFile | uploadFile

   **UploadFile Entity**
    - Long | seq
    - String | uploadFileName
    - String | storeFileName
    - String | storedPath


	사용자로부터 받은 이미지 파일은 서버 디렉토리에 저장. (DB에 저장 X)

	DB에는 파일 고유 번호와 파일 경로 저장.

#### UI 설계

[comment]: <> (![UI설계]&#40;Summary/images/UI설계.jpg&#41;)

- 아직 미구현

### 추후 계획

- [ ] React로 Front-end 구현

- [ ] Oauth 2.0으로 소셜 로그인 구현

- [X] 현재 API서버 응답 시간이 1초 정도걸리는데 이를 단축
	- Yolo 저장소에서 모델을 불러옴 -> detect 함수에서 바로 검출값을 리턴하도록 함. 

- [ ] Yolo v5 모델 개선
	- 아직 다양한 음식들을 검출해내지 못 함. 
    
- [ ] API로직 개선 : 현재 API호출을 위해 사용하는 동기식 호출 방식인 RestTemplate를 비동기식인 WebClient로 마이그레이션.

- [ ] AWS로 호스팅


## 어려웠던 점

- Spring - Flask 간 통신 과정
- `Multipart/form-data`를 어떻게 요청 메세지에 담을 것인가.
- 이는 `RestTemplate`라는 Spring 라이브러리를 사용함.
- 처음 파일만 보내고 그 파일을 Flask에서 받아 칼로리를 Response 해주기로 함.
- 하지만 파일을 바이너리 데이터 형태로 보내야함. 그리고 Restful API를 위해 전송 데이터는 JSON으로 한정지음.
- 그래서 이미지 파일을 바이너리화 해서 스트링으로 변환 후 Json Type으로 전송함.
- 플라스크에서는 해당 String을 `Base64`로 String to Binary 디코딩하여 파일을 저장함.
- Yolov5의 detect는 이미지를 경로에서 읽어옴 -> 맨 처음에는 저장소에서 불러와서 1.x초가 걸렸는데 수정 후 0.x초 걸림
