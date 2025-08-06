CREATE TABLE classes (
    class_id serial primary key,
    teacher_id integer NOT NULL,
    course_id integer NOT NULL,
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(course_id)
)