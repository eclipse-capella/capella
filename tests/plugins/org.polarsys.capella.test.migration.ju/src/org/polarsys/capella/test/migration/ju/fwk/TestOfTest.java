/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.migration.ju.fwk;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Ensure that MigrationContributionTest is working
 */
public class TestOfTest extends MigrationContributionTest {

  static final String OWNER = "a";
  static final String ONE = "b";
  static final String TWO = "c";

  static final String EOBJECT_PROCEEDED = "A";
  
  @Override
  public void test() throws Exception {
    IFile file = getFile("test.model");
    Factory f = new Factory(file);

    f.init((Resource res) -> {
      EObject owner = f.create("Owner", OWNER);
      res.getContents().add(owner);
    });

    f.test((Resource res) -> {
      EObject owner = res.getEObject(OWNER);
      assertTrue(owner != null);
    });
    
  }

}
