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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReSourceScope extends ReScope {

  @Override
  protected boolean isSource() {
    return true;
  }

  /**
   * @param elements
   * @param context
   */
  public ReSourceScope(CatalogElement element, ITraceabilityHandler handler, Collection<? extends EObject> elements,
      IContext context) {
    super(element, handler, elements, context);
  }

  @Override
  public List<Object> get(EObject source, EAttribute attribute) {

    List<Object> values = super.get(source, attribute);

    // if values is not empty
    if (!values.isEmpty()) {

      // if this command is update RPL from REC:
      String commandValue = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
      if (IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(commandValue)) {

        // and if element is a REC
        if ((element.getKind() == CatalogElementKind.REC) || (element.getKind() == CatalogElementKind.REC_RPL)) {

          // find the link about the current source
          for (CatalogElementLink link : element.getOwnedLinks()) {
            if (link.getTarget() == source) {

              // if the element is suffixed
              if (link.isSuffixed()) {

                // get the RPL suffix
                CatalogElement rpl = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);
                if (rpl != null) {
                  String suffix = rpl.getSuffix();
                  if (suffix != null) {

                    // and add it on all values
                    List<Object> newValues = new ArrayList<Object>();
                    for (Object value : values) {
                      newValues.add(value + suffix);
                    }

                    return Collections.unmodifiableList(newValues);
                  }
                }
              }
            }
          }
        }
      }

      // if this command is update REC from RPL:
      else if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(commandValue)) {

        // and if element is a RPL
        if ((element.getKind() == CatalogElementKind.RPL) || (element.getKind() == CatalogElementKind.REC_RPL)) {

          // find the link about the current source
          for (CatalogElementLink link : element.getOwnedLinks()) {
            if (link.getTarget() == source) {

              // if the element is suffixed
              if (link.isSuffixed()) {

                // get the RPL suffix
                String suffix = element.getSuffix();
                if (suffix != null) {

                  // and remove it on all values:
                  List<Object> newValues = new ArrayList<Object>();
                  for (Object object : values) {
                    if (object instanceof String) {
                      String currentValue = (String) object;
                      
                      // if found
                      if (currentValue.endsWith(suffix)) {

                        // remove the suffix
                        String newValue = currentValue.substring(0, currentValue.length() - suffix.length());
                        newValues.add(newValue);
                      } else {
                        newValues.add(currentValue);
                      }
                    } else {
                      newValues.add(object);
                    }
                  }

                  return Collections.unmodifiableList(newValues);
                }
              }
            }
          }
        }
      }
    }

    return values;
  }
}
