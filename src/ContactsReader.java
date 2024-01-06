import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The ContactsReader class provides methods for reading contacts data from a file.
 * It includes functionality to read all contacts from the file and get a specific contact by personal ID.
 * The contacts are assumed to be stored in a CSV format.
 */
public class ContactsReader {
    private static final String FILE_PATH = "contacts.csv";

    /**
     * Reads all contacts from the file and returns a list of ContactsManager objects.
     * 
     * @return A list of ContactsManager objects representing the contacts read from the file.
     */
    public static List<ContactsManager> readContactsFromFile() {
        List<ContactsManager> contacts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line and create ContactsManager objects
                String[] parts = line.split(",");
                String personalId = parts[0];
                String firstName = parts[1];
                String lastName = parts[2];
                String phoneNumber = parts[3];
                String address = parts[4];
                String email = parts[5];

                // Create a new ContactsManager object and add it to the list
                ContactsManager contact = new ContactsManager(personalId, firstName, lastName, phoneNumber, address, email);
                contacts.add(contact);
            }
        } catch (IOException e) {
            // Handle exceptions if there are any issues with reading from the file
            System.err.println("Error reading contacts from file: " + e.getMessage());
        }

        return contacts;  // Don't forget this return statement
    }

    /**
     * Gets a specific contact from the list based on the personal ID.
     * 
     * @param personalId The personal ID of the contact to retrieve.
     * @return The ContactsManager object representing the contact with the specified personal ID,
     *         or null if the contact is not found.
     */
    public static ContactsManager getContactByPersonalId(String personalId) {
        List<ContactsManager> contacts = readContactsFromFile();
        for (ContactsManager contact : contacts) {
            if (contact.getPersonalId().equals(personalId)) {
                return contact;
            }
        }
        return null; // Return null if the contact with the specified personalId is not found
    }
}