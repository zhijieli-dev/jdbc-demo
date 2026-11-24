package dao.impl;

import dao.BaseDao;
import dao.EmpDao;
import pojo.Employee;

import java.util.List;

public class EmpDaoImpl implements EmpDao {

    private final BaseDao baseDao= new BaseDao();

    @Override
    public List<Employee> searchAll() throws Exception {
        String sql = "SELECT emp_id empId, emp_name empName, emp_salary empSalary, emp_age empAge FROM t_emp";
        return baseDao.executeQuery(Employee.class, sql);
    }

    @Override
    public Employee searchById(Integer id) throws Exception {
        String sql = "SELECT emp_id empId, emp_name empName, emp_salary empSalary, emp_age empAge FROM t_emp WHERE emp_id = ?";
        return baseDao.executeQueryBean(Employee.class, sql, id);
    }

    @Override
    public Integer insert(Employee employee) throws Exception {
        String sql = "INSERT INTO t_emp VALUES(?, ?, ?, ?)";
        return baseDao.executeUpdate(sql, employee.getEmpId(), employee.getEmpName(), employee.getEmpSalary(), employee.getEmpAge());
    }

    @Override
    public Integer updateSalaryById(Integer id, Double salary) throws Exception {
        String sql = "UPDATE t_emp SET emp_salary = ? WHERE emp_id = ?";
        return baseDao.executeUpdate(sql, salary, id);
    }

    @Override
    public Integer deleteById(Integer id) throws Exception {
        String sql = "DELETE FROM t_emp WHERE emp_id = ?";
        return baseDao.executeUpdate(sql, id);
    }

}
