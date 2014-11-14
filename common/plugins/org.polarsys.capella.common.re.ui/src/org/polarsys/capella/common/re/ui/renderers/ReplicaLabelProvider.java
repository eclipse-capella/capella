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
package org.polarsys.capella.common.re.ui.renderers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.ui.DefaultLabelProvider;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReplicaLabelProvider extends DefaultLabelProvider {

  IPropertyContext _context;

  /**
   * @param labelProvider_p
   */
  public ReplicaLabelProvider(IPropertyContext context_p, ILabelProvider labelProvider_p) {
    super(labelProvider_p);
    _context = context_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage(Object object_p) {

    if (object_p instanceof CatalogElementLink) {
      if (((CatalogElementLink) object_p).getTarget() instanceof CatalogElement) {
        IProperty property = _context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__TARGET);
        Object replica = _context.getCurrentValue(property);
        return super.getImage(replica);
      }
      return getImage(((CatalogElementLink) object_p).getTarget());
    }

    if (object_p instanceof CatalogElement) {
      IProperty property = _context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__TARGET);
      Object replica = _context.getCurrentValue(property);
      if (object_p.equals(replica)) {
        return super.getImage(replica);
      }
    }
    return super.getImage(object_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText(Object object_p) {
    IContext context = (IContext) _context.getSource();

    if (object_p instanceof EObject) {
      return AttributesHandlerHelper.getInstance(context).getName((EObject) object_p, context, _context);
    }
    return super.getText(object_p);
  }
}
