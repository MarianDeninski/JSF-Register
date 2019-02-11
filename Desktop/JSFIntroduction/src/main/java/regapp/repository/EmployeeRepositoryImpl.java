package regapp.repository;

import regapp.domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Employee> findAll() {

        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager
                .createQuery("SELECT e FROM Employee e",Employee.class)
                .getResultList();

        this.entityManager.getTransaction().commit();

        return employees;
    }

    @Override
    public Employee findById(String id) {

        this.entityManager.getTransaction().begin();

        Employee employee = this.entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.id=:id",Employee.class)
                .setParameter("id",id)
                .getSingleResult();

        this.entityManager.getTransaction().commit();

        return employee;
    }

    @Override
    public void remove(String id) {

        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("DELETE FROM Employee e WHERE e.id = :id")
                .setParameter("id",id).executeUpdate();
        this.entityManager.getTransaction().commit();

    }

    public BigDecimal sum(){
        this.entityManager.getTransaction().begin();
       Query hello =  this.entityManager.createQuery("SELECT SUM(p.salary) FROM Employee p");
       Number result =(Number) hello.getSingleResult();
        System.out.println(result);
        this.entityManager.getTransaction().commit();

        BigDecimal bb = (BigDecimal)result;

        return bb;

    }




}
