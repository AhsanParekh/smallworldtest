package com.smallworld.unitTests;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.data.LoadTransactions;
import com.smallworld.data.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Author:- Ahsan Parekh (Principle Software Engineer)
 * Date:- 24/06/2023
 **/
public class Main {
    private final  TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();
    @Test
    public void testLoadAllTransactions(){
        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testLoadAllTransactions::::[Start]");
        List<Transaction> transactionList= LoadTransactions.getTransactions();
        System.out.println("Unit Test::::testLoadAllTransactions::::Transaction Count->"+transactionList.size());
        Assert.assertTrue(transactionList.size()>0);
        System.out.println("Unit Test::::testLoadAllTransactions::::[END]");
    }
    @Test
    public void testTotalTransactionAmount(){
        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testTotalTransactionAmount::::[START]");
        double totalTransactionAmount=transactionDataFetcher.getTotalTransactionAmount();
        System.out.println("Unit Test::::testTotalTransactionAmount::::Total Transaction Amount->"+totalTransactionAmount);
        Assert.assertEquals(4371.37,totalTransactionAmount,0.0);
        System.out.println("Unit Test::::testTotalTransactionAmount::::[END]");
    }
    @Test
    public void testTotalTransactionAmountSentBy(){
        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testTotalTransactionAmountSentBy::::[START]");
        String senderFullName = "Tom Shelby";
        double totalTransactionAmountSentBy=transactionDataFetcher.getTotalTransactionAmountSentBy(senderFullName);
        System.out.println("Unit Test::::testTotalTransactionAmountSentBy::::Total Transaction Amount Sent By "+ senderFullName
                +"->"+totalTransactionAmountSentBy);
        Assert.assertEquals(828.26,totalTransactionAmountSentBy,0.0);
        System.out.println("Unit Test::::testTotalTransactionAmountSentBy::::[END]");
    }
    @Test
    public void testMaxTransactionAmount(){
        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testMaxTransactionAmount::::[START]");
        double maxTransactionAmount=transactionDataFetcher.getMaxTransactionAmount();
        System.out.println("Unit Test::::testMaxTransactionAmount::::Max Transaction Amount->"+maxTransactionAmount);
        Assert.assertEquals(985.0,maxTransactionAmount,0.0);
        System.out.println("Unit Test::::testMaxTransactionAmount::::[END]");
    }
    @Test
    public void testCountUniqueClients(){
        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testCountUniqueClients::::[START]");
        long countUniqueClients=transactionDataFetcher.countUniqueClients();
        System.out.println("Unit Test::::testCountUniqueClients::::Count Unique Clients->"+countUniqueClients);
        Assert.assertEquals(5.0,countUniqueClients,0.0);
        System.out.println("Unit Test::::testCountUniqueClients::::[END]");
        //countUniqueClients
    }
    @Test
    public void testHasOpenComplianceIssues(){
        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testHasOpenComplianceIssues::::[START]");
        String clientFullName = "Tom Shelby";
        boolean hasOpenComplianceIssues = transactionDataFetcher.hasOpenComplianceIssues(clientFullName);
        System.out.println("Unit Test::::testHasOpenComplianceIssues::::Has Open Compliance Issues Client Name "+clientFullName+"->"+hasOpenComplianceIssues);
        Assert.assertTrue(hasOpenComplianceIssues);
        clientFullName = "Aunt Polly";
        hasOpenComplianceIssues = transactionDataFetcher.hasOpenComplianceIssues(clientFullName);
        System.out.println("Unit Test::::testHasOpenComplianceIssues::::Has Open Compliance Issues Client Name "+clientFullName+"->"+hasOpenComplianceIssues);
        Assert.assertFalse(hasOpenComplianceIssues);
        clientFullName = "Ben Younger";
        hasOpenComplianceIssues = transactionDataFetcher.hasOpenComplianceIssues(clientFullName);
        System.out.println("Unit Test::::testHasOpenComplianceIssues::::Has Open Compliance Issues Client Name "+clientFullName+"->"+hasOpenComplianceIssues);
        Assert.assertTrue(hasOpenComplianceIssues);
        System.out.println("Unit Test::::testHasOpenComplianceIssues::::[END]");
    }
    @Test
    public void  testTransactionsByBeneficiaryName(){
        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testTransactionsByBeneficiaryName::::[START]");
        Set<String> data = new HashSet<>();
        data.add("MacTavern");
        data.add("Major Campbell");
        data.add("Luca Changretta");
        data.add("Ben Younger");
        data.add("Arthur Shelby");
        data.add("Aberama Gold");
        data.add("Winston Churchill");
        data.add("Oswald Mosley");
        data.add("Michael Gray");
        data.add("Alfie Solomons");
        Map<String,Transaction> transactionData = transactionDataFetcher.getTransactionsByBeneficiaryName();
        for(Map.Entry<String, Transaction> entry : transactionData.entrySet()){
            Assert.assertTrue(data.contains(entry.getKey()));
            System.out.println("Unit Test::::testTransactionsByBeneficiaryName::::Beneficiary Name->"+entry.getKey()+" Mtn->"+entry.getValue().getIssueId());
        }
        System.out.println("Unit Test::::testTransactionsByBeneficiaryName::::[END]");
    }
    @Test
    public void testUnsolvedIssueIds(){

        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testUnsolvedIssueIds::::[START]");
        Set<Integer> data = new HashSet<>();
        data.add(1);
        data.add(3);
        data.add(99);
        data.add(54);
        data.add(15);
        Set<Integer> unsolvedIssueIds= transactionDataFetcher.getUnsolvedIssueIds();
        for(Integer issueId:unsolvedIssueIds){
            Assert.assertTrue(data.contains(issueId));
            System.out.println("Unit Test::::testUnsolvedIssueIds::::Issue Id->"+issueId);
        }
        System.out.println("Unit Test::::testUnsolvedIssueIds::::[END]");
    }
    @Test
    public void  testAllSolvedIssueMessages(){
        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testAllSolvedIssueMessages::::[START]");
        Set<String> data = new HashSet<>();
        data.add("Never gonna give you up");
        data.add("Never gonna let you down");
        data.add("Never gonna run around and desert you");

        List<String> messages = transactionDataFetcher.getAllSolvedIssueMessages();
        messages.stream().forEach(msg->{
            Assert.assertTrue( Objects.isNull(msg) || data.contains(msg));
        });

        System.out.println("Unit Test::::testAllSolvedIssueMessages::::[END]");
    }
    @Test
    public void  testTop3TransactionsByAmount(){
        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testTop3TransactionsByAmount::::[START]");
        Set<Integer> data = new HashSet<>();
        data.add(15);
        data.add(54);
        data.add(78);
        List<Transaction> transactions= transactionDataFetcher.getTop3TransactionsByAmount();
        transactions.forEach(transaction -> {
            Assert.assertTrue(  data.contains(transaction.getIssueId()));
        });

        System.out.println("Unit Test::::testTop3TransactionsByAmount::::[END]");
    }
    @Test
    public void  testTopSender(){

        System.out.println("________________________________________________");
        System.out.println("Unit Test::::testTopSender::::[START]");

        Optional<String> topSender= transactionDataFetcher.getTopSender();
        Assert.assertTrue(topSender.isPresent());
        Assert.assertTrue(topSender.get().equals("Grace Burgess"));
        System.out.println("Unit Test::::testTopSender::::[END]");
    }
}
