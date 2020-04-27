/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
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
  public ReSourceScope(CatalogElement element, Collection<? extends EObject> elements, IContext context) {
    super(element, elements, context);
    
    // if the command is:
    String commandValue = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    // - Update selected RPL from its REC
    if (IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(commandValue)) {
      setOriginator(Messages.ReSourceScope_REC);
    }
    // - Update REC from selection
    else if (IReConstants.COMMAND__UPDATE_CURRENT_REPLICA_FROM_REPLICA.equals(commandValue)) {
      setOriginator(Messages.ReSourceScope_selection);
    }
    // - Update REC from selected RPL
    else if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(commandValue)) {
      setOriginator(Messages.ReSourceScope_selectedRPL);
    }
    // else
    else {
      setOriginator(Messages.ReSourceScope_candidateModel);
    }
  }

  @Override
  public List<Object> get(EObject source, EAttribute attribute) {

    List<Object> values = super.get(source, attribute);

    // if values is not empty
    if (!values.isEmpty()) {

      if (!attribute.equals(AttributesHandlerHelper.getInstance(context).getSuffixableFeature(source, context))) {
        return values;
      }

      // if this command is update RPL from REC:
      String commandValue = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
      if (IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(commandValue)
          || IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(commandValue)) {

        // and if element is a REC
        if ((element.getKind() == CatalogElementKind.REC) || (element.getKind() == CatalogElementKind.REC_RPL)) {

          // find the link about the current source
          for (CatalogElementLink link : element.getOwnedLinks()) {
            if (link.getTarget() == source) {

              // if the element is suffixed
              if (link.isSuffixed()) {
                List<Object> newValues = new ArrayList<Object>();
                if (AttributesHandlerHelper.getInstance(context).getCustomNameElements(context).contains(link)) {
                  newValues.add(AttributesHandlerHelper.getInstance(context).getCustomName(link, context));

                } else {

                  // get the RPL suffix
                  CatalogElement rpl = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);
                  if (rpl != null) {
                    CatalogElementLink r2Link = null;
                    for (CatalogElementLink rlink : rpl.getOwnedLinks()) {
                      if ((rlink.getOrigin() != null) && rlink.getOrigin().equals(link)) {
                        r2Link = rlink;
                        break;
                      }
                    }

                    String name = (String) values.iterator().next();
                    if ((r2Link != null) && AttributesHandlerHelper.getInstance(context).getCustomNameElements(context).contains(r2Link)) {
                      name = AttributesHandlerHelper.getInstance(context).getCustomName(r2Link, context);
                    } else {
                      String suffix = rpl.getSuffix();
                      if (suffix != null) {
                        name = values.iterator().next() + suffix;
                      }
                    }
                    newValues.add(name);
                  }
                }

                return Collections.unmodifiableList(newValues);

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
