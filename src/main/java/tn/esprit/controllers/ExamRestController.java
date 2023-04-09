package tn.esprit.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.entities.Project;
import tn.esprit.entities.Sprint;
import tn.esprit.entities.User;
import tn.esprit.services.IExamService;

import java.util.Set;

@RestController
@AllArgsConstructor
public class ExamRestController {

    IExamService examService;

    @PostMapping("/ajouterUser")
    public User addUser(@RequestBody User user){

        return examService.addUser(user);
    }

    @PostMapping("/addProject")
    public Project addProject(@RequestBody Project project){
        return examService.addProject(project);
    }

    @PostMapping("/assignProjectToDeveloper")
    public void assignProjectToDeveloper(int projectId, int devId){
        examService.assignProjectToDeveloper(projectId, devId);
    }

    @PutMapping("/assignProjectToScrum")
    public void assignProjectToScrum(int projectId, String fName, String lName){
        examService.assignProjectToScrumMaster(projectId,fName,lName);
    }

    @GetMapping("/getProjectsByScrumMaster")
    public Set<Project> getProjectsByScrumMaster(String fName, String lName){
        return examService.getProjectsByScrumMaster(fName,lName);
    }

    @PostMapping("/ajouterSprint/{idProject}")
    public void addSprintAndAssignToProject(@RequestBody Sprint sprint, @PathVariable int idProject){
        examService.addSprintAndAssignToProject(sprint,idProject);
    }


}