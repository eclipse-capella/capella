/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.rpl2re.updatedef.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.constants.Messages;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicaProperty extends AbstractProperty implements IEditableProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext propertyContext) {
    try {

      IContext context = (IContext) propertyContext.getSource();
      CatalogElement replica = (CatalogElement) context.get("RPL");

      if (replica == null) {

        CatalogElement source =
            (CatalogElement) propertyContext.getCurrentValue(propertyContext.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
        Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(context).getIndirectlySelectedReplicableElements(context);

        Collection<Object> selection = (Collection<Object>) context.get(ITransitionConstants.TRANSITION_SOURCES);
        if (selection.size() > 0) {
          // for all selected elements of type CatalogElement (RPLs)
          for (Object element : selectedElements) {
            if (element instanceof CatalogElement) {
              CatalogElement rpl = (CatalogElement) element;
              // if the origin of the selected RPL is the right REC
              if (source.equals(rpl.getOrigin())) {
                // return this RPL
                if (replica == null) {
                  replica = rpl;
                // but if there is another RPL found
                } else if (replica != rpl) {
                  // it is not possible to update a REC with several RPLs at the same time
                  return null;
                }
              }
            }
          }
        }

        context.put("RPL", replica);
      }

      return replica;
    } catch (Exception e) {

    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return CatalogElement.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object toType(Object value, IPropertyContext propertyContext) {
    IContext context = (IContext) propertyContext.getSource();
    CatalogElement element = (CatalogElement) context.get("RPL");
    if (value instanceof String) {
      element.setName((String) value);
      return element;
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    
    if (newValue == null) {
      return new Status(Status.ERROR, "unknown", Status.ERROR, Messages.Update_REC_from_RPL_only_one, null);
    }
    
    return Status.OK_STATUS;
  }

}
