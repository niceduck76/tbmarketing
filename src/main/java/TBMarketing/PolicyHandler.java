package TBMarketing;

import TBMarketing.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    TbMarketingRepository tbMarketingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCancelled_ChangeInventory(@Payload Cancelled cancelled){

        if(cancelled.isMe()){

            String purchaseId = cancelled.getPurchaseId();


            System.out.println("1111111111 purchaseId=" + purchaseId);

            if(null != purchaseId || !purchaseId.equals("")){
                System.out.println("22222222222 purchaseId=" + purchaseId);

                TbMarketing tbMarketing = tbMarketingRepository.findAllByPurchaseId(purchaseId);

                tbMarketing.setPurchaseStatus(cancelled.getPurchaseStatus());
                tbMarketingRepository.save(tbMarketing);
                System.out.println("##### listener wheneverCancelled_ChangeInventory : " + cancelled.toJson());
            }
        }

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchased_Tbpurchase(@Payload Purchased purchased){

        if(purchased.isMe()){
            TbMarketing tbMarketing = new TbMarketing();
            tbMarketing.setPurchaseId(purchased.getPurchaseId());
            tbMarketing.setPrdNm(purchased.getPrdNm());
            tbMarketing.setCustNm(purchased.getCustNm()); //전화번호
            tbMarketing.setPurchaseStatus(purchased.getPurchaseStatus());

            tbMarketingRepository.save(tbMarketing);
            System.out.println("##### listener wheneverPurchased_Tbpurchase : " + purchased.toJson());
        }
    }

}
