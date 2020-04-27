/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.Exception;


/**
 *
 */
public class ExceptionExt {
  /**
   * This method returns the root owner {@link ExceptionPkg} of the current {@link Exception}
   * @param exception
   *          the current Exception
   * @return the root owner DataPkg
   */
  static public DataPkg getRootOwnerDataPkg(Exception exception) {
    if (null != exception) {
      Object container = exception.eContainer();
      if (container instanceof Exception) {
        return getRootOwnerDataPkg((Exception) container);
      } else if (container instanceof DataPkg) {
        return DataPkgExt.getRootDataPkg((DataPkg) container);
      }
    }
    return null;
  }
}
