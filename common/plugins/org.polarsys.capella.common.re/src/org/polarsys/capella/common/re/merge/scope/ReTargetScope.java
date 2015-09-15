/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re.merge.scope;

import java.util.Collection;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.merge.scope.ITargetModelScope;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReTargetScope extends ReSourceScope implements ITargetModelScope {

  @Override
  protected boolean isSource() {
    return false;
  }

  /**
   * @param element
   * @param handler
   * @param elements
   * @param context
   */
  public ReTargetScope(CatalogElement element, ITraceabilityHandler handler, Collection<? extends EObject> elements,
      IContext context) {
    super(element, handler, elements, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> retrieveTransformedElementsFromTarget(EObject targetElement) {
    return null;
  }

  @Override
  public boolean add(EObject source, EAttribute attribute, Object value) {

    // if command is create or update RPL from REC
    String commandValue = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    if (IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(commandValue)
        || IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(commandValue)) {

      // and if element is a RPL
      if ((element.getKind() == CatalogElementKind.RPL) || (element.getKind() == CatalogElementKind.REC_RPL)) {

        // find the link about the current source
        for (CatalogElementLink link : element.getOwnedLinks()) {
          if (link.getTarget() == source) {

            // if it is suffixed and the attribute is the name feature, add the RPL suffix on value
            if (link.isSuffixed() && "name".equals(attribute.getName())) {
              value += element.getSuffix();
            }
            // if not, do nothing
            else {
              break;
            }
          }
        }
      }
    }

    return super.add(source, attribute, value);
  }
}
