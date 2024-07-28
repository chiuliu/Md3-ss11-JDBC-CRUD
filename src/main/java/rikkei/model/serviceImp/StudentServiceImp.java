package rikkei.model.serviceImp;

import rikkei.model.dao.StudentDAO;
import rikkei.model.daoImp.StudentDAOImp;
import rikkei.model.entity.Student;
import rikkei.model.service.StudentService;

import java.util.List;

public class StudentServiceImp implements StudentService<Student,String> {

    private StudentDAO<Student,String> studentDAO = new StudentDAOImp();

    @Override
    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    @Override
    public boolean save(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDAO.update(student);
    }

    @Override
    public boolean delete(String id) {
        return studentDAO.delete(id);
    }

    @Override
    public Student getById(String id) {
        return studentDAO.getById(id);
    }

    @Override
    public List<Student> searchStudentByName(String name) {
        return studentDAO.searchStudentByName(name);
    }
}
