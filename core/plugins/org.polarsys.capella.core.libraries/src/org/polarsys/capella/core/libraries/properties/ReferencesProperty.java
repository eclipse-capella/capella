/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.Activator;
import org.polarsys.capella.core.libraries.Messages;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

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
    if (!model.getUnsavedModels().isEmpty()) {
      String unsavedTxt = model.getUnsavedModels().stream().map(m -> m.getIdentifier().getName())
          .collect(Collectors.joining(", "));
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, NLS.bind(Messages.otherSessionDirtyMsg, unsavedTxt));
    }

    boolean unsavedModel = model.isUnsavedRootModel();
    if (unsavedModel) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.dirtySessionMsg);
    }

    if (!model.getUnresolvableReferencedLibraries().isEmpty()) {
      String unresolvableLibs = model.getUnresolvableReferencedLibraries().stream()
          .map(m -> m.getIdentifier().getName()).collect(Collectors.joining(", "));
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          NLS.bind(Messages.unresolvedLibrariesMsg, unresolvableLibs));
    }

    if (!model.getCycles().isEmpty()) {
      Collection<IModel> cycle = model.getCycles().iterator().next();
      String cycleLibTxt = cycle.stream().map(m -> m.getIdentifier().getName()).collect(Collectors.joining(" "));
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          NLS.bind(Messages.cyclesMsg, model.getCycles().size(), cycleLibTxt));
    }

    ResourceSet rSet = model.domain.getResourceSet();
    boolean resourceSetDirty = false;
    for (Resource res : rSet.getResources()) {
      if (res.isModified() && CapellaResourceHelper.isCapellaResource(res)) {
        resourceSetDirty = true;
        break;
      }
    }
    if (resourceSetDirty) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.resourceSetDirtyMsg);
    }

    Collection<IModel> removedLibraries = new ArrayList<IModel>(model.getInitialAllReferencedLibrariesByRootModel());
    removedLibraries.removeAll(model.getAllReferencedLibrariesByRootModel());

    if (!removedLibraries.isEmpty()) {
      return new Status(IStatus.WARNING, Activator.PLUGIN_ID, Messages.warningMsg);
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
    IModel.Edit rootModel = model.getRootModel();
    Collection<IModel> newReferencedLibraries = model.getReferencedLibrariesByRootModel();
    Collection<IModel> initialReferencedLibraries = model.getInitialReferencedLibrariesByRootModel();
    for (IModel library : newReferencedLibraries) {
      if (!initialReferencedLibraries.contains(library)) {
        rootModel.addReference(library);
      }
    }
    for (IModel library : initialReferencedLibraries) {
      if (!newReferencedLibraries.contains(library)) {
        rootModel.removeReference(library);
      }
    }
  }

}
