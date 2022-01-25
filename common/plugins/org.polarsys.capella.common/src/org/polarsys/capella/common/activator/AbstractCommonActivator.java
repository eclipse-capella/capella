/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.activator;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.mdsofa.common.activator.AbstractActivator;

/**
 */
public abstract class AbstractCommonActivator extends AbstractActivator {
  /**
   * Log a message in the Eclipse log file.
   * @param severity one of <code>IStatus.OK</code>, <code>IStatus.ERROR</code>, <code>IStatus.INFO</code>, <code>IStatus.WARNING</code>, or
   *          <code>IStatus.CANCEL</code>
   * @param message a human-readable message, localized to the current locale
   * @param exception a low-level exception, or <code>null</code> if not applicable
   */
  @Deprecated
  public void log(int severity, String message, Throwable exception) {
    Platform.getLog(getBundle()).log(new Status(severity, getBundle().getSymbolicName(), message, exception));
  }
}
