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

    public void printInvoice() {
        System.out.print("|" + "\t");
        System.out.printf("%-20s",costumer.getFirstName());
        System.out.print("|" + "\t");
        System.out.printf("%-20s", costumer.getLastName());
        System.out.print("|" + "\t");
        System.out.printf("%-20s", nightsStayed);
        System.out.print("|" + "\t");
        System.out.printf("%-20s", (discount * 100) + "%");
        System.out.print("|" + "\t");
        System.out.printf("%-20s", totalPrice);
        System.out.print("|\n");
    }
}