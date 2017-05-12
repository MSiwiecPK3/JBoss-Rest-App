package pl.project.surveys;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Surveys {
	public List<Survey> surveys = new ArrayList<Survey>();
		
	public Surveys(List<Survey> surveys){
		this.surveys = surveys;
	}
	public Surveys() {}
	
	public List<Survey> getSurveys(){
		return surveys;
	}
	public void setSurveys(List<Survey> surveys){
		this.surveys = surveys;
	}

}
