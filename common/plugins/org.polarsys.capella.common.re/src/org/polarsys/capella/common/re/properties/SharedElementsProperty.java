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
package org.polarsys.capella.common.re.properties;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.scope.DependenciesHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SharedElementsProperty extends AbstractProperty implements ICompoundProperty, IRestraintProperty, IEditableProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {

    IContext context = (IContext) context_p.getSource();
    if (!context.exists(IReConstants.PROPERTY__SHARED_ELEMENTS) || (context.get(IReConstants.PROPERTY__SHARED_ELEMENTS) == null)) {
      Collection<EObject> scopeElements = (Collection) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

      if (scopeElements == null) {
        context.put(IReConstants.PROPERTY__SHARED_ELEMENTS, Collections.emptyList());
      } else {
        Object values = DependenciesHandlerHelper.getInstance(context).getSharedElements(scopeElements, scopeElements, context);
        context.put(IReConstants.PROPERTY__SHARED_ELEMENTS, new HashSet<Object>((Collection) values));
      }
    }

    return context.get(IReConstants.PROPERTY__SHARED_ELEMENTS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Object> getChoiceValues(IPropertyContext context_p) {

    IContext context = (IContext) context_p.getSource();
    Collection<EObject> scopeElements = (Collection) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

    if (scopeElements == null) {
      return Collections.emptyList();
    }
    Object values = DependenciesHandlerHelper.getInstance(context).getSharedElements(scopeElements, scopeElements, context);
    return new HashSet<Object>((Collection) values);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {

    if (newValue_p instanceof Collection) {
      if (!((Collection) newValue_p).isEmpty()) {
        return new Status(IStatus.INFO, getId(), "REC have references to external elements");
      }
    }
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
  public Object toType(Object value_p, IPropertyContext context_p) {
    return value_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__SCOPE, IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE, IReConstants.PROPERTY__LOCATION_TARGET };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    context.put(IReConstants.PROPERTY__SHARED_ELEMENTS, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMany() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    //Nothing here
  }
}
