package picc.wentian.soapUI.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.utils.mapper.BeanMapper;
import org.springside.modules.web.MediaTypes;

import picc.wentian.soapUI.domain.SoapUITestCase;
import picc.wentian.soapUI.dto.SoapUITestCaseDto;
import picc.wentian.soapUI.service.SoapUITestCaseService;
import picc.wentian.soapUI.service.exception.ErrorCode;
import picc.wentian.soapUI.service.exception.ServiceException;

// Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class SoapUITestCaseEndpoint {

	private static Logger logger = LoggerFactory.getLogger(SoapUITestCaseEndpoint.class);

	@Autowired
	private SoapUITestCaseService soapUITestCaseService;

	@RequestMapping(value = "/api/soapUITestCases", produces = MediaTypes.JSON_UTF_8)
	public List<SoapUITestCaseDto> listAllSoapUITestCase(Pageable pageable) {
		Iterable<SoapUITestCase> soapUITestCase = soapUITestCaseService.findAll(pageable);

		return BeanMapper.mapList(soapUITestCase, SoapUITestCase.class,SoapUITestCaseDto.class);
	}

	@RequestMapping(value = "/api/soapUITestCase/view/{id}", produces = MediaTypes.JSON_UTF_8)
	public SoapUITestCaseDto listOneSoapUITestCase(@PathVariable("id") Long id) {
		SoapUITestCase soapUITestCase = soapUITestCaseService.findOne(id);
		SoapUITestCaseDto dto = BeanMapper.map(soapUITestCase, SoapUITestCaseDto.class);
		return dto;
	}

	@RequestMapping(value = "/api/soapUITestCase/add", method = RequestMethod.POST,
			produces = MediaTypes.JSON_UTF_8)
	public void add(@RequestBody SoapUITestCaseDto soapUITestCaseDto) {

		if (StringUtils.isEmpty(soapUITestCaseDto.name) || StringUtils.isEmpty(soapUITestCaseDto.statusState)
				|| StringUtils.isEmpty(soapUITestCaseDto.Reason)|| StringUtils.isEmpty(soapUITestCaseDto.duration)
				|| StringUtils.isEmpty(soapUITestCaseDto.operateDate)|| StringUtils.isEmpty(soapUITestCaseDto.operateUser)) {
			logger.error("出错了。");
			throw new ServiceException("一些必录项没有录入，请检查。", ErrorCode.BAD_REQUEST);
		}

		// 使用BeanMapper, 将与外部交互的BookDto对象复制为应用内部的Book对象
		SoapUITestCase soapUITestCase = BeanMapper.map(soapUITestCaseDto, SoapUITestCase.class);
		soapUITestCaseService.add(soapUITestCase);
	}
}
