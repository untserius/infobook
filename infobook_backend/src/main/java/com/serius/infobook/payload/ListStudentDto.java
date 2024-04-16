package com.serius.infobook.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListStudentDto {
    private List<StudentDto> studentDto;
    private int totalPages;
    private int totalElements;
    private boolean lastPage;
    private boolean firstPage;
    private int pageNumber;
}
