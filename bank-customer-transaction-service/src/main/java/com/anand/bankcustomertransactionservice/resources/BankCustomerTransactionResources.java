package com.anand.bankcustomertransactionservice.resources;

import com.anand.bankcustomertransactionservice.models.BankAccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/Transaction")
public class BankCustomerTransactionResources {

    @Autowired
    private WebClient.Builder objWebClientBuilder;

    @RequestMapping(value="/DepositAmount", method= RequestMethod.GET)
    public BankAccountDetail depositAmount(BankAccountDetail objBankAccountDetail,@RequestParam(value="intCustomerId") String intCustomerId,@RequestParam(value="intDepositAmount") String intDepositAmount){
        //return objWebClientBuilder.build().get().uri("http://localhost:7071/BankAccount/DepositAmount?intCustomerId="+intCustomerId+"&intDepositAmount="+intDepositAmount).retrieve().bodyToMono(BankAccountDetail.class).block();
        return objWebClientBuilder.build().get().uri("http://BANK-ACCOUNT-INFO-SERVICE/BankAccount/DepositAmount?intCustomerId="+intCustomerId+"&intDepositAmount="+intDepositAmount).retrieve().bodyToMono(BankAccountDetail.class).block();
    }

    @RequestMapping(value="/WithdrawAmount", method= RequestMethod.GET)
    public String withdrawAmount(BankAccountDetail objBankAccountDetail,@RequestParam(value="intCustomerId") String intCustomerId,@RequestParam(value="intWithdrawAmount") String intWithdrawAmount){
        return objWebClientBuilder.build().get().uri("http://BANK-ACCOUNT-INFO-SERVICE/BankAccount/WithdrawAmount?intCustomerId="+intCustomerId+"&intWithdrawAmount="+intWithdrawAmount).retrieve().bodyToMono(String.class).block();
        //return objWebClientBuilder.build().get().uri("http://localhost:7071/BankAccount/WithdrawAmount?intCustomerId="+intCustomerId+"&intWithdrawAmount="+intWithdrawAmount).retrieve().bodyToMono(String.class).block();
    }



}
