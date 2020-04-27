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
package org.polarsys.capella.common.re.re2rpl.detach.properties;

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
	  public Collection<?> getChoiceValues(IPropertyContext context) {
	    IContext ctx = getContext(context);
	    //return ReplicableElementHandlerHelper.getInstance(context).getAllDefinedRecReplicableElements(context);
	    return ReplicableElementHandlerHelper.getInstance(ctx).getAllDefinedReplicableElements(ctx);
	  }

	  @Override
	  protected Object getInitialValue(IPropertyContext context) {
	    Object element = null;
	    IContext ctx = getContext(context);
	    Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(ctx).getIndirectlySelectedReplicableElements(ctx);
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
	    //return false;
		  return true;
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public IStatus validate(Object newValue, IPropertyContext context) {
	    if ((newValue == null) || !(newValue instanceof CatalogElement)) {
	      return new Status(IStatus.ERROR, "n", "Select a RPL to detach");
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
