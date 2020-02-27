package com.anand.bankcustomeraccountservice.resources;


import com.anand.bankcustomeraccountservice.dao.DBBankAccountInfo;
import com.anand.bankcustomeraccountservice.models.BankAccountDetail;
import com.anand.bankcustomeraccountservice.models.BankCustomerInfoItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/BankAccount")
public class BankAccountResources {

    @Autowired
    private WebClient.Builder objWebClientBuilder;

    @Autowired
    private DBBankAccountInfo repoDBBankAccountInfo;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping(value="/{intCustomerId}")
    public BankAccountDetail getAccountDetail(BankAccountDetail objBankAccountDetail, @PathVariable("intCustomerId") String intCustomerId){
        //public BankAccountDetail getAccountDetail(BankAccountDetail objBankAccountDetail,@RequestParam(value="intCustomerId") String intCustomerId){

        //return Collections.singletonList(new BankAccountDetail(1,1000,1));
        List<Map<String, Object>> retVal;
        retVal =  jdbcTemplate.queryForList("Select * from BANK_ACCOUNT_DETAIL  where CUSTOMER_ID=" + intCustomerId);
        if(retVal.size()==1){

            objBankAccountDetail.setIntAccountNumber(Integer.parseInt(retVal.get(0).get("ACCOUNT_NUMBER").toString()));
            objBankAccountDetail.setIntAccountBalance(Integer.parseInt(retVal.get(0).get("ACCOUNT_BALANCE").toString()));
            objBankAccountDetail.setIntCustomerId(Integer.parseInt(retVal.get(0).get("CUSTOMER_ID").toString()));
        }else{
            System.out.println("Did not received any customer ID");
        }
        return objBankAccountDetail;

    }



    @RequestMapping(value="/CreateBankAccount", method= RequestMethod.GET)
    public boolean createBankAccount(BankAccountDetail objBankAccountDetail,@RequestParam(value="intCustomerId") String intCustomerId){
        //BankCustomerInfoItems objBankCustomerInfoItems=objWebClientBuilder.build().get().uri("http://localhost:7072/CustomerInfo/"+intCustomerId).retrieve().bodyToMono(BankCustomerInfoItems.class).block();
        BankCustomerInfoItems objBankCustomerInfoItems=objWebClientBuilder.build().get().uri("http://BANK-CUSTOMER-INFO-SERVICE/CustomerInfo/"+intCustomerId).retrieve().bodyToMono(BankCustomerInfoItems.class).block();
        if(objBankCustomerInfoItems.getIntCustomerId()==Integer.parseInt(intCustomerId)){
            objBankAccountDetail.setIntCustomerId(Integer.parseInt(intCustomerId));
            objBankAccountDetail.setIntAccountNumber(1000+Integer.parseInt(intCustomerId));
            objBankAccountDetail.setIntAccountBalance(0);

            repoDBBankAccountInfo.save(objBankAccountDetail);
        }else{
            System.out.println("The account is  not created");
            return false;
        }
        return true;

    }


    @RequestMapping(value="/DepositAmount", method= RequestMethod.GET)
    public BankAccountDetail depositAmount(BankAccountDetail objBankAccountDetail,@RequestParam(value="intCustomerId") String intCustomerId,@RequestParam(value="intDepositAmount") String intDepositAmount){
        objBankAccountDetail=getAccountDetail(objBankAccountDetail,intCustomerId);
        Integer intNumber = objBankAccountDetail.getIntAccountBalance();
        objBankAccountDetail.setIntAccountBalance(intNumber + Integer.parseInt(intDepositAmount));
        intNumber = objBankAccountDetail.getIntAccountBalance();
        repoDBBankAccountInfo.save(objBankAccountDetail);
        return objBankAccountDetail;

    }

    @RequestMapping(value="/WithdrawAmount", method= RequestMethod.GET)
    public String withdrawAmount(BankAccountDetail objBankAccountDetail,@RequestParam(value="intCustomerId") String intCustomerId,@RequestParam(value="intWithdrawAmount") String intWithdrawAmount){
        String strReturnVal;
        objBankAccountDetail=getAccountDetail(objBankAccountDetail,intCustomerId);
        Integer intAccountBalance = objBankAccountDetail.getIntAccountBalance();
        if(intAccountBalance<Integer.parseInt(intWithdrawAmount)){
            //Low Account Balanace
            strReturnVal=" Withdraw amount :-"+ intWithdrawAmount+" is greater than Account Balance amount :- "+ intAccountBalance.toString();
        }else{
            Integer intOldAccountBalance=intAccountBalance;
            objBankAccountDetail.setIntAccountBalance(intAccountBalance - Integer.parseInt(intWithdrawAmount));
            intAccountBalance = objBankAccountDetail.getIntAccountBalance();
            repoDBBankAccountInfo.save(objBankAccountDetail);
            objBankAccountDetail.toString();
            strReturnVal=" Withdrawn amount :-"+ intWithdrawAmount+" from your account and new Account Balance amount is :- "+ intAccountBalance.toString() +" and old account balance was :- "+intOldAccountBalance.toString();
        }

        return strReturnVal;

    }



}
