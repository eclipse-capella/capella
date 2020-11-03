/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.libraries.ju.testcases.basic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.SessionHelper;

/**
 * @author Erwan Brottier
 */
public class LibraryManager_setLibraryActiveState extends BasicTestCase {

  @SuppressWarnings("nls")
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("MyProject1", "MyLibrary1", "MyLibrary2", "MyLibrary3");
  }

  @SuppressWarnings("nls")
  @Override
  public void test() {
    // -- SCENARIO -- //
    CapellaModel monProjet1 = getTestModel("MyProject1");
    CapellaModel maLibrairie1 = (CapellaModel) getTestModel("MyLibrary1");
    CapellaModel maLibrairie2 = (CapellaModel) getTestModel("MyLibrary2");
    CapellaModel maLibrairie3 = (CapellaModel) getTestModel("MyLibrary3");
    monProjet1.addReference(maLibrairie1);
    SessionHelper.saveSession(monProjet1);
    maLibrairie1.addReference(maLibrairie2);
    SessionHelper.saveSession(maLibrairie1);
    maLibrairie2.addReference(maLibrairie3);
    SessionHelper.saveSession(maLibrairie2);
    
    monProjet1.setActive(maLibrairie1, true);
    monProjet1.setActive(maLibrairie2, false);
    monProjet1.setActive(maLibrairie3, true);
    SessionHelper.saveSession(monProjet1);
    maLibrairie1.setActive(maLibrairie2, true);
    maLibrairie1.setActive(maLibrairie3, false);
    SessionHelper.saveSession(maLibrairie1);
    // -- ORACLE -- //
    Collection<IModel> libs = LibraryManagerExt.getAllActivesReferences(monProjet1);
    assertTrue(libs.size() == 2);
    assertTrue(libs.contains(maLibrairie1));
    assertFalse(libs.contains(maLibrairie2));
    assertTrue(libs.contains(maLibrairie3));
    libs = LibraryManagerExt.getAllActivesReferences(maLibrairie1);
    assertTrue(libs.size() == 1);
    assertTrue(libs.contains(maLibrairie2));
    assertFalse(libs.contains(maLibrairie3));
  }
}
