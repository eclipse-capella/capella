/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
   * @param labelProvider
   */
  public ReplicaLabelProvider(IPropertyContext context, ILabelProvider labelProvider) {
    super(labelProvider);
    _context = context;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage(Object object) {

    if (object instanceof CatalogElementLink) {
      if (((CatalogElementLink) object).getTarget() instanceof CatalogElement) {
        IProperty property = _context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
        Object replica = _context.getCurrentValue(property);
        return super.getImage(replica);
      }
      return getImage(((CatalogElementLink) object).getTarget());
    }

    if (object instanceof CatalogElement) {
      IProperty property = _context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
      Object replica = _context.getCurrentValue(property);
      if (object.equals(replica)) {
        return super.getImage(replica);
      }
    }
    return super.getImage(object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText(Object object) {
    IContext context = (IContext) _context.getSource();

    if (object instanceof EObject) {
      return AttributesHandlerHelper.getInstance(context).getCurrentName((EObject) object, context, _context);
    }
    return super.getText(object);
  }
}
