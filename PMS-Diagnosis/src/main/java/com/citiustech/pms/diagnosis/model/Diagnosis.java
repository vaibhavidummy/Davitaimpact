package com.citiustech.pms.diagnosis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
@Entity
public class Diagnosis implements Serializable {

	private static final long serialVersionUID = -3661065571206334359L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "created_by")
	private String created_by;

}
