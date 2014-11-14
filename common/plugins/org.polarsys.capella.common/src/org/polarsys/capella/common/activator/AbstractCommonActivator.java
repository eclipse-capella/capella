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
package org.polarsys.capella.common.activator;

import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.mdsofa.common.activator.AbstractActivator;

/**
 */
public abstract class AbstractCommonActivator extends AbstractActivator {
  /**
   * Log a message in the Eclipse log file.
   * @param severity_p one of <code>IStatus.OK</code>, <code>IStatus.ERROR</code>, <code>IStatus.INFO</code>, <code>IStatus.WARNING</code>, or
   *          <code>IStatus.CANCEL</code>
   * @param message a human-readable message, localized to the current locale
   * @param exception a low-level exception, or <code>null</code> if not applicable
   */
  public void log(int severity_p, String message_p, Throwable exception_p) {
    getLog().log(new Status(severity_p, getPluginId(), message_p, exception_p));
  }
}
