/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
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

    Session session = getSession("emptyDiagram");
    Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE
        .getAllRepresentationDescriptors(session);

    // On all invalid representations, it shall display an (Invalid) tag
    for (DRepresentationDescriptor descriptor : descriptors) {
      if (!descriptor.getName().contains("Valid")) {
        String description = RepresentationHelper.getRepresentationFullPathText(descriptor);
        assertTrue("Status line doesn't display Invalid status", description.endsWith("(Invalid)"));
      }
    }
  }

}
