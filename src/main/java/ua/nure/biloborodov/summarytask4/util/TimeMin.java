package ua.nure.biloborodov.summarytask4.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

public class TimeMin extends SimpleTagSupport{

    private static final Logger LOG = Logger.getLogger(TimeMin.class);

	private String time;

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public void doTag() throws JspException, IOException {

		LOG.trace("Time -> " + time);
		
		if(time == null || time.isEmpty()) {
		    time = "0";
		}
		
		String timeMin = String.valueOf(Integer.parseInt(time)/60);
		LOG.trace("Time after -> " + timeMin);
		try {
			getJspContext().getOut().write(timeMin);
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}
    
}
