package picc.wentian.soapUI.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import picc.wentian.soapUI.domain.SoapUITestRun;

/**
 * 基于Spring Data JPA的Dao接口, 自动根据接口生成实现.
 * 
 * PagingAndSortingRepository默认有针对实体对象的CRUD与分页查询函数.
 * 
 * Spring Data JPA 还会解释新增方法名生成新方法的实现.
 */
public interface SoapUITestRunDao extends PagingAndSortingRepository<SoapUITestRun, Long> {
	List<SoapUITestRun> findById(Long id, Pageable pageable);
	//Iterable<SoapUITestRun> findByPageAndSort(Pageable pageable, Sort sort);

}
