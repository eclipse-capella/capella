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
package org.polarsys.capella.test.libraries.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.libraries.ju.testcases.basic.CapellaModel_unicity;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_addReference;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_addReference_cacheFocus;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_addReference_cycleCase;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_addReference_metadata;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_canReference;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_getAllReferencedLibraries;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_getAndSetAccessPolicy_diamondCase;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_getAndSetAccessPolicy_levelPriorityFocus;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_getReferencedLibraries;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_isActiveLibrary;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_isLibrary;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_removeReferenceToLibrary;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_removeReferenceToLibrary_cacheFocus;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_removeReferenceToLibrary_diamondCase;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_removeReferenceToLibrary_metadata;
import org.polarsys.capella.test.libraries.ju.testcases.basic.LibraryManager_setLibraryActiveState;

/**
 * @author Hakim Sellou
 */
public class LibrariesBasicTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new LibrariesBasicTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CapellaModel_unicity());
    tests.add(new LibraryManager_addReference_cacheFocus());
    tests.add(new LibraryManager_addReference_cycleCase());
    tests.add(new LibraryManager_addReference());
    tests.add(new LibraryManager_addReference_metadata());
    tests.add(new LibraryManager_canReference());
    tests.add(new LibraryManager_getAllReferencedLibraries());
    tests.add(new LibraryManager_getAndSetAccessPolicy_diamondCase());
    tests.add(new LibraryManager_getAndSetAccessPolicy_levelPriorityFocus());
    tests.add(new LibraryManager_getReferencedLibraries());
    tests.add(new LibraryManager_isActiveLibrary());
    tests.add(new LibraryManager_isLibrary());
    tests.add(new LibraryManager_removeReferenceToLibrary_cacheFocus());
    tests.add(new LibraryManager_removeReferenceToLibrary_diamondCase());
    tests.add(new LibraryManager_removeReferenceToLibrary());
    tests.add(new LibraryManager_removeReferenceToLibrary_metadata());
    tests.add(new LibraryManager_setLibraryActiveState());
    return tests;
  }

}
