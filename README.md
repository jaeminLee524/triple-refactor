## 1. 목표

- 트리플여행자 클럽 마일리지 서비스

<br>

## 2. 사용 기술 스택

### 🛠 Tech Stack 🛠

#### [ Language ]
<img src="https://user-images.githubusercontent.com/75469281/177996285-60fadf73-3ed7-4c84-9b60-675ebc253bce.png" width="60"/><br>


#### [ Framework & Build ]
<p align="left">

<img src="https://user-images.githubusercontent.com/75469281/177997368-8ff41afc-60f3-4a96-82a4-b6b02366bb80.png" width="100">
<img src="https://user-images.githubusercontent.com/75469281/177998190-1dd0cf69-289a-4424-b6f8-cf41462b7305.png" width="120">

</p>

#### [ ORM & Query DSL ]
<img src="https://user-images.githubusercontent.com/75469281/177998375-cd7c5243-7013-48b2-bca2-10d37ef8279f.png" width="130">



#### [ Test Support ]
<p align="left">
<img src="https://user-images.githubusercontent.com/75469281/178000185-b690babb-c980-4265-81c9-1fc94011918e.png" width="90"/>
&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/75469281/178000356-7c51c966-6fcb-4ac2-a924-e3e4caf8e9c1.png" width="90"/>
</p>


#### [ API Document ]
<img src="https://user-images.githubusercontent.com/75469281/177996432-8863fb40-ca04-4ddb-8de8-51d59339f4b1.png" width="100"/>

#### [ DataBase (mysql8.0.28) ]
<img src="https://user-images.githubusercontent.com/75469281/177999783-d9fe5728-5a44-4b84-8161-c6bd282b39fc.png" width="80"/>

#### [ VCS ]
<img src="https://user-images.githubusercontent.com/75469281/178001243-f9465b06-640d-428d-a432-b724da043c6c.png" width="100"/>


#### [ CI/CD ]
<p align="left">
<img src="https://user-images.githubusercontent.com/75469281/178001802-7b03ff76-c6c0-4584-92c9-f78c1ecfe843.png" width="140"/>
&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/75469281/178002745-1f8e2516-ac75-4115-9690-8c6784f6f3c9.png" width="80"/>
&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/75469281/178002257-b4a52340-b01e-4c90-9c5a-2c858b59df63.png" width="80"/>
&nbsp;&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/75469281/178002123-f2422aac-44f5-40b2-864c-5e5698d29d73.png" width="120"/>

</p>

<br>

## 3. API Document

- [Swagger API](http://3.39.78.227:8080/swagger-ui.html#/)

<br>

## 4. ERD

<img src="https://user-images.githubusercontent.com/44490394/177985675-46b4a131-7b75-4672-a7d5-16bf5b469b88.png" width="550" height="350"/>

<br>

## 5. DDL

- [schema.sql](https://github.com/jaeminLee524/triple-homework/blob/develop/src/main/resources-prod/schema.sql)

<img src="https://user-images.githubusercontent.com/44490394/177983835-a532b976-b824-49cb-8a60-c068c2e1ff6d.png" width="800" height="260"/>
<img src="https://user-images.githubusercontent.com/44490394/177983839-12f584f6-c864-4769-84ca-2302126515fa.png" width="800" height="60"/>

<br>

# 6. 개발 환경
## 6-1. Infra 구축

### [ CI/CD 파이프라인 자동화 환경 구축 ]

- GitHub-Actions, Docker, Docker-Compose 이용
  - [workflow script](.github/workflows/github-actions.yml)
  - [docker-compose.yml](./infra/docker-compose.yml)
- AWS EC2 인스턴스에 개발(dev), 운영(prod) 환경 별, AWS EC2 인스턴스 자동 배포
  - 배포 과정 기록: [notion](https://jaeminlee.notion.site/GitHub-Actions-EC2-12b4a528e5d8456ab3ad50f9d58c6f31)



### [ DB 환경 및 schema 생성 자동화 ]

- Docker-Compose가 환경에 컨테이너를 띄울때, DB생성 및 사용자에게 권한 부여

<img src="https://user-images.githubusercontent.com/44490394/177982878-dd99c8e3-c911-40ca-9a89-6420193c8efb.png" width="300" height="220"/></br>
<img src="https://user-images.githubusercontent.com/44490394/177989254-441e4164-b84e-43d8-a948-ec36a5969001.png" width="1050" height="120"/>

<br>

## 6-2. branch 관리 전략

### [ git flow ]
```
main ----------------------
    |  
  develop -----------------
        |                 
     feature-{구현 기능} ----
```
- 기능 별로 브랜치를 나누고 Self Pull Request(PR)를 진행하였습니다. (feature 브랜치를 지우지않고 남겨놨습니다.)
  - **main**: 배포 시 사용
  - **develop**: 개발이 끝난 부분에 대해서만 Merge 진행
  - **feature**: 기능 개발을 진행할 때 사용

<br>

## 6-3. TestContainers 로 멱등성있는 테스트 환경 구축

### [ TestContainer 를 사용한 이유 ]
1. H2 in-memory db와 mysql간 호환되지 문법이 존재하기에 H2 사용을 지양
2. MySQL을 사용하는 운영 환경이므로, TestContainers를 이용하여 멱등성있는 테스트 환경 구축

<img src="https://user-images.githubusercontent.com/44490394/177988761-dfeeba43-eaaa-4371-8306-70ca945c22b5.png" width="1100" height="50"/>


