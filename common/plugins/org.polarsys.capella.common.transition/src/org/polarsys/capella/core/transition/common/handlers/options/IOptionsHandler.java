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
package org.polarsys.capella.core.transition.common.handlers.options;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface IOptionsHandler extends IHandler {

  String getStringValue(IContext context, String scope, String key, String defaults);

  boolean getBooleanValue(IContext context, String scope, String key, boolean defaults);

  Collection<EObject> getCollectionValue(IContext context, String scope, String key, Collection<EObject> defaults);

  Object getValue(IContext context, String scope, String key, Object defaults);

}
