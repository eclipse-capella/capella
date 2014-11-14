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
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class InvalidSharedElementsProperty extends AbstractProperty implements ICompoundProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {

    IContext context = (IContext) context_p.getSource();
    if (!context.exists(IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS) || (context.get(IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS) == null)) {
      Collection<EObject> scopeElements = (Collection) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__SHARED_ELEMENTS));

      if (scopeElements == null) {
        context.put(IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS, Collections.emptyList());
      } else {

        Collection<EObject> result = new HashSet<EObject>();
        CatalogElementPkg pkg = (CatalogElementPkg) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET));
        if (pkg != null) {

          IModel targetModel = ILibraryManager.INSTANCE.getModel(pkg);
          if (targetModel != null) {
            Collection<IModel> referencedLibraries = LibraryManagerExt.getAllReferences(targetModel);
            if (targetModel != null) {
              for (Object object : scopeElements) {
                if (object instanceof EObject) {
                  IModel sourceModel = ILibraryManager.INSTANCE.getModel((EObject) object);
                  if (!targetModel.equals(sourceModel) && !referencedLibraries.contains(sourceModel)) {
                    result.add((EObject) object);
                  }
                }
              }
            }
          }
        }

        context.put(IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS, new HashSet<Object>(result));
      }
    }

    return context.get(IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {

    if (newValue_p instanceof Collection) {
      if (!((Collection) newValue_p).isEmpty()) {
        return new Status(IStatus.WARNING, getId(),
            "Some referenced elements are not available in the target location.\nReferences to such elements will be lost.");
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
    return new String[] { IReConstants.PROPERTY__SHARED_ELEMENTS, IReConstants.PROPERTY__LOCATION_TARGET };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    context.put(IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS, null);
  }
}
