package tn.esprit.services;

import tn.esprit.entities.Project;
import tn.esprit.entities.Sprint;
import tn.esprit.entities.User;

import java.util.Set;

public interface IExamService {

	public User addUser(User user);
	public Project addProject (Project project);

	public void assignProjectToDeveloper (int projectId, int devId);

	public void assignProjectToScrumMaster(int projectId, String fName, String lName);

	public Set<Project> getProjectsByScrumMaster(String fName, String lName);
	public void addSprintAndAssignToProject(Sprint sprint, int idProject);

}
