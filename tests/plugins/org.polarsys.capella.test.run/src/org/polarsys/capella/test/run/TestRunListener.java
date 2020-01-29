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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.jdt.internal.junit.model.ITestRunListener2;

public class TestRunListener implements ITestRunListener2 {

  ConsoleTestRunnerApplication application = null;

  String testSuite;
  String fileName;
  BufferedWriter writer;

  HashMap<String, TestSuiteElement> testSuites = new HashMap<String, TestSuiteElement>();
  LinkedList<TestElement> remainingTests = new LinkedList<>();
  LinkedList<TestElement> runnedTests = new LinkedList<>();

  public TestRunListener(ConsoleTestRunnerApplication consoleTestRunnerApplication, String title, Writer bwriter) {
    this(consoleTestRunnerApplication, title);
    writer = new BufferedWriter(bwriter);
  }

  public TestRunListener(ConsoleTestRunnerApplication consoleTestRunnerApplication, String title) {
    application = consoleTestRunnerApplication;
    testSuite = title;
    fileName = testSuite + ".xml";

    try {
      String current = new java.io.File(".").getCanonicalPath();
      System.out.println("Current dir:" + current);
      writer = new BufferedWriter(new FileWriter(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void write(String content) {
    if (writer != null) {
      try {
        writer.write(content);
      } catch (IOException e) {
        e.printStackTrace();
        System.err.println("write:" + e.getMessage());
      }
    }
  }

  @Override
  public void testRunStarted(int testCount) {
    System.err.println("testRunStarted:" + testCount);
    System.out.println("testRunStarted:" + testCount);
    try {

      writer.write("<testsuites>\n");
      writer.write("<testsuite name=\"" + TestElement.encode(testSuite) + "\">");

    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("testRunStarted:" + e.getMessage());
    }
  }

  @Override
  public void testRunEnded(long elapsedTime) {
    System.err.println("testRunEnded:" + elapsedTime);
    System.out.println("testRunEnded:" + elapsedTime);

    for (TestElement test : testSuites.values()) {
      if (test.isRoot) {
        write(test.toString());
      }
    }
    for (TestElement test : runnedTests) {
      if (test.isRoot) {
        write(test.toString());
      }
    }

    if (writer != null) {
      try {
        writer.write("</testsuite>\r\n");
        writer.write("</testsuites>");
        writer.close();
      } catch (IOException e) {
        System.err.println("testRunEnded:" + e.getMessage());
        e.printStackTrace();
      }
    }

    if (application != null) {
      synchronized (application) {
        application.notifyAll();
      }
    }
  }

  @Override
  public void testRunStopped(long elapsedTime) {
    System.out.println("testRunStopped:" + elapsedTime);
  }

  TestElement current = null;

  @Override
  public void testStarted(String testId, String testName) {
    System.out.println("testStarted:" + testId + " " + testName);
    current = remainingTests.removeFirst();
    runnedTests.add(current);
    if (!testId.equals(current.id)) {
      System.out.println("testWrongSId:" + current.id + " " + testId + " " + testName);
    }
    current.timeStart = System.currentTimeMillis();
  }

  @Override
  public void testEnded(String testId, String testName) {
    System.out.println("testEnded:" + testId + " " + testName);
    if (!testId.equals(current.id)) {
      System.out.println("testWrongEId:" + current.id + " " + testId + " " + testName);
    }
    current.timeEnd = System.currentTimeMillis();
  }

  @Override
  public void testRunTerminated() {
    System.err.println("testRunTerminated:");
    System.out.println("testRunTerminated:");
  }

  TestSuiteElement parent = null;

  @Override
  public void testTreeEntry(String description) {
    // format:"testId","testName","isSuite","testcount","isDynamicTest","parentId","displayName","parameterTypes","uniqueId"
    System.out.println("testTreeEntry:" + description);
    String testId = description.split(",")[0];
    String testName = description.split(",")[1];
    int testCount = Integer.parseInt(description.split(",")[3]);
    boolean isSuite = "true".equals(description.split(",")[2]);

    TestElement element = null;
    if (isSuite) {
      if (testSuites.containsKey(testId)) {
        System.out.println("Duplicate id:" + testId + " " + description);
      }
      element = new TestSuiteElement(testId, testName, testCount);
      testSuites.put(testId, (TestSuiteElement) element);
      String parentId = description.split(",")[5];
      parent = ((TestSuiteElement) testSuites.get(parentId));
    } else {
      element = new TestElement(testId, testName);
      remainingTests.addLast(element);
    }

    if (parent != null && parent.remainingTestCount == 0) {
      parent = parent.parent;
    }
    if (parent != null && parent.remainingTestCount > 0) {
      element.setParent(parent);
    }
    if (isSuite) {
      parent = ((TestSuiteElement) element);
    }
  }

  @Override
  public void testFailed(int status, String testId, String testName, String trace, String expected, String actual) {
    System.out.println("testFailed:" + current.id + " " + testName);
    current.stack = trace;
    current.failed = true;
    current.failureMessage = expected + " " + actual;
    if (expected == null && actual == null && trace != null) {
      current.failureMessage = trace.split("\n")[0];
    }
    String status2 = status == ITestRunListener2.STATUS_OK ? "OK"
        : (status == ITestRunListener2.STATUS_ERROR ? "ERROR" : "WARNING");
    current.status = status2;
  }

  @Override
  public void testReran(String testId, String testClass, String testName, int status, String trace, String expected,
      String actual) {
    System.out.println("testReran:" + testId + " " + testName);
  }

}
