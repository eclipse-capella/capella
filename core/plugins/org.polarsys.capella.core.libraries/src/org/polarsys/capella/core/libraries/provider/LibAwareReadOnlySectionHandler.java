/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.model.handler.provider.IReadOnlyListener;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;

/**
 * This implementation of the {@link IReadOnlySectionHandler} is aware about access policy of elements inside libraries.
 * It's contributed to disable sections for elements of with read-only access policy.
 *
 */
public class LibAwareReadOnlySectionHandler implements IReadOnlySectionHandler {

    @Override
    public void register(EObject semanticElement, IReadOnlyListener listener) {
        // Do nothing
    }

    @Override
    public void unregister(EObject semanticElement, IReadOnlyListener listener) {
        // Do nothing
    }

    @Override
    public boolean isLockedByOthers(EObject semanticElement) {
      if(semanticElement != null){
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(semanticElement);
        if(domain != null) {
          return isLockedByOthers(ILibraryManager.INSTANCE.getModel(domain), ILibraryManager.INSTANCE.getModel(semanticElement));
        }        
      }
        return false;
    }

    private boolean isLockedByOthers(IModel domainModel, IModel elementModel) {
        return domainModel != null && elementModel != null ? domainModel.getAccess(elementModel) == AccessPolicy.READ_ONLY : false;
    }

    @Override
    public boolean isControllable(EObject semanticElement) {
      if(semanticElement != null){
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(semanticElement);
        return domain != null ? domain.isControllable(semanticElement) : false;        
      }
      return false;
    }
}
