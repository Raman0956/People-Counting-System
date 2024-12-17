package personnel;

public class Employee {
    private String employeeID;
   
    private String cardID;

    public Employee(String employeeID, String cardID) {
        this.employeeID = employeeID;
 
        this.cardID = cardID;
    }

    public String getCardID() {
        return cardID;
    }
}
