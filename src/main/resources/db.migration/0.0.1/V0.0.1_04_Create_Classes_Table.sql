create table classes (
    class_id serial primary key,
    class_name varchar(100) not null,
    teacher_id int not null,
    course_id int not null,
    start_date date not null,
    foreign key (teacher_id) references teachers(teacher_id),
    foreign key (course_id) references courses(course_id)
);