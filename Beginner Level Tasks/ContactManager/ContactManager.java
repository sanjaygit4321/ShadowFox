import java.util.*;
import java.util.regex.Pattern;

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Name  : " + name + "\n" +
               "   Phone : " + phone + "\n" +
               "   Email : " + email;
    }
}

public class ContactManager {
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n---- Contact Management System ----\n");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit\n");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1 -> addContact();
                case 2 -> viewContacts();
                case 3 -> searchContact();
                case 4 -> updateContact();
                case 5 -> deleteContact();
                case 6 -> {
                    System.out.println("System Closed!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty() || name.matches("\\d+")) {
            System.out.println("Invalid name! Name cannot be empty or numbers only.");
            return;
        }

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        if (!phone.matches("\\d{10}")) {
            System.out.println("Invalid phone number!");
            return;
        }
        if (isDuplicatePhone(phone)) {
            System.out.println("Contact with this phone already exists!");
            return;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("Invalid email format!");
            return;
        }

        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        int count = 1;
        System.out.println("      "+"Contacts");
        for (Contact c : contacts) {
            System.out.println("-----------------------");
            System.out.println(count++ + ". " + c);
        }
        System.out.println("-----------------------");
    }

    private static void searchContact() {
        System.out.print("Enter name to search: ");
        String keyword = scanner.nextLine().trim();
        boolean found = false;
        int count = 1;

        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("-----------------------");
                System.out.println(count++ + ". " + c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching contacts found.");
        } else {
            System.out.println("-----------------------");
        }
    }

    private static void updateContact() {
        System.out.print("Enter name of contact to update: ");
        String name = scanner.nextLine().trim();

        ArrayList<Contact> matchedContacts = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                matchedContacts.add(c);
            }
        }

        if (matchedContacts.isEmpty()) {
            System.out.println("No matching contacts found.");
            return;
        }

        System.out.println("Matching contacts:");
        int index = 1;
        for (Contact c : matchedContacts) {
            System.out.println("-----------------------");
            System.out.println(index++ + ". " + c);
        }
        System.out.println("-----------------------");

        int choice = 1;
        if (matchedContacts.size() > 1) {
            System.out.print("Enter the record no of the contact to update: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice < 1 || choice > matchedContacts.size()) {
                System.out.println("Invalid selection!");
                return;
            }
        }

        Contact contactToUpdate = matchedContacts.get(choice - 1);

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty() || newName.matches("\\d+")) {
            System.out.println("Invalid name! Name cannot be empty or numbers only.");
            return;
        }

        System.out.print("Enter new phone number (10 digits): ");
        String newPhone = scanner.nextLine().trim();
        if (!newPhone.matches("\\d{10}")) {
            System.out.println("Invalid phone number!");
            return;
        }
        if (!newPhone.equals(contactToUpdate.getPhone()) && isDuplicatePhone(newPhone)) {
            System.out.println("Phone number already exists for another contact!");
            return;
        }

        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine().trim();
        if (!EMAIL_PATTERN.matcher(newEmail).matches()) {
            System.out.println("Invalid email!");
            return;
        }

        contactToUpdate.setName(newName);
        contactToUpdate.setPhone(newPhone);
        contactToUpdate.setEmail(newEmail);

        System.out.println("Contact updated successfully!");
    }

    private static void deleteContact() {
        System.out.print("Enter phone number to delete: ");
        String phone = scanner.nextLine();

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhone().equals(phone)) {
                contacts.remove(i);
                System.out.println("Contact deleted successfully!");
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    private static boolean isDuplicatePhone(String phone) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }
}
