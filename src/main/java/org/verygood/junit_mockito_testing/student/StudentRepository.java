package org.verygood.junit_mockito_testing.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student, Integer> {
}
