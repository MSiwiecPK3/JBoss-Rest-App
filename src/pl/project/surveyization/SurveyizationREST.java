package pl.project.surveyization;

import java.util.List;


import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/surveyization")
@Consumes({ "application/xml" })
@Produces({ "application/xml" })
public class SurveyizationREST implements Surveyization {

	@EJB
	SurveyizationEJB bean;
	
	@Override
	@POST
	@Path("/survey")
	public String create(Survey survey) {
		bean.create(survey);
		String response = "survey created!" + survey.getIds();
		for (Question q : survey.getQuestions()){
			response += ("\n " + q.getText() + " " + q.getIdq());
		}
		return response;
	}

	@Override
	@GET
	@Path("/survey/{ids}")
	public Survey findSurvey(@PathParam("ids") int ids) {
		Survey survey = bean.findSurvey(ids);
		return survey;
	}

	@Override
	@GET
	@Path("/survey")
	public SetResponse<Survey> getSurveys() {
		List<Survey> list = bean.getSurveys();
		SetResponse<Survey> surveys = new SetResponse<Survey>(list);
		return surveys;
	}

	@Override
	@PUT
	@Path("/survey/{ids}")
	public String update(@PathParam("ids") int ids, Survey survey) {
		bean.deleteSurvey(ids);
		bean.create(survey);
		String response = "survey created!" + survey.getIds();
		for (Question q : survey.getQuestions()){
			response += ("\n " + q.getText() + " " + q.getIdq());
		}
		return response;
	}
	
	@Override
	@DELETE
	@Path("/survey/{ids}")
	public String deleteSurvey(@PathParam("ids") int ids) {
		String description = bean.deleteSurvey(ids);	
		return "survey with description " + description + " deleted";
	}
	
	@POST
	@Override
	@Path("/filledsurvey")
	public String create(FilledCreator filled) {
		bean.create(filled);
		return "FilledSurvey created!" + filled.getFilled().getIdf();
	}

	@Override
	@GET
	@Path("/filledsurvey/{idf}")
	public FilledSurvey findFilledSurvey(@PathParam("idf") int idf) {
		FilledSurvey filled = bean.findFilledSurvey(idf);
		return filled;
	}

	@Override
	@GET
	@Path("/filledsurvey")
	public SetResponse<FilledSurvey> getFilledSurveys() {
		List<FilledSurvey> list = bean.getFilledSurvey();
		SetResponse<FilledSurvey> filled = new SetResponse<FilledSurvey>(list);
		return filled;
	}

	@Override
	@PUT
	@Path("/filledsurvey/{idf}")
	public String update(@PathParam("idf") int idf, FilledSurvey filled) {
		try {
			bean.updateFilledSurvey(idf, filled);
			return "filled updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "filled not updated :(";
		}
	}

	@Override
	@DELETE
	@Path("/filledsurvey/{idf}")
	public String deleteFilledSurvey(@PathParam ("idf") int idf) {
		String description = bean.deleteFilledSurvey(idf);	
		return "FilledSurvey with description " + description + " deleted";
	}
	
	@Override
	@POST
	@Path("/teacher")
	public String create(Teacher teacher) {
		bean.create(teacher);
		return "teacher created!" + teacher.getIdt();
	}

	@Override
	@GET
	@Path("/teacher/{idt}")
	public Teacher findTeacher(@PathParam("idt") int idt) {
		Teacher teacher = bean.findTeacher(idt);
		return teacher;
	}

	@Override
	@GET
	@Path("/teacher")
	public SetResponse<Teacher> getTeachers() {
		List<Teacher> list = bean.getTeacher();
		SetResponse<Teacher> teachers = new SetResponse<Teacher>(list);
		return teachers;
	}

	@Override
	@PUT
	@Path("/teacher/{idt}")
	public String update(@PathParam("idt") int idt ,Teacher teacher) {
		try {
			teacher.setidt(idt);
			bean.updateTeacher(teacher);
			return "teacher updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "teacher not updated :(";
		}
	}

	@Override
	@DELETE
	@Path("/teacher/{idt}")
	public String deleteTeacher(@PathParam ("idt") int idt) {
		String name =  bean.deleteTeacher(idt);	
		return "teacher " + name + " deleted";
	}
}
