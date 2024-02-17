package com.web.dto;

import lombok.Data;

@Data
public class SendDataDTO {
	private String query;
	private String target;
	private int page;
	private String sort;
	private String isbn;
	private int display;
}
