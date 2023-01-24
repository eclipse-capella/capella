/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.copyformat.keyproviders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.sirius.ui.copyformat.AbstractCapellaFormatDataKey;
import org.polarsys.capella.core.sirius.ui.copyformat.CapellaDecoratorFormatDataKey;

/**
 * Retrieve keys to be able to match chains and paths with layout from/to their involved elements
 */
public class InvolvementKeyProvider implements IKeyProvider {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<FormatDataKey> getKeys(FormatDataKey key) {
    Collection<FormatDataKey> keys = new ArrayList<FormatDataKey>();

    if (key instanceof CapellaDecoratorFormatDataKey) {
      CapellaDecoratorFormatDataKey mKey = (CapellaDecoratorFormatDataKey) key;
      EObject semantic = mKey.getSemantic();

      if (semantic instanceof FunctionalChainInvolvement && !(semantic instanceof FunctionalChainReference)) {
        // Allows copy paste between an Involvement and its Function/Exchange (e.g. FCD to xAB/xDFB)
        keys.add(createKey(((FunctionalChainInvolvement) semantic).getInvolved()));
        
        // Allows copy paste between an Involvement and other involvements of the involvedElement
        for (FunctionalChainInvolvement inv : getInvolvingInvolvements(((FunctionalChainInvolvement) semantic).getInvolved())) {
          if (doesntHaveSameInvolver((Involvement) semantic, inv)) {
            keys.add(createKey(inv));
          }
        }
        
      } else if (semantic instanceof AbstractFunction || semantic instanceof FunctionalExchange) {
        // Allows copy paste between a Function/Exchange and a FunctionalChainInvolvement (e.g. xAB/xDFB to FCD)
        for (FunctionalChainInvolvement inv : getInvolvingInvolvements((InvolvedElement) semantic)) {
          keys.add(createKey(inv));
        }

      } else if (semantic instanceof PhysicalPathInvolvement
          && !(semantic instanceof PhysicalPathReference)) {
        // Allows copy paste between an Involvement and its Component/Link (e.g. PPD to xAB)
        keys.add(createKey(((PhysicalPathInvolvement) semantic).getInvolved()));
        
        // Allows copy paste between an Involvement and other involvements of the involvedElement
        for (PhysicalPathInvolvement inv : getInvolvingInvolvements((AbstractPathInvolvedElement)((PhysicalPathInvolvement) semantic).getInvolved())) {
          if (doesntHaveSameInvolver((Involvement) semantic, inv)) {
            keys.add(createKey(inv));
          }
        }
        
      } else if (semantic instanceof Part || semantic instanceof PhysicalLink) {
        // Allows copy paste between a Component/Link and a PhysicalPathInvolvement (e.g. xAB to PPD)
        for (PhysicalPathInvolvement inv : getInvolvingInvolvements((AbstractPathInvolvedElement) semantic)) {
          keys.add(createKey(inv));
        }
      }
    }

    return keys;
  }

  protected boolean doesntHaveSameInvolver(Involvement inv, Involvement inv2) {
    return inv.getInvolver() != inv2.getInvolver();
  }
  
  protected AbstractCapellaFormatDataKey createKey(EObject object) {
    return new AbstractCapellaFormatDataKey(object);
  }

  /**
   * Returns the FCI involving the current element
   */
  protected Collection<FunctionalChainInvolvement> getInvolvingInvolvements(InvolvedElement semantic) {
    List<FunctionalChainInvolvement> involvements = ((InvolvedElement) semantic).getInvolvingInvolvements().stream()
        .filter(FunctionalChainInvolvement.class::isInstance).map(FunctionalChainInvolvement.class::cast)
        .collect(Collectors.toList());
    return involvements;
  }

  /**
   * Returns the PPI involving the current element
   */
  protected Collection<PhysicalPathInvolvement> getInvolvingInvolvements(AbstractPathInvolvedElement semantic) {
    List<PhysicalPathInvolvement> involvements = ((InvolvedElement) semantic).getInvolvingInvolvements().stream()
        .filter(PhysicalPathInvolvement.class::isInstance).map(PhysicalPathInvolvement.class::cast)
        .collect(Collectors.toList());
    return involvements;
  }

}
