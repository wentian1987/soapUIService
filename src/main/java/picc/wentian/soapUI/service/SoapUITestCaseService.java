package picc.wentian.soapUI.service;

import java.sql.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import picc.wentian.soapUI.domain.SoapUITestCase;
import picc.wentian.soapUI.repository.SoapUITestCaseDao;
import picc.wentian.soapUI.service.exception.ErrorCode;
import picc.wentian.soapUI.service.exception.ServiceException;

// Spring Bean的标识.
@Service
public class SoapUITestCaseService {

	private static Logger logger = LoggerFactory.getLogger(SoapUITestCaseService.class);

	@Autowired
	private SoapUITestCaseDao soapUITestCaseDao;

	@Transactional(readOnly = true)
	public Iterable<SoapUITestCase> findAll(Pageable pageable) {
		return soapUITestCaseDao.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public SoapUITestCase findOne(Long id) {
		return soapUITestCaseDao.findOne(id);
	}

	@Transactional(readOnly = false)
	public void add(SoapUITestCase soapUITestCase) {
		if (StringUtils.isBlank(soapUITestCase.name) || StringUtils.isBlank(soapUITestCase.operateUser)) {
			throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
		}

		if (!StringUtils.isBlank(soapUITestCase.statusState) && StringUtils.isBlank(soapUITestCase.Reason)) {
			throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
		}
		
		soapUITestCaseDao.save(soapUITestCase);
		logger.info("保存成功。");
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
		
		SoapUITestCase soapUITestCase = new SoapUITestCase();
		soapUITestCase.name = name;
		soapUITestCase.statusState = statusState;
		soapUITestCase.Reason = Reason;
		soapUITestCase.duration = duration;
		soapUITestCase.operateDate = operateDate;
		soapUITestCase.operateUser = operateUser;
		
		soapUITestCaseDao.save(soapUITestCase);
		logger.info("保存成功。");
	}

}
