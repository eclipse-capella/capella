/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.handlers.traceability;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.TwoSideTraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This traceability handler allows to use
 * {@link org.polarsys.capella.core.transition.common.rules.IRuleAttachment.retrieveDefaultContainer} implementations
 * already defined in Capella transitions while we are looking for ILocationHandler.getDefaultLocation
 */
public class LocationTraceabilityHandler extends TwoSideTraceabilityHandler {

  public LocationTraceabilityHandler() {
    super(LocationTraceabilityHandler.class.getSimpleName());
  }

  @Override
  public Collection<EObject> retrieveSourceElements(EObject source, IContext context) {
    return Collections.singletonList(source);
  }

  @Override
  public Collection<EObject> retrieveTracedElements(EObject source, IContext context) {
    return Collections.singletonList(source);
  }

  public static void update(boolean isLinkSource, IContext context) {
    ITraceabilityHandler handler = (ITraceabilityHandler) context
        .get(IReConstants.TRACEABILITY_LOCATION_HANDLER);
    ((LocationTraceabilityConfiguration) ((CompoundTraceabilityHandler) handler).getConfiguration()).setKind(isLinkSource);
  }

}
