CREATE TABLE StudentsOfClass (
    class_id INT NOT NULL,
    student_id INT NOT NULL,

    PRIMARY KEY (class_id, student_id),

    CONSTRAINT fk_students_of_class_class FOREIGN KEY (class_id) REFERENCES classes(class_id) ON DELETE CASCADE,

    CONSTRAINT fk_students_of_class_student FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);