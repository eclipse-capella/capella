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
package org.polarsys.capella.common.flexibility.wizards.renderer;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 * A renderer for an elements list which content according to filter
 */
public class EditListRenderer extends SelectListRenderer {

  @Override
  protected boolean isMultipleSelection() {
    return true;
  }

  @Override
  protected Object createInput(IProperty property_p, IRendererContext propertyContext_p) {
    Object value = propertyContext_p.getPropertyContext().getCurrentValue(property_p);
    if ((value != null) && (value instanceof Collection)) {
      Collection<Object> dataa = (Collection) value;
      TreeData data = new TreeData(dataa, null);
      return data;
    }
    return new ListData(Collections.emptyList(), null);
  }

  @Override
  public void initialize(IProperty property_p, IRendererContext propertyContext_p) {
    Object value = propertyContext_p.getPropertyContext().getDefaultValue(property_p);
    updatedValue(property_p, propertyContext_p, value);
    reloadInput(property_p, propertyContext_p);
    getViewer().getClientViewer().setSelection(getInitialSelection(propertyContext_p));
    selectionChange(new StructuredSelection(), propertyContext_p);
  }

  @Override
  public void selectionChange(IStructuredSelection selection_p, IRendererContext context_p) {
    //Nothing here
  }

  @Override
  public void updatedValue(IProperty property_p, IRendererContext propertyContext_p, Object newValue_p) {
    reloadInput(property_p, propertyContext_p);
  }
}
