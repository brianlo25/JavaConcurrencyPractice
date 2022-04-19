package javaconcurrency.chapter07.threadLocal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleDateFormatSafetyExample {
    private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<>();

    private static DateFormat getDateFormat() {
        DateFormat df = dateFormatThreadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat(DATEFORMAT);
            dateFormatThreadLocal.set(df);
        }
        return df;
    }

    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 9; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(parse("2022-04-19 17:12:30"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
