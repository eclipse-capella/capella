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
package org.polarsys.capella.test.odesign.ju.domainclass;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CheckDomainClassTest extends BasicTestCase {

  protected String domainClass;

  private final String domainClassErrorMessage = " is not a domain class!";

  public CheckDomainClassTest(String domainClass) {
    this.domainClass = domainClass;
  }

  @Override
  public void test() throws Exception {

    ResourceSet resourceSet;

    final ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    resourceSet = manager.getEditingDomain().getResourceSet();

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(resourceSet);
    boolean eIsKnownType = accessor.eIsKnownType(domainClass);

    assertTrue(domainClass + domainClassErrorMessage, eIsKnownType);
  }
}
