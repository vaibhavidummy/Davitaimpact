package com.citiustech.pms.procedure.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Component
@Entity
public class ProcedureDetail implements Serializable {

	private static final long serialVersionUID = -3025916076728582932L;

	@Id
	@Column
	@GeneratedValue
	private long id;

	@Column
	private String name;

	@Column
	private String description;
}
