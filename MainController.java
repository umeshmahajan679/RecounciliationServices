package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Model.ReconciliationServiceModel;
import com.example.demo.Reposistory.ReconciliationServiceReposistory;

@Controller
public class MainController {
	
	
	@Autowired
	ReconciliationServiceReposistory reconciliationServiceReposistory;
	
	
	
	@GetMapping(value="/")
	public String  data( ReconciliationServiceModel  reconciliationServiceModel) {
		
		return "index";
	}
	
	 

	@GetMapping(value="/addData")
	public String  adddata( ReconciliationServiceModel  reconciliationServiceModel) {
		
		return "addData";
	}
	
	
	@PostMapping("/addReconciliationRule")
    public String addUser(@Validated ReconciliationServiceModel  reconciliationServiceModel, BindingResult result, Model model) {
       try {
		if (result.hasErrors()) {
            return "addData";
        }
        reconciliationServiceReposistory.save(reconciliationServiceModel);
        return "redirect:/index";
       }
       catch(Exception e){
    	   e.printStackTrace();
    	   return null;
       }
       
}
	
	@GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("reconciliationServiceModels", reconciliationServiceReposistory.findAll());
        return "index";
    }
	
	
	 @GetMapping("/edit/{id}")
	    public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    	System.out.println("inside in Getmapping");
	    	ReconciliationServiceModel reconciliationServiceModel = reconciliationServiceReposistory.findById(id)
	         .orElseThrow(() -> new IllegalArgumentException("Invalid Service Id:" + id));
	        
	        model.addAttribute("reconciliationServiceModel", reconciliationServiceModel);
	        return "/update";
	    }
	    
	    
	    @PostMapping("/update/{id}")
	    public String updateService(@PathVariable("id") long id, @Validated ReconciliationServiceModel reconciliationServiceModel, 
	      BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	reconciliationServiceModel.setId(id);
	            return "update";
	        }
	            
	        reconciliationServiceReposistory.save(reconciliationServiceModel);
	        return "redirect:/index";
	    }
	        
	    @GetMapping("/delete/{id}")
	    public String deleteService(@PathVariable("id") long id, Model model) {
	    	ReconciliationServiceModel reconciliationServiceModel = reconciliationServiceReposistory.findById(id)
	          .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    	reconciliationServiceReposistory.delete(reconciliationServiceModel);
	        return "redirect:/index";
	    }

	
	

}
