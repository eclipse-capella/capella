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
package org.polarsys.capella.core.libraries.flexibilityProperties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.libraries.Activator;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;

public class ReferencesProperty extends AbstractProperty implements IEditableProperty {

  LibraryManagerModel model = null;

  public ReferencesProperty() {
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
    Collection<Collection<IAbstractModel>> cycles = model.getCycles();
    if (cycles.size() > 0) {
      Collection<IAbstractModel> cycle = cycles.iterator().next();
      StringBuffer b = new StringBuffer();
      for (IAbstractModel pathElement : cycle) {
        b.append(pathElement.getName() + " ");
      }
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, cycles.size() + " cycles found. First one is [" + b + "]");
    }
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

  @Override
  // Called when the wizard is validated and the value corresponding to this property has been set as modified.
  public void setValue(IPropertyContext context_p) {
    // set the values of the property
    IAbstractModel rootModel = model.getRootModel();
    Collection<IAbstractLibrary> newReferencedLibraries = model.getReferencedLibrariesByRootModel();
    Collection<IAbstractLibrary> initialReferencedLibraries = model.getInitialReferencedLibrariesByRootModel();
    for (IAbstractLibrary library : newReferencedLibraries) {
      if (!initialReferencedLibraries.contains(library)) {
        ILibraryManager.INSTANCE.addReferenceToLibrary(rootModel, library);
      }
    }
    for (IAbstractLibrary library : initialReferencedLibraries) {
      if (!newReferencedLibraries.contains(library)) {
        ILibraryManager.INSTANCE.removeReferenceToLibrary(rootModel, library);
      }
    }
  }

}
