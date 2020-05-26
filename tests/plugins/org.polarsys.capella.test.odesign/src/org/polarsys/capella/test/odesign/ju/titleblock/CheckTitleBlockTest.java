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
package org.polarsys.capella.test.odesign.ju.titleblock;

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public abstract class CheckTitleBlockTest extends BasicTestCase {
  protected boolean isDiagramDescriptionIgnored(DiagramDescription diagDes) {
    return diagDes.getName().equals("AD diagram") || diagDes.getName().equals("Capella Architecture")
        || diagDes.getName().equals("Capability Realization Refinement") || diagDes.getName().equals("Modes & States");
  }
}
