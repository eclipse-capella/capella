/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.capella;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * This class removes all references towards aird from semantic model
 */
public class InvalidAirdReferenceMigrationContribution extends AbstractMigrationContribution {

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    if (currentElement instanceof Element) {
      for (EReference eReference : currentElement.eClass().getEAllReferences()) {
        if (!eReference.isDerived() && eReference.isChangeable()) {
          Object value = currentElement.eGet(eReference);
          
          if (value instanceof InternalEObject) {
            InternalEObject internalEObject = (InternalEObject) value;
            if (internalEObject.eIsProxy() && CapellaResourceHelper.isAirdResource(internalEObject.eProxyURI())) {
              currentElement.eSet(eReference, null);
              
            } else {
              Resource resource = internalEObject.eResource();
              if (resource != null && CapellaResourceHelper.isAirdResource(resource.getURI())) {
                currentElement.eSet(eReference, null);
              }
            }
            
          } else if (value instanceof List<?>) {
            Iterator<?> val = ((List<?>) value).iterator();
            while (val.hasNext()) {
              Object object = val.next();
              if (object instanceof InternalEObject) {
                InternalEObject internalEObject = (InternalEObject) object;

                if (internalEObject.eIsProxy() && CapellaResourceHelper.isAirdResource(internalEObject.eProxyURI())) {
                  val.remove();
                  
                } else {
                  Resource resource = internalEObject.eResource();
                  if (resource != null && CapellaResourceHelper.isAirdResource(resource.getURI())) {
                    val.remove();
                  }
                }
              }
            }
          }
        }
      }
    }
  }

}
