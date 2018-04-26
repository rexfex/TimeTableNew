package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Group;
import org.itstep.model.Teacher;
import org.itstep.model.Teacher;
import org.itstep.model.Teacher;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.itstep.service.TeacherService;
import org.itstep.service.TeacherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TeacherControllerTest {

	@MockBean
	TeacherService 	teacherService;

	@Autowired
	TestRestTemplate restTemplate;

	private List<Teacher>teachers;

	@Before
	public void setUp() throws Exception {
		
		Subject subject = new Subject();
		subject.setName("Java");

		teachers = new ArrayList<Teacher>();
		
			Teacher teacher1 = new Teacher();
			teacher1.setFirstName("Alex");
			teacher1.setSubject(subject);
			teacher1.setLogin("Alex");
			teacher1.setPassword("qwerty");
			teacher1.setSecondName("Pupkin");
			teachers.add (teacher1);	
			
			Teacher teacher2 = new Teacher();
			teacher2.setFirstName("Oleg");
			teacher2.setSubject(subject);
			teacher2.setLogin("Oleg");
			teacher2.setPassword("qwerty");
			teacher2.setSecondName("Popkin");
			teachers.add (teacher2);	
		}
	@Test
	public void testSave() throws URISyntaxException {
		Mockito.when(teacherService.save(Mockito.any(Teacher.class))).thenReturn(teachers.get(0));

		RequestEntity<Teacher> request = new RequestEntity<Teacher>(teachers.get(0), HttpMethod.POST, new URI("/teacher"));

		ResponseEntity<Teacher> response = restTemplate.exchange(request, Teacher.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(teacherService, Mockito.times(1)).save(Mockito.any(Teacher.class));
	}

	@Test
	public void testUpdate()throws URISyntaxException {
		Mockito.when(teacherService.update(Mockito.any(Teacher.class))).thenReturn(teachers.get(0));

		RequestEntity<Teacher> request = new RequestEntity<Teacher>(teachers.get(0), HttpMethod.PUT, new URI("/teacher"));

		ResponseEntity<Teacher> response = restTemplate.exchange(request, Teacher.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(teacherService, Mockito.times(1)).update(Mockito.any(Teacher.class));
	}

	@Test
	public void testGetOne() throws URISyntaxException {
		Mockito.when(teacherService.get(Mockito.anyString())).thenReturn(teachers.get(0));

		HttpHeaders headers = new HttpHeaders();
		headers.add("login", "Alex");
		
		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI("/teacher/get-one"));
		ResponseEntity<Teacher> response = restTemplate.exchange(request, Teacher.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(teacherService, Mockito.times(1)).get(Mockito.anyString());
	}

	@Test
	public void testFindAllByGroup()throws URISyntaxException {
		Mockito.when(teacherService.findAllBySubject(Mockito.anyString())).thenReturn(teachers);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("name","qqq");
		
		RequestEntity request = new RequestEntity(headers, HttpMethod.GET, new URI("/teacher/get-by-subject"));
		ResponseEntity<List> response = restTemplate.exchange(request, List.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(teacherService, Mockito.times(1)).findAllBySubject(Mockito.anyString());
		
		assertEquals(2, response.getBody().size());
		
	}

	@Test
	public void testDelete()  throws URISyntaxException {
		Mockito.doNothing().when(teacherService).delete(Mockito.any(Teacher.class));
		
		RequestEntity<Teacher> request = new RequestEntity<Teacher>(teachers.get(0), HttpMethod.DELETE, new URI("/teacher"));

		ResponseEntity<HttpStatus> response = restTemplate.exchange(request, HttpStatus.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Mockito.verify(teacherService, Mockito.times(1)).delete(Mockito.any(Teacher.class));
	}



}
