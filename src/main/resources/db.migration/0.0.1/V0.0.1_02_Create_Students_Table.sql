CREATE TABLE IF NOT EXISTS public.students
(
    student_id serial NOT NULL,
    student_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    birthday date,
    phone_number character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT students_pkey PRIMARY KEY (student_id)
)