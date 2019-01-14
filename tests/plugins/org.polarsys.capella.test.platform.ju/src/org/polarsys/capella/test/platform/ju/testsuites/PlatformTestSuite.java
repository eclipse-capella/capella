/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.platform.ju.testcases.CapellaCDOGenerationOfDerivedFeature;
import org.polarsys.capella.test.platform.ju.testcases.CapellaDefaultEditorEnabled;
import org.polarsys.capella.test.platform.ju.testcases.CapellaPlatformVersionNotNull;
import org.polarsys.capella.test.platform.ju.testcases.CapellaSiriusCustomisationEnabled;
import org.polarsys.capella.test.platform.ju.testcases.InvalidRepresentationDescriptorAdapterFactory;
import org.polarsys.capella.test.platform.ju.testcases.ViatraSurrogateAllDerivedFeaturesImplemented;

import junit.framework.Test;

/**
 *
 */
public class PlatformTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new PlatformTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CapellaSiriusCustomisationEnabled());
    tests.add(new CapellaPlatformVersionNotNull());
    tests.add(new CapellaDefaultEditorEnabled());
    tests.add(new InvalidRepresentationDescriptorAdapterFactory());
    tests.add(new CapellaCDOGenerationOfDerivedFeature());
    tests.add(new ViatraSurrogateAllDerivedFeaturesImplemented());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return null;
  }
}
