package picc.wentian.soapUI.service;

import java.sql.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import picc.wentian.soapUI.domain.SoapUITestRun;
import picc.wentian.soapUI.repository.SoapUITestRunDao;
import picc.wentian.soapUI.service.exception.ErrorCode;
import picc.wentian.soapUI.service.exception.ServiceException;

// Spring Bean的标识.
@Service
public class SoapUITestRunService {
	
	private static Logger logger = LoggerFactory.getLogger(SoapUITestRunService.class);

	@Autowired
	private SoapUITestRunDao soapUITestRunDao;

	@Transactional(readOnly = true)
	public Iterable<SoapUITestRun> findAll(Pageable pageable) {
		return soapUITestRunDao.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public SoapUITestRun findOne(Long id) {
		return soapUITestRunDao.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void add(String name,String statusState,String Reason,Long duration,Date operateDate,
			String operateUser) {
		if (StringUtils.isBlank(name) || StringUtils.isBlank(operateUser)) {
			throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
		}

		if (!StringUtils.isBlank(statusState) && StringUtils.isBlank(Reason)) {
			throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
		}
		
		SoapUITestRun soapUITestRun = new SoapUITestRun();
		soapUITestRun.statusState = statusState;
		soapUITestRun.operateDate = operateDate;
		soapUITestRun.operateUser = operateUser;
		
		soapUITestRunDao.save(soapUITestRun);
		logger.info("保存成功。");
	}

	public void add(SoapUITestRun soapUITestRun) {
		// TODO Auto-generated method stub
		
	}

}
