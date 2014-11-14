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
package org.polarsys.capella.common.re.re2rpl.update.properties;

import java.util.Collection;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementProperty extends org.polarsys.capella.common.re.re2rpl.create.properties.InitialSourceProperty {

  @Override
  protected Object getInitialValue(IPropertyContext context_p) {
    Object element = null;
    IContext context = getContext(context_p);

    CatalogElement source =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(context).getIndirectlySelectedReplicableElements(context);
    for (CatalogElement aelement : selectedElements) {
      if (ReplicableElementHandlerHelper.getInstance(context).isReplica(aelement, source)) {
        return aelement;
      }
    }
    if (source != null) {
      element = source.getOrigin();
    }
    return element;
  }

}
