package picc.wentian.soapUI.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

// JPA实体类的标识
@Entity(name="soapuitestcase")
public class SoapUITestCase {
	// JPA 主键标识, 策略为由数据库生成主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String name;
	public String statusState;
	public String Reason;
	public Long duration;
	public Date operateDate;
	
	public String operateUser;
	public SoapUITestCase() {
	}

	public SoapUITestCase(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
