/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.navigator.ju;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This class test the Status Line on invalid representations
 */
public class NavigatorStatusLineRepresentation extends BasicTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("emptyDiagram");
  }

  @Override
  public void test() throws Exception {

    CapellaCommonNavigator navigator = (CapellaCommonNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
        .getActivePage().findView(CapellaCommonNavigator.ID);

    Session session = getSession("emptyDiagram");
    Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);

    //On all invalid representations, it shall display an (Invalid) tag
    IDescriptionProvider provider = navigator.getNavigatorContentService().createCommonDescriptionProvider();
    for (DRepresentationDescriptor descriptor : descriptors) {
      if (!descriptor.getName().contains("Valid")) {
        String description = provider.getDescription(descriptor);
        assertTrue("Status line doesn't display Invalid status", description.endsWith("(Invalid)"));
      }
    }

  }

}
