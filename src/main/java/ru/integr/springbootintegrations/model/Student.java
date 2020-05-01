package ru.integr.springbootintegrations.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 3757222193338699423L;

    private int id;
    private String name;
    private String school;

    @Override
    public String toString() {
        return "Student[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                ']';
    }
}
