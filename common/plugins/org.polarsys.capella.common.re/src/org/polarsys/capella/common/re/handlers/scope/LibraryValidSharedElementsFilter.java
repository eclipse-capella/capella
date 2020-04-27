/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.handlers.scope;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This Filter returns whether the given element is referenced through referenced libraries of the target destination
 */
public class LibraryValidSharedElementsFilter implements IScopeFilter {

  @Override
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  @Override
  public boolean isValidScopeElement(EObject element, IContext context) {
    IPropertyContext ctx = ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context,
        (String) context.get(ITransitionConstants.OPTIONS_SCOPE));

    CatalogElementPkg pkg = (CatalogElementPkg) ctx
        .getCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET));
    if (pkg != null) {
      IModel targetModel = ILibraryManager.INSTANCE.getModel(pkg);
      if (targetModel != null) {

        if (element instanceof EObject) {
          IModel sourceModel = ILibraryManager.INSTANCE.getModel((EObject) element);
          if (targetModel.equals(sourceModel)
              || LibraryManagerExt.getAllReferences(targetModel).contains(sourceModel)) {
            return true;
          }
        }
      }
    }
    return false;
  }

}
