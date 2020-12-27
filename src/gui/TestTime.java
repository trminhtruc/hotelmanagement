package gui;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class TestTime {

	static final int MINUTES_PER_HOUR = 60;
	static final int SECONDS_PER_MINUTE = 60;
	static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

	public static void main(String[] args) {
//        LocalDateTime toDateTime = LocalDateTime.of(2014, 9, 9, 19, 46, 45);
//        LocalDateTime fromDateTime = LocalDateTime.of(1984, 12, 16, 7, 45, 55);
		LocalDateTime toDateTime = LocalDateTime.of(2019, 12, 5, 19, 46, 45);
		LocalDateTime fromDateTime = LocalDateTime.of(2019, 12, 2, 20, 52, 38);
		// 2019-12-02T22:52:38.903
		Period period = getPeriod(fromDateTime, toDateTime);
		long time[] = getTime(fromDateTime, toDateTime);
		System.out.println(getPeriod(fromDateTime, toDateTime).getDays());
		System.out.println(ChronoUnit.DAYS.between(fromDateTime.toLocalDate(), toDateTime.toLocalDate()));
		System.out.println(period.getYears() + " years " + period.getMonths() + " months " + period.getDays() + " days "
				+ time[0] + " hours " + time[1] + " minutes " + time[2] + " seconds.");
		BigDecimal decimal = new BigDecimal("2123450067898876");
		System.out.println(decimal);
		double o = 1223432424;
		System.out.println(new BigDecimal(o));
		te();
	}

	private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
		return Period.between(dob.toLocalDate(), now.toLocalDate());
	}

	private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
		LocalDateTime today = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), dob.getHour(),
				dob.getMinute(), dob.getSecond());
		Duration duration = Duration.between(today, now);

		long seconds = duration.getSeconds();

		long hours = seconds / SECONDS_PER_HOUR;
		long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
		long secs = (seconds % SECONDS_PER_MINUTE);

		return new long[] { hours, minutes, secs };
	}
	private static void te() {
		int overflowExample = 2147483647;
		 System.out.println("Overflow: "+ (overflowExample + 1));

		 //roll over effect to upper limit in underflow
		 int underflowExample = -2147483648;
		 System.out.println("Underflow: "+ (underflowExample - 1));

		 byte b = 127;
		 // following line uncommented results in compilation error
		 // constants are checked at compile time for size
		 // b = b*b;

		 double d = 1e308;
		 System.out.println(d + "*10= " + d*10);
		 //gradual underflow
		 d = 1e-305 * Math.PI;
		 System.out.print("gradual underflow: " + d + "\n      ");
		 for (int i = 0; i < 4; i++)
		 System.out.print(" " + (d /= 100000));
	}
}