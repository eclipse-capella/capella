/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
  public void attachTracedElements(EObject source, EObject target, EReference feature, IContext iContext) {

    for (EObject referenced : retrieveReferenceAsList(source, feature)) {

      //Attach elements prior to their level. If attach at some level, don't attach lower level elements
      Collection<EObject> tracedReferenced = TraceabilityHandlerHelper.getInstance(iContext).retrieveTracedElements(referenced, iContext);
      LinkedList<Level> levels = LevelHandlerHelper.getInstance(iContext).getLevels(iContext);

      Iterator<Level> levelIterator = levels.descendingIterator();
      boolean hasAttach = false;
      while (levelIterator.hasNext() && !hasAttach) {
        Level level = levelIterator.next();

        ISelectionContext context = LevelHandlerHelper.getInstance(iContext).getSelectionContext(level, iContext);
        for (EObject traced : tracedReferenced) {
          if (context.match(referenced, traced, iContext)) {
            attachElementByReference(source, target, referenced, traced, feature, feature);
            hasAttach = true;
          }
        }
      }
    }

  }
  

  public void invertedAttachTracedElements(EObject source, EObject target, EReference feature, EReference targetFeature, IContext iContext) {
    for (EObject referenced : retrieveReferenceAsList(source, feature)) {

      //Attach elements prior to their level. If attach at some level, don't attach lower level elements
      Collection<EObject> tracedReferenced = TraceabilityHandlerHelper.getInstance(iContext).retrieveTracedElements(referenced, iContext);
      LinkedList<Level> levels = LevelHandlerHelper.getInstance(iContext).getLevels(iContext);

      Iterator<Level> levelIterator = levels.descendingIterator();
      boolean hasAttach = false;
      while (levelIterator.hasNext() && !hasAttach) {
        Level level = levelIterator.next();

        ISelectionContext context = LevelHandlerHelper.getInstance(iContext).getSelectionContext(level, iContext);
        for (EObject traced : tracedReferenced) {
          if (context.match(referenced, traced, iContext)) {
            attachElementByReference(referenced, traced, source, target, targetFeature, targetFeature);
            hasAttach = true;
          }
        }
      }
    }

  }

}
