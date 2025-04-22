package com.jsp.capstoneProject.util;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessResponse 
{
	
	private int status;
	private String msg;
	private LocalDateTime dateTime;
	private Object data;
}
