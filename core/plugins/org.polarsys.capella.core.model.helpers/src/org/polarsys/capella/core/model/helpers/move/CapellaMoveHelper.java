/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.move;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;

/**
 *
 */
public class CapellaMoveHelper extends MoveHelper {
  /**
   * We consider that 2 architectures from the same eClass but from different resources are from same layer
   * Indeed, we have many resources when we use REC in the library and RPL in the model.
   */
  @Override
  protected boolean areInSameLayer(ModelElement element1_p, ModelElement element2_p) {
    if (super.areInSameLayer(element1_p, element2_p)) {
      return true;
    }

    BlockArchitecture arch1 = (BlockArchitecture) EcoreUtil2.getFirstContainer(element1_p, CsPackage.Literals.BLOCK_ARCHITECTURE);
    BlockArchitecture arch2 = (BlockArchitecture) EcoreUtil2.getFirstContainer(element2_p, CsPackage.Literals.BLOCK_ARCHITECTURE);
    if ((arch1 != null) && (arch1.eClass() != null) && arch1.eClass().isInstance(arch2)) {
      return true;
    }
    return false;
  }

}
