package org.itstep.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "TEACHERS")
public class Teacher extends User{
	
	@ManyToOne(targetEntity = Subject.class)
	private Subject subject;
}
