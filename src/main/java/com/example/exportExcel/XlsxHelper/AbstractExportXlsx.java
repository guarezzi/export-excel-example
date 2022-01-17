package com.example.exportExcel.XlsxHelper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.util.Strings;

public abstract class AbstractExportXlsx {

	protected Properties properties;
	protected NumberFormat maskCurrency;
	
	public AbstractExportXlsx(final String propertiesPath) throws IOException {
		ClassLoader classLoader = AbstractExportXlsx.class.getClassLoader();
		maskCurrency = NumberFormat.getCurrencyInstance();
		properties = new Properties();

		String messagesPath = Strings.isBlank(propertiesPath) ? "i18n/messages.properties" : propertiesPath;
		InputStreamReader reader = new InputStreamReader(classLoader.getResourceAsStream(messagesPath), "UTF-8");
		properties.load(reader);
	}

	public abstract List<String> generateXlsxTitles();
	public abstract List<String> generateXlsxValues();

}