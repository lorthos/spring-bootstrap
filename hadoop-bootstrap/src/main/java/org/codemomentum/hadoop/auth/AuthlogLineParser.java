package org.codemomentum.hadoop.auth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * todo
 *
 * @author codemomentum@gmail.com
 */
public class AuthlogLineParser {

	public static AuthLogRecord parseLine(String line) throws Exception {
		String regex = "^([\\w]{3} [\\d ]\\d \\d{2}:\\d{2}:\\d{2}) \\S+ (\\w+)\\[(\\d+)\\]: (.*)$";
		Matcher matcher = Pattern.compile(regex).matcher(line);
		if (!matcher.matches()) {
			return null;
		}

		String dateStr = matcher.group(1);

		DateFormat formatter = new SimpleDateFormat("MMM dd HH:mm:ss");
		Date date = formatter.parse(dateStr);

		Calendar dateCal = new GregorianCalendar();
		int currentYear = dateCal.get(Calendar.YEAR);
		dateCal.setTime(date);
		dateCal.set(Calendar.YEAR, currentYear);
		date = dateCal.getTime();

		String daemonStr = matcher.group(2);

		String processStr = matcher.group(3);
		Integer processId = Integer.valueOf(processStr);

		String msg = matcher.group(4);
		return new AuthLogRecord(date, daemonStr, processStr, processId, msg);
	}
}
