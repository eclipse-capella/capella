/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

public class I_40_Resolver extends AbstractDeleteCommandResolver {

  @Override
  public Object getElementToDelete(Object obj) {

    return getDuplicateInvolvements((Involvement) obj);
  }

  private List<Involvement> getDuplicateInvolvements(Involvement selectedInvolvement) {

    InvolverElement involvementSource = selectedInvolvement.getInvolver();
    InvolvedElement involvementTarget = selectedInvolvement.getInvolved();

    List<Involvement> duplicates = new ArrayList<>();

    if (null != involvementSource && null != involvementTarget) {

      EList<Involvement> involvementLinks = involvementSource.getInvolvedInvolvements();

      for (Involvement inv : involvementLinks) {

        if (inv.getInvolved().equals(involvementTarget)) {
          duplicates.add(inv);
        }
      }

      // Remove the first element, because we need to keep the original Involvement
      if (duplicates.size() > 1) {

        duplicates.remove(0);
      }
    }

    return duplicates;
  }
}
