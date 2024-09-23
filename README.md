# Board (24.09.19)
## 1. 게시판 기능 요구 사항
1. **게시판 생성**
- 게시판 정보는 제목, 내용, 작성 날짜가 있습니다. 컨셉은 본인의 일기장으로 작성자는 모두 본인으로 생각합니다.
- 추가로 생각해볼 수 있는 것은 댓글 기능이 있습니다.

2. **게시판 조회**
- 제목과 내용 키워드로 조회할 수 있습니다.

3. **게시판 목록 조회**
- 게시판 전체 목록을 조회할 수 있습니다.
- 검색 결과는 pagination 되어 보여집니다.
- 검색 결과 목록순을

4. **게시판 수정**
- 게시판의 제목과 글을 수정할 수 있습니다.

5. **게시판 삭제**
- 게시판을 삭제합니다.

<br>

## 2. 데이터 베이스 테이블
**board**

| column | type |
| --- | --- |
| id | long |
| title | varchar |
| content | varchar |
| createdAt | localdatetime |
| updatedAt | localdatetime |
| deletedAt | localdatetime |

<br>

## 3. 프로젝트 개발
**개발 환경**

- SpringBoot 3.3.
- Java 21
- Gradle
- JPA
- QueryDSL
- h2

**라이브러리**

- h2 database
- querydsl

**브랜치 관리**

- 초기 설정 : init
- 기능 추가 브랜치 : feat/기능명
