import java.text.SimpleDateFormat;

class Member extends Guest {
    String memberTier;
    double discount;

    Member(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        memberTier = "Basic";
        setDiscount();
    }

    public void setMemberTier(String memberTier) {
        this.memberTier = memberTier;
        setDiscount();
    }

    public String getMemberTier() {
        return memberTier;
    }

    public void setDiscount() {
        if (memberTier.equals("Basic")) {
            discount = 0.05;
        } else if (memberTier.equals("Standard")) {
            discount = 0.1;
        } else if (memberTier.equals("Premium")) {
            discount = 0.2;
        } else {
            discount = 0;
        }
    }

    public double getDiscout() {
        return discount;
    }
}
