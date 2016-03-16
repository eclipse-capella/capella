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

package org.polarsys.capella.common.model;

import org.eclipse.emf.common.notify.Notification;

/**
 */
public interface IDelegatedListener {

  /**
   * @return {@code TRUE} if the given notification {@code notification} has to be filtered, and {@code FALSE} otherwise.
   */
  public boolean filterNotification(Notification notification);

  /**
   * This determines whether this property for specified object supports set (and reset).
   * @return {@code TRUE} if the given object {@code object} is modifiable.
   */
  public boolean canSetProperty(Object object);

}
