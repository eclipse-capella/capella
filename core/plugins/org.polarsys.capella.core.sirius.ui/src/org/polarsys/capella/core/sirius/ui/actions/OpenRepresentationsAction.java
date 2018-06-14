/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageMonitoringLogger;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.ui.helper.SiriusItemWrapperHelper;
import org.polarsys.capella.core.sirius.ui.runnable.OpenRepresentationsRunnable;

/**
 * The action allowing to open representations.
 */
public class OpenRepresentationsAction extends BaseSelectionListenerAction {
  // Log4j reference logger.
  private static final Logger LOGGER = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);
  private List<DRepresentationDescriptor> representationDescriptors;
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
  public OpenRepresentationsAction(RepresentationDescription description, DRepresentationDescriptor repDesc) {
    super(repDesc.getName());

    parent = true;
    representationDescriptors = new ArrayList<DRepresentationDescriptor>();
    representationDescriptors.add(repDesc);

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
    List<DRepresentation> reps = new ArrayList<>();
    if (parent) {
      for (DRepresentationDescriptor repDesc : representationDescriptors) {
        reps.add(repDesc.getRepresentation());
      }
    } else {
      IStructuredSelection selection = getStructuredSelection();
      
      reps = RepresentationHelper.getRepresentations((SiriusItemWrapperHelper.filterItemWrapper(selection)));
    }
    // Precondition	
    if (reps.isEmpty()) {
      return;
    }
    
    String eventName = "Open Representation";
	String eventContext = ICommonConstants.EMPTY_STRING;
	String addendum = ICommonConstants.EMPTY_STRING;
	UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.NONE, addendum);
    
    IRunnableWithProgress runnable = new OpenRepresentationsRunnable(reps, false);
    ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
    try {
      progressDialog.run(false, false, runnable);
      UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.OK, addendum);
    } catch (InvocationTargetException e) {
      LOGGER.debug(new EmbeddedMessage(e.getMessage(), IReportManagerDefaultComponents.UI));
      UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.ERROR, addendum);
    } catch (InterruptedException e) {
      LOGGER.debug(new EmbeddedMessage(e.getMessage(), IReportManagerDefaultComponents.UI));
      UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.ERROR, addendum);
    }
    parent = false;
  }

}
