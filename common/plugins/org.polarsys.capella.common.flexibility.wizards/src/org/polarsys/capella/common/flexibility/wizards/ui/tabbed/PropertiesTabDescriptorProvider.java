/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

  protected IRendererContext rendererContext = null;

  protected IPropertyContext propertyContext = null;

  protected IRenderers renderers = null;

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
    if (rendererContext == null) {
      rendererContext = new RendererContext(getRenderers(), getPropertyContext());
    }
    return rendererContext;
  }

  /**
   * {@inheritDoc}
   */
  public IPropertyContext getPropertyContext() {
    return propertyContext;
  }

  /**
   * {@inheritDoc}
   */
  public IRenderers getRenderers() {
    if (renderers == null) {
      renderers = createRenderers(getPropertyContext().getProperties());
    }
    return renderers;
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
