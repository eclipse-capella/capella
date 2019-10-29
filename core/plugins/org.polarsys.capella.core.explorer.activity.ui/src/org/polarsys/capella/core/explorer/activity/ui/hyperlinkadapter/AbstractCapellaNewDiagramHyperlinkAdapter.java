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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractNewDiagramHyperlinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.tools.api.ui.RefreshEditorsPrecommitListener;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.tools.report.appenders.usage.UsageMonitoringLogger;
import org.polarsys.capella.common.tools.report.appenders.usage.util.UsageMonitoring.EventStatus;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.shared.id.handler.IdManager;

public abstract class AbstractCapellaNewDiagramHyperlinkAdapter extends AbstractNewDiagramHyperlinkAdapter {

  public AbstractCapellaNewDiagramHyperlinkAdapter() {
    super(ActivityExplorerManager.INSTANCE.getRootSemanticModel());
  }

  protected boolean useDefaultName() {
      return false;
  }
  
  @Override
  public void linkActivated(HyperlinkEvent event) {
    Session session = ActivityExplorerManager.INSTANCE.getSession();
    RefreshEditorsPrecommitListener repl = session.getRefreshEditorsListener();
    repl.notify(SessionListener.REPRESENTATION_CHANGE);
    repl.notify(SessionListener.SEMANTIC_CHANGE);

    IModel model = ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
    Project project = ((ICapellaModel) model).getProject(session.getTransactionalEditingDomain());

    String eventName = "Create Representation";
    String eventContext = getRepresentationName();
    EObject modelElement = getModelElement(project);
    String addendum = IdManager.getInstance().getId(modelElement);

    UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.NONE, addendum);
    linkPressed(event, modelElement, session);
    UsageMonitoringLogger.getInstance().log(eventName, eventContext, EventStatus.OK, addendum);
  }

  @Override
  public boolean createDiagram(EObject rootObj, Session session) {
    if (rootObj == null && session == null) {
      return false;
    }
    RepresentationDescription representationDescription = getDiagramRepresentation(session, rootObj);
    NewRepresentationAction action = initNewRepresentationAction(rootObj, session, representationDescription);
    RecordingCommand cmd = new RecordingCommand(TransactionUtil.getEditingDomain(rootObj)) {
      @Override
      protected void doExecute() {
        action.run();
      }
    };
    TransactionUtil.getEditingDomain(rootObj).getCommandStack().execute(cmd);
    return true;
  } 
  

  protected NewRepresentationAction initNewRepresentationAction(EObject rootObj, Session session,
      RepresentationDescription representationDescription) {
    return new NewRepresentationAction(representationDescription, rootObj, session, useDefaultName(), true);
  }
}
