package com.webtools.converters;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StringToDateConverter implements Converter<String,Date> {

	@Override
	public Date convert(String s) {
		
		Date date=new Date();
		
		try {
			date=new SimpleDateFormat("yyyyMMDD").parse(s);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}

}
