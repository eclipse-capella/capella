/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.aird;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.migration.Activator;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationRunnable;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.Messages;

/**
 * Migration contributor that will open the session and update width and height value of DNodes with Square style when
 * auto-size has been enabled in their mapping; auto-size is enabled when SquareDescription width == -1 and height ==
 * -1. If auto-size is enabled for these nodes, then width and height of the Square style should be updated with -1
 * value, and the width/height of the DNode should be updated with the value of the SizeComputationExpression.
 * 
 * @author Glenn Plouhinec
 */
public class AutoSizeSquareStyleMigrationContributor extends AirdMigrationContributor {

  @Override
  public MigrationRunnable getRunnable(IFile file) {
    return new AirdMigrationRunnable(file) {
      @Override
      public String getName() {
        return org.polarsys.capella.core.data.migration.aird.Messages.MigrationAction_AutoSizeSquareStyleMigration;
      }

      @Override
      public IStatus run(MigrationContext context, boolean checkVersion) {
        IFile airdFile = getFile();
        URI airdURI = URI.createPlatformResourceURI(airdFile.getFullPath().toString(), true);
        Session session = SessionManager.INSTANCE.getSession(airdURI, new NullProgressMonitor());
        if (session != null) {
          session.open(new NullProgressMonitor());

          session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
              updateSquareStyle(session);
            }
          });
          session.save(new NullProgressMonitor());
          session.close(new NullProgressMonitor());
        }
        return Status.OK_STATUS;
      }
    };
  }

  private void updateSquareStyle(Session session) {
    final Optional<DAnalysis> sharedMainDAnalysis = session.getSharedMainDAnalysis();
    if (sharedMainDAnalysis.isPresent()) {
      DAnalysis dAnalysis = sharedMainDAnalysis.get();
      List<DNode> allDNodeWithAutoSizedSquareStyle = getAllDNodeWithAutoSizeSquareStyle(dAnalysis);
      for (DNode dNode : allDNodeWithAutoSizedSquareStyle) {
        if (dNode.getOwnedStyle() instanceof Square squareStyle && dNode.getActualMapping().getStyle() instanceof SquareDescription squareDesc) {
          if (squareStyle.getWidth() != -1 || squareStyle.getHeight() != -1) {
            squareStyle.setWidth(-1);
            squareStyle.setHeight(-1);
          }
          Integer computedSCE = getSizeComputationExpressionValue(dNode, session.getInterpreter(), squareDesc.getSizeComputationExpression());
          if (computedSCE != null) {
            dNode.setHeight(computedSCE);
            dNode.setWidth(computedSCE);
          }
        }
      }
    }
  }

  private List<DNode> getAllDNodeWithAutoSizeSquareStyle(DAnalysis dAnalysis) {
    List<DRepresentationDescriptor> repdesc = dAnalysis.getOwnedViews().stream() //
        .flatMap(dView -> dView.getOwnedRepresentationDescriptors().stream()) //
        .toList();
    List<DRepresentation> allRepresentations = repdesc.stream() //
        .map(DRepresentationDescriptor::getRepresentation) //
        .toList();
    List<DNode> allDNodes = allRepresentations.stream().flatMap(rep -> StreamSupport.stream( //
          Spliterators.spliteratorUnknownSize(rep.eAllContents(), 0), false)) //
        .filter(DNode.class::isInstance) //
        .map(DNode.class::cast) //
        .toList();
    List<DNode> allDNodeWithAutoSizedSquareStyle = allDNodes.stream() //
        .filter(dnode -> dnode.getOwnedStyle() instanceof Square ownedStyle //
            && dnode.getActualMapping().getStyle() instanceof SquareDescription squareDesc //
            && squareDesc.getWidth() == -1 && squareDesc.getHeight() == -1 //
            && ownedStyle.getLabelPosition().getValue() == LabelPosition.NODE) //
        .toList();
    return allDNodeWithAutoSizedSquareStyle;
  }

  private Integer getSizeComputationExpressionValue(DNode dNode, IInterpreter interpreter, String sizeComputationExpression) {
    Integer result = null;
    if (sizeComputationExpression != null && !sizeComputationExpression.isBlank() && interpreter != null)
      try {
        result = interpreter.evaluateInteger(dNode, sizeComputationExpression);
      } catch (EvaluationException e) {
        String errorMsg = MessageFormat.format(Messages.MigrationAction_AutoSizeSquareNodes_sizeComputationExpressionEvaluationError, sizeComputationExpression, dNode.getName(),
            dNode.getParentDiagram().getName(), dNode.getActualMapping().getName());
        Activator.getDefault().getLog().error(errorMsg, e);
      }
    return result;
  }

  public String getKind() {
    return MigrationConstants.MIGRATION_KIND__AUTOSIZE_SQUARE_STYLE;
  }

}
