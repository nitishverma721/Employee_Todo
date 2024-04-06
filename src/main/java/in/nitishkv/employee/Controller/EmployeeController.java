package in.nitishkv.employee.Controller;

import in.nitishkv.employee.Entity.Employee;
import in.nitishkv.employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    @Autowired
    public EmployeeRepository employeeRepository;

    @GetMapping({"/", "/list"})
    public ModelAndView showEmployees(){
        ModelAndView mav = new ModelAndView("list-employee");
        mav.addObject("employees", employeeRepository.findAll());
        return mav;
    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee newEmpoyee = new Employee();
        mav.addObject("employee", newEmpoyee);
        return mav;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
        ModelAndView mav = new ModelAndView("add-employee-form");
        Employee emp = employeeRepository.findById(employeeId).get();
        mav.addObject("employee", emp);
        return mav;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return "redirect:/list";
    }
}
