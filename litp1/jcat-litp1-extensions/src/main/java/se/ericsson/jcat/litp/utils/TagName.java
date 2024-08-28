package se.ericsson.jcat.litp.utils;
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

public enum TagName {
	SERVER("server"),
	IP("ip"),
	USER("user"),
	PASSWORD("password"),
	TYPE("type");
	
	private String tag;
	
	private TagName(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return tag;
	}
}
