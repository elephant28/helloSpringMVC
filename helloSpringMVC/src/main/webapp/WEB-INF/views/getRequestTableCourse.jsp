<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>getRequestTableCourse.jsp</title>
<style>
table, tr, td {
	border: 1px solid;
	border-collapse: collapse;
}
tr {
	text-align: center;
}
</style>
</head>
<body>
	수강신청 가능 목록
	<table>
		<tr>
			<td>과목코드
			<td>수강년도
			<td>학기
			<td>과목명
			<td>구분
			<td>학점
			<td>
		</tr>
		<c:forEach var="nextSemesterCourse" items="${nextSemesterCourse}">
			<tr>
				<td><c:out value="${nextSemesterCourse.getCourseID()}"></c:out>
				<td><c:out value="${nextSemesterCourse.getYear()}"></c:out>
				<td><c:out value="${nextSemesterCourse.getSemester()}"></c:out>
				<td><c:out value="${nextSemesterCourse.getCourseName()}"></c:out>
				<td><c:out value="${nextSemesterCourse.getClassify()}"></c:out>
				<td><c:out value="${nextSemesterCourse.getCourseGrade()}"></c:out>
				<td><a href="${pageContext.request.contextPath}/Request/doRequestCourse?courseID=${nextSemesterCourses.getCourseID()}"> 신청 </a>
			</tr>
		</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath}">go home</a>
</body>
</html>