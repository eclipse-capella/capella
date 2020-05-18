/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.IModel;

public class AccessPolicyProperty extends AbstractProperty implements IEditableProperty, ICompoundProperty {

  protected LibraryManagerModel model = null;

  public AccessPolicyProperty() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    if (model == null) {
      model = (LibraryManagerModel) context_p.getSource();
    }
    return model;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    return Status.OK_STATUS;
  }

  @Override
  public Object getType() {
    return Object.class;
  }

  @Override
  public Object toType(Object value_p, IPropertyContext context_p) {
    return value_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  // Called when the wizard is validated and the value corresponding to this property has been set as modified.
  public void setValue(IPropertyContext context_p) {
    // set the values of the property
    IModel.Edit rootModel = model.getRootModel();
    Collection<IModel> directReferencedLibraries = model.getReferencedLibrariesByRootModel();
    for (IModel library : directReferencedLibraries) {
      AccessPolicy currentAccessPolicy = model.getAccessPolicy(library);
      if (currentAccessPolicy != model.getInitialAccessPolicy(library)) {
        rootModel.setAccess(library, currentAccessPolicy);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { FlexibilityIds.REFERENCE_PROPERTY };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {
    if (property_p.getId().equals(FlexibilityIds.REFERENCE_PROPERTY)) {
    }
  }

}
