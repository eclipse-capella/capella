/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * 
 */
public class FCDDiagramMigrationRunnable extends AirdMigrationRunnable {

  public FCDDiagramMigrationRunnable(IFile file) {
    super(file);
  }

  @Override
  public String getName() {
    return Messages.MigrationAction_FCDDiagramMigration;
  }

  @Override
  public IStatus run(MigrationContext context, boolean checkVersion) {
    IFile airdFile = getFile();
    URI airdURI = URI.createPlatformResourceURI(airdFile.getFullPath().toString(), true);
    Session session = SessionManager.INSTANCE.getSession(airdURI, new NullProgressMonitor());
    session.open(new NullProgressMonitor());
    List<DSemanticDiagram> fcdDiagrams = DialectManager.INSTANCE.getAllLoadedRepresentations(session).stream()
        .filter(DSemanticDiagram.class::isInstance).map(DSemanticDiagram.class::cast)
        .filter(
            diagram -> diagram.getDescription().getName().equals(IDiagramNameConstants.FUNCTIONAL_CHAIN_DIAGRAM_NAME)
                || diagram.getDescription().getName()
                    .equals(IDiagramNameConstants.OPERATIONAL_PROCESS_DESCRIPTION_DIAGRAM_NAME))
        .collect(Collectors.toList());
    for (DSemanticDiagram diagram : fcdDiagrams) {
      if (diagram.getOwnedDiagramElements().stream()
          .noneMatch(element -> element.getMapping().getName().equals("FC_FunctionalChain")))
        continue;
      Display.getDefault().syncExec(() -> {
        // Store the layout
        DiagramHelper.getService().offScreenDiagramCopyLayout(diagram, context.getShell());
        // Clean old FC_FunctionalChain mapping elements in the diagram
        cleanOldRCRMapping(diagram);
        // Refresh diagram to display new FCR mapping elements
        refreshDiagramInTransaction(diagram);
        collapseAllFCRContainer(diagram);
        // Refresh diagram again to display link at the node container
        refreshDiagramInTransaction(diagram);
        // Apply the layout
        pasteLayoutInTransaction(context, diagram);
      });
    }
    session.save(new NullProgressMonitor());
    session.close(new NullProgressMonitor());
    return Status.OK_STATUS;
  }

  protected void cleanOldRCRMapping(DSemanticDiagram diagram) {
    List<DDiagramElement> oldMappingFCRs = diagram.getOwnedDiagramElements().stream()
        .filter(element -> element.getMapping().getName().equals("FC_FunctionalChain")).collect(Collectors.toList());
    TransactionHelper.getExecutionManager(diagram).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        oldMappingFCRs.stream().forEach(SiriusUtil::delete);
      }
    });
  }

  protected void pasteLayoutInTransaction(MigrationContext context, DSemanticDiagram diagram) {
    TransactionHelper.getExecutionManager(diagram).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        DiagramHelper.getService().offScreenDiagramPasteLayout(diagram, context.getShell());
      }
    });
  }

  protected void refreshDiagramInTransaction(DSemanticDiagram diagram) {
    TransactionHelper.getExecutionManager(diagram).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        DialectManager.INSTANCE.refresh(diagram, true, new NullProgressMonitor());
      }
    });
  }

  /**
   * Collapse all FCR nodes
   * 
   * @param diagram
   */
  protected void collapseAllFCRContainer(DDiagram diagram) {
    diagram.getDiagramElements().stream()
        .filter(diagramElement -> (diagramElement.getTarget() instanceof FunctionalChainReference))
        .map(DNodeContainer.class::cast)
        .map(node -> node.getOwnedDiagramElements().stream().filter(DNodeContainer.class::isInstance)
            .map(DNodeContainer.class::cast).findFirst().get())
        .forEach(node -> DiagramHelper.collapseContainer(TransactionHelper.getEditingDomain(node), node));
  }
}
