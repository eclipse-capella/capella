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

package org.polarsys.capella.core.model.helpers;

import java.util.List;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.Message;

/**
 */
public class MessageExt {
  static public List<Message> getAllMessages;

  /**
   * This method returns the root owner {@link MessagePkg} of the current {@link Message}
   * @param message
   *          the current message
   * @return the root owner DataPkg
   */
  static public DataPkg getRootOwnerDataPkg(Message message) {
    if (null != message) {
      Object container = message.eContainer();
      if (container instanceof Message) {
        return getRootOwnerDataPkg((Message) container);
      } else if (container instanceof DataPkg) {
        return DataPkgExt.getRootDataPkg((DataPkg) container);
      }
    }
    return null;
  }
}
