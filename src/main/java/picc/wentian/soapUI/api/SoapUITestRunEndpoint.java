package picc.wentian.soapUI.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.utils.mapper.BeanMapper;
import org.springside.modules.web.MediaTypes;

import picc.wentian.soapUI.domain.SoapUITestRun;
import picc.wentian.soapUI.dto.SoapUITestRunDto;
import picc.wentian.soapUI.service.SoapUITestRunService;
import picc.wentian.soapUI.service.exception.ErrorCode;
import picc.wentian.soapUI.service.exception.ServiceException;

// Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class SoapUITestRunEndpoint {

	private static Logger logger = LoggerFactory.getLogger(SoapUITestRunEndpoint.class);

	@Autowired
	private SoapUITestRunService soapUITestRunService;

	@RequestMapping(value = "/api/soapUITestRuns", produces = MediaTypes.JSON_UTF_8)
	public List<SoapUITestRunDto> listAllSoapUITestRun(Pageable pageable) {
		Iterable<SoapUITestRun> soapUITestRun = soapUITestRunService.findAll(new PageRequest(pageable.getPageNumber(), pageable.getPageSize() ,new Sort(Direction.DESC, "operateDate")));
		//Iterable<SoapUITestRun> soapUITestRun = soapUITestRunService.findAll(pageable ,new Sort(Direction.DESC, "operateDate"));

		return BeanMapper.mapList(soapUITestRun, SoapUITestRun.class,SoapUITestRunDto.class);
	}

	@RequestMapping(value = "/api/soapUITestRun/view/{id}", produces = MediaTypes.JSON_UTF_8)
	public SoapUITestRunDto listOneSoapUITestRun(@PathVariable("id") Long id) {
		SoapUITestRun soapUITestRun = soapUITestRunService.findOne(id);
		SoapUITestRunDto dto = BeanMapper.map(soapUITestRun, SoapUITestRunDto.class);
		return dto;
	}

	@RequestMapping(value = "/api/soapUITestRun/add", method = RequestMethod.POST,
			produces = MediaTypes.JSON_UTF_8)
	public void add(@RequestBody SoapUITestRunDto soapUITestRunDto) {

		if ( StringUtils.isEmpty(soapUITestRunDto.statusState)
				||  StringUtils.isEmpty(soapUITestRunDto.operateDate)|| StringUtils.isEmpty(soapUITestRunDto.operateUser)) {
			logger.error("出错了。");
			throw new ServiceException("一些必录项没有录入，请检查。", ErrorCode.BAD_REQUEST);
		}

		// 使用BeanMapper, 将与外部交互的BookDto对象复制为应用内部的Book对象
		SoapUITestRun soapUITestRun = BeanMapper.map(soapUITestRunDto, SoapUITestRun.class);
		soapUITestRunService.add(soapUITestRun);
	}
}
