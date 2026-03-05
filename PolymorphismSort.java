public class PolymorphismSort {
    public static void main(String[] args)
    {
        Contact[] friends = new Contact[9];
        friends[0] = new Contact("John", "Smith", "610-555-7384");
        friends[1] = new Contact("Sarah", "Barnes", "215-555-3827");
        friends[2] = new Contact("Aaron", "Riley", "733-555-2969");
        friends[3] = new Contact("Laura", "Ramone", "663-555-3984");
        friends[4] = new Contact("Larry", "Smith", "464-555-3489");
        friends[5] = new Contact("Frank", "Phelps", "322-555-2284");
        friends[6] = new Contact("Mario", "Guzman", "804-555-9066");
        friends[7] = new Contact("Marsha", "Grant", "464-555-3489");
        friends[8] = new Contact("Joey", "Ramone", "464-555-3489");
        //calling the sort on the array, which implements Comparable
        
        Sorting.selectionSort(friends);
        System.out.println("*** Selection Sort ***");
        for (Contact friend : friends)
            System.out.println(friend);
        
        Sorting.insertionSort(friends);
        System.out.println("*** Insertion Sort ***");
        for (Contact friend : friends)
            System.out.println(friend);
        
    }
}

class Sorting{
    public static void selectionSort(Comparable[] list)
    {
        int min;
        Comparable temp;
        for (int index = 0; index < list.length-1; index++)
        {
            min = index;
            for (int scan = index+1; scan < list.length; scan++)
                if (list[scan].compareTo(list[min]) < 0){
                    min = scan;
                }
            // Swap the values
            temp = list[min];
            list[min] = list[index];
            list[index] = temp;
        }
    }
/**
* @param args the command line arguments
*/
    public static void insertionSort(Comparable[] list)
    {
        for (int index = 1; index < list.length; index++)
        {
            Comparable key = list[index];
            int position = index;
            // Shift larger values to the right
            // FIXED: Changed list[position+1] to list[position-1] and position++ to position--
            while (position > 0 && key.compareTo(list[position-1]) < 0)
            {
                list[position] = list[position-1];
                position--;
            }
            list[position] = key;
        }
    }
}

class Contact implements Comparable
{
    private String firstName, lastName, phone;
    //-----------------------------------------------------------------
    // Constructor: Sets up this contact with the specified data.
    //-----------------------------------------------------------------
    public Contact(String first, String last, String telephone)
    {
        firstName = first;
        lastName = last;
        phone = telephone;
    }
    //-----------------------------------------------------------------
    // Returns a description of this contact as a string.
    //-----------------------------------------------------------------
    public String toString()
    {
        return lastName + ", " + firstName + "\t" + phone;
    }
    //-----------------------------------------------------------------
    // Returns a description of this contact as a string.
    //-----------------------------------------------------------------
    public boolean equals(Object other)
    {
        return (lastName.equals(((Contact)other).getLastName()) &&
        firstName.equals(((Contact)other).getFirstName()));
    }
    //-----------------------------------------------------------------
    // Uses phone number first, then last and first names to determine ordering.
    //-----------------------------------------------------------------
    public int compareTo(Object other)
    {
        int result;
        Contact otherContact = (Contact)other;
        String otherFirst = otherContact.getFirstName();
        String otherLast = otherContact.getLastName();
        String otherPhone = otherContact.getPhone();

        // Check phone first
        if (!phone.equals(otherPhone)) {
            result = phone.compareTo(otherPhone);
        }
        // If phone is same, check last name
        else if (!lastName.equals(otherLast)) {
            result = lastName.compareTo(otherLast);
        }
        // If last name is also same, check first name
        else {
            result = firstName.compareTo(otherFirst);
        }
        
        return result;
    }
    //-----------------------------------------------------------------
    // First name accessor.
    //-----------------------------------------------------------------
    public String getFirstName()
    {
        return firstName;
    }
    //-----------------------------------------------------------------
    // Last name accessor.
    //-----------------------------------------------------------------
    public String getLastName()
    {
        return lastName;
    }
    //-----------------------------------------------------------------
    // Phone accessor.
    //-----------------------------------------------------------------
    public String getPhone()
    {
        return phone;
    }
}
