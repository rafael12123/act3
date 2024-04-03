interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

class Magazine implements LibraryItem {
    private String title;
    private String publisher;
    private int issueNumber;
    private boolean borrowed;

    public Magazine(String title, String publisher, int issueNumber) {
        this.title = title;
        this.publisher = publisher;
        this.issueNumber = issueNumber;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "[Magazine] " + title + " published by " + publisher + " (Issue #" + issueNumber + ")";
    }
}

class Laptop implements LibraryItem {
    private String brand;
    private String model;
    private boolean borrowed;

    public Laptop(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "[Laptop] " + brand + " " + model;
    }
}

abstract class LibraryUser {
    protected String name;
    protected String id;

    public LibraryUser(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract void borrowItem(LibraryItem item);

    public abstract void returnItem(LibraryItem item);

    protected void printBorrowedHeader() {
        System.out.println("Items borrowed by " + name + ":");
        System.out.println("-----------------------------");
    }

    protected void printBorrowedFooter() {
        System.out.println("-----------------------------\n");
    }

    public abstract void printItemsBorrowed();

    @Override
    public String toString() {
        return "[LibraryUser] " + name + " (" + id + ")";
    }
}

class Researcher extends LibraryUser {
    private LibraryItem[] borrowedItems;
    private int numBorrowedItems;

    public Researcher(String name, String id) {
        super(name, id);
        borrowedItems = new LibraryItem[5];
        numBorrowedItems = 0;
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (numBorrowedItems < borrowedItems.length) {
            item.borrowItem();
            borrowedItems[numBorrowedItems++] = item;
        } else {
            System.out.println("Cannot borrow more items.");
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        item.returnItem();
        for (int i = 0; i < numBorrowedItems; i++) {
            if (borrowedItems[i] == item) {
                borrowedItems[i] = borrowedItems[numBorrowedItems - 1];
                borrowedItems[numBorrowedItems - 1] = null;
                numBorrowedItems--;
                break;
            }
        }
    }

    @Override
    public void printItemsBorrowed() {
        printBorrowedHeader();
        for (int i = 0; i < numBorrowedItems; i++) {
            System.out.println("- " + borrowedItems[i]);
        }
        printBorrowedFooter();
    }

    @Override
    public String toString() {
        return "[Researcher] " + name + " (" + id + ")";
    }
}

class Librarian extends LibraryUser {
    private LibraryItem[] borrowedItems;
    private int numBorrowedItems;

    public Librarian(String name, String id) {
        super(name, id);
        borrowedItems = new LibraryItem[10];
        numBorrowedItems = 0;
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (numBorrowedItems < borrowedItems.length) {
            item.borrowItem();
            borrowedItems[numBorrowedItems++] = item;
        } else {
            System.out.println("Cannot borrow more items.");
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        item.returnItem();
        for (int i = 0; i < numBorrowedItems; i++) {
            if (borrowedItems[i] == item) {
                borrowedItems[i] = borrowedItems[numBorrowedItems - 1];
                borrowedItems[numBorrowedItems - 1] = null;
                numBorrowedItems--;
                break;
            }
        }
    }

    @Override
    public void printItemsBorrowed() {
        printBorrowedHeader();
        for (int i = 0; i < numBorrowedItems; i++) {
            System.out.println("- " + borrowedItems[i]);
        }
        printBorrowedFooter();
    }

    @Override
    public String toString() {
        return "[Librarian] " + name + " (" + id + ")";
    }
}

public class App {
    public static void main(String[] args) {
        
        Magazine magazine1 = new Magazine("National Geographic", "National Geographic Society", 125);
        Magazine magazine2 = new Magazine("TIME", "TIME USA, LLC", 301);

       
        Laptop laptop1 = new Laptop("Dell", "Latitude E7470");
        Laptop laptop2 = new Laptop("Lenovo", "ThinkPad X1 Carbon");

        
        Researcher researcher1 = new Researcher("Dr. Smith", "RS123");
        Researcher researcher2 = new Researcher("Prof. Johnson", "PJ456");

        Librarian librarian1 = new Librarian("Ms. Parker", "MP789");
        Librarian librarian2 = new Librarian("Mr. Lee", "ML987");

        
        researcher1.borrowItem(magazine1);
        researcher1.borrowItem(laptop1);
        researcher2.borrowItem(magazine2);
        researcher2.borrowItem(laptop2);
        librarian1.borrowItem(laptop2);
        librarian2.borrowItem(magazine2);

        researcher1.returnItem(magazine1);

        
        System.out.println(researcher1);
        researcher1.printItemsBorrowed();
        System.out.println(researcher2);
        researcher2.printItemsBorrowed();
        System.out.println(librarian1);
        librarian1.printItemsBorrowed();
        System.out.println(librarian2);
        librarian2.printItemsBorrowed();
    }
}
