/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.ui.views.libraryManager.accessPolicyManager;

import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.core.libraries.flexibilityProperties.FlexibilityIds;
import org.polarsys.capella.core.libraries.flexibilityProperties.LibraryManagerModel;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.AbstractRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

public class AccessPolicyRenderer extends AbstractRenderer {

  protected AccessPolicyManagerWidget widget;

  public AccessPolicyRenderer() {
    super();
  }

  @Override
  public void performRender(Composite parent_p, IRendererContext context_p) {
    widget = new AccessPolicyManagerWidget(parent_p);
  }

  @Override
  public void initialize(IProperty property_p, IRendererContext rendererContext_p) {
    LibraryManagerModel model = (LibraryManagerModel) property_p.getValue(rendererContext_p.getPropertyContext());
    widget.initializeView(model, property_p, rendererContext_p);
  }

  @Override
  public void updatedValue(IProperty property_p, IRendererContext propertyContext_p, Object newValue_p) {
    super.updatedValue(property_p, propertyContext_p, newValue_p);
    if (property_p.getId().equals(FlexibilityIds.ACCESS_POLICY_PROPERTY)) {
      widget.refreshView();
    }
  }

}
