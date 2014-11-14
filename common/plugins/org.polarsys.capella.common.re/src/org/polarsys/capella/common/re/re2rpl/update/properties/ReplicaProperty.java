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
import org.polarsys.capella.common.re.re2rpl.create.properties.InitialTargetProperty;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicaProperty extends InitialTargetProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    CatalogElement rootElement = (CatalogElement) context.get(TARGET);

    if (rootElement == null) {
      Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(context).getIndirectlySelectedReplicableElements(context);
      if (!selectedElements.isEmpty()) {
        rootElement = selectedElements.iterator().next();
        context.put(TARGET, rootElement);
      }
    }

    return rootElement;
  }
}
