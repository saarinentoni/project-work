import java.util.List;

/**
 * The ContactUpdater class provides functionality to update contacts in the ContactsApp program.
 * It allows the user to select a contact from the list, update its information, and save the changes.
 */
class ContactUpdater {

    /**
     * Updates a selected contact by obtaining new information from the user.
     * The updated contact is then saved to the list of contacts, and the changes are written to the file.
     */
    public static void updateContact() {
        // Read existing contacts from the file
        List<ContactsManager> contacts = ContactsReader.readContactsFromFile();
        // Display the list of contacts to the user
        ContactUpdater.displayContacts(contacts);

        // Prompt the user to enter the index of the contact to update
        int contactIndex = ContactUpdater.promptUserForContactIndex(contacts.size());
        // Get updated information for the contact from the user
        ContactsManager updatedContact = ContactsManager.getUpdatedContactInfo();

        // Update the contact in the list
        contacts.set(contactIndex, updatedContact);
        // Save the updated list of contacts to the file
        ContactsFilesSaver.saveContactsToFile(contacts);

        System.out.println("Contact updated successfully.");
    }

    /**
     * Displays the list of contacts to the console.
     * 
     * @param contacts The list of contacts to be displayed.
     */
    private static void displayContacts(List<ContactsManager> contacts) {
        System.out.println("Contacts:");
        contacts.forEach(System.out::println);
    }

    /**
     * Prompts the user to enter the index of the contact to be updated.
     * Repeats the prompt until a valid index is provided.
     * 
     * @param maxIndex The maximum valid index for the contact list.
     * @return The index of the contact to be updated.
     */
    private static int promptUserForContactIndex(int maxIndex) {
        int contactIndex;
        do {
            // Prompt the user to enter the index and parse it as an integer
            System.out.print("Enter the index of the contact to update: ");
            contactIndex = Integer.parseInt(System.console().readLine().trim());
        } while (contactIndex < 0 || contactIndex >= maxIndex);

        return contactIndex;
    }
}