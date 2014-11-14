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
package org.polarsys.capella.common.re.re2rpl.create.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.properties.AbstractContextProperty;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class InitialSourceProperty extends AbstractContextProperty implements IRestraintProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Object> getChoiceValues(IPropertyContext context_p) {
    IContext context = getContext(context_p);
    return ReplicableElementHandlerHelper.getInstance(context).getAllDefinedRecReplicableElements(context);
  }

  @Override
  protected Object getInitialValue(IPropertyContext context_p) {
    Object element = null;
    IContext context = getContext(context_p);
    Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(context).getIndirectlySelectedReplicableElements(context);
    if (!selectedElements.isEmpty()) {
      element = selectedElements.iterator().next();
    }
    return element;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMany() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    if ((newValue_p == null) || !(newValue_p instanceof CatalogElement)) {
      return new Status(IStatus.ERROR, "n", "Select a REC to create a RPL");
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return CatalogElement.class;
  }

}
