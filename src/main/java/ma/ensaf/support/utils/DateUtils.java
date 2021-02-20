package ma.ensaf.support.utils;

import java.util.Date;

public class DateUtils {
	
	public static Date add(Date d, Long seconds) {
		return new Date(d.getTime() + seconds * 1000);
	}

}
