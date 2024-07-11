package org.verygood.junit_mockito_testing.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final  StudentMapper studentMapper;

    public StudentResponseDto saveStudent(StudentDto studentDto){
        var student = studentMapper.toStudent(studentDto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }


    public List<StudentResponseDto> findAllStudents(){
        return studentRepository.findAll().stream().map(studentMapper::toStudentResponseDto).collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(Integer id){
        return studentRepository.findById(id).map(studentMapper::toStudentResponseDto).orElseThrow();
    }
}
