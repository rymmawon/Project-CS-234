import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Services {
    private List<String> freeServices;
    private List<String> paidServices;
    private List<Guest> guests;

    public Services(List<Guest> guests) {
        this.freeServices = new ArrayList<>();
        this.paidServices = new ArrayList<>();
        this.guests = guests;

        // Add default free services
        addDefaultFreeServices();


    }       
    
    private void addDefaultFreeServices() {
        freeServices.add("Pool");
        freeServices.add("Gym");
        freeServices.add("Free Wifi");
        freeServices.add("Free Parking");
        freeServices.add("Room Service");
    }

    

    public void displayAvailableServices() {
        System.out.println("Available Services:");
        for (String service : freeServices) {
            System.out.println(service);
        }
    }

    public void checkMembership(int roomPrice,Scanner scanner,int nights) {
        for (Guest guest : guests) {
            String membership = guest.getMembership();
            if (membership != null) {
                System.out.println("Membership ID found. All services are free with the membership.");
                addAllFees(0, guest,roomPrice,scanner,nights);// 0 indicating no upcharge with membership
            } else {
                addAllFees(25, guest,roomPrice,scanner,nights); // Fee pricing per night without membership
            }
        }
    }
    
    public void addAllFees(float fee, Guest guest,int roomPrice,Scanner scanner,int nights) {
        int nightsStayedFee = nights*roomPrice;
        float serviceFee = fee * nights; // Calculating total fee
        System.out.println("Room Service Fee: $" + serviceFee + "\nStayed Service Fee: $" + nightsStayedFee + "\nroom: $" + roomPrice);
    
        Invoice totalPrice = new Invoice(serviceFee, nightsStayedFee);
    
        // Get the membership status from the guest object
        String membership = guest.getMembership();
    
        if (membership != null) {
            float discountedTotal = totalPrice.calculatedisTotal(); // Use the Invoice method for discounted total
            System.out.println("Grand Total with Discount: $" + discountedTotal);
        } else {
            float grandTotal = totalPrice.calculateTotal(); // Use the Invoice method for the total
            System.out.println("Grand Total: $" + grandTotal);
        }
    }

}
