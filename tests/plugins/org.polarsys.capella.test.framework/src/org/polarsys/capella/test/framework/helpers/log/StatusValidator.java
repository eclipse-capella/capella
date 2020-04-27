/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers.log;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;

public class StatusValidator implements ILogListener {

  private boolean valid = false;

  private Predicate<IStatus> predicate;
  
  private List<IStatus> matchedStatuses = new ArrayList<IStatus>();
  
  public List<IStatus> getMatchedStatuses() {
    return matchedStatuses;
  }

  public boolean isValid() {
    return valid;
  }
  
  public StatusValidator(Predicate<IStatus> predicate) {
    this.predicate=predicate;
  }

  @Override
  public void logging(IStatus status, String plugin) {
    if (predicate.test(status)) {
      matchedStatuses.add(status);
      valid = true;
    }
  }
  
}
