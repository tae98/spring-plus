![header](https://capsule-render.vercel.app/api?type=waving&height=300&color=gradient&text=Spring%20Plus)

# 🚀 STACK

Environment

![인텔리제이](   https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![깃허브](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![깃](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![POSTMAN](https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

Development

![자바](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SPRING BOOT](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![SQL](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonwebservices&logoColor=white)


# LV3.12 AWS
## HEALTH CHECK
<img width="1271" alt="Screenshot 2024-10-10 at 5 03 24 PM" src="https://github.com/user-attachments/assets/6879cb05-c0ff-4985-87fd-ebc76fefc580">

## EC2
<img width="1565" alt="Screenshot 2024-10-10 at 5 07 48 PM" src="https://github.com/user-attachments/assets/210002a6-5e10-49ed-b398-3544c664e2ec">

## RDS Database

![Screenshot 2024-10-10 at 8 07 36 PM](https://github.com/user-attachments/assets/d6c4b089-2395-4959-8341-d10823eafedc)

# LV3.13 대용량 데이터 처리


1. 테이블 인덱스 미적용 (JPQL)<br/><br/> 
3. 테이블 인덱스 미적용 (JPA)<br/><br/> 
4. 테이블 인덱스 적용 (JPQL)<br/><br/> 
5. 테이블 인덱스 적용 (JPA)<br/><br/> 



## 테이블 인덱스 미적용 (JPQL)
<img width="843" alt="Screenshot 2024-10-10 at 9 20 40 PM" src="https://github.com/user-attachments/assets/dfbaec9d-8c08-450d-a6e1-dae3ae9123ce">

## 테이블 인덱스 미적용 (JPA)
<img width="844" alt="Screenshot 2024-10-10 at 9 20 27 PM" src="https://github.com/user-attachments/assets/562c2a1b-a8aa-4861-9505-54aa84232995">

## 테이블 인덱스 적용 (JPQL)
<img width="832" alt="Screenshot 2024-10-10 at 9 36 55 PM" src="https://github.com/user-attachments/assets/309ec9ea-58ad-4bb1-8ca2-bdf0e69b181c">

## 테이블 인덱스 적용 (JPA)

<img width="839" alt="Screenshot 2024-10-10 at 9 38 45 PM" src="https://github.com/user-attachments/assets/06513347-b906-44fc-aca0-c532e0c56bc3">

## 📊결과

| 테이블 인덱스    |JPA | JPQL| 
| ----         |:----:  |:----:|
| 테이블 인덱스 미적용 | 428ms |214ms |
| 테이블 인덱스 적용 | 27ms   |7ms  |

- JPA 테이블 인덱스 적용시 94% 성능향상<br/><br/> 
- JPQL 테이블 인덱스 적용시 97% 성능향상<br/><br/> 
- JPA to JPQL 테이블 인덱스 적용시 75% 성능향상<br/><br/> 
- JPA to JPQL 테이블 인덱스 미적용시 50% 성능향상<br/><br/>

# ⚒️Troubleshooting

## 1.  JAR파일 실행시 환경변수 인식 오류
### 원인
- application.yaml의 환경변수가 Intellij에서는 선언되었지만 AWS EC2 Ubuntu에서는 적용이 안된 상태
</br></br>
### 해결
- AWS EC2 Ubuntu bash에서 bashrc를 수정하여 환경변수 선언.

      nano .bashrc
      //로드된 데이터 아래에 환경변수 key 와 value를 선언
      export {key} = value

      cntrl+x -> y -> enter //변경된 .bashrc 저장

      source .bashrc // 저장된 .bashrc내부의 환경 변수 source로 지정
      java -jar {jarfilename}

## 2.  RDS 데이터 베이스 연결 url 및 유저 인식 오류
### 원인
- Mysql Master Id를 사용하더라도 데이터베이스의 권한이 없어 연결을 못함
- application-local.yaml 파일 내부의 database url이 local host를 사용

### 해결
- EC2 Mysql 접속하여 유저를 만들거나 이미 존재하는 유저에게 권한을 부여

       mysql -u{RDSMASTERID} -p -h{endpoint}
        {RDSPASSWORD}

        CREATE USER '{USERNAME}'@'%' IDENTIFIED BY '{PASSWORD}'; // USER생성
        CREATE DATABASE {DBNAME};//DATABASE 생성
        GRANT ALL PRIVILEGES ON *.* TO  '{USERNAME}'@'%'; // 데이터베이스 내의 모든 DB & TABLE에 권한부여
        OR
        GRANT ALL PRIVILEGES ON {DBNAME}.* TO  '{USERNAME}'@'%'; // 특적 DB에 대해 모든 권한부여
        FLUSH PRIVILEGES;//변경된 권한을 즉시 반영
- application-local.yaml 내부의 database url을 환경변수 변경하여 Intellij와 AWS EC2 에서 다르게 적용 (Intellij -> localhost, AWS EC2 -> RDS)

         url: jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DBNAME}

  AWS EC2에서 1번과 같이 .bashrc에서 환경변수 선언
          
          MYSQL_HOST = {RDSendpoint}
          MYSQL_PORT = {RDSPort}
          MYSQL_DBNAME = {DBname}




