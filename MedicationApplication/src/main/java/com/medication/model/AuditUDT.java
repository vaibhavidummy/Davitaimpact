package com.medication.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@UserDefinedType(value = "audit_info")
@AllArgsConstructor
@NoArgsConstructor
public class AuditUDT {
	@CreatedDate
	@Getter
	@Column(value = "created_date")
	private LocalDateTime createdDate;

	@Getter
	@Setter
	@Column(value = "created_by")
	private String createdBy;


	}



/*
 * @Getter
 * 
 * @Setter
 * 
 * @Column(value = "last_modified_by") private String lastModifiedBy;
 */

/*
 * @Getter
 * 
 * @Setter
 * 
 * @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
 * 
 * @LastModifiedDate
 * 
 * @Column(value = "last_updated_date") private LocalDateTime lastUpdatedDate;
 */

/*@Transient
@Getter
@Setter
private boolean isNew;

public void setCreatedDate(LocalDateTime createdDate) {
	if (this.isNew()) {
		this.createdDate = createdDate;
	}*/
