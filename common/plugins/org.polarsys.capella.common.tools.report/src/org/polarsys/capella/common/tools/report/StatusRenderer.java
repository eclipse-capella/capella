/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.tools.report;

import org.apache.log4j.or.ObjectRenderer;
import org.eclipse.core.runtime.IStatus;

public class StatusRenderer implements ObjectRenderer {
  
  public String doRender(Object theObject) {
    if (theObject instanceof IStatus) {
      IStatus status = (IStatus) theObject;
      if (status.getSeverity() == IStatus.CANCEL) {
        return Messages.StatusRenderer_Cancel;
        
      } else if (status.isOK()) {
        return status.getMessage();
      }
      return status.getMessage();
    }
    return theObject.toString();
  }
  
}
