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
package org.polarsys.capella.core.transition.common.handlers.traceability.config;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public interface ITraceabilityConfiguration extends IHandler {

  public List<ITraceabilityHandler> getDefinedHandlers(IContext context_p);

  public boolean useHandlerForAttachment(EObject source_p, EObject target_p, ITraceabilityHandler handler_p, IContext context_p);

  public boolean useHandlerForTracedElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p);

  public boolean useHandlerForSourceElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p);

}
