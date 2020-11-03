/*******************************************************************************
 * Copyright (c) 2018, 2020, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

public class SuffixReminderProperty extends StringProperty {

  public static final String SUFFIX_REMINDER_MSG = "Please specify if your REC elements are suffixed";
  private IStatus suffixReminderStatus;

  @Override
  public Object getValue(IPropertyContext context) {
    return SUFFIX_REMINDER_MSG;
  }

  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {

    if (suffixReminderStatus == null) {
      suffixReminderStatus = new Status(IStatus.INFO, getId(), SUFFIX_REMINDER_MSG);
    }

    return suffixReminderStatus;
  }

}
