package com.ailois.regular;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegularTest {

    private static final Logger logger = LoggerFactory.getLogger(RegularTest.class);

    public static void main(String[] args) {

        String test = "222, 22,22";
        Arrays.stream(test.split(", |,")).collect(Collectors.toList()).forEach(logger::info);

        String test2 = "(\"12(1)2\", \"2323\")";
        List<String> parenthesesRemove = Arrays.stream(test2.replaceFirst("\\(", "")
                        .replace("\"", "").split(","))
                .map(x -> StringUtils.removeEnd(x, ")")).collect(Collectors.toList());
        logger.info("parenthesesRemove: {}", parenthesesRemove);

        String test3 = "a in (\"qqq\", \"qqw\") and b in (\"2332\")";
        for (String x : Arrays.stream(test3.split(" and | or ")).collect(Collectors.toList())) {
            String[] split = x.split(" in ");
            String[] split1 = split[1].replaceAll("\\(|\\)| |\"", "").split(",");//NOSONAR
            List<String> parseFilter = Arrays.stream(split1).collect(Collectors.toList());
            logger.info("parseFilter: {}", parseFilter);
        }

        String s = testReg("2020-02-22 00:00:00");
        logger.info("{}", s);
        String regex1 = "FR|RB|Residual";
        String regex2 = ">=|<=|>|<|=|is|more than|!=";
        String regex3 = "\\d+.\\d+|\\d+";
        testReg2("FR composite > 0.01", regex1);
        testReg2("RB composite >= 0.01", regex2);
        testReg2("RB composite > 0.02", regex3);
        testReg2("col(test:11) is null", "col\\(\\w+\\)|col\\(\\w+\\:\\w+\\)");
    }

    private static void testReg2(String text, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        String test = null;
        if (matcher.find()) {
            test = matcher.group(0);
        }
        logger.info("matcher {}", test);
    }

    public static String testReg(String text) { //NOSONAR
        String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
                + "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+)");
            logger.info("{}", pattern);
        }
        return null;
    }

}
