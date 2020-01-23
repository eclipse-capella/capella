/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.richtext.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.richtext.ju.testcases.RichTextLinksClipboardTest;
import org.polarsys.capella.test.richtext.ju.testcases.RichtextOpenLinkTest;

import junit.framework.Test;

/**
 * Test Suite for Richtext component (integrated in Capella). 
 */
public class RichtextTestSuite extends BasicTestSuite {

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new RichtextOpenLinkTest());
    tests.add(new RichTextLinksClipboardTest());
    // FIXME This test cannot be launched on Linux environment due to the XULRunner bug
    // tests.add(new FinishOnPropertyWizard());
    return tests;
  }

  /**
   * Added in order to launch this test suite without the Capella test framework.
   * @return
   */
  public static Test suite() {
    return new RichtextTestSuite();
  }

}
