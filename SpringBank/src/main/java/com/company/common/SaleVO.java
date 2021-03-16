package com.company.common;

import lombok.Data;

@Data
public class SaleVO {
	
	private String sale_seq;
	private String sale_date;
	private String product_id;
	private String sale_qty;
	private String sale_price;
	private String member_id;
	
}
