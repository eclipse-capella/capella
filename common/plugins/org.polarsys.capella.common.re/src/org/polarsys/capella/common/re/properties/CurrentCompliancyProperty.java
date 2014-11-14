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
public class CurrentCompliancyProperty extends AbstractProperty implements IEditableProperty, IRestraintProperty, ICompoundProperty, IModifiedProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    IProperty property = context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__TARGET);
    CatalogElement element = (CatalogElement) context_p.getCurrentValue(property);
    if ((element != null) && (element.getCurrentCompliancy() != null)) {
      return element.getCurrentCompliancy();
    }

    property = context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE);
    element = (CatalogElement) context_p.getCurrentValue(property);
    if (element != null) {
      return element.getDefaultReplicaCompliancy();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return null;
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
    return new String[] { IReConstants.PROPERTY__LOCATION_SOURCE, IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE };
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
    IProperty locationProperty = context_p.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_SOURCE);
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
    IProperty locationProperty = context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__TARGET);
    EObject sourceProperty = (EObject) context_p.getCurrentValue(locationProperty);
    if ((sourceProperty != null) && (sourceProperty instanceof CatalogElement)) {
      CompliancyDefinition definition = (CompliancyDefinition) context_p.getCurrentValue(this);
      if (definition != null) {
        CatalogElement source = (CatalogElement) sourceProperty;
        if (source.getDefaultReplicaCompliancy() == null) {
          source.setDefaultReplicaCompliancy(definition);
        }
        source.setCurrentCompliancy(definition);

      }
    }
  }

}
