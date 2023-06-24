package com.smallworld;

import com.smallworld.data.LoadTransactions;
import com.smallworld.data.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionDataFetcher {


    /**
     * Returns the sum of the amounts of all transactions
     */
    private final List<Transaction> transactionList = LoadTransactions.getTransactions();
    public double getTotalTransactionAmount() {
        double total = transactionList.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        return total;
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        double total = transactionList.stream()
                .filter(transaction -> transaction.getSenderFullName().equalsIgnoreCase(senderFullName))
                .mapToDouble(Transaction::getAmount)
                .sum();
        return total;
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        Optional<Transaction> maxData = transactionList.stream()
                .max(Comparator.comparingDouble(Transaction::getAmount));
        if(!maxData.isPresent()){
            return 0.0;
        }
        return maxData.get().getAmount();
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        long totalCount = transactionList.stream()
                .map(Transaction::getSenderFullName)
                .distinct()
                .count();
        return totalCount;
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        List<Transaction> hasOpenComplianceIssuesTransaction = transactionList.stream()
                .filter(transaction -> (transaction.getSenderFullName().equalsIgnoreCase(clientFullName) || transaction.getBeneficiaryFullName().equalsIgnoreCase(clientFullName)) && !transaction.getIssueSolved())
                .toList();
        return hasOpenComplianceIssuesTransaction.size() > 0;
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        Map<String, Transaction> data = new HashMap<>();
        transactionList.forEach(transaction -> {
            if(!data.containsKey(transaction.getBeneficiaryFullName())){
                data.put(transaction.getBeneficiaryFullName(),transaction);
            }
        });
//                transactionList.stream()
//                .collect(Collectors.toMap(Transaction::getBeneficiaryFullName, transaction -> transaction));
        return data;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        Set<Integer> data = transactionList.stream()
                .filter(transaction -> !transaction.getIssueSolved())
                .map(Transaction::getIssueId)
                .collect(Collectors.toSet());
        return data;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        List<String> data = transactionList.stream()
                .filter(transaction -> transaction.getIssueSolved())
                .map(Transaction::getIssueMessage)
                .toList();
        return data;
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
        List<Transaction> top3Transactions = transactionList.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
        return top3Transactions;

    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        Map<String, Double> totalSentAmountBySender = transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::getSenderFullName, Collectors.summingDouble(Transaction::getAmount)));
        Optional<Map.Entry<String, Double>> maxEntry = totalSentAmountBySender.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        return Optional.ofNullable(maxEntry.get().getKey());
    }

}
