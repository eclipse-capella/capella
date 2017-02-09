/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.ui.runnable.OpenRepresentationsRunnable;

/**
 * The action allowing to open representations.
 */
public class OpenRepresentationsAction extends BaseSelectionListenerAction {
  // Log4j reference logger.
  private static final Logger LOGGER = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);
  private List<DRepresentation> representations;
  private boolean parent = false;

  /**
   * Constructs the action allowing to open representations.
   */
  public OpenRepresentationsAction() {
    super("Open"); //$NON-NLS-1$
  }

  /**
   * @param drep
   */
  public OpenRepresentationsAction(RepresentationDescription description, DRepresentation drep) {
    super(drep.getName());

    parent = true;
    representations = new ArrayList<DRepresentation>();
    representations.add(drep);

    ImageDescriptor imageDescriptor = null;
    // Handle specific representations : Table ones.
    if (description instanceof CrossTableDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID,
          "/icons/full/obj16/CrossTableDescription.gif"); //$NON-NLS-1$
    } else if (description instanceof EditionTableDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID, "/icons/full/obj16/DTable.gif"); //$NON-NLS-1$
    } else if (description instanceof SequenceDiagramDescription) {
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.sirius.diagram.sequence.edit", //$NON-NLS-1$
          "/icons/full/obj16/TSequenceDiagram.gif"); //$NON-NLS-1$
    } else {
      // Standard diagram.
      imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(DiagramUIPlugin.ID,
          "/icons/full/obj16/DDiagram.gif"); //$NON-NLS-1$
    }
    if (null == imageDescriptor) {
      imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
    }
    setImageDescriptor(imageDescriptor);
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    List<DRepresentation> reps;
    if (parent) {
      reps = representations;
    } else {
      IStructuredSelection selection = getStructuredSelection();
      reps = RepresentationHelper.getRepresentations(selection.toList());
    }
    // Precondition
    if (reps.isEmpty()) {
      return;
    }
    IRunnableWithProgress runnable = new OpenRepresentationsRunnable(reps, false);
    ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
    try {
      progressDialog.run(false, false, runnable);
    } catch (InvocationTargetException e) {
      LOGGER.debug(new EmbeddedMessage(e.getMessage(), IReportManagerDefaultComponents.UI));
    } catch (InterruptedException e) {
      LOGGER.debug(new EmbeddedMessage(e.getMessage(), IReportManagerDefaultComponents.UI));
    }
    parent = false;
  }

}
