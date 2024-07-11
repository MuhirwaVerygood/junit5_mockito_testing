package org.verygood.junit_mockito_testing.student;

import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public  Student toStudent(StudentDto dto){
        if(dto ==null){
            throw new NullPointerException("The student Dto should not be null");
        }
        var student = new Student();
        student.setFirstname(dto.getFirstname());
        student.setLastname(dto.getLastname());
        student.setEmail(dto.getEmail());

        var school = new School();
        school.setId(dto.getSchoolId());

        student.setSchool(school);
        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        StudentResponseDto dto = new StudentResponseDto();
        dto.setFirstname(student.getFirstname());
        dto.setLastname(student.getLastname());
        dto.setEmail(student.getEmail());
        return dto;
    }



}
