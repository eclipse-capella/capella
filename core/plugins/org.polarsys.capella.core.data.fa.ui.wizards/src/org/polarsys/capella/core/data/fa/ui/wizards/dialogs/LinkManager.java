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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;

/**
 */
public class LinkManager {

  List<Object> _startedElements;

  public LinkManager() {
    _startedElements = new ArrayList<Object>();
  }

  public void makeLinkTo(Collection<?> endElements_p) {
    for (Object sepObj : _startedElements) {
      for (Object tepObj : endElements_p) {
        if ((sepObj instanceof AbstractFunction) && (tepObj instanceof AbstractFunction)) {
          EIAllocationModelHelpers.handleAllocation((AbstractFunction) sepObj, (AbstractFunction) tepObj);
        } else if ((sepObj instanceof FunctionPort) && (tepObj instanceof FunctionPort)) {
          EIAllocationModelHelpers.handleAllocation((FunctionPort) sepObj, (FunctionPort) tepObj);
        }
      }
    }
    clearStartedElements();
  }

  public void startLinkFrom(Collection<?> startedElements_p) {
    clearStartedElements();
    _startedElements.addAll(startedElements_p);
  }

  public void clearStartedElements() {
    if (!_startedElements.isEmpty()) {
      _startedElements.clear();
    }
  }

  public List<?> getStartedElements() {
    return _startedElements;
  }

  public boolean isStartedElement(Object element_p) {
    return _startedElements.contains(element_p);
  }

  public boolean isStarted() {
    return !_startedElements.isEmpty();
  }
}
