/*
 *  COPYRIGHT
 *  ---------
 *  Copyright (C) 2010 by
 *  Ericsson AB
 *  SWEDEN
 *
 *  All rights reserved.
 *
 *
 *  REVISION HISTORY
 *  ----------------
 *
 *  Revised:  Geoffrey Zhang 2010-05-20
 *
 */
package se.ericsson.jcat.litp.fw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import se.ericsson.jcat.fw.ExtendedFetchLogs;
import se.ericsson.jcat.fw.NonUnitTestCase;
import se.ericsson.jcat.fw.SUT;
import se.ericsson.jcat.fw.utils.Cat2Utils;

public class LitpSutFetchLogs implements ExtendedFetchLogs {

	private static Logger logger;
	private static final String NAME = "Test Logs";
	private String path = null;

	/**
	 * constructor
	 */
	public LitpSutFetchLogs() {
		LitpSutFetchLogs.logger = Logger.getLogger(LitpSutFetchLogs.class);
	}

	/**
	 * This method will return dummy log and compress t file into a zip file.
	 * 
	 * @param sut
	 *            on which the log should be fetched from
	 * @param testCase
	 *            the test case which will use this interface,it will be used as
	 *            the suffix of the zip file
	 * @param logfilePath
	 *            the file path under which the zip file will be saved
	 */
	public void fetchLogs(SUT[] sut, NonUnitTestCase testCase,
			String logfilePath) {
		List<String> logFiles = new ArrayList<String>();
		String result[];
		try {
			File zipFile = File.createTempFile(testCase.getName(), ".zip",
					new File(logfilePath));
			path = zipFile.getName();
			for (SUT s : sut) {
				result = new String[] { "log", "fetched", "." };
				if (result != null) {
					String fileName = saveToTempFile(result, s + "fetchlog",
							"temp");
					logFiles.add(fileName);
				}
			}

			AddFilesToZip(logFiles.toArray(new String[logFiles.size()]),
					zipFile.getAbsolutePath());
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Return a unique name for this implementation of interface
	 * {@link ExtendedFetchLogs}. This name will be shown in column
	 * "Fetched Logs" in the result web page and user can open the fetched logs
	 * by clicking the name.
	 */
	public String getName() {
		return NAME;
	}

	/**
	 * Return the path of the fetched log. Click the name shown in column
	 * "Fetched Logs" in the result web page, it will go to this path and then
	 * open the fetched log.
	 */

	public String getPath() {
		return path;
	}

	private boolean AddFilesToZip(String[] fileNames, String toZipFile)
			throws IOException {
		if (fileNames == null)
			return true;

		byte[] buf = new byte[1024];
		int len;
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				toZipFile));
		try {
			for (String fileName : fileNames) {

				InputStream in = new FileInputStream(fileName);
				try {
					File file = new File(fileName);
					out.putNextEntry(new ZipEntry(file.getName()));
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
				} finally {
					in.close();
				}
				out.closeEntry();
			}
		} finally {
			out.close();
		}
		return true;
	}

	private String saveToTempFile(String[] content, String fileName, String dir) {
		File file = null;
		try {
			String logdirPath = se.ericsson.jcat.fw.utils.TestInfo.getLogDir()
					+ Cat2Utils.fs + dir;
			File logdir = new File(logdirPath);
			if (!logdir.exists()) {
				logdir.mkdir();
			}
			file = File.createTempFile(fileName, ".log", logdir);
			System.out
					.println("Generating log file: " + file.getAbsolutePath());
			logger.debug("Generating log file: " + file.getAbsolutePath());
			PrintWriter writer = new PrintWriter(file);
			writer.write(content.toString());
			writer.close();
		} catch (IOException e) {
			logger.info(e.getMessage());
			return null;
		}
		return file.getAbsolutePath();
	}

}
