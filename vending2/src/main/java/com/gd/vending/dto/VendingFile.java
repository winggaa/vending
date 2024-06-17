package com.gd.vending.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendingFile {
		
		private int fileNo;
		private int drinkNo;
		private String fileName;
		private String originalName;
		private String fileType;
		private long  fileSize;
		private String updateDate;
		private String createDate;
	
}
