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

import java.util.List;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.Message;

/**
 */
public class MessageExt {
  static public List<Message> getAllMessages;

  /**
   * This method returns the root owner {@link MessagePkg} of the current {@link Message}
   * @param message_p
   *          the current message
   * @return the root owner DataPkg
   */
  static public DataPkg getRootOwnerDataPkg(Message message_p) {
    if (null != message_p) {
      Object container = message_p.eContainer();
      if (container instanceof Message) {
        return getRootOwnerDataPkg((Message) container);
      } else if (container instanceof DataPkg) {
        return DataPkgExt.getRootDataPkg((DataPkg) container);
      }
    }
    return null;
  }
}
