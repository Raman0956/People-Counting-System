package personnel;

public class CardReader {
    public boolean verifyPersonnel(Employee employee) {
        System.out.println("Verifying personnel " + employee.getCardID() + ".........");
        return employee.getCardID().equals("CARD"); // Simulated verification
    }
}
