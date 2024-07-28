package rikkei.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.Part;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private int age;
    private Date birthDate;
    private boolean studentStatus;
    private String avatar;

}
