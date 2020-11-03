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
package org.polarsys.capella.core.transition.diagram.helpers;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.system.handlers.traceability.RealizationLinkTraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TraceabilityHelper extends RealizationLinkTraceabilityHandler {

  /**
   * @param identifier_p
   */
  public TraceabilityHelper() {
    super("");
  }

  private static TraceabilityHelper _instance = null;

  public static TraceabilityHelper getService(IContext context_p) {
    if (_instance == null) {
      _instance = new TraceabilityHelper();
    }
    return _instance;
  }

  public Collection<EObject> getAllocatingElements(IContext context_p, EObject object_p) {
    Collection<EObject> result = getTargetAttachments(object_p, context_p);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return super.init(context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return super.dispose(context_p);
  }

}
