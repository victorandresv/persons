package com.victorvargascodetest;

import android.provider.BaseColumns;

public class DbPersonsDefinition {
    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME ="persons";

        public static final String ID = "ID";
        public static final String FIRST_NAME = "FIRST_NAME";
        public static final String LAST_NAME = "LAST_NAME";
        public static final String PHONE_NUMBER = "PHONE_NUMBER";
        public static final String DATE_OF_BIRTH = "DATE_OF_BIRTH";
        public static final String ZIPCODE = "ZIPCODE";
    }
}
