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
package org.polarsys.capella.core.transition.common.handlers.options;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public interface IOptionsHandler extends IHandler {

  public String getStringValue(IContext context_p, String scope_p, String key_p, String default_p);

  public boolean getBooleanValue(IContext context_p, String scope_p, String key_p, boolean default_p);

  public Collection<EObject> getCollectionValue(IContext context_p, String scope_p, String key_p, Collection<EObject> default_p);

  public Object getValue(IContext context_p, String scope_p, String key_p, Object default_p);

}
