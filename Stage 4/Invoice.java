import java.text.SimpleDateFormat;

class Invoice {
    private Guest costumer;
    private double roomPrice;
    private int nightsStayed;
    private double discount;
    private double totalPrice;

    public Invoice(Guest costumer, double roomPrice, int nightsStayed) {
        this.costumer = costumer;
        this.roomPrice = roomPrice;
        this.nightsStayed = nightsStayed;
        if(costumer instanceof Member) {
            discount = ((Member) costumer).getDiscout();
        } else {
            discount = 0;
        }
        calculateTotal();
    }

    public void setDiscount(double roomPrice) {
        this.roomPrice = roomPrice;
        calculateTotal();
    }
    public void setCostumer(Guest costumer) {
        this.costumer = costumer;
    }

    public Guest getCostumer() {
        return costumer;
    }

    public void calculateTotal() {
        double totalPrice = (roomPrice * nightsStayed);
        totalPrice = totalPrice + totalPrice * discount;
    }

    public String printInvoice() {
        StringBuilder invoiceString = new StringBuilder();
        invoiceString.append("|" + "\t");
        invoiceString.append(String.format("%-20s", costumer.getFirstName()));
        invoiceString.append("|" + "\t");
        invoiceString.append(String.format("%-20s", costumer.getLastName()));
        invoiceString.append("|" + "\t");
        invoiceString.append(String.format("%-20s", nightsStayed));
        invoiceString.append("|" + "\t");
        invoiceString.append(String.format("%-20s", (discount * 100) + "%"));
        invoiceString.append("|" + "\t");
        invoiceString.append(String.format("%-20s", totalPrice));
        invoiceString.append("|\n");
    
        return invoiceString.toString();
    }
}