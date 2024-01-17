package cpsc2150.banking.controllers;

import cpsc2150.banking.models.*;
import cpsc2150.banking.views.*;

public class MortgageGUIController implements IMortgageController {
    private IMortgageView view;

    public MortgageGUIController(IMortgageView v) {
        view = v;
    }
    public void submitApplication() {
     // input variables
        String name;
        double yearlyIncome;
        double monthlyDebt;
        int creditScore;
        double houseCost;
        double downPayment;
        int years;
        boolean approved;

        // removes the need for a magic 0
        int ZERO = 0;

        name = view.getName();      // takes input
        if (name.equals(""))    // input validation
        {
            view.printToUser("Invalid Name");
            return;
        }

        yearlyIncome = view.getYearlyIncome();  // takes input
        if (yearlyIncome <= ZERO)          // input validation
        {
            view.printToUser("Income must be greater than 0.");
            return;
        }

        monthlyDebt = view.getMonthlyDebt();    // takes input
        if (monthlyDebt < ZERO)            // input validation
        {
            view.printToUser("Debt must be greater than or equal to 0.");
            return;
        }

        creditScore = view.getCreditScore();        // takes input
        if (creditScore <= ZERO || creditScore > ICustomer.MAX_CREDIT_SCORE)       // input validation
        {
            view.printToUser("Credit Score must be greater than 0 and less than 850");
            return;
        }

        houseCost = view.getHouseCost();        // takes input
        if (houseCost <= ZERO)             // input validation
        {
            view.printToUser("Cost must be greater than 0.");
            return;
        }

        downPayment = view.getDownPayment();        // takes input
        if (downPayment <= ZERO || downPayment > houseCost)    // input validation
        {
            view.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
            return;
        }

        years = view.getYears();        // takes input
        if (years <= ZERO)                 // input validation
        {
            view.printToUser("Years must be greater than 0.");
            return;
        }

        // creates customer w/ user-provided data
        ICustomer customer = new Customer(monthlyDebt, yearlyIncome, creditScore, name);
        // creates mortgage w/ user-provided data
        IMortgage mortgage = new Mortgage(houseCost, downPayment, years, customer);

        // establishes loan approval status
        approved = mortgage.loanApproved();
        // displays loan approval status
        view.displayApproved(approved);

        if(approved)        // if approved
        {
            view.displayRate(mortgage.getRate());
            view.displayPayment(mortgage.getPayment());
        }
        else        // not approved
        {
            view.displayRate(0.0);
            view.displayPayment(0.0);
        }
    }
}