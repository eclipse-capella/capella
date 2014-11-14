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
package org.polarsys.capella.core.projection.common.handlers;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.projection.common.context.IContext;

/**
 * for further use
 */
public interface ISelectionContext {

  public static final ISelectionContext EMPTY_CONTEXT = null;

  public boolean match(EObject source_p, EObject target_p, IContext context_p);

}
