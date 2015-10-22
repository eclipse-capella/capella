/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.api;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 *
 */
public abstract class AbstractDiagramTestCase extends BasicTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getRequiredTestModel());
  }

  protected abstract String getRequiredTestModel();

  protected void mustBeInstanceOf(SessionContext context, String objectId, EClass clazz) {
    assertTrue(clazz.isInstance(context.getSemanticElement(objectId)));
  }

  protected void mustBeLinkedTo(SessionContext context, String objectId, String valueId, EStructuralFeature feature) {
    EObject object = context.getSemanticElement(objectId);
    EObject value = context.getSemanticElement(valueId);

    if (feature.isMany()) {
      assertTrue(((EList) object.eGet(feature)).contains(value));

    } else {
      assertTrue(object.eGet(feature).equals(value));

    }
  }

  protected void mustBeOwnedBy(SessionContext context, String objectId, String containerId) {
    EObject object = context.getSemanticElement(objectId);
    EObject container = context.getSemanticElement(containerId);
    assertTrue(object.eContainer().equals(container));
  }

}
