/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.handlers.location;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Supplier;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.data.capellacore.NamedElement;

class SpecificPackageLocationAdapter extends AdapterImpl {

  /**
   * The factory that actually creates the packages for this adapter.
   */
  private final SpecificPackageSupplierFactory factory;

  /**
   * Stores all created packages so we can delete empty ones on disposal
   * and to keep the package names in sync with the rpl name.
   */
  private final Collection<EObject> created = new HashSet<EObject>();

  SpecificPackageLocationAdapter(IPropertyContext context, Resource destinationResource) {

    // The name isn't set on the RPL CatalogElement EObject until the wizard
    // finishes so we must listen to property changes too
    context.registerListener(new PropertyChangeListener() {
      @Override
      public void update(PropertyChangedEvent event) {
        if (IReConstants.PROPERTY__TARGET_NAME.equals(event.getProperty().getId())) {
          for (EObject pkg : created) {
            if (pkg instanceof NamedElement) {
              ((NamedElement) pkg).setName((String)context.getCurrentValue(event.getProperty()));
            }
          }
        }
      }
    });

    factory = new SpecificPackageSupplierFactory(destinationResource);

  }



  @Override
  public void notifyChanged(Notification msg) {
    if (msg.getFeature() == RePackage.Literals.RE_NAMED_ELEMENT__NAME) {
      for (EObject pkg : created) {
        if (pkg instanceof NamedElement) {
          ((NamedElement) pkg).setName((String) msg.getNewValue());
        }
      }
    }
  }

  /**
   * Get the specific package in which the packagedElement should be stored
   * @param packagedElement
   */
  public Supplier<EObject> getSpecificPackage(EObject packagedElement) {
    Supplier<EObject> result = null;
    final Supplier<EObject> pkgSupplier = factory.getSpecificPackageSupplier(packagedElement);
    if (pkgSupplier != null) {
      result = new Supplier<EObject>() {
        @Override
        public EObject get() {
          EObject pkg = pkgSupplier.get();
          created.add(pkg);
          if (pkg instanceof NamedElement) {
            ((NamedElement) pkg).setName(((CatalogElement)getTarget()).getName());
          }
          return pkg;
        }
      };
    }
    return result;
  }

  /**
   * Disposes this adapter and deletes all empty created packages. Created packages
   * may be empty if the user picked a custom location for an element afterwards.
   */
  public void dispose() {
    getTarget().eAdapters().remove(this);
    for (Iterator<EObject> it = created.iterator(); it.hasNext();) {
      EObject next = it.next();
      if (next.eContents().isEmpty()) {
        EObject container = next.eContainer();
        if (container != null) {
          ((Collection<?>) container.eGet(next.eContainingFeature())).remove(next);
        }
      }
    }
  }

  @Override
  public boolean isAdapterForType(Object type) {
    return type == SpecificPackageLocationAdapter.class;
  }

}