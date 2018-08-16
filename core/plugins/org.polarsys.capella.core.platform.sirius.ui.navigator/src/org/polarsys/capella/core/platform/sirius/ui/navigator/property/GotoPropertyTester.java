/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.property;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IMarker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;
import org.polarsys.kitalpha.emde.model.Element;

public class GotoPropertyTester extends PropertyTester {
  
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    boolean isValidInstance = false;
    
    isValidInstance |= (receiver instanceof Element);
    isValidInstance |= (receiver instanceof IGraphicalEditPart);
    isValidInstance |= (receiver instanceof DTableElement);
    isValidInstance |= (receiver instanceof IMarker);
    
    if (isValidInstance) {
      return !NavigationAdvisor.getInstance().getNavigableElements(receiver).isEmpty();
    }
    
    return false;
  }

}
