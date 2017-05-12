package pl.project.surveys;

import javax.ejb.Local;

@Local
public interface Surveyization {

	public abstract String create(Survey survey);
	
	public abstract Survey find(int ids);

	public abstract Surveys get();

	public abstract String update(Survey survey);

	public abstract void delete(int ids);
	
}
