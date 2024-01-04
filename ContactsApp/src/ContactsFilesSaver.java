import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The ContactsFilesSaver class provides methods for managing contacts data in a file.
 * It includes functionality to delete a contact from the file and save a list of contacts to the file.
 * The contacts are stored in a CSV format.
 */
public class ContactsFilesSaver {
    private static final String FILE_PATH = "contacts.csv";

    /**
     * Deletes a contact with the specified personal ID from the file.
     * 
     * @param personalIdToDelete The personal ID of the contact to be deleted.
     */
    public static void deleteContactFromFile(String personalIdToDelete) {
        // Read all contacts from the file
        List<ContactsManager> contacts = ContactsReader.readContactsFromFile();

        // Remove the contact with the specified personalId
        contacts.removeIf(contact -> contact.getPersonalId().equals(personalIdToDelete));

        // Save the updated list of contacts back to the file
        saveContactsToFile(contacts);
    }

    /**
     * Saves a list of contacts to the file in CSV format.
     * 
     * @param contacts The list of ContactsManager objects to be saved to the file.
     */
    public static void saveContactsToFile(List<ContactsManager> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Iterate over the list of contacts and write them to the file in CSV format
            for (ContactsManager contact : contacts) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s%n",
                        contact.getPersonalId(), contact.getFirstName(), contact.getLastName(),
                        contact.getPhoneNumber(), contact.getAddress(), contact.getEmail()));
            }
            System.out.println("Contacts saved successfully.");
        } catch (IOException e) {
            // Handle exceptions if there are any issues with writing to the file
            System.err.println("Error saving contacts to file: " + e.getMessage());
        }
    }
}