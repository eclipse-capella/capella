/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.helpers;

import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.provider.ModelElementItemProvider;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ChildrenFeaturesItemProviderTest extends BasicTestCase {

  @Override
  public void test() throws Exception {
    ModelElementItemProvider provider = new ModelElementItemProvider(
        CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
    LogicalFunction function = LaFactory.eINSTANCE.createLogicalFunction();
    assertTrue(
        "ownedMigratedElements shall be removed from ModelElementItemProvider.getChildrenFeatures to prevent drag-and-drop into this feature",
        !provider.getChildrenFeatures(function)
            .contains(ModellingcorePackage.Literals.MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS));
  }

}
