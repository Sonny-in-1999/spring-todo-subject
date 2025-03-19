# TODO LIST
- - -

## API 명세서

| 기능       | Method | URL                 | request                                                               | response                                                                                                               | 상태코드         |
|----------|--------|---------------------|-----------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|--------------|
| 일정 등록    | POST   | /api/todos          | { "todoId": 1, "title": "할일", "writer" : "작성자명", "password": "비밀번호" } | { "todoId": 1, "title": "할일", "writer" : "작성자명", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }   | 201(created) |
| 일정 전건 조회 | GET    | /api/todos          | -                                                                     | [{ "todoId": 1, "title": "할일", "writer" : "작성자명", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }] | 200(ok)      |
| 일정 단건 조회 | GET    | /api/todos/{todoId} | 요청 param                                                              | { "todoId": 1, "title": "할일", "writer" : "작성자명", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }   | 200(ok)      |
| 일정 수정    | PUT    | /api/todos/{todoId} | { "title": "할일", "writer" : "작성자명", "password": "비밀번호" }              | { "todoId": 1, "title": "할일", "writer" : "작성자명", "createdAt": "yyyy-mm-dd", "updatedAt": "createdAt": "yyyy-mm-dd" }   | 200(ok)      |
| 일정 삭제    | DELETE | /api/todos/{todoId} | { "password": "비밀번호" }                                                | -                                                                                                                      | 200(ok)      |
