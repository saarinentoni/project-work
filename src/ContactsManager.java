import java.io.Console;
import java.util.ArrayList;
import java.util.List;

/**
 * The ContactsManager class represents a contact in the ContactsApp program.
 * It contains information such as personal ID, name, phone number, address, and email.
 * It provides methods for creating and updating contacts.
 */
public class ContactsManager {
    private String personalId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;

    private static Console console = System.console();

    /**
     * Default constructor for ContactsManager.
     * Creates an empty instance of the class.
     */
    public ContactsManager() {
        // Empty constructor for flexibility
    }

    /**
     * Parameterized constructor for ContactsManager.
     * Initializes an instance of the class with the provided information.
     * 
     * @param personalId  The personal ID of the contact.
     * @param firstName   The first name of the contact.
     * @param lastName    The last name of the contact.
     * @param phoneNumber The phone number of the contact.
     * @param address     The address of the contact.
     * @param email       The email of the contact.
     */
    public ContactsManager(String personalId, String firstName, String lastName,
                            String phoneNumber, String address, String email) {
        this.personalId = personalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    /**
     * Creates a list of contacts by prompting the user for information.
     * The user can create multiple contacts in a single session.
     * 
     * @return A list of ContactsManager objects representing the created contacts.
     */
    public static List<ContactsManager> getContacts() {
        List<ContactsManager> contactsList = new ArrayList<>();

        do {
            // Prompt the user to enter contact information
            System.out.print("Type your personal id: ");
            String personalId = console.readLine().trim();
            System.out.print("Type your first name: ");
            String firstName = console.readLine().trim();
            System.out.print("Type your last name: ");
            String lastName = console.readLine().trim();
            System.out.print("Type your phone number: ");
            String phoneNumber = console.readLine().trim();
            System.out.print("Type your address: ");
            String address = console.readLine().trim();
            System.out.print("Type your email: ");
            String email = console.readLine().trim();

            // Create a new ContactsManager instance and add it to the list
            ContactsManager contactsApp = new ContactsManager(personalId, firstName, lastName, phoneNumber, address, email);
            contactsList.add(contactsApp);

            // Ask the user if they want to create another contact
            System.out.print("Do you want to create another contact? (yes/no): ");
            String response = console.readLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        } while (true);

        return contactsList;
    }

    /**
     * Gets the personal ID of the contact.
     * 
     * @return The personal ID of the contact.
     */
    public String getPersonalId() {
        return personalId;
    }

    /**
     * Gets the first name of the contact.
     * 
     * @return The first name of the contact.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the contact.
     * 
     * @return The last name of the contact.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the phone number of the contact.
     * 
     * @return The phone number of the contact.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the address of the contact.
     * 
     * @return The address of the contact.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the email of the contact.
     * 
     * @return The email of the contact.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Prompts the user to update contact information and returns a new ContactsManager instance.
     * 
     * @return A new ContactsManager instance with updated information.
     */
    public static ContactsManager getUpdatedContactInfo() {
        // Prompt the user to update contact information
        System.out.print("Type your personal id: ");
        String personalId = console.readLine().trim();
        System.out.print("Type your first name: ");
        String firstName = console.readLine().trim();
        System.out.print("Type your last name: ");
        String lastName = console.readLine().trim();
        System.out.print("Type your phone number: ");
        String phoneNumber = console.readLine().trim();
        System.out.print("Type your address: ");
        String address = console.readLine().trim();
        System.out.print("Type your email: ");
        String email = console.readLine().trim();

        // Return a new ContactsManager instance with updated information
        return new ContactsManager(personalId, firstName, lastName, phoneNumber, address, email);
    }

    /**
     * Returns a string representation of the contact.
     * 
     * @return A formatted string containing the contact's information.
     */
    @Override
    public String toString() {
        return String.format("Personal ID: %s, Name: %s %s, Phone: %s, Address: %s, Email: %s",
                personalId, firstName, lastName, phoneNumber, address, email);
    }
}