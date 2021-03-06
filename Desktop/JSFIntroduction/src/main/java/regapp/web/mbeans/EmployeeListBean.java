package regapp.web.mbeans;


import org.modelmapper.ModelMapper;
import regapp.domain.models.views.EmployeeListViewModel;
import regapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {

    private List<EmployeeListViewModel> employees;

    private EmployeeService employeeService;

    private ModelMapper modelMapper;

    private String totalPrice;

    private String getAveragePrice;

    public EmployeeListBean() {


    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper){


        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.findAllEmployees().stream()
                .map(e -> this.modelMapper.map(e,EmployeeListViewModel.class))
                .collect(Collectors.toList());
    }


    public List<EmployeeListViewModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }


    public String getTotalPrice() {
        if(this.employeeService.sum()==null){

            return "0.00";
        }
        return String.format("%.2f",this.employeeService.sum());
    }

    public String getGetAveragePrice() {
        try {
            BigDecimal average = BigDecimal.valueOf(this.employeeService.sum().doubleValue()/ this.employeeService.findAllEmployees().size());
            return String.format("%.2f",average);
        }catch (Exception e){

            return "0.00";
        }
    }
}
