/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.flexibility.wizards.ui.tabbed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider;

import org.polarsys.capella.common.flexibility.wizards.renderer.RendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 */
public class PropertiesTabDescriptorProvider implements ITabDescriptorProvider {

  protected IRendererContext _rendererContext = null;

  protected IPropertyContext _protertyContext = null;

  protected IRenderers _renderers = null;

  protected IProperties getProperties(Collection<Object> selection) {
    return null;
  }

  protected IRenderers createRenderers(IProperties properties) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public IRendererContext getRendererContext() {
    if (_rendererContext == null) {
      _rendererContext = new RendererContext(getRenderers(), getPropertyContext());
    }
    return _rendererContext;
  }

  /**
   * {@inheritDoc}
   */
  public IPropertyContext getPropertyContext() {
    return _protertyContext;
  }

  /**
   * {@inheritDoc}
   */
  public IRenderers getRenderers() {
    if (_renderers == null) {
      _renderers = createRenderers(getPropertyContext().getProperties());
    }
    return _renderers;
  }

  /**
   * @param selection
   */
  protected Collection<Object> getSources(ISelection selection) {
    Collection<Object> result = new ArrayList<Object>();
    if (selection != null) {
      Iterator<Object> itSelection = ((IStructuredSelection) selection).iterator();
      while (itSelection.hasNext()) {
        Object element = itSelection.next();
        Object adapted = adapt(element);
        if (adapted instanceof Collection) {
          result.addAll((Collection) adapted);
        } else {
          result.add(adapted);
        }
      }
    }
    return result;
  }

  /**
   * @param next
   * @return
   */
  protected Object adapt(Object next) {
    return next;
  }

  /**
   * {@inheritDoc}
   */
  public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part, ISelection selection) {
    Collection<ITabDescriptor> desc = new ArrayList<ITabDescriptor>();

    IProperties properties = getProperties(getSources(selection));
    for (final IPropertyGroup mainGroup : properties.getGroups(IPropertyGroup.EMPTY)) {
      if (mainGroup.getParentId() == null) {
        desc.add(createTabDescriptor(getPropertyContext(), getRendererContext(), mainGroup));
      }
    }

    return desc.toArray(new ITabDescriptor[0]);
  }

  /**
   * @param context
   * @param rendererContext
   * @param mainGroup
   * @return
   */
  protected ITabDescriptor createTabDescriptor(IPropertyContext context, IRendererContext rendererContext, IPropertyGroup mainGroup) {
    return new PropertiesTabDescriptor(context, rendererContext, mainGroup);
  }
}
