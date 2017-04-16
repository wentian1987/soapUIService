package picc.wentian.soapUI.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import picc.wentian.soapUI.domain.SoapUITestSuite;
import picc.wentian.soapUI.repository.SoapUITestSuiteDao;

// Spring Bean的标识.
@Service
public class SoapUITestSuiteService {
	
	private static Logger logger = LoggerFactory.getLogger(SoapUITestSuiteService.class);

	@Autowired
	private SoapUITestSuiteDao SoapUITestSuiteDao;

	@Transactional(readOnly = true)
	public Iterable<SoapUITestSuite> findAll(Pageable pageable) {
		return SoapUITestSuiteDao.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public SoapUITestSuite findOne(Long id) {
		return SoapUITestSuiteDao.findOne(id);
	}
}
