package com.anand.bankcustomerinfoservice.resources;

import com.anand.bankcustomerinfoservice.dao.DBBankCustomerInfo;
import com.anand.bankcustomerinfoservice.models.BankCustomerInfoItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodType;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/CustomerInfo")  //Endpoint URL to access
public class BankCustomerResources {

    private BankCustomerInfoItems objBankCustomerInfoItems;
    @Autowired
    DBBankCustomerInfo repoDBBankCustomerInfo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Iterable<BankCustomerInfoItems> arrayBankCustomerInfoItems;
    private List<Map<String, Object>> retVal;


    @RequestMapping(value = "/CreateCustomer", method = RequestMethod.GET)
    public boolean createCustomer( BankCustomerInfoItems objBankCustomerInfoItems,@RequestParam(value="strCustomerName") String strCustomerName,@RequestParam(value="strCustomerCity") String strCustomerCity,@RequestParam(value="intCustomerId") String intCustomerId) {
        objBankCustomerInfoItems.setStrCustomerCity(strCustomerCity);
        objBankCustomerInfoItems.setStrCustomerName(strCustomerName);
        objBankCustomerInfoItems.setIntCustomerId(Integer.parseInt(intCustomerId));
        repoDBBankCustomerInfo.save(objBankCustomerInfoItems);
        return true;
    }

    @RequestMapping(value="GetAllCustomerInformation",method = RequestMethod.GET)
    public Iterable<BankCustomerInfoItems> getAllCustomerInfo(){
       return  arrayBankCustomerInfoItems = repoDBBankCustomerInfo.findAll();
       // System.out.println("Count:- "+repoDBBankCustomerInfo.count());
        //return Collections.singletonList(new BankCustomerInfoItems("Anand","London",1));
    }

    @RequestMapping(value="/{intCustomerId}",method = RequestMethod.GET)
    public BankCustomerInfoItems getCustomerInfo(BankCustomerInfoItems objBankCustomerInfoItems,@PathVariable("intCustomerId") Integer intCustomerId){
        //public List<Map<String, Object>> getCustomerInfo(BankCustomerInfoItems objBankCustomerInfoItems,@PathVariable("intCustomerId") Integer intCustomerId){
        //Iterable<BankCustomerInfoItems> getVal = repoDBBankCustomerInfo.findAll();
        List<Map<String, Object>> retVal;
        retVal =  jdbcTemplate.queryForList("Select * from CUSTOMER_INFO where INT_CUSTOMER_ID=" + intCustomerId);
        if(retVal.size()==1){

            objBankCustomerInfoItems.setStrCustomerName(retVal.get(0).get("STR_CUSTOMER_NAME").toString());
            objBankCustomerInfoItems.setStrCustomerCity(retVal.get(0).get("STR_CUSTOMER_CITY").toString());
            objBankCustomerInfoItems.setIntCustomerId(Integer.parseInt(retVal.get(0).get("INT_CUSTOMER_ID").toString()));
        }else{
            System.out.println("Did not received any customer ID");
        }
        return objBankCustomerInfoItems;
        //return   retVal;


//        System.out.println("Count:- "+repoDBBankCustomerInfo.count());
//        return Collections.singletonList(new BankCustomerInfoItems("Anand","London",1));
    }
    //@RequestMapping("/AddCustomer")
    //public boolean  getCustomerInfo(@RequestParam String strCustomerName,@RequestParam String strCustomerCity,@RequestParam Integer intCustomerId)
    //@RequestMapping(method= RequestMethod.POST)
    @RequestMapping(value="/AddCustomer", method= RequestMethod.POST)
    public boolean  getCustomerInfo(){
        System.out.println("1Set Value");
        //System.out.println("1Set Value"+strCustomerCity);
        //return Collections.singletonList(new BankCustomerInfoItems("Anand","London",1));
        //repoDBBankCustomerInfo.save(objBankCustomerInfoItems);
        //objBankCustomerInfoItems.setStrCustomerName(strCustomerName);
        //objBankCustomerInfoItems.setStrCustomerCity(strCustomerCity);
       // objBankCustomerInfoItems.setIntCustomerId(intCustomerId);
        System.out.println("Set Value"+objBankCustomerInfoItems.getStrCustomerName());
        //repoDBBankCustomerInfo.save( objBankCustomerInfoItems);
        return true;
    }

    @RequestMapping(value = "/time", params = { "info=time" })
    public String showTime() {

        LocalTime now = LocalTime.now();

        return String.format("%s", now.toString());
    }



}
