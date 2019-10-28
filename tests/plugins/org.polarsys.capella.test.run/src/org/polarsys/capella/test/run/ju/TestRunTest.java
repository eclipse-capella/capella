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
package org.polarsys.capella.test.run.ju;

import java.io.StringWriter;

import org.junit.Assert;
import org.polarsys.capella.test.run.TestRunListener;

import junit.framework.TestCase;

public class TestRunTest extends TestCase {
  
  StringWriter w;
  TestRunListener listener;
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    w = new StringWriter();
    listener = new TestRunListener(null, "test", w);
    listener.testTreeEntry("1,one(A),true,2,false,-1,AAA,,");
    listener.testTreeEntry("2,two(A),true,2,false,1,AAA,,");
    listener.testTreeEntry("3,three(AA),false,1,false,-1,AA,,");
    listener.testTreeEntry("3,three(AA),false,1,false,-1,AA,,");
    listener.testTreeEntry("4,four(AA),false,1,false,-1,AA,,");
    listener.testTreeEntry("5,five(AA),false,1,false,-1,AA,,");
    listener.testStarted("3", "three(AA)");
    listener.testStarted("3", "three(AA)");
    listener.testStarted("4", "four(AA)");
    listener.testStarted("5", "five(AA)");
    listener.testRunEnded(0);
  }
  
  public void testDuplicate() {
    String value = w.getBuffer().toString();
    value = value.replaceAll("three", "@");
    Assert.assertTrue("Duplicate id", 2 == value.chars().filter(x->x == '@').count());
  }
  
  public void testRootTest() {
    String value = w.getBuffer().toString();
    value = value.replaceAll("five", "@");
    Assert.assertTrue("Root test", 1 == value.chars().filter(x->x == '@').count());
  }

  public void testTestSuites() {
    String value = w.getBuffer().toString();
    value = value.replaceAll("<testsuite", "@");
    Assert.assertTrue("Test suites", 2 == value.chars().filter(x->x == '@').count());
  }

  public void testTestCases() {
    String value = w.getBuffer().toString();
    value = value.replaceAll("<testcase", "@");
    Assert.assertTrue("Test cases", 4 == value.chars().filter(x->x == '@').count());
  }
}
