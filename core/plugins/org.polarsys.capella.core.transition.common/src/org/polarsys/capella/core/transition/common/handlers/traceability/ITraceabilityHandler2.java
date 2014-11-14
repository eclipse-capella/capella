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
package org.polarsys.capella.core.transition.common.handlers.traceability;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface ITraceabilityHandler2 extends ITraceabilityHandler {

  @Deprecated
  public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p, EClass clazz_p);

  @Deprecated
  public String getId(EObject element_p, IContext context_p);

  @Deprecated
  public boolean isTraced(EObject element_p, IContext context_p);
}
