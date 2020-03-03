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
import org.polarsys.capella.test.platform.ju.testcases.CapellaCheckAcceleo2NotUsed;
import org.polarsys.capella.test.platform.ju.testcases.CapellaDefaultEditorEnabled;
import org.polarsys.capella.test.platform.ju.testcases.CapellaPlatformVersionNotNull;
import org.polarsys.capella.test.platform.ju.testcases.CapellaSiriusCustomisationEnabled;
import org.polarsys.capella.test.platform.ju.testcases.CapellaVersionConsistencyTest;
import org.polarsys.capella.test.platform.ju.testcases.DataNotifierBeforeTransactionRecorder;
import org.polarsys.capella.test.platform.ju.testcases.DerivedFeaturesImplementation;
import org.polarsys.capella.test.platform.ju.testcases.DiffmergeExternalReferences;
import org.polarsys.capella.test.platform.ju.testcases.ExportCSVPreferencesTest;
import org.polarsys.capella.test.platform.ju.testcases.InvalidPreferencesInitializer;
import org.polarsys.capella.test.platform.ju.testcases.InvalidRepresentationDescriptorAdapterFactory;
import org.polarsys.capella.test.platform.ju.testcases.ProjectSelectionDialogTest;
import org.polarsys.capella.test.platform.ju.testcases.PropertiesWizardIconTest;
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
    tests.add(new CapellaVersionConsistencyTest());
    tests.add(new CapellaSiriusCustomisationEnabled());
    tests.add(new CapellaPlatformVersionNotNull());
    tests.add(new DerivedFeaturesImplementation());
    tests.add(new CapellaDefaultEditorEnabled());
    tests.add(new InvalidRepresentationDescriptorAdapterFactory());
    tests.add(new CapellaCDOGenerationOfDerivedFeature());
    tests.add(new ViatraSurrogateAllDerivedFeaturesImplemented());
    tests.add(new CapellaCheckAcceleo2NotUsed());
    tests.add(new PropertiesWizardIconTest());
    tests.add(new ExportCSVPreferencesTest());
    tests.add(new DiffmergeExternalReferences());
    tests.add(new InvalidPreferencesInitializer());
    tests.add(new ProjectSelectionDialogTest());
    tests.add(new DataNotifierBeforeTransactionRecorder());

    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return null;
  }
}
