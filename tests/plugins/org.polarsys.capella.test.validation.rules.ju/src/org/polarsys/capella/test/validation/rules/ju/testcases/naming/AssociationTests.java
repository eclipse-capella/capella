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
package org.polarsys.capella.test.validation.rules.ju.testcases.naming;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Name conflict tests for Associations.
 */
public class AssociationTests extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] {
        "org.polarsys.capella.core.data.information.validation.I_02",
        "org.polarsys.capella.core.data.core.validation.I_19"});
  }

  @Override
  public void test() throws Exception {
    Association ass1 = InformationFactory.eINSTANCE.createAssociation("association"); //$NON-NLS-1$
    Association ass2 = InformationFactory.eINSTANCE.createAssociation("association"); //$NON-NLS-1$

    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg("package"); //$NON-NLS-1$
    pkg.getOwnedAssociations().add(ass1);
    pkg.getOwnedAssociations().add(ass2);

    ok(pkg);
  }

}
