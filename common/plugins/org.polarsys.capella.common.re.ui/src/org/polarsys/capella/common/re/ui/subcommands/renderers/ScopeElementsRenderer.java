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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.SelectListRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;

/**
 *
 */
public class ScopeElementsRenderer extends SelectListRenderer {

  @Override
  protected int getExpandLevel() {
    return AbstractTreeViewer.ALL_LEVELS;
  }

  @Override
  protected void doubleClicked(ISelection doubleClickedElement, IRendererContext context) {
    // Nothing here

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContentProvider createContentProvider(IRendererContext context) {
    return new DataContentProvider();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object createInput(IProperty property, IRendererContext context) {
    Object value = context.getPropertyContext().getCurrentValue(property);

    if ((value != null) && (value instanceof Collection)) {
      Collection<EObject> scopeElements = (Collection) value;
      TreeData data = new TreeData(scopeElements, null);
      return data;
    }
    return new ListData(Collections.emptyList(), context.getPropertyContext());
  }

  @Override
  protected ILabelProvider getLabelProvider(IRendererContext context) {
    return context.getLabelProvider();
  }

}
