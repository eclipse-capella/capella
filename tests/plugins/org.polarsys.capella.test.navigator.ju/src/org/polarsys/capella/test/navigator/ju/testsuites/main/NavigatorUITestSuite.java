/*******************************************************************************
 * Copyright (c) 2018, 2022 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.navigator.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.navigator.ju.CancelCapellaWizardTest;
import org.polarsys.capella.test.navigator.ju.DragDropTest;
import org.polarsys.capella.test.navigator.ju.FinishCapellaWizardTest;
import org.polarsys.capella.test.navigator.ju.NavigableElementsTest;
import org.polarsys.capella.test.navigator.ju.NavigatorFilterClasses;
import org.polarsys.capella.test.navigator.ju.NavigatorFilterInvalidRepresentation;
import org.polarsys.capella.test.navigator.ju.NavigatorLabelProviderColors;
import org.polarsys.capella.test.navigator.ju.NavigatorStatusLineRepresentation;
import org.polarsys.capella.test.navigator.ju.PartIcon;
import org.polarsys.capella.test.navigator.ju.ShowInPropertiesDialogTest;
import org.polarsys.capella.test.navigator.ju.dnd.DnDTestSuite;

import junit.framework.Test;

public class NavigatorUITestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new NavigatorUITestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ShowInPropertiesDialogTest());
    tests.add(new CancelCapellaWizardTest());
    tests.add(new FinishCapellaWizardTest());
    tests.add(new NavigatorFilterClasses());
    tests.add(new NavigatorLabelProviderColors());
    tests.add(new NavigatorFilterInvalidRepresentation());
    tests.add(new NavigatorStatusLineRepresentation());
    tests.add(new PartIcon());
    tests.add(new DragDropTest());
    tests.add(new NavigableElementsTest());
    tests.add(new DnDTestSuite());

    return tests;
  }

}
