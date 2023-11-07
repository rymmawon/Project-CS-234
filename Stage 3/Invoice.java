import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Invoice {
    private float fee;
    private int nightsStayedfee;
    private int discount = 25;

    public Invoice(float fee, int nightsStayedfee) {
        this.fee = fee;
        this.nightsStayedfee = nightsStayedfee;
    }

    public float calculateTotal() {
        float total = (fee + nightsStayedfee);
        //*simple if method to make the sure method does  not return anything smaller then 0*/
        if(total > 0){
            return total;}
            else{
            return 0;}
    }

    public float calculatedisTotal() {
        float total = (fee + nightsStayedfee) - discount;
        //*simple if method to make the sure method does  not return anything smaller then 0*/
        if(total > 0){
        return total;}
        else{
        return 0;}
    }
}