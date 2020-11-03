/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.operations;

import java.util.Map;

import org.eclipse.gmf.runtime.emf.clipboard.core.PasteTarget;
import org.eclipse.gmf.runtime.emf.clipboard.core.internal.PasteIntoParentOperation;
import org.eclipse.gmf.runtime.emf.clipboard.core.internal.PasteOperation;

@SuppressWarnings("restriction")
public class CapellaPasteIntoParentOperation extends PasteIntoParentOperation {

  // Override for later use and to know existence because provided in a new interface IClipboardSupport2
  public CapellaPasteIntoParentOperation(PasteOperation pasteProcess, PasteTarget element, Map hintsMap)
      throws Exception {
    super(pasteProcess, element, hintsMap);
  }
}
