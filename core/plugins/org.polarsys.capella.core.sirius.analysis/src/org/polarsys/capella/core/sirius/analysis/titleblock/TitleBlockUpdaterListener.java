/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.titleblock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.TitleBlockServices;

/**
 * This {@link ResourceSetListener} updates the titleBLock when the representation timeStamp is modified.
 * 
 * @author lfasani
 *
 */
public class TitleBlockUpdaterListener extends ResourceSetListenerImpl {

  private final Session siriuSession;

  /**
   * @param siriuSession
   */
  TitleBlockUpdaterListener(Session session) {
    this.siriuSession = session;

  }

  @Override
  public boolean isPrecommitOnly() {
    return true;
  }

  @Override
  public boolean isAggregatePrecommitListener() {
    return true;
  }

  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
    List<Notification> notifications = event.getNotifications();
    Iterator<Notification> notifIterator = notifications.iterator();

    Map<DRepresentationDescriptor, List<DAnnotation>> titleBlockCells = new LinkedHashMap<>();

    // if DREPRESENTATION_DESCRIPTOR__CHANGE_ID is changed, find the title block cell that uses
    // CapellaServices.getLastModificationDate which is based on DRepresentationDescriptor.changeId
    while (notifIterator.hasNext()) {
      Notification notification = notifIterator.next();
      Object notifier = notification.getNotifier();
      if (ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR.isInstance(notifier)
          && ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__CHANGE_ID.equals(notification.getFeature())) {
        DRepresentation representation = ((DRepresentationDescriptor) notifier).getRepresentation();
        if (representation instanceof DDiagram) {
          List<DAnnotation> diagramTitleBlocks = TitleBlockServices.getService()
              .getTitleBlocksInDiagram((DDiagram) representation);

          for (DAnnotation titleBlock : diagramTitleBlocks) {

            for (DAnnotation line : TitleBlockHelper.getTitleBlockLines(titleBlock)) {
              for (DAnnotation cell : TitleBlockHelper.getTitleBlockCells(line)) {
                String expression = cell.getDetails().get(TitleBlockHelper.CONTENT);
                if (expression.contains("getLastModificationDate")) { //$NON-NLS-1$
                  List<DAnnotation> annotations = Optional.ofNullable(titleBlockCells.get(notifier))
                      .orElse(new ArrayList<>());
                  titleBlockCells.put((DRepresentationDescriptor) notifier, annotations);
                  annotations.add(cell);
                }
              }
            }
          }
        }
      }
    }
    if (!titleBlockCells.isEmpty()) {
      RecordingCommand changeIdRecordingCommand = new RecordingCommand(
          this.siriuSession.getTransactionalEditingDomain()) {
        @Override
        protected void doExecute() {

          for (DRepresentationDescriptor repDesc : titleBlockCells.keySet()) {
            for (DAnnotation cell : titleBlockCells.get(repDesc)) {
              String expression = cell.getDetails().get(TitleBlockHelper.CONTENT);
              Object result;
              try {
                result = siriuSession.getInterpreter().evaluate(repDesc, expression);

                if (result != null) {
                  for (EObject titleBlockContent : cell.getReferences()) {
                    if (titleBlockContent instanceof DAnnotation) {
                      // update the DAnnotation content
                      ((DAnnotation) titleBlockContent).getDetails().put(TitleBlockHelper.CONTENT, result.toString());

                      // update the DNodeContainer corresponding to the Title block content
                      List<DNodeListElement> titleBlockContentNodeListElement = new EObjectQuery(titleBlockContent)
                          .getInverseReferences(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET).stream()
                          .filter(DNodeListElement.class::isInstance).map(DNodeListElement.class::cast)
                          .collect(Collectors.toList());

                      for (DNodeListElement nodeListELement : titleBlockContentNodeListElement) {
                        DDiagramElementSynchronizer dDiagramElementSynchronizer = new DDiagramElementSynchronizer(
                            (DSemanticDiagram) repDesc.getRepresentation(), siriuSession.getInterpreter(),
                            siriuSession.getModelAccessor());
                        dDiagramElementSynchronizer.refresh(nodeListELement);
                      }
                    }
                  }
                }
              } catch (EvaluationException e) {
              }
            }
          }
        }

      };
      return changeIdRecordingCommand;
    }
    return null;
  }
}
