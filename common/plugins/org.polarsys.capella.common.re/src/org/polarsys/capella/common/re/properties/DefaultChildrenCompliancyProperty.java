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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultChildrenCompliancyProperty extends AbstractProperty implements IEditableProperty, IRestraintProperty, ICompoundProperty, IModifiedProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    IProperty property = context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
    CatalogElement element = (CatalogElement) context_p.getCurrentValue(property);
    if ((element != null) && (element.getDefaultReplicaCompliancy() != null)) {
      return element.getDefaultReplicaCompliancy();
    }

    Collection<Object> objects = getChoiceValues(context_p);
    if (!objects.isEmpty()) {
      return objects.iterator().next();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    if ((newValue_p == null) || !(newValue_p instanceof CompliancyDefinition)) {
      return new Status(IStatus.WARNING, getGroupId(), "A default compliancy should be set");
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return CompliancyDefinition.class;
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
  public boolean isModified(IPropertyContext context_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__LOCATION_TARGET, IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Object> getChoiceValues(IPropertyContext context_p) {
    IProperty locationProperty = context_p.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET);
    EObject sourceProperty = (EObject) context_p.getCurrentValue(locationProperty);

    IContext context = (IContext) context_p.getSource();
    Collection<CompliancyDefinition> compliancies = ReplicableElementHandlerHelper.getInstance(context).getAllDefinedCompliancy(sourceProperty);
    if (compliancies.isEmpty()) {
      ReplicableElementHandlerHelper.getInstance(context).createDefaultCompliancies(sourceProperty);
      compliancies = ReplicableElementHandlerHelper.getInstance(context).getAllDefinedCompliancy(sourceProperty);
    }

    return (Collection) compliancies;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMany() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    IProperty locationProperty = context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
    EObject sourceProperty = (EObject) context_p.getCurrentValue(locationProperty);
    if ((sourceProperty != null) && (sourceProperty instanceof CatalogElement)) {
      CompliancyDefinition definition = (CompliancyDefinition) context_p.getCurrentValue(this);
      if (definition != null) {
        ((CatalogElement) sourceProperty).setDefaultReplicaCompliancy(definition);
      }
    }
  }

}
