package com.training.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

	private String title;
	private String Description;
	private String Physician;
	private LocalDate date;
	private LocalTime time;
	private String status;
	private String Reason; // �In case of edit or delete
}
