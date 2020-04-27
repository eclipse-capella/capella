/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
public class LibraryManager_removeReferenceToLibrary_diamondCase extends BasicTestCase {

  @SuppressWarnings("nls")
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("MyProject1", "MyLibrary1", "MyLibrary2");
  }

  @SuppressWarnings("nls")
  @Override
  public void test() {
    // -- SCENARIO -- //
    CapellaModel monProjet1 = getTestModel("MyProject1");
    CapellaModel maLibrairie1 = (CapellaModel) getTestModel("MyLibrary1");
    CapellaModel maLibrairie2 = (CapellaModel) getTestModel("MyLibrary2");
    monProjet1.addReference(maLibrairie1);
    SessionHelper.saveSession(monProjet1);
    maLibrairie1.addReference(maLibrairie2);
    SessionHelper.saveSession(maLibrairie1);
    monProjet1.addReference(maLibrairie2);
    SessionHelper.saveSession(monProjet1);
    // -- ORACLE -- //
    Collection<IModel> libs = LibraryManagerExt.getAllReferences(monProjet1);
    assertTrue(libs.size() == 2);
    assertTrue(libs.contains(maLibrairie1));
    assertTrue(libs.contains(maLibrairie2));
    monProjet1.removeReference(maLibrairie2);
    SessionHelper.saveSession(monProjet1);
    libs = LibraryManagerExt.getAllReferences(monProjet1);
    assertTrue(libs.size() == 2);
    assertTrue(libs.contains(maLibrairie1));
    assertTrue(libs.contains(maLibrairie2));
  }
}
