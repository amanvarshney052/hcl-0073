package org.example.Assessment_2_corejava;

import java.time.Year;

public abstract class LibraryItems implements Borrowable{
    private static final int MIN_YEAR = 1900;
    private static int idCounter = 1001;

    private int id;
    private String title;
    private int year;
    private boolean available = true;

    protected void LibraryItem(String title, int year) {
        setTitle(title);
        setYear(year);
        this.id = idCounter++;   // only consumed if validation passes
    }

    private void setTitle(String title) {
    }

    protected LibraryItems(int id) {
        this.id = id;
    }

    

    
}
