/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.ui.subcommands.renderers;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.SelectListRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 *
 */
public class SelectionElementsRenderer extends SelectListRenderer {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doubleClicked(ISelection doubleClickedElement, IRendererContext context) {
    super.doubleClicked(doubleClickedElement, context);
  }

  @Override
  protected boolean isMultipleSelection() {
    return true;
  }

  @Override
  protected Object createInput(IProperty property, IRendererContext propertyContext) {
    Object value = propertyContext.getPropertyContext().getCurrentValue(property);
    if ((value != null) && (value instanceof Collection)) {
      Collection<Object> dataa = (Collection) value;
      TreeData data = new TreeData(dataa, null);
      return data;
    }
    return new ListData(Collections.emptyList(), null);
  }

  @Override
  public void initialize(IProperty property, IRendererContext propertyContext) {
    Object value = propertyContext.getPropertyContext().getDefaultValue(property);
    updatedValue(property, propertyContext, value);
    reloadInput(property, propertyContext);
    getViewer().getClientViewer().setSelection(getInitialSelection(propertyContext));
    selectionChange(new StructuredSelection(), propertyContext);
  }

  @Override
  public void selectionChange(IStructuredSelection selection, IRendererContext context) {
    //Nothing here
  }

  @Override
  public void updatedValue(IProperty property, IRendererContext propertyContext, Object newValue) {
    reloadInput(property, propertyContext);
  }
}
