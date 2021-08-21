package loan;

import java.util.Date;
import java.util.List;

public class CustomerLoan extends DataAccess<CustomerLoan> {

    public CustomerLoan(){
    }

    public CustomerLoan(int customerId){
        this.customerId = customerId;
    }

    private int loanId;
    private int amount; //small money, without decimal
    private int dailyRate; //small money, without decimal
    private int customerId;
    private int productId;
    private Date loanDate;
    private PaymentStatus status;
    private List<LoanPayment> payments;

    enum PaymentStatus{ACTIVE, COMPLETED, DEFAULT};

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public PaymentStatus isStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        Customer customer = null; //get customer details with id
        return customer;
    }

    public void setCustomer(int customerId) {
        this.customerId = customerId;
    }

    public int getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getTotalPaid() {
        //access database to get total amount paid
        return payments.stream().mapToInt(x->x.getAmount()).sum();
    }

    public void notifyQueue(){
        //send message to the Queue for notifications
    }
}

