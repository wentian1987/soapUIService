package picc.wentian.soapUI.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SoapUITestCaseDto {
	public Long id;
	
	public String name;
	public String statusState;
	public String Reason;
	public Long duration;

	public String operateUser;
	@JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+08:00")
	public Date operateDate;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
