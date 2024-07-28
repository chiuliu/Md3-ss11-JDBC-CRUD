package rikkei.model.daoImp;

import rikkei.model.dao.StudentDAO;
import rikkei.model.entity.Student;
import rikkei.model.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO<Student,String> {
    @Override
    public List<Student> searchStudentByName(String name) {
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_SearchStudentByName(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                st.setStudentId(rs.getString("StudentId"));
                st.setStudentName(rs.getString("StudentName"));
                st.setAge(rs.getInt("Age"));
                st.setBirthDate(rs.getDate("BirthDate"));
                st.setStudentStatus(rs.getBoolean("StudentStatus"));
                students.add(st);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }


    @Override
    public List<Student> getAll() {
        //THực hiện kết nối đến cơ so du liẹu
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> listStudent = null;
        try{
            //1. THuc hiện kết nối đến CSDL
            conn = ConnectionDB.openConnection();
            //2. Khoi tao doi tượng CallableStatement de goi procedure
            callSt = conn.prepareCall("{call proc_getAllStudent()}");
            //3. Thuc hien callSt và nhận ResultSet
            //--proc là câu select thì thực hiện phương thức executeQuery
            //--proc là câu lệnh thực hiện insert, update, delete ma chỉ có tham số vao - executeUpdate()
            //--proc là câu lệnh thực hiện insert, update, delete ma có tham so ra - execute()
            ResultSet rs = callSt.executeQuery();
            listStudent= new ArrayList<>();
            //4. Duyet resultset đầy đ dữ liệu ra listStudent
            while (rs.next()){
                Student st = new Student();
                st.setStudentId(rs.getString("StudentId"));
                st.setStudentName(rs.getString("StudentName"));
                st.setAge(rs.getInt("Age"));
                st.setBirthDate(rs.getDate("BirthDate"));
                st.setStudentStatus(rs.getBoolean("StudentStatus"));
                listStudent.add(st);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listStudent;
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call pro_insertStudent(?,?,?,?,?,?)}");
            //thuc hien set gia tri cho cac tham so vao
            callSt.setString(1,student.getStudentId());
            callSt.setString(2,student.getStudentName());
            callSt.setInt(3,student.getAge());
            ///convert java.util.Date --> java.sql.Date
            callSt.setDate(4,new Date(student.getBirthDate().getTime()));
            callSt.setBoolean(5,student.isStudentStatus());
            // thuc hien đăng ki kieu du lieu cac tham so ra
            callSt.registerOutParameter(6, Types.INTEGER);
            //Thuc hien goi produce
            callSt.execute();
            //Lay gia tri tham so ra
            int cnt = callSt.getInt(6);
        }catch (Exception ex){
            result=false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }


    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_UpdateStudent(?,?,?,?,?)}");
            //thuc hien set gia tri cho cac tham so vao
            callSt.setString(1,student.getStudentId());
            callSt.setString(2,student.getStudentName());
            callSt.setInt(3,student.getAge());
            ///convert java.util.Date --> java.sql.Date
            callSt.setDate(4,new Date(student.getBirthDate().getTime()));
            callSt.setBoolean(5,student.isStudentStatus());
            //Thuc hien goi produce
            callSt.executeUpdate();
        }catch (Exception ex){
            result=false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_DeleteStudent(?)}");
            //thuc hien set gia tri cho cac tham so vao
            callSt.setString(1,id);
            //Thuc hien goi produce
            callSt.executeUpdate();
        }catch (Exception ex){
            result=false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;

    }

    @Override
    public Student getById(String id) {
        //THực hiện kết nối đến cơ so du liẹu
        Connection conn = null;
        CallableStatement callSt = null;
        Student studentInfo = null;
        try{
            //1. THuc hiện kết nối đến CSDL
            conn = ConnectionDB.openConnection();
            //2. Khoi tao doi tượng CallableStatement de goi procedure
            callSt = conn.prepareCall("{call proc_GetStudentById(?)}");
            //set gia tri cho tham so vào
            callSt.setString(1,id);
            //3. Thuc hien callSt và nhận ResultSet
            ResultSet rs = callSt.executeQuery();
            studentInfo = new Student();
            //4. Duyet resultset đầy đ dữ liệu ra studentInfo
            if (rs.next()){
                studentInfo.setStudentId(rs.getString("StudentId"));
                studentInfo.setStudentName(rs.getString("StudentName"));
                studentInfo.setAge(rs.getInt("Age"));
                studentInfo.setBirthDate(rs.getDate("BirthDate"));
                studentInfo.setStudentStatus(rs.getBoolean("StudentStatus"));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return studentInfo;
    }
}
