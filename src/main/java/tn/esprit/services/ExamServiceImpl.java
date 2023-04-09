package tn.esprit.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.esprit.entities.Project;
import tn.esprit.entities.Role;
import tn.esprit.entities.Sprint;
import tn.esprit.entities.User;
import tn.esprit.repositories.ProjectRepository;
import tn.esprit.repositories.SprintRepository;
import tn.esprit.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ExamServiceImpl implements IExamService {

   // @Autowired
    UserRepository userRepository;

    ProjectRepository projectRepository;
    SprintRepository sprintRepository;


    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public Project addProject(Project project) {
        Set<Sprint> sprints = project.getSprints();
        for ( Sprint sprint: sprints) {
            sprint.setProject(project);
        }
      return projectRepository.save(project);
    }

    @Override
    public void assignProjectToDeveloper(int projectId, int devId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        User developer = userRepository.findById(devId).orElse(null);
        developer.getProjectsDev().add(project);
        userRepository.save(developer);
    }

    @Override
    public void assignProjectToScrumMaster(int projectId, String fName, String lName) {
        Project project = projectRepository.findById(projectId).orElse(null);
        User scrumMaster = userRepository.findByFirstNameAndLastNameAndRole(fName,lName,Role.SCRUM_MASTER);
        scrumMaster.getProjectsScrum().add(project);
        userRepository.save(scrumMaster);
    }

    @Override
    public Set<Project> getProjectsByScrumMaster(String fName, String lName) {
        User scrumMaster = userRepository.findByFirstNameAndLastNameAndRole(fName,lName,Role.SCRUM_MASTER);
        return  scrumMaster.getProjectsScrum();

    }

    @Override
    public void addSprintAndAssignToProject(Sprint sprint, int idProject) {
        Project project = projectRepository.findById(idProject).orElse(null);
        sprint.setProject(project);
        sprintRepository.save(sprint);
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void getProjectsCurrentSprints(){
        List<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            for (Sprint sprint : project.getSprints()) {
                if (sprint.getStartDate().before(new Date()))
                    log.info(project.getTitle());
            }
        }
    }
}
