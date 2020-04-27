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
package org.polarsys.capella.common.flexibility.wizards.ui;

import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * A MultiStatus displaying a merged message from its invalid children
 */
public class CompoundMultiStatus extends MultiStatus {

  public CompoundMultiStatus(String pluginId) {
    super(pluginId, IStatus.OK, Status.OK_STATUS.getMessage(), null);
  }

  @Override
  public String getMessage() {
    LinkedHashSet<String> values = new LinkedHashSet<String>();

    int severity = getSeverity();
    StringBuffer message = new StringBuffer();
    for (IStatus child : getChildren()) {
      if (child.matches(severity)) {
        values.add(child.getMessage());
      }
    }
    for (String value : values) {
      if (!value.isEmpty()) {
        if (message.length() > 0) {
          message.append(ICommonConstants.WHITE_SPACE_CHARACTER);
          message.append(ICommonConstants.SEMICOLON_CHARACTER);
          message.append(ICommonConstants.WHITE_SPACE_CHARACTER);
        }
        message.append(value);
      }
    }
    return message.toString();
  }
}