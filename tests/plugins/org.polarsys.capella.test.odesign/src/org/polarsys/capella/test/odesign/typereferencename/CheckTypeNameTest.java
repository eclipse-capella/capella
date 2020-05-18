/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.odesign.typereferencename;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CheckTypeNameTest extends BasicTestCase {

  protected String typeName;

  private final String typeNameErrorMessage = " is not a type name";

  public CheckTypeNameTest(String atypeName) {
    typeName = atypeName;
  }

  @Override
  public void test() throws Exception {
    // check the reference name is not null
    assertNotNull("type name is null", typeName);

    ResourceSet resourceSet;

    final ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    resourceSet = manager.getEditingDomain().getResourceSet();

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(resourceSet);
    boolean eIsKnownType = accessor.eIsKnownType(typeName);

    assertTrue(typeName + typeNameErrorMessage, eIsKnownType);

  }

}
