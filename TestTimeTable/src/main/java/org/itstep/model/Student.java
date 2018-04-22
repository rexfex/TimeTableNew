package org.itstep.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "STUDENTS")
public class Student extends User{

	@ManyToOne(targetEntity = Group.class)
	private Group group;
}
