package com.fms.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document //Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetail {

	@Id
	private String id;
	
	private String eventId;
	
	private String baseLocation;
	
	private String beneficiaryName;
	
	private String councilName;
	
	private String eventName;
	
	private String eventDescription;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate eventDate;
	
	private int employeeId;
	
	private String employeeName;
	
	private double volunteerHours;
	
	private double travelHours;
	
	private double livesImpacted;
	
	private String businessUnit;
	
	private String status;
	
	private String iiep;
}
