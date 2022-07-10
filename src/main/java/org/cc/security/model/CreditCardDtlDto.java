package org.cc.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCardDtlDto {
	
    private Long id;
	
    private String ccHolderName;
	
    private String ccNumber;
	
    private String ccExpiry;
	
    private String createId;
	
    private String updateId;
    

}
