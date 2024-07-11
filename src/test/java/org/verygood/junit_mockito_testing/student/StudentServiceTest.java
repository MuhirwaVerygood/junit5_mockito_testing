package org.verygood.junit_mockito_testing.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    //declare dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_student(){
        //Given
        StudentDto studentDto = new StudentDto(
                "Verygood",
                "Muhirwa",
                "verygoodmuhirwa2@gmail.com",
                1
        );

        Student student = new Student(
                "Verygood",
                "Muhirwa",
                "verygoodmuhirwa2@gmail.com",
                12
        );

        Student savedStudent = new Student(
                "Verygood",
                "Muhirwa",
                "verygoodmuhirwa2@gmail.com",
                12
        );

        savedStudent.setId(1);

        // Mock the calls
        Mockito.when(studentMapper.toStudent(studentDto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("Verygood","Muhirwa","verygoodmuhirwa2@gmail.com"));


        //When



        StudentResponseDto studentResponseDto = studentService.saveStudent(studentDto);


        //Then
        assertEquals(studentDto.getFirstname(), studentResponseDto.getFirstname());
        assertEquals(studentDto.getLastname(), studentResponseDto.getLastname());
        assertEquals(studentDto.getEmail(), studentResponseDto.getEmail());

        Mockito.verify(studentMapper, Mockito.times(1)).toStudent(studentDto);
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
        Mockito.verify(studentMapper, Mockito.times(1)).toStudentResponseDto(savedStudent);

    }

    @Test
    public void should_return_all_students(){
        //Given
        ArrayList<Student> students = new ArrayList<>();
        students.add( new Student(
                "Verygood",
                "Muhirwa",
                "verygoodmuhirwa2@gmail.com",
                12
        )
        );


        //mock the calls
        Mockito.when(studentRepository.findAll()).thenReturn(students);
        Mockito.when(studentMapper.toStudentResponseDto(Mockito.any(Student.class))).thenReturn(
                new StudentResponseDto(
                        "Verygood",
                        "Muhirwa",
                        "verygoodmuhirwa2@gmail.com"
                )
        );

        //when
        List<StudentResponseDto> getAllStudents= studentService.findAllStudents();
        assertEquals(students.size(), getAllStudents.size());

        Mockito.verify(studentRepository,Mockito.times(1)).findAll();

    }

    @Test
    public void should_find_student_by_id(){
        //given
      Student student = new Student( "Verygood",
              "Muhirwa",
              "verygoodmuhirwa2@gmail.com",
              12);
      student.setId(1);
      Integer id=1;
        //mock the calls
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toStudentResponseDto(student)).thenReturn(new StudentResponseDto(
                "Verygood",
                "Muhirwa",
                "verygoodmuhirwa2@gmail.com"
        ));

        //when
        StudentResponseDto studentResponseDto= studentService.findStudentById(1);
        assertEquals(student.getFirstname(),studentResponseDto.getFirstname());
        assertEquals(student.getLastname(),studentResponseDto.getLastname());
        assertEquals(student.getEmail(),studentResponseDto.getEmail());


        Mockito.verify(studentRepository, Mockito.times(1)).findById(id);
    };
}