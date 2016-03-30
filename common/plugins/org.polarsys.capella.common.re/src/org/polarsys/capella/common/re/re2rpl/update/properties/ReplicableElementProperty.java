/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  protected Object getInitialValue(IPropertyContext context) {
    Object element = null;
    IContext ctx = getContext(context);

    CatalogElement source =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(ctx).getIndirectlySelectedReplicableElements(ctx);
    for (CatalogElement aelement : selectedElements) {
      if (ReplicableElementHandlerHelper.getInstance(ctx).isReplica(aelement, source)) {
        return aelement;
      }
    }
    if (source != null) {
      element = source.getOrigin();
    }
    return element;
  }

}
