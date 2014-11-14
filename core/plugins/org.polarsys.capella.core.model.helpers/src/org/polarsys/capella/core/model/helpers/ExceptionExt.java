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
package org.polarsys.capella.core.model.helpers;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.Exception;


/**
 *
 */
public class ExceptionExt {
  /**
   * This method returns the root owner {@link ExceptionPkg} of the current {@link Exception}
   * @param exception_p
   *          the current Exception
   * @return the root owner DataPkg
   */
  static public DataPkg getRootOwnerDataPkg(Exception exception_p) {
    if (null != exception_p) {
      Object container = exception_p.eContainer();
      if (container instanceof Exception) {
        return getRootOwnerDataPkg((Exception) container);
      } else if (container instanceof DataPkg) {
        return DataPkgExt.getRootDataPkg((DataPkg) container);
      }
    }
    return null;
  }
}
