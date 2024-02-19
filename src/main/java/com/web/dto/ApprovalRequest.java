package com.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApprovalRequest {
	private Long detailId;
	private Long orderId;
}
