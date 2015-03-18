/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.testcases.libraries;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * @author Erwan Brottier
 */
public class LibraryManager_removeReferenceToLibrary_diamondCase extends BasicTestCase {

  @SuppressWarnings("nls")
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("libraries/MyProject1", "libraries/MyLibrary1", "libraries/MyLibrary2");
  }

  @SuppressWarnings("nls")
  @Override
  public void test() {
    // -- SCENARIO -- //
    CapellaModel monProjet1 = getTestModel("libraries/MyProject1");
    CapellaModel maLibrairie1 = (CapellaModel) getTestModel("libraries/MyLibrary1");
    CapellaModel maLibrairie2 = (CapellaModel) getTestModel("libraries/MyLibrary2");
    monProjet1.addReference(maLibrairie1);
    maLibrairie1.addReference(maLibrairie2);
    monProjet1.addReference(maLibrairie2);
    // -- ORACLE -- //
    Collection<IModel> libs = LibraryManagerExt.getAllReferences(monProjet1);
    assertTrue(libs.size() == 2);
    assertTrue(libs.contains(maLibrairie1));
    assertTrue(libs.contains(maLibrairie2));
    monProjet1.removeReference(maLibrairie2);
    libs = LibraryManagerExt.getAllReferences(monProjet1);
    assertTrue(libs.size() == 2);
    assertTrue(libs.contains(maLibrairie1));
    assertTrue(libs.contains(maLibrairie2));
  }
}
