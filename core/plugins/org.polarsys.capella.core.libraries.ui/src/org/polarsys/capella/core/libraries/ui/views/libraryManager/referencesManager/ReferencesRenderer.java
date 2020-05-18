/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.referencesManager;

import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.core.libraries.properties.LibraryManagerModel;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.AbstractRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

public class ReferencesRenderer extends AbstractRenderer {

  protected ReferencesManagerWidget widget;

  public ReferencesRenderer() {
    super();
  }

  @Override
  public void performRender(Composite parent_p, IRendererContext context_p) {
    widget = new ReferencesManagerWidget(parent_p);
  }

  @Override
  public void initialize(IProperty property_p, IRendererContext rendererContext_p) {
    LibraryManagerModel model = (LibraryManagerModel) property_p.getValue(rendererContext_p.getPropertyContext());
    widget.initializeView(model, property_p, rendererContext_p);
  }

}
