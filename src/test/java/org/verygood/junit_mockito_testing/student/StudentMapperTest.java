package org.verygood.junit_mockito_testing.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
//
//    @BeforeAll
//    static void beforeAll() {
//        System.out.println("Inside before all method");
//    }
//
//    @AfterAll
//    static void afterAll() {
//        System.out.println("Inside after all method");
//    }
//
//    @BeforeEach
//    void setUp() {
//        System.out.println("Before each method");
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.out.println("After each method");
//
//    }
//
//    @Test
//    public void testMethod1(){
//        System.out.println("My first test method");
//    }
//
//    @Test
//    public void testMethod2(){
//        System.out.println("My second test method");
//    }


    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper= new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){

        //Given
        StudentDto studentDto = new StudentDto(
                "Verygood",
                "Muhirwa",
                "verygoodmuhirwa2@gmail.com",
                1
        );

        //When

        Student student = studentMapper.toStudent(studentDto);

        //Then
        Assertions.assertEquals(studentDto.getFirstname(), student.getFirstname());
        Assertions.assertEquals(studentDto.getLastname(), student.getLastname());
        Assertions.assertEquals(studentDto.getEmail(), student.getEmail());
        assertNotNull(student.getSchool());
        Assertions.assertEquals(studentDto.getSchoolId(), student.getSchool().getId());
    }


    @Test
    public void shouldMapStudentToStudentDto(){
        //Given
        Student student = new Student("Verygood","Muhirwa", "verygoodmuhirwa2@gmail.com",12);

        //When
        StudentResponseDto dto = studentMapper.toStudentResponseDto(student);
        //Then
        assertEquals(dto.getFirstname(), student.getFirstname());
        assertEquals(dto.getLastname(), student.getLastname());
        assertEquals(dto.getEmail(), student.getEmail());
    }

    @Test
    public void should_throw_nullpointer_exception_when_student_studentDto_is_null(){
//        assertEquals("", student.getFirstname());
//        assertEquals("", student.getLastname());
       var message=  assertThrows(NullPointerException.class, ()-> studentMapper.toStudent(null));
       assertEquals("The student Dto should not be null", message.getMessage());
    }
}