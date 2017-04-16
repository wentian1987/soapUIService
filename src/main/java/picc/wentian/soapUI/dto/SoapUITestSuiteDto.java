package picc.wentian.soapUI.dto;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

// JPA实体类的标识
public class SoapUITestSuiteDto {
	// JPA 主键标识, 策略为由数据库生成主键
	public Long id;

	public String statusState;
	public String name;

	@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+08:00")
	public Date operateDate;

	public String operateUser;

	public List<SoapUITestCaseDto> soapUITestCases;
	
	public SoapUITestSuiteDto() {
	}

	public SoapUITestSuiteDto(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
