package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.binding.RegisterBindingModel;
import regapp.domain.models.services.EmployeeServiceModel;
import regapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRegisterBean {

    private  RegisterBindingModel registerBindingModel;
    private  EmployeeService employeeService;
    private  ModelMapper modelMapper;

    public EmployeeRegisterBean() {


        this.registerBindingModel = new RegisterBindingModel();

    }

    @Inject
    public EmployeeRegisterBean(EmployeeService employeeService,
                                ModelMapper modelMapper) {

        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;

    }

    public RegisterBindingModel getRegisterBindingModel() {

        return registerBindingModel;
    }

    public void setRegisterBindingModel(RegisterBindingModel registerBindingModel) {


        this.registerBindingModel = registerBindingModel;
    }

    public void register() throws IOException {

        this.employeeService.saveEmployee(this.modelMapper.map(this.registerBindingModel,EmployeeServiceModel.class));

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");

    }
}
