package com.example.springtodosubject.todo.dto.request;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.todo.entity.Todo;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record CreateTodoRequest(

        @Size(max = 200, message = "일정은 200자 이내로 작성되어야 합니다.")
        @NotNull(message = "일정 항목의 정보는 반드시 포함되어야 합니다.")
        String title,

        @NotNull(message = "일정 관리 비밀번호가 반드시 포함되어야 합니다.")
        String password,

        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "날짜 형식은 yyyy-MM-dd 이어야 합니다.")
        @NotNull(message = "일정 시작일자가 반드시 포함되어야 합니다.")
        @Past(message = "과거 날짜만 입력 가능합니다.")
        String startDate,

        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "날짜 형식은 yyyy-MM-dd 이어야 합니다.")
        @NotNull(message = "일정 종료일자가 반드시 포함되어야 합니다.")
        @Future(message = "미래 날짜만 입력 가능합니다.")
        String endDate
) {

    // Entity로 변환
    public Todo convertToEntity(Author author) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        return Todo.builder()
                .title(title)
                .author(author)
                .password(password)
                .startDate(LocalDate.parse(startDate, formatter))
                .endDate(LocalDate.parse(startDate, formatter))
                .build();
    }
}
