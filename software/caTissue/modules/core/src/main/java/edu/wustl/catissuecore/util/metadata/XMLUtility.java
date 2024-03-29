
package edu.wustl.catissuecore.util.metadata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import edu.common.dynamicextensions.exception.DynamicExtensionsSystemException;
import edu.common.dynamicextensions.util.FileReader;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.logger.Logger;
import edu.wustl.common.util.logger.LoggerConfig;

public class XMLUtility
{

	static
	{
		LoggerConfig.configureLogger(System.getProperty("user.dir"));
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getCommonLogger(XMLUtility.class);

	/**
	 * This method parses the given XML and returns the Java POJO corresponding to the XML provided.
	 *
	 * @param xmlFileName the xml file name
	 * @param fileDirectory the file directory
	 * @param packageName the package name
	 * @param xsdFileName the xsd file name
	 *
	 * @return the Java object
	 *
	 * @throws DynamicExtensionsSystemException the dynamic extensions system exception.
	 * @throws SAXException while parsing wrong XML.
	 */
	public static Object getJavaObjectForXML(String xmlFileName, String fileDirectory,
			String packageName, String xsdFileName) throws DynamicExtensionsSystemException,
			SAXException
	{
		try
		{
			// Creates URL of the XSD specified.
			URL xsdFileUrl = Thread.currentThread().getContextClassLoader()
					.getResource(xsdFileName);

			// Instantiate the Converter class which calls the Object factory generated by JAXB.
			XMLToObjectConverter converter = new XMLToObjectConverter(packageName, xsdFileUrl);

			// Read the XML file and create fileReader object
			final FileReader fileReader = new FileReader(xmlFileName, fileDirectory);

			// Parse the XML and create Permissible Values POJO
			return converter.getJavaObject(new FileInputStream(fileReader.getFilePath()));
		}
		catch (JAXBException e)
		{
			LOGGER.error(ApplicationProperties.getValue("jaxb.error"), e);
			throw new DynamicExtensionsSystemException(
					ApplicationProperties.getValue("jaxb.error"), e);
		}
		catch (FileNotFoundException e)
		{
			LOGGER.error(ApplicationProperties.getValue("jaxb.filenotfound.error"), e);
			throw new DynamicExtensionsSystemException(ApplicationProperties
					.getValue("jaxb.filenotfound.error"), e);
		}
		// This exception is throw when in a given XML string is given instead of integer
		catch (NumberFormatException e)
		{
			LOGGER.error(ApplicationProperties.getValue("jaxb.parse.error"), e);
			throw new DynamicExtensionsSystemException(ApplicationProperties
					.getValue("jaxb.parse.error"), e);
		}
	}

	/**
	 * This method parses the given XML and returns the Java POJO corresponding to the XML provided.
	 *
	 * @param xmlFileName the xml file name
	 * @param inputStream inputStream
	 * @param packageName the package name
	 * @param xsdFileName the xsd file name
	 *
	 * @return the Java object
	 *
	 * @throws DynamicExtensionsSystemException the dynamic extensions system exception.
	 * @throws SAXException while parsing wrong XML.
	 */
	public static Object getJavaObjectForXML(String xmlFileName,InputStream inputStream,
			String packageName, String xsdFileName) throws DynamicExtensionsSystemException,
			SAXException
	{
		try
		{
			// Creates URL of the XSD specified.
			URL xsdFileUrl = Thread.currentThread().getContextClassLoader()
					.getResource(xsdFileName);

			// Instantiate the Converter class which calls the Object factory generated by JAXB.
			XMLToObjectConverter converter = new XMLToObjectConverter(packageName, xsdFileUrl);

			// Parse the XML and create Permissible Values POJO
			return converter.getJavaObject(inputStream);
		}
		catch (JAXBException e)
		{
			LOGGER.error(ApplicationProperties.getValue("jaxb.error"), e);
			throw new DynamicExtensionsSystemException(
					ApplicationProperties.getValue("jaxb.error"), e);
		}
		// This exception is throw when in a given XML string is given instead of integer
		catch (NumberFormatException e)
		{
			LOGGER.error(ApplicationProperties.getValue("jaxb.parse.error"), e);
			throw new DynamicExtensionsSystemException(ApplicationProperties
					.getValue("jaxb.parse.error"), e);
		}
	}
}
