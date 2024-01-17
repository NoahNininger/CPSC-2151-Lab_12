/* Noah Nininger */
package cpsc2150.banking.models;
import java.lang.Math;

/**
 * holds information about mortgage, and implements IMortgage & 
 * extends AbsMortgage
 *
 * @correspondences payment = Payment
 *                 rate = Rate
 *                 customer = Customer
 *                 debtToIncomeRatio = DebtToIncomeRatio
 *                 principal = Principal
 *                 numberOfPayments = NumberOfPayments
 *                 percentDown = PercentDown
 *
 * @invariants payment = (rate * principal) / (1-(1+rate)^ -numberOfPayments) AND
 *              0 <= rate <= 1 AND
 *              0 < debtToIncomeRatio AND
 *              MIN_YEARS * MONTHS_IN_YEAR <= numberOfPayments 
 *                  <= MAX_YEARS * MONTHS_IN_YEAR AND
 *              0 < principal AND
 *              0 <= percentDown < 1
 */
public class Mortgage extends AbsMortgage implements IMortgage
{
    private double payment, rate, debtToIncomeRatio, principal, percentDown;
    private int numberOfPayments;
    private ICustomer customer;

    /**
     * that takes in the cost of the home (double), the down payment (double), 
     * the number of years (int), and the customer (ICustomer)
     *
     * @param costOfHome double that represents the cost of home
     * @param downPayment double that represents the down payment on the home
     * @param numYears int that represents the number of years the mortgage is
     * @param input instance of ICustomer that takes in data
     *
     * @post payment = (rate * principal) / (1-(1+rate)^ -numberOfPayments) AND
     *       0 <= rate <= 1 AND
     *       0 < debtToIncomeRatio AND
     *       MIN_YEARS * MONTHS_IN_YEAR <= numberOfPayments
     *          <= MAX_YEARS * MONTHS_IN_YEAR AND
     *       0 < principal AND
     *       0 <= percentDown < 1
     */
    public Mortgage(double costOfHome, double downPayment, int numYears, 
                    ICustomer input)
    {
        customer = input;   // initializes the instance of ICustomer to input
        rate = BASERATE;    // initializes rate to BASERATE

     // if loan is less than 30 years add .5%, otherwise add 1%
        if(numYears < MAX_YEARS)
            { rate += 0.005; }
        else { rate += 0.01; }

     // if percent down is not at least 20%
        if(downPayment < (PREFERRED_PERCENT_DOWN * costOfHome))
            { rate += 0.005; }

     // uses credit score to determine apr
        if(customer.getCreditScore() < BADCREDIT)
            { rate += VERYBADRATEADD; }
        else if(customer.getCreditScore() < FAIRCREDIT)
            { rate += BADRATEADD; }
        else if(customer.getCreditScore() < GOODCREDIT)
            { rate += NORMALRATEADD; }
        else if(customer.getCreditScore() < GREATCREDIT)
            { rate += GOODRATEADD; }

     // calculations
        rate = rate / MONTHS_IN_YEAR;
        principal = costOfHome - downPayment;
        percentDown = downPayment / costOfHome;
        numberOfPayments = numYears * MONTHS_IN_YEAR;
        payment = (rate * principal) / (1 - Math.pow(1 + rate, -numberOfPayments));
        debtToIncomeRatio = (payment + customer.getMonthlyDebtPayments()) / 
                            (customer.getIncome() / MONTHS_IN_YEAR);
    }

    /**
     * This method checks to see if the mortgage loan has been approved or not.
     *
     * @return true if the loan is approved, false otherwise.
     *
     * @post loanApproved iff (Rate*12 < RATE_TOO_HIGH AND PercentDown >= MIN_PERCENT_DOWN AND DebtToIncomeRatio <= DTOITOOHIGH) AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public boolean loanApproved()
    {
        if((getRate() < RATETOOHIGH && percentDown >= 
                MIN_PERCENT_DOWN && debtToIncomeRatio <= DTOITOOHIGH))
            { return true; }
        else { return false; }
    }

    /**
     * This method returns the amount that must be paid per month for the mortgage loan.
     *
     * @return the monthly payment on the loan
     *
     * @post getPayment = Payment AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public double getPayment()
        { return payment; }

    /**
     * This method returns the interest rate (APR).
     *
     * @return the interest rate (APR) for this customer
     *
     * @post getRate = Rate * 12 AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public double getRate()
        { return rate * MONTHS_IN_YEAR; }

    /**
     * This method returns the customer's principal amount for the mortgage loan.
     *
     * @return the principal amount of the loan
     *
     * @post getPrincipal = Principal AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public double getPrincipal()
        { return principal; }

    /**
     * This method returns the amount of years the customer applied the mortgage loan for.
     *
     * @return the number of years the loan is for
     *
     * @post getYears = Years AND
     *          Payment = #Payment AND Rate = #Rate AND Customer = #Customer AND DebtToIncomeRatio = #DebtToIncomeRatio AND
     *          Principal = #Principal AND NumberOfPayments = #NumberOfPayments AND PercentDown = #PercentDown
     */
    public int getYears()
        { return numberOfPayments / MONTHS_IN_YEAR; }
}
