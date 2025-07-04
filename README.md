# 🧠 My Contributions - CSBRAIN Project (이민영)

이 저장소는 **이민영(Lee Minyoung)** 개발자가 직접 기여한 `CSBRAIN` 프로젝트의 핵심 소스 코드 일부를 모아둔 저장소입니다.  
주로 Java + JSP 기반의 Spring MVC 구조로 구성되어 있으며, Backend 로직, SQL Mapper, Controller, Service, VO 구조가 포함되어 있습니다.


## 📁 프로젝트 구조

```
CSBRAIN/
├── src/main/java/
│   ├── csbrain/common/controller/SheetController.java
│   ├── csbrain/common/service/
│   │   ├── *.java (VO 및 인터페이스 다수)
│   │   └── impl/*.java (DAO 및 ServiceImpl)
│   ├── csbrain/data/controller/*.java
│   ├── csbrain/data/service/*.java
│   └── csbrain/main/controller/*.java
├── src/main/resources/
│   ├── csbrain/common/config/*.properties, *.xml
│   └── csbrain/common/sqlmap/*.xml
├── WebContent/
│   ├── css/, js/, images/
│   └── WEB-INF/jsp/include/**/*.jsp
```

## 🔧 기술 스택

- **Back-end**: Java, Spring Framework, MyBatis
- **Front-end**: JSP, JavaScript, jQuery, CSS
- **Database**: MySQL (Mapper 기반 SQL)
- **환경**: Eclipse 기반, SVN 형상관리

## 📂 포함된 주요 기능 및 클래스 설명

### 📌 Controller

- `SheetController.java`: 인지검사, 설문지 시트 요청 처리
- `DataController.java`: 데이터 조회, 검색 기능 제공
- `TargetController.java`: 대상자 등록 및 조회 관리

### 🧠 Service / DAO

- `CommonService`, `SheetService`, `DataService`, `TargetService`: 각 도메인별 서비스 인터페이스 및 구현
- `CommonDAO`, `SheetDAO`, `DataDAO`, `TargetDAO`: MyBatis 기반 DAO 구현체
- `MemberServiceImpl`: 로그인, 사용자 정보 처리

### 📦 VO 클래스

- `MbKmmse2VO`, `MbDrVO`, `MbSmVO` 등: 각 설문지, 의료 정보, 인지검사 데이터 등 저장 구조
- `TargetVO`, `MemberVO`: 사용자 및 대상자 관리용 VO

### 🧾 Mapper 및 설정

- `*.xml`: MyBatis 매퍼 (`Sheet-Mapper.xml`, `CSBR10-Mapper.xml` 등)
- `jdbc-local.properties`: 로컬 DB 접속 정보
- `log4j.xml`: 로그 설정

### 🖼 JSP

- `/jsp/include/sheet/`: 설문 시트 출력용 JSP
- `/jsp/include/data/`: 데이터 검색 및 리스트 출력 JSP
- `/jsp/include/target/`: 대상자 관련 뷰 JSP
- `csbr1010.jsp`, `csbr3020_upload.jsp`: 핵심 기능 JSP 예시

## 📍 기여도 및 역할

- 기존 레거시 코드 분석 및 신규 기능 개발
- 복잡한 설문지 구조별 VO/Mapper/Service 구조 설계
- JSP 기반 동적 UI 설계 및 jQuery 기능 구현
- DB 연동 테스트 및 마이그레이션 스크립트 작성

## 🔗 기타

- SVN 기준 `Author: my.lee` 로 커밋한 파일만 선별
- 공통 기능 외 다른 개발자의 코드는 포함하지 않음

---

📌 이 저장소는 실무 경험과 기술력을 이력서/포트폴리오 용도로 보여주기 위해 정리되었습니다.
