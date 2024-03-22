/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package rw.iprctumba.classof2024;

/**
 *
 * @author HIRWA
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Loan extends JFrame implements ActionListener {
    private JTextField amountField;
    private JTextField interestRateField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton viewTransactionsButton;
    private JTextArea displayArea;
    private BankAccount bankAccount;

    public Loan() {
        setTitle("Banking Application");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up fonts and colors
        Font titleFont = new Font("Arial", Font.BOLD, 28);
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Color titleColor = new Color(51, 102, 204);
        Color buttonColor = new Color(51, 153, 102);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Banking Application");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(titleColor);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(labelFont);
        amountField = new JTextField();
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        JLabel interestRateLabel = new JLabel("Interest Rate (%):");
        interestRateLabel.setFont(labelFont);
        interestRateField = new JTextField();
        inputPanel.add(interestRateLabel);
        inputPanel.add(interestRateField);

        depositButton = new JButton("Deposit");
        depositButton.setFont(labelFont);
        depositButton.setBackground(buttonColor);
        depositButton.setForeground(Color.WHITE);
        depositButton.addActionListener(this);
        inputPanel.add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(labelFont);
        withdrawButton.setBackground(buttonColor);
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.addActionListener(this);
        inputPanel.add(withdrawButton);

        viewTransactionsButton = new JButton("View Transactions");
        viewTransactionsButton.setFont(labelFont);
        viewTransactionsButton.setBackground(buttonColor);
        viewTransactionsButton.setForeground(Color.WHITE);
        viewTransactionsButton.addActionListener(this);
        inputPanel.add(viewTransactionsButton);

        mainPanel.add(inputPanel, BorderLayout.WEST);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(labelFont);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        bankAccount = new BankAccount();

        add(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            performDeposit();
        } else if (e.getSource() == withdrawButton) {
            performWithdrawal();
        } else if (e.getSource() == viewTransactionsButton) {
            viewTransactions();
        }
    }

    private void performDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid deposit amount.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            bankAccount.deposit(amount);

            displayArea.append("Deposit of $" + amount + " successful.\n");
            displayArea.append("Current Balance: $" + bankAccount.getBalance() + "\n\n");

            amountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numerical amount.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performWithdrawal() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid withdrawal amount.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (bankAccount.getBalance() < amount) {
                JOptionPane.showMessageDialog(this, "Insufficient funds for withdrawal.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            bankAccount.withdraw(amount);

            displayArea.append("Withdrawal of $" + amount + " successful.\n");
            displayArea.append("Current Balance: $" + bankAccount.getBalance() + "\n\n");

            amountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numerical amount.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewTransactions() {
        List<String> transactions = bankAccount.getTransactionHistory();
        if (transactions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No transactions to display.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        displayArea.append("Transaction History:\n");
        for (String transaction : transactions) {
            displayArea.append(transaction + "\n");
        }
        displayArea.append("\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Loan());
    }

    private static class BankAccount {
                private double balance;
        private List<String> transactionHistory;

        public BankAccount() {
            this.balance = 0;
            this.transactionHistory = new ArrayList<>();
        }

        public void deposit(double amount) {
            balance += amount;
            transactionHistory.add("Deposit: +" + amount);
        }

        public void withdraw(double amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
        }

        public double getBalance() {
            return balance;
        }

        public List<String> getTransactionHistory() {
            return transactionHistory;
        }
    }
}





