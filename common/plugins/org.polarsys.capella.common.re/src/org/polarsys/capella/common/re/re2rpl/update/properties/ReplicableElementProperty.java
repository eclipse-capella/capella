/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.re2rpl.update.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.Activator;
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

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    if ((newValue == null) || !(newValue instanceof CatalogElement)) {
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Your RPL is invalid (no REC), please validate your model");
    }
    return Status.OK_STATUS;
  }
}
