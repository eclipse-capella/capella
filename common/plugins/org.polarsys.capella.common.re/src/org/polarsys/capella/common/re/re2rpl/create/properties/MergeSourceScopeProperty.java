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
package org.polarsys.capella.common.re.re2rpl.create.properties;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class MergeSourceScopeProperty extends AbstractProperty implements IEditableProperty, ICompoundProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();

    Collection<EObject> scopeElements = new HashSet<EObject>();
    CatalogElement source =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_SOURCE));

    scopeElements.addAll(ReplicableElementHandlerHelper.getInstance(ctx).getAllElements(source));

    Collection<EObject> sharedElements = (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__SHARED_ELEMENTS));
    if (sharedElements != null) {
      scopeElements.addAll(sharedElements);
    }

    Collection<EObject> invalidSharedElements =
        (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS));
    if (invalidSharedElements != null) {
      scopeElements.removeAll(invalidSharedElements);
    }

    return scopeElements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return Collection.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object toType(Object value, IPropertyContext context) {
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_SOURCE, IReConstants.PROPERTY__SCOPE, IReConstants.PROPERTY__SHARED_ELEMENTS,
                         IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    //Nothing here
  }
}
