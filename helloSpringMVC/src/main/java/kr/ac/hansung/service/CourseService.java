package kr.ac.hansung.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.CourseDAO;
import kr.ac.hansung.model.Course;
import kr.ac.hansung.model.SemesterList;

@Service
public class CourseService {

	private CourseDAO courseDao;

	@Autowired
	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}

	public List<SemesterList> getCourseBySemester() {
		List<SemesterList> semesterLists = getSemestersLists();

		for (SemesterList sl : semesterLists) {
			int totalGrade = courseDao.getSumBySemester(sl.getYear(), sl.getSemester());
			sl.setTotalGrade(totalGrade);
		}

		return semesterLists;
	}

	private List<SemesterList> getSemestersLists() {
		List<SemesterList> semesterList = courseDao.getSemesterList();
		return semesterList;
	}

	public List<Course> getCourseBySemesterDetail(int year, int semester) {
		List<Course> course = courseDao.getCourse(year, semester);
		return course;
	}

	public int[] getTotalGradeByClassify(List<String> classifies) {
		int[] totalGrade = new int[classifies.size()];
		for (int i = 0; i < classifies.size(); i++) {
			totalGrade[i] = courseDao.getTotalGradeByClassify(classifies.get(i));
		}
		return totalGrade;
	}

	public List<String> getClassifiesLists() {
		List<String> classifyList = courseDao.getClassifiesLists();
		return classifyList;
	}

	public List<Course> getRequestableCourse() {
		List<Course> course = courseDao.getRequestableCourse();
		return course;
	}

	public boolean isListenedCourse(String courseID) {
		Course course = courseDao.getCourse(courseID);
		if (course == null)
			return false;
		else
			return true;
	}

	public void doRequestCourse(String courseID) {
		Course course = courseDao.doRequestCourse(courseID);
		courseDao.doRequestCourse(course);
	}

	public List<Course> getRequestedCourse() {
		List<Course> course = courseDao.getRequestedCourse();
		return course;
	}

	public boolean doDeleteRequestedCourse(String CourseID) {
		boolean isDeleted = courseDao.doDeleteRequestedCourse(CourseID);
		return isDeleted;
	}

}