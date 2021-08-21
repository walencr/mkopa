package loan;

import java.util.Date;

public class LoanProcessor {

    //API CALL
    public void add(Customer customer, int productId, int amount, int dailyRate){
        if (customer.isActive()){
            //msg: customer is not active
        }

        if (customer.hasActiveLoan()){
            //msg: "customer has active loans";
        }

        CustomerLoan customerLoan = new CustomerLoan();
        customerLoan.setCustomer(customer.getCustomerId());
        customerLoan.setLoanDate(new Date());
        customerLoan.setAmount(amount);
        customerLoan.setStatus(CustomerLoan.PaymentStatus.ACTIVE);
        customerLoan.setDailyRate(dailyRate);
        customerLoan.setProductId(productId);

        customerLoan.save();
        customerLoan.notifyQueue();
    }

    //MESSAGE BROKER
    public void processPayment(int customerId, int amount, String transactionId){
        //get active loan
        //apply payment

        LoanPayment payment = null; //get payment by transactionId
        if (payment != null){
            //payment already applied
            return;
        }

        CustomerLoan loan = null; //get customer loan using customerId
        if (loan != null){
            LoanPayment loanPayment = new LoanPayment();
            loanPayment.setLoanId(loan.getLoanId());
            loanPayment.setAmount(amount);
            loanPayment.setCustomerId(customerId);
            loanPayment.setTransactionId(transactionId);

            loanPayment.save();
        }

        float totalAmountPaid = loan.getTotalPaid();

        if (totalAmountPaid > loan.getAmount()){
            loan.setStatus(CustomerLoan.PaymentStatus.COMPLETED);
            loan.save();
            loan.notifyQueue();
        }
    }

    public boolean hasActiveLoan(int customerId){
        return false;
    }


}


