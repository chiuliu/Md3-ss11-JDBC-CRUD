package rikkei.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.Part;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequest {
    private String studentId;
    private String studentName;
    private int age;
    private Date birthDate;
    private boolean studentStatus;
    private Part file;
}
