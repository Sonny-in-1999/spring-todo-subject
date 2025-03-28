# TODO LIST
- - -

## API 명세서

| 기능        | Method | URL                     | request                                                               | response                                                                                                               | 상태코드         |
|-----------|--------|-------------------------|-----------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|--------------|
| 일정 등록     | POST   | /api/todos              | { "todoId": 1, "title": "할일", "writer" : "작성자명", "password": "비밀번호" } | { "todoId": 1, "title": "할일", "writer" : "작성자명", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }   | 201(created) |
| 일정 전건 조회  | GET    | /api/todos              | Request param(페이지네이션)                                                 | [{ "todoId": 1, "title": "할일", "writer" : "작성자명", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }] | 200(ok)      |
| 일정 단건 조회  | GET    | /api/todos/{todoId}     | Path Variable                                                         | { "todoId": 1, "title": "할일", "writer" : "작성자명", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }   | 200(ok)      |
| 일정 수정     | PUT    | /api/todos/{todoId}     | { "title": "할일", "writer" : "작성자명", "password": "비밀번호" }              | { "todoId": 1, "title": "할일", "writer" : "작성자명", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }   | 200(ok)      |
| 일정 삭제     | DELETE | /api/todos/{todoId}     | Path Variable                                                         | 할 일 삭제완료                                                                                                               | 200(ok)      |
| 작성자 생성    | POST   | /api/authors            | { "name" : "작성자명", "email" : "이메일" }                                  | { "authorId": 1, "name": "작성자명", "email": "이메일", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }   | 201(created) |
| 작성자 단건 조회 | GET    | /api/authors/{authorId} | Path Variable                                                         | { "authorId": 1, "name": "작성자명", "email": "이메일", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }   | 200(ok)      |
| 작성자 수정    | PUT    | /api/authors/{authorId} | { "name" : "작성자명", "email" : "이메일" }                                  | { "authorId": 1, "name": "작성자명", "email": "이메일", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }   | 200(ok)      |
| 작성자 삭제    | DELETE | /api/authors/{authorId} | Path Variable                                                         | 작성자 삭제완료                                                                                                               | 200(ok)      |

[ERD](![Untitled.png](erd.png))