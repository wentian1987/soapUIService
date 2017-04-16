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
@Entity(name="soapuitestrun")
public class SoapUITestRun {
	// JPA 主键标识, 策略为由数据库生成主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String statusState;
	
	public Date operateDate;
	public String operateUser;
	
	@OneToMany(targetEntity=SoapUITestSuite.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="testrun_id",referencedColumnName="id")
	public List<SoapUITestSuite> soapUITestSuite ;
	
	public SoapUITestRun() {
	}

	public SoapUITestRun(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusState() {
		return statusState;
	}

	public void setStatusState(String statusState) {
		this.statusState = statusState;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public List<SoapUITestSuite> getSoapUITestSuite() {
		return soapUITestSuite;
	}

	public void setSoapUITestSuite(List<SoapUITestSuite> soapUITestSuite) {
		this.soapUITestSuite = soapUITestSuite;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
