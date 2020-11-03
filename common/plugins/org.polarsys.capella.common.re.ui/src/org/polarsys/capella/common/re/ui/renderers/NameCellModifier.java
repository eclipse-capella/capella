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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.TreeItem;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class NameCellModifier implements ICellModifier {
  private Viewer viewer;
  IRendererContext context;

  public NameCellModifier(Viewer viewer, IRendererContext context) {
    this.viewer = viewer;
    this.context = context;
  }

  public boolean canModify(Object element, String property) {
    if (element instanceof CatalogElementLink) {
      EObject target = ((CatalogElementLink) element).getTarget();
      return (target != null) && canModify(target, property);
    }
    if (element instanceof EObject) {
      IPropertyContext pContext = context.getPropertyContext();
      IContext iContext = (IContext) pContext.getSource();
      EStructuralFeature feature = AttributesHandlerHelper.getInstance(iContext).getSuffixableFeature((EObject) element, iContext);
      return feature != null;
    }
    return false;
  }

  public Object getValue(Object element, String property) {
    if ((element instanceof CatalogElementLink) || (element instanceof CatalogElement)) {
      IPropertyContext pContext = context.getPropertyContext();
      IContext iContext = (IContext) pContext.getSource();
      return AttributesHandlerHelper.getInstance(iContext).getCurrentName((EObject) element, iContext, pContext);

    } else if (element instanceof EObject) {
      IPropertyContext pContext = context.getPropertyContext();
      IContext iContext = (IContext) pContext.getSource();
      EStructuralFeature feature = AttributesHandlerHelper.getInstance(iContext).getSuffixableFeature((EObject) element, iContext);
      return ((EObject) element).eGet(feature);
    }
    return element;
  }

  public void modify(Object element, String property, Object value) {

    if (element instanceof TreeItem) {
      Object data = ((TreeItem) element).getData();
      modify(data, property, value);
      return;

    } else if ((element instanceof CatalogElementLink) || (element instanceof CatalogElement)) {
      IPropertyContext pContext = context.getPropertyContext();
      IContext iContext = (IContext) pContext.getSource();

      if ((value == null) || ((value instanceof String) && ((String) value).isEmpty())) {
        AttributesHandlerHelper.getInstance(iContext).unsetCustomName((EObject) element, (String) value, iContext, pContext);
      } else {
        AttributesHandlerHelper.getInstance(iContext).setCustomName((EObject) element, (String) value, iContext);
      }

    } else if (element instanceof EObject) {
      EObject object = (EObject) element;
      EStructuralFeature feature = object.eClass().getEStructuralFeature(property);
      object.eSet(feature, value);
    }

    IProperty suffix = context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX);
    context.getPropertyContext().setCurrentValue(suffix, context.getPropertyContext().getCurrentValue(suffix));
  }
}
