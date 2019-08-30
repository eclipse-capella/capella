/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.kitalpha.emde.model.Element;

public class InvalidRepresentationDescriptorAdapterFactory extends BasicTestCase {

  @Override
  public void test() throws Exception {

    //Create a descriptor
    SystemAnalysis analysis = CtxFactory.eINSTANCE.createSystemAnalysis();
    DRepresentationDescriptor descriptor = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
    descriptor.setTarget(analysis);
    
    //Find an adapter. If descriptor can be adapted to its target, there is an issue.
    EObject obj = Adapters.adapt(descriptor, Element.class, true);
    if (obj instanceof Element) {
      assertFalse(
          "An adapter from 'DRepresentationDescriptor' towards 'Element' exist. "
          + "It breaks menus like 'Show in Semantic Browser, Show in Diagram' as they will now also appears on descriptors. "
          + "Please ensure that adapter is really wanted and fix menus in that case.",
          true);
    }
  }

}
