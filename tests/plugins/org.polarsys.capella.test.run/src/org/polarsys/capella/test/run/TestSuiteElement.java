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

import java.util.ArrayList;

public class TestSuiteElement extends TestElement {
  public int remainingTestCount = 0;

  public TestSuiteElement(String id, String name, int testCount) {
    super(id, name);
    this.remainingTestCount = testCount;
  }

  ArrayList<TestElement> childs = new ArrayList<TestElement>();

  public boolean isFailed() {
    if (!childs.isEmpty()) {
      for (TestElement test : childs) {
        if (test.isFailed()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public String toString() {
    String result = "";
    int failed = 0;
    for (TestElement test : childs) {
      result += test.toString();
      if (test.isFailed()) {
        failed++;
      }
    }
    return "\n<testsuite name=\"" + encode(name) + "\" tests=\"" + childs.size() + "\" failures=\"" + failed + "\">\r\n"
        + result + "\n</testsuite>\r\n";
  }
  
}