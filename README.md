## Cookie Challange

Cookie Challenge is Stand alone Java application which takes cookies information file and target date as a input and returns Most Active cookies on that Day

### Requirements 

Maven   
Java 8 or above

### Building Application

Using maven we can build this application 

Command to build the application 

mvn clean install

Build will create CookieChallenge jar in target folder 


### How to execute Application 
Application Jar supports following Options 

Usage: <main class> [-hV] -d=<inputDate> -f=<filePath>   
  -d, --date=<inputDate>   Input date in yyyy-mm-dd format   
  -f, --filePath=<filePath>    
                           Path of the input file   
  -h, --help               Show this help message and exit.   
  -V, --version            Print version information and exit    
  
  Example Execution :  
  
 java -jar target/CookieChallenge-1.0-jar-with-dependencies.jar --filePath=<PATH_TO_COOKIE_LOG>/cookie_log.csv --date=2018-12-09

