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
package org.polarsys.capella.core.transition.common.handlers.traceability.config;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface ITraceabilityConfiguration extends IHandler {

  List<ITraceabilityHandler> getDefinedHandlers(IContext context);

  boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler, IContext context);

  boolean useHandlerForTracedElements(EObject source, ITraceabilityHandler handler, IContext context);

  boolean useHandlerForSourceElements(EObject source, ITraceabilityHandler handler, IContext context);

}
