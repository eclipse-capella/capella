/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.run;

public class TestElement {

  private static final double ONE_SECOND = 1000.0;
  
  public boolean isRoot = true;
  public String id;
  public String name;
  public boolean failed = false;
  public String failureMessage = "";
  public String stack = "";
  public TestSuiteElement parent;
  public String status = "OK";
  public long timeStart;
  public long timeEnd;

  public TestElement(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public boolean isFailed() {
    return failed;
  }

  public double getEllapsedTime() {
    return (timeEnd - timeStart) / ONE_SECOND;
  }

  @Override
  public String toString() {
    String result = "";
    String[] splitedNames = name.split("[\\(\\)]");
    if (splitedNames.length > 1) {
      String classname = splitedNames[1];
      String shortname = splitedNames[0];
      result += "<testcase status=\"" + status + "\" name=\"" + encode(shortname) + "\" classname=\"" + encode(classname)
      + "\" time=\"" + getEllapsedTime() + "\">";
      if (isFailed()) {
        result += "<failure message=\"" + encode(failureMessage) + "\">";
        result += "<![CDATA[";
        result += stack;
        result += "]]>";
        result += "</failure>";
      }
      result += "</testcase>\r\n";
    }
    return result;
  }

  public static String encode(String value) {
    return value.replaceAll("\"", "'");
  }

  public void setParent(TestSuiteElement newParent) {
    parent = newParent;
    parent.childs.add(this);
    parent.remainingTestCount--;
    isRoot = false;
  }
  
}