package org.verygood.junit_mockito_testing.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String firstname;
    private String lastname;
    private String email;
    private Integer schoolId;
}
