import dao.EmpDao;
import dao.impl.EmpDaoImpl;
import org.junit.Test;
import pojo.Employee;
import utils.JdbcUtil;

import java.sql.Connection;
import java.util.List;

public class EmpTest {

    private final EmpDao empDao = new EmpDaoImpl();

    @Test
    public void searchAll() {
        try {
            List<Employee> employees = empDao.searchAll();
            employees.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void searchById() {
        try {
            Employee employee = empDao.searchById(100);
            System.out.println(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void insertEmp() {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            // Le operazioni DAO successive usano la stessa connessione e partecipano a questa transazione.
            connection.setAutoCommit(false);

            Employee employee = new Employee(100, "Beta", 1200.00, 18);

            Integer row = empDao.insert(employee);

            connection.commit();
            System.out.println(row);

        } catch (Exception e) {
            try {
                if(connection != null) {
                    connection.rollback();
                }
                System.out.println(e.getMessage());
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        } finally {
            JdbcUtil.release();
        }
    }

    @Test
    public void updateSalaryById() {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);

            Integer row = empDao.updateSalaryById(100, 200.00);

            connection.commit();
            System.out.println(row);

        } catch (Exception e) {
            try {
                if(connection != null) {
                    connection.rollback();
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            JdbcUtil.release();
        }
    }

    @Test
    public void deleteById() {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);

            Integer row = empDao.deleteById(100);

            connection.commit();
            System.out.println(row);

        } catch (Exception e) {
            try {
                if(connection != null) {
                    connection.rollback();
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            JdbcUtil.release();
        }
    }

}
