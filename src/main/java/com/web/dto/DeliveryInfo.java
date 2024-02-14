package com.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryInfo {
	private String name;
	private String phone;
	private String email;
	private String address;
}
