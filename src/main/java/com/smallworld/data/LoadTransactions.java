package com.smallworld.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Author:- Ahsan Parekh (Principle Software Engineer)
 * Date:- 24/06/2023
 **/
public class LoadTransactions {
    public static  final ObjectMapper objectMapper = new ObjectMapper();
    public static  List<Transaction> transactionList;
    public static List<Transaction> getTransactions()  {
        try {
            if(Objects.isNull(transactionList) || transactionList.isEmpty() || transactionList.size()<1){
                System.out.println("Load Transactions");
                File file = new File("transactions.json");
                transactionList = objectMapper.readValue(file, new TypeReference<List<Transaction>>() {});
            }
            return transactionList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
