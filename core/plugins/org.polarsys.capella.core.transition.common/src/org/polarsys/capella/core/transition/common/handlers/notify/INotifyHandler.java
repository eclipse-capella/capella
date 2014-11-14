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
package org.polarsys.capella.core.transition.common.handlers.notify;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A handler used to be able to define listener notification according to a given kind
 */
public interface INotifyHandler extends IHandler {

  public void addListener(String kind_p, INotifyListener listener_p, IContext context_p);

  public void notify(String kind_p, INotifyChangeEvent event_p, IContext context_p);

}
