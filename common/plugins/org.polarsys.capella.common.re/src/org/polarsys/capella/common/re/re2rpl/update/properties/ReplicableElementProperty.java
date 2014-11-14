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
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementProperty extends org.polarsys.capella.common.re.re2rpl.create.properties.ReplicableElementProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    CatalogElement rootElement = (CatalogElement) context.get("RE");

    if (rootElement == null) {
      CatalogElement source = (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty("target"));

      Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(context).getIndirectlySelectedReplicableElements(context);

      for (CatalogElement element : selectedElements) {
        if (ReplicableElementHandlerHelper.getInstance(context).isReplica(element, source)) {
          context.put("RE", element);
          ReplicableElementHandlerHelper.getInstance(context).setSource(context, element);
          return element;
        }
      }

      if (source != null) {
        rootElement = source.getOrigin();
      }
      context.put("RE", rootElement);
      ReplicableElementHandlerHelper.getInstance(context).setSource(context, rootElement);

    }

    return rootElement;

  }

}
