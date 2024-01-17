/* Noah Nininger */
package cpsc2150.banking.views;
import cpsc2150.banking.controllers.*;
/**
 * This interface specifiec the functionality required by the view for our mortgage
 application program
 * There are very few contracts as the view does not do any sort of input
 validation, it just
 * interacts with the user.
 *
 * Defines:
 * Controller: The IMortgageController associated with this view
 */
public interface IMortgageView {
    /**
     * This method adds a controller, so the view can inform it has
     * received information.
     *
     * @param c the Controller object
     *
     * @pre c != NULL
     * @post Controller = c
     */
    void setController(IMortgageController c);
    /**
     * This method retrieves the house cost.
     *
     * @return the house cost provided by the user
     *
     * @post getHouseCost = [ a floating-point number entered by the user ]
     */
    double getHouseCost();
    /**
     * This method retrieves the down payment.
     *
     * @return the down payment provided by the user
     *
     * @post getDownPayment = [ a floating-point number entered by the user ]
     */
    double getDownPayment();
    /**
     * This method returns the number of years the user intend to mortgage.
     *
     * @return the years provided by the user
     *
     * @post getYears = [ an integer number entered by the user ]
     */
    int getYears();
    /**
     * This method returns the monthly debt the user has.
     *
     * @return the monthly debt provided by the user
     *
     * @post getMonthlyDebt = [ a floating-point number entered by the user ]
     */
    double getMonthlyDebt();
    /**
     * This method returns the user's yearly income.
     *
     * @return the income provided by the user
     */
    double getYearlyIncome();
    /**
     * This method returns the user's credit score.
     *
     * @return the credit score provided by the user
     *
     * @post getCreditScore = [ an integer number entered by the user ]
     */
    int getCreditScore();
    /**
     * This method returns the user's name.
     *
     * @return the name provided by the user
     *
     * @post getName = [ a string entered by the user ]
     */
    String getName();
    /**
     * This method prints a message to the user.
     *
     * @param s the message to provide to the user
     *
     * @post [ displays the message in s to the user ]
     */
    void printToUser(String s);
    /**
     * This method displays the monthly mortgage payment amount.
     *
     * @param p the monthly payment amount for the loan
     *
     * @pre p >= 0
     * @post [ displays the floating-point number p to the user ]
     */
    void displayPayment(double p);
    /**
     * This method displays the interest rate.
     *
     * @param r the (APR) interest rate for the loan
     *
     * @pre 0 <= r <= 1
     * @post [ displays the the floating-point number r to the user ]
     */
    void displayRate(double r);
    /**
     * This method displays whether or not the loan was approved.
     *
     * @param a whether or not the mortgage application was approved
     *
     * @pre a iff [loan was approved]
     * @post [ displays the value of a to the user ]
     */
    void displayApproved(boolean a);
    /**
     * This method ask to see if the user would like to enter another
     * mortgage information.
     *
     * @return whether or not the user would like to apply for another mortgage
    with the same customer
     *
     * @post getAnotherMortgage = [ true if the user wants to enter another
    mortgage, false otherwise ]
     */
    boolean getAnotherMortgage();
    /**
     * This method ask to see if the user would like to enter another
     * user information.
     *
     * @return whether or not the user would like to enter another customer
     *
     * @post getAnotherMortgage = [ true if the user wants to enter another
    customer, false otherwise ]
     */
    boolean getAnotherCustomer();
}