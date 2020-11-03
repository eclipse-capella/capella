/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.navigator.ju;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This class test the InvalidRepresentations filter
 */
public class NavigatorFilterInvalidRepresentation extends BasicTestCase {

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

    ViewerFilter filter = getInvalidRepresentationFilter();
    for (DRepresentationDescriptor descriptor : descriptors) {
      if (descriptor.getName().contains("Valid")) {
        assertTrue("Descriptor shall be black", filter.select(navigator.getCommonViewer(), null, descriptor));
      } else {
        assertTrue("Descriptor shall be gray", !filter.select(navigator.getCommonViewer(), null, descriptor));
      }
    }

  }

  private ViewerFilter getInvalidRepresentationFilter() {
    for (IConfigurationElement element : Platform.getExtensionRegistry()
        .getConfigurationElementsFor("org.eclipse.ui.navigator.navigatorContent")) {
      if ("commonFilter".equals(element.getName())
          && "capella.project.explorer.filters.invalidRepresentation".equals(element.getAttribute("id"))) {
        try {
          return (ViewerFilter) element.createExecutableExtension("class");
        } catch (CoreException e) {
          // Nothing here
        }
      }
    }
    return null;

  }

}
