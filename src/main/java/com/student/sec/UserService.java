package com.student.sec;

import java.util.List;

public interface UserService {

    List<student> getAllstudents();
    List<student> getAllStudentsByName(String name);
    void addupstudent(student stud);


}