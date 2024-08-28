package se.ericsson.jcat.litp.utils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

public class XMLParser {

	private File xmlFile = null;

	public XMLParser(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	public XMLParser(String url) {
		xmlFile = new File(url);
	}

	public ArrayList<SSHConnect> getServers() {
		ArrayList<SSHConnect> serverList = new ArrayList<SSHConnect>();
		if (xmlFile == null) {
			return serverList;
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			Element element = document.getDocumentElement();

			NodeList serverNodes = element.getElementsByTagName(TagName.SERVER
					.getTag());

			for (int i = 0; i < serverNodes.getLength(); i++) {
				Element serverElement = (Element) serverNodes.item(i);
				SSHConnect connect = new SSHConnect();
				NodeList childNodes = serverElement.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node childNode = childNodes.item(j);
					if (childNode.getNodeType() == Node.ELEMENT_NODE) {
						String nodeName = childNode.getNodeName();
						if (nodeName.equals(TagName.IP.getTag())) {
							connect.setServer(childNode.getFirstChild()
									.getNodeValue());
						} else if (nodeName.equals(TagName.PASSWORD.getTag())) {
							connect.setPassword(childNode.getFirstChild()
									.getNodeValue());
						} else if (nodeName.equals(TagName.USER.getTag())) {
							connect.setUser(childNode.getFirstChild()
									.getNodeValue());
						}
					}
				}
				serverList.add(connect);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return serverList;

	}
}
