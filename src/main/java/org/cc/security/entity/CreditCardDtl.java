package org.cc.security.entity;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.cc.security.constants.JsonDateDeserializer;
import org.cc.security.constants.JsonDateSerializer;
import org.cc.security.constants.TemplateConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CREDIT_CARD_DTL")
public class CreditCardDtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native",strategy = "native")
    private Long id;
	
	@Column(name = "cc_holder_name")
    private String ccHolderName;
	
	@Column(name = "cc_number")
    private String ccNumber;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TemplateConstants.DT_YY_MM_SLASH)
	@Column(name = "cc_expiry")
    private Date ccExpiry;
	
	@Column(name = "create_id")
    private String createId;
	
	@CreationTimestamp
	@Column(name = "create_dt")
    private Timestamp createDt;
	
	@Column(name = "update_id")
    private String updateId;
	
	@UpdateTimestamp
	@Column(name = "update_dt")
    private Timestamp updateDt;
	
	@Transient
    private String ccExpiryStrDt;
	

}
