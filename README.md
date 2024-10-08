# Board (24.10.02)
## 1. 게시판 기능 요구 사항
1. **게시판 생성**
- 게시판 정보는 제목, 내용, 작성 날짜가 있습니다.

2. **게시판 목록 조회**
- 게시판 전체 목록을 조회할 수 있습니다.
- 검색 결과는 pagination 되어 보여집니다.
- 검색 결과 조건 : 작성 날짜 오름차순, 내림차순

3. **게시판 수정**
- 게시판의 제목과 글을 수정할 수 있습니다.

4. **게시판 삭제**
- 게시판을 삭제합니다.

<br>

## 2. 데이터 베이스 테이블
**board**

| column | type |
| --- | --- |
| id | long |
| title | varchar |
| content | varchar |
| createdAt | date |
| updatedAt | date |
| deletedAt | date |

<br>

## 3. API
| 기능 | method | url |
| --- | --- | --- |
| 게시판 생성 | POST | /board
| 게시판 단건 조회 | GET | /board/{boardId}
| 게시판 전체 조회 | GET | /board
| 게시판 수정 | PATCH | /board/{boardId}
| 게시판 삭제 | DELETE | /board/{boardId}

[- API 명세](https://github.com/erinleeme/Board/wiki/API-%EC%84%A4%EA%B3%84)

<br>

## 4. 프로젝트 개발
**개발 환경**

- SpringBoot 3.3.
- Java 21
- Gradle
- JPA
- QueryDSL
- H2

**라이브러리**

- h2 database
- querydsl

**브랜치 관리**

- 초기 설정 : init
- 기능 추가 브랜치 : feat/기능명
