package dao;

import pojo.Employee;

import java.util.List;

public interface EmpDao {
    List<Employee> searchAll() throws Exception;
    Employee searchById(Integer id) throws Exception;
    Integer insert(Employee employee) throws Exception;
    Integer updateSalaryById(Integer id, Double salary) throws Exception;
    Integer deleteById(Integer id) throws Exception;
}
