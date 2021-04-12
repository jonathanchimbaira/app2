package com.student.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    studrep repo;

    public List<student> getAllstudents() {
        List<student> stu = repo.findAll();
        return stu;
    }

    public List<student> getAllStudentsByName(String name) {
        List<student> students = new ArrayList<>();
        List<student> st = (List<student>) repo.findAll();
        for (int i = 0; i < st.size(); i++) {
            if (st.get(i).getName().equals(name)) {
                students.add(st.get(i));
            }
        }
        return students;
    }

    public void addupstudent(student stud) {

        repo.save(stud);
    }
}
