package picc.wentian.soapUI.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.utils.mapper.BeanMapper;
import org.springside.modules.web.MediaTypes;

import picc.wentian.soapUI.domain.SoapUITestSuite;
import picc.wentian.soapUI.dto.SoapUITestSuiteDto;
import picc.wentian.soapUI.service.SoapUITestSuiteService;

// Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class SoapUITestSuiteEndpoint {

	private static Logger logger = LoggerFactory.getLogger(SoapUITestSuiteEndpoint.class);

	@Autowired
	private SoapUITestSuiteService soapUITestSuiteService;

	@RequestMapping(value = "/api/soapUITestSuites", produces = MediaTypes.JSON_UTF_8)
	public List<SoapUITestSuiteDto> listAllSoapUITestSuite(Pageable pageable) {
		Iterable<SoapUITestSuite> soapUITestSuite = soapUITestSuiteService.findAll(pageable);

		return BeanMapper.mapList(soapUITestSuite, SoapUITestSuite.class,SoapUITestSuiteDto.class);
	}

	@RequestMapping(value = "/api/soapUITestSuite/view/{id}", produces = MediaTypes.JSON_UTF_8)
	public SoapUITestSuiteDto listOneSoapUITestSuite(@PathVariable("id") Long id) {
		SoapUITestSuite soapUITestSuite = soapUITestSuiteService.findOne(id);
		return BeanMapper.map(soapUITestSuite, SoapUITestSuiteDto.class);
	}
}
