package regapp.service;

import regapp.domain.models.services.EmployeeServiceModel;
import regapp.domain.models.views.EmployeeListViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {

    boolean saveEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> findAllEmployees();

    boolean removeEmployee(String id);
    BigDecimal sum();

}
