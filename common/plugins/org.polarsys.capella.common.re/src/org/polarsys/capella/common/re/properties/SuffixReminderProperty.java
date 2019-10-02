/*******************************************************************************
 * Copyright (c) 2018, 2019, THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
