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
package org.polarsys.capella.core.transition.system.topdown.handlers.attachment;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.system.handlers.attachment.CapellaDefaultAttachmentHandler;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.ILevelHandler.Level;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.LevelHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TopDownAttachmentHelper extends CapellaDefaultAttachmentHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public void attachTracedElements(EObject source_p, EObject target_p, EReference feature_p, IContext context_p) {

    for (EObject traced : retrieveReferenceAsList(source_p, feature_p)) {

      //Attach elements prior to their level. If attach at some level, don't attach lower level elements
      Collection<EObject> relateds = TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(traced, context_p);
      LinkedList<Level> levels = LevelHandlerHelper.getInstance(context_p).getLevels(context_p);

      Iterator<Level> levelIterator = levels.descendingIterator();
      boolean hasAttach = false;
      while (levelIterator.hasNext() && !hasAttach) {
        Level level = levelIterator.next();

        ISelectionContext context = LevelHandlerHelper.getInstance(context_p).getSelectionContext(level, context_p);
        for (EObject related : relateds) {
          if (context.match(traced, related, context_p)) {
            attachElementByReference(source_p, target_p, traced, related, feature_p, feature_p);
            hasAttach = true;
          }
        }
      }

    }

  }

}
