import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The ContactsApp class represents a simple contacts management application.
 * It allows users to perform operations like creating, reading, updating, and deleting contacts.
 * Contacts information is stored in a CSV file.
 * 
 * This class serves as the entry point for the ContactsApp program.
 * Users can interact with the program through a console-based menu system.
 * 
 * @author Toni Saarinen
 */
public class ContactsApp {

    /** The file path to the CSV file where contacts are stored. */
    private static final String FILE_PATH = "contacts.csv";

    /**
     * The main method of the ContactsApp program.
     * It displays a menu to the user and handles their choices.
     * The program continues running until the user chooses to exit.
     * 
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        while (true) {
            // Display the main menu and get the user's choice
            printMenu();
            int choice = getChoice();

            // Handle user's choice with a switch statement
            switch (choice) {
                case 1:
                    // User chose to create a new contact
                    createContact();
                    break;
                case 2:
                    // User chose to read and display contacts
                    readContacts();
                    break;
                case 3:
                    // User chose to update an existing contact
                    ContactUpdater.updateContact();
                    break;
                case 4:
                    // User chose to delete an existing contact
                    deleteContact();
                    break;
                case 5:
                    // User chose to exit the program
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    // Handle invalid user input
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    /**
     * Prints the main menu of the ContactsApp program.
     */
    private static void printMenu() {
        System.out.println("1. Create Contact");
        System.out.println("2. Read Contacts");
        System.out.println("3. Update Contact");
        System.out.println("4. Delete Contact");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Gets the user's choice from the console input.
     * 
     * @return The user's choice as an integer.
     */
    private static int getChoice() {
        try {
            // Parse the user's input as an integer
            return Integer.parseInt(System.console().readLine().trim());
        } catch (NumberFormatException e) {
            // Handle invalid input, treat as if the user entered 0
            return 0;
        }
    }

    /**
     * Creates a new contact and adds it to the list of contacts.
     * The new contact information is obtained through user input.
     */
    private static void createContact() {
        // Read existing contacts from the file
        List<ContactsManager> contacts = ContactsReader.readContactsFromFile();
        // Get information for the new contact from the user
        ContactsManager newContact = ContactsManager.getUpdatedContactInfo();
        // Add the new contact to the list
        contacts.add(newContact);
        // Save the updated list of contacts to the file
        ContactsFilesSaver.saveContactsToFile(contacts);
    }

    /**
     * Reads and displays the list of contacts.
     * If no contacts are found, a message is printed indicating so.
     */
    private static void readContacts() {
        // Read existing contacts from the file
        List<ContactsManager> contacts = ContactsReader.readContactsFromFile();
        if (contacts.isEmpty()) {
            // If no contacts are found, display a message
            System.out.println("No contacts found.");
        } else {
            // Display the list of contacts
            System.out.println("Contacts:");
            contacts.forEach(System.out::println);
        }
    }

    /**
     * Deletes a contact based on the user's input.
     * The user is prompted for the Personal ID of the contact to be deleted.
     * If the contact is found, the user is asked for confirmation before deletion.
     * 
     * If the contact is deleted successfully, a success message is displayed.
     * If the contact is not found, a message is displayed indicating so.
     */
    private static void deleteContact() {
        // Prompt the user for the Personal ID to delete
        System.out.print("Enter the Personal ID to delete: ");
        String personalIdToDelete = System.console().readLine().trim();
        // Get the contact to delete based on the Personal ID
        ContactsManager contactToDelete = ContactsReader.getContactByPersonalId(personalIdToDelete);

        if (contactToDelete != null) {
            // Display the contact to be deleted and ask for confirmation
            System.out.println("Contact to delete:");
            System.out.println(contactToDelete);
            System.out.print("Are you sure you want to delete this contact? (yes/no): ");
            String confirmation = System.console().readLine().trim().toLowerCase();

            if (confirmation.equals("yes")) {
                // If the user confirms, delete the contact and display a success message
                ContactsFilesSaver.deleteContactFromFile(personalIdToDelete);
                System.out.println("Contact deleted successfully.");
            } else {
                // If the user cancels deletion, display a message
                System.out.println("Deletion canceled.");
            }
        } else {
            // If the contact is not found, display a message
            System.out.println("Contact not found.");
        }
    }
}