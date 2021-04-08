package com.citiustech.pms.model;

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
		@Column(name="procedure_id")
		@GeneratedValue
	    //@GeneratedValue(strategy =GenerationType.AUTO ,generator="system-uuid")
		//@GenericGenerator(name="system-uuid", strategy = "uuid")
		private long procedure_id;
		
		@Column(name="procedure_name")
		private String procedure_name;
		
		@Column(name="procedure_description")
		private String procedure_description;
	
	    
	

}
