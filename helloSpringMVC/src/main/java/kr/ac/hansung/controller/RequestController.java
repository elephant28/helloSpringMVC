package kr.ac.hansung.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.model.Course;
import kr.ac.hansung.service.CourseService;

@Controller()
@RequestMapping("/Request")
public class RequestController {

	private CourseService courseService;

	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@RequestMapping(value = "/getRequestableCourse", method = RequestMethod.GET)
	public String getRequestableCourses(Model model) {
		List<Course> nextSemesterCourse = courseService.getRequestableCourse();
		model.addAttribute("nextSemesterCourse", nextSemesterCourse);
		return "getRequestableCourseForm";
	}

	@RequestMapping(value = "/doRequestCourse")
	public String doRequestCourse(Model model, String courseID) {
		if (courseService.isListenedCourse(courseID))
			return "alreadyListened";

		else {
			courseService.doRequestCourse(courseID);
			List<Course> nextSemesterCourse = courseService.getRequestableCourse();
			model.addAttribute("nextSemesterCourse", nextSemesterCourse);
			return "getRequestableCoursesForm";
		}
	}

	@RequestMapping(value = "/getRequestedCourse", method = RequestMethod.GET)
	public String getRequestedCourse(Model model) {
		List<Course> course = courseService.getRequestedCourse();
		model.addAttribute("course", course);
		return "getRequestedCourse";
	}

	@RequestMapping(value = "/doDeleteRequestedCourse", method = RequestMethod.GET)
	public String doDeleteRequestedCourse(Model model, String CourseID) {
		courseService.doDeleteRequestedCourse(CourseID);

		List<Course> course = courseService.getRequestedCourse();
		model.addAttribute("course", course);
		return "getRequestedCourse";
	}
}