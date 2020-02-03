/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.ETypedElement;

public class CapellaSearchMatchOccurence extends AbstractCapellaSearchEntry {
  int lineNumber;
  String fullContent;

  public CapellaSearchMatchOccurence(AbstractCapellaSearchEntry parent, Object object, String valuation,
      boolean matched, IProject project) {
    super(parent, object, valuation, matched, project);
  }

  public ETypedElement getTypedElement() {
    return getElement() instanceof ETypedElement ? (ETypedElement) getElement() : null;
  }

}
