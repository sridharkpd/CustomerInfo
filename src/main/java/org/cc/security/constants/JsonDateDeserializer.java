package org.cc.security.constants;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date> {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());


	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext paramDeserializationContext) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TemplateConstants.DT_YY_MM_SLASH);
		String value = jp.getText().trim();
		Date date = null;
		if (value!=null) {
			try {
				date = dateFormat.parse(value);
			} catch (ParseException ex) {
				LOGGER.error("Invalid date format: {}", TemplateConstants.DT_YY_MM_SLASH);
			}
		}
		return date;
	}

	
}
