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
package org.polarsys.capella.common.re.ui.subcommands.renderers;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;

import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.SelectListRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 *
 */
public class AllElementsRenderer extends SelectListRenderer {

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContentProvider createContentProvider(IRendererContext context_p) {
    return new AdapterFactoryContentProvider(MDEAdapterFactory.getAdapterFactory());
  }

  @Override
  protected ILabelProvider createLabelProvider(IRendererContext context_p) {
    return new MDEAdapterFactoryLabelProvider(MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object createInput(IProperty property_p, IRendererContext context_p) {
    return context_p.getPropertyContext().getCurrentValue(property_p);
  }

}
