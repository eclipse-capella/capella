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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;

/**
 */
public class LinkManager {

  List<Object> startedElements;

  public LinkManager() {
    startedElements = new ArrayList<Object>();
  }

  public void makeLinkTo(Collection<?> endElements) {
    for (Object sepObj : startedElements) {
      for (Object tepObj : endElements) {
        if ((sepObj instanceof AbstractFunction) && (tepObj instanceof AbstractFunction)) {
          EIAllocationModelHelpers.handleAllocation((AbstractFunction) sepObj, (AbstractFunction) tepObj);
        } else if ((sepObj instanceof FunctionPort) && (tepObj instanceof FunctionPort)) {
          EIAllocationModelHelpers.handleAllocation((FunctionPort) sepObj, (FunctionPort) tepObj);
        }
      }
    }
    clearStartedElements();
  }

  public void startLinkFrom(Collection<?> startedElements) {
    clearStartedElements();
    this.startedElements.addAll(startedElements);
  }

  public void clearStartedElements() {
    if (!startedElements.isEmpty()) {
      startedElements.clear();
    }
  }

  public List<?> getStartedElements() {
    return startedElements;
  }

  public boolean isStartedElement(Object element) {
    return startedElements.contains(element);
  }

  public boolean isStarted() {
    return !startedElements.isEmpty();
  }
}
