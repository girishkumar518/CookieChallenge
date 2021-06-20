package com.cookie.demo.constants;
/*
 *  Created by Girish Kumar CH on 6/17/21
 */

import org.joda.time.DateTimeZone;

public class ApplicationConstants {

        public static final String FILE_DATE_FORMAT  =  "yyyy-mm-dd'T'HH:mm:ssZ";
        public static final String CSV_REGEX   =  "^.*\\.(csv|CSV)$";

        public static final String FORMAT_CSV =  "CSV";

        public static final String INPUT_DATE_FORMAT = "yyyy-mm-dd";
        public static final DateTimeZone TIME_ZONE = DateTimeZone.UTC;

        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";

}