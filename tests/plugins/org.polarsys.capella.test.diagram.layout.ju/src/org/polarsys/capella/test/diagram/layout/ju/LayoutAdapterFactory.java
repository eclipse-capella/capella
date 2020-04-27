/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.model.handler.helpers.SemanticResourcesScope;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout;
import org.polarsys.kitalpha.emde.model.Element;

public class LayoutAdapterFactory implements IAdapterFactory {

  @Override
  public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
    Session session = SessionManager.INSTANCE.getSessions().iterator().next();
    IScope capellaSemanticResourceScope = new SemanticResourcesScope(session.getTransactionalEditingDomain().getResourceSet());
    EObject element =  (EObject)IdManager.getInstance().getEObject(((ISemanticLayout)adaptableObject).getId(), capellaSemanticResourceScope);
    return (T) element;
  }

  @Override
  public Class<?>[] getAdapterList() {
    return new Class[] { Element.class };
  }

}
