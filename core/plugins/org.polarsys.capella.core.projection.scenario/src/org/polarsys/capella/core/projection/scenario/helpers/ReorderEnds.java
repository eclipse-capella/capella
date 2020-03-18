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
package org.polarsys.capella.core.projection.scenario.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.projection.scenario.es2is.ES2ISExt;
import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;

/**
 *
 */
public class ReorderEnds implements IFinalizer {

  /** List of AbstractEnd to be moved in correct position */
  private static List<AbstractEnd> _ends = new ArrayList<AbstractEnd>();

  public static void add(AbstractEnd ir_p) {
    _ends.add(ir_p);
  }

  /**
   * @see org.polarsys.capella.core.tiger.IFinalizer#finalize(org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void finalize(ITransfo transfo_p) {

    try {
      Set<AbstractEnd> dones = new HashSet<AbstractEnd>();

      // Move messages of transitioned functional exchanges which conveys some exchange item
      for (AbstractEnd end : _ends) {
        if (!dones.contains(end)) {
          if (end instanceof MessageEnd) {
            MessageEnd me = (MessageEnd) end;
            Collection<AbstractEnd> ends = ES2ISExt.getRelateds(me);
            AbstractEnd eend = ES2ISExt.getEndEnd(me);
            moveAfter(end, ends, eend, transfo_p);
            dones.addAll(ends);
          }
        }
      }
    } finally {
      clean();
    }
  }

  /**
   * Cleanup the finalizer
   */
  protected void clean() {
    if (_ends != null) {
      _ends.clear();
    }
  }

  /**
   * @param ends_p
   * @param eend_p
   */
  @SuppressWarnings("unchecked")
  private void moveAfter(AbstractEnd end, Collection<AbstractEnd> ends_p, AbstractEnd eend_p, ITransfo transfo_p) {
    Scenario scenario = (Scenario) Query.retrieveFirstTransformedElement(end.eContainer(), transfo_p, InteractionPackage.Literals.SCENARIO);
    if (eend_p == null) {
      return;
    }

    Collection<AbstractEnd> sourceEnds = new ArrayList<AbstractEnd>(ends_p);

    // Retrieve positions of ends
    List<AbstractEnd> ends = (List<AbstractEnd>) Query.retrieveTransformedElements(eend_p, transfo_p, InteractionPackage.Literals.ABSTRACT_END);
    if (ends.size() > 0) {
      sourceEnds.remove(eend_p);

      int k = 1;
      for (AbstractEnd eand : ends) {
        Integer position = Integer.valueOf(scenario.getOwnedInteractionFragments().indexOf(eand));

        for (AbstractEnd source : sourceEnds) {
          int j = 0;
          for (AbstractEnd target : (List<AbstractEnd>) Query.retrieveTransformedElements(source, transfo_p, InteractionPackage.Literals.ABSTRACT_END)) {
            if (j == k) {
              scenario.getOwnedInteractionFragments().move(position.intValue(), target);
            }
            j++;
          }
        }
        k++;
      }
    }
  }

}
