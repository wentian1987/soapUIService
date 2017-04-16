package picc.wentian.soapUI.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;

// JPA实体类的标识
@Entity(name="soapuitestsuite")
public class SoapUITestSuite {
	// JPA 主键标识, 策略为由数据库生成主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String name;

	public String statusState;
	public Date operateDate;

	public String operateUser;

	@OneToMany(targetEntity=SoapUITestCase.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="testsuite_id",referencedColumnName="id")
	public List<SoapUITestCase> soapUITestCases ;
	
	public SoapUITestSuite() {
	}

	public SoapUITestSuite(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
