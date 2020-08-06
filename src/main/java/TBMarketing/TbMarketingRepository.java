package TBMarketing;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TbMarketingRepository extends PagingAndSortingRepository<TbMarketing, Long>{

//    TbMarketing findById(Long id);
    TbMarketing findAllByPurchaseId(String purchaseId);

}