package com.cookie.demo;

/*
 *  Created by Girish Kumar CH on 6/18/21
 *  This is the entry point for the application it will take named parameters as the input
 *  will process cookie information
 */

import com.cookie.demo.constants.ApplicationConstants;
import com.cookie.demo.dto.Cookie;
import com.cookie.demo.parsers.Parser;
import com.cookie.demo.parsers.ParserFactory;
import com.cookie.demo.service.CookieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CommandLine.Command(mixinStandardHelpOptions = true, version = "Cookie Application V1")
public class CookieApplication implements Runnable {

    private static final Logger LOG = LogManager.getLogger(CookieApplication.class);

    @CommandLine.Option(names = {"-f", "--filePath"}, required = true, description = "Path of the input file")
    private String filePath;

    @CommandLine.Option(names = {"-d", "--date"}, required = true, description = "Input date in yyyy-mm-dd format")
    private String inputDate;


    public static void main(String[] args) throws IOException {

        LOG.info("Application Started ");

        int exitCode = new CommandLine(new CookieApplication()).execute(args);

        LOG.info("Application execution completed with exit code {} ", exitCode);
        System.exit(exitCode);

    }


    @Override
    public void run() {

        try {
            Parser csvParser = ParserFactory.getInstance().getParser(ApplicationConstants.FORMAT_CSV);

            LOG.debug("File path received filePath={}", filePath);
            LOG.debug("Input Date received inputDate={}", inputDate);

            Map<String, Cookie> cookieMap = csvParser.getParsedCookies(filePath);

            CookieService cookieService = new CookieService();
            List<String> activeCookies = cookieService.getActiveCookiesByDate(cookieMap, inputDate);

            //printing final output in new lines in console
            LOG.info("PRINTING FINAL OUTPUT");
            LOG.info("**********************START*********************************");
            activeCookies.forEach(c->System.out.println(ApplicationConstants.GREEN_BOLD_BRIGHT+c+ApplicationConstants.ANSI_RESET));
            LOG.info("**********************END*********************************");

        } catch (IOException e) {
            LOG.error("Exception occured details {}", e);
        }

    }
}
