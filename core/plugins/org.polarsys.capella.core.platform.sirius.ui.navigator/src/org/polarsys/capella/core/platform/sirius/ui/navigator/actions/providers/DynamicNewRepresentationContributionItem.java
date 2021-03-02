/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IIdentifier;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.menu.dynamic.DynamicActionProvider;
import org.polarsys.capella.core.menu.dynamic.DynamicCreationAction;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.NewScenarioRepresentationAction;

/**
 * A wrapper to the ContributionItem used to create elements on Capella Specific case: we use the serviceLocator to
 * retrieve selection made on the TreeViewer instead of global selection
 */
public class DynamicNewRepresentationContributionItem extends CompoundContributionItem
    implements IWorkbenchContribution {
  IServiceLocator locator = null;

  class NewRepresentationActionProvider extends DynamicActionProvider {
    private static final String CAPELLA_PROJECT_DIAGRAMS_MENU_ID = "capella.project.diagrams.menu";

    @Override
    protected void fillContextMenu(IMenuManager menu, DynamicCreationAction action, String groupId) {
      ISelectionService service = locator.getService(ISelectionService.class);
      ISelection selection = service.getSelection();
      if (selection instanceof IStructuredSelection) {
        createMenu(menu, (IStructuredSelection) selection);
      }
    }

    @Override
    protected IContributionItem createContributionItem() {
      IMenuManager subMenuManager = new MenuManager(
          Messages.NewRepresentationActionProvider_NewRepresentationAction_Title, CAPELLA_PROJECT_DIAGRAMS_MENU_ID);

      IActivityManager activityManager = PlatformUI.getWorkbench().getActivitySupport().getActivityManager();
      IIdentifier identifier = activityManager
          .getIdentifier(NewRepresentationActionProvider.CAPELLA_PROJECT_DIAGRAMS_MENU_ID);

      if (identifier.isEnabled()) {
        fillContextMenu(subMenuManager, dynamicAction, null);
      }

      return subMenuManager;
    }

    /**
     * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    private void createMenu(IMenuManager menu, IStructuredSelection selection) {

      IActivityManager activityManager = PlatformUI.getWorkbench().getActivitySupport().getActivityManager();
      IIdentifier identifier = activityManager.getIdentifier(CAPELLA_PROJECT_DIAGRAMS_MENU_ID);

      if (identifier.isEnabled()) {
        Object firstElement = selection.getFirstElement();

        if (firstElement instanceof EObject) {
          EObject firstSelectedEObject = (EObject) firstElement;
          Session currentSession = SessionManager.INSTANCE.getSession(firstSelectedEObject);

          if (null != currentSession) {
            IModel sessionModel = ILibraryManager.INSTANCE.getModel(TransactionHelper.getEditingDomain(currentSession));
            IModel currentElementModel = ILibraryManager.INSTANCE.getModel(firstSelectedEObject);

            if (sessionModel == null || sessionModel.equals(currentElementModel)) {
              createNewDiagramMenu(firstSelectedEObject, menu, currentSession);
            }
          }
        }
      }
    }

    private void createNewDiagramMenu(EObject firstSelectedEObject, IMenuManager menu, Session currentSession) {
      Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);

      Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE
          .getAvailableRepresentationDescriptions(selectedViewpoints, firstSelectedEObject);

      if (!descriptions.isEmpty()) {
        // Computes the "New Diagram..." menu content according to the current selection.
        for (RepresentationDescription description : descriptions) {
          menu.add(new NewRepresentationAction(description, firstSelectedEObject, currentSession));
        }

        // Create scenarios from capabilities
        if (firstSelectedEObject instanceof AbstractCapability) {
          addScenariosFromCapabilitiesMenuEntries((AbstractCapability) firstSelectedEObject, menu, currentSession,
              selectedViewpoints);
        }
      }
    }

    private void addScenariosFromCapabilitiesMenuEntries(AbstractCapability capa, IMenuManager menu,
        Session currentSession, Collection<Viewpoint> selectedViewpoints) {
      for (Viewpoint vp : selectedViewpoints) {
        for (RepresentationDescription representationDescription : vp.getOwnedRepresentations()) {
          if (representationDescription instanceof SequenceDiagramDescription) {
            SequenceDiagramDescription sdd = (SequenceDiagramDescription) representationDescription;
            String precondition = sdd.getPreconditionExpression();
            try {
              if (InterpreterUtil.getInterpreter(capa).evaluateBoolean(capa, precondition)) {
                menu.add(new NewScenarioRepresentationAction(sdd, capa, currentSession));
              }
            } catch (EvaluationException e) {
              // Catch exception silently
              e.printStackTrace();
            }
          }
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initialize(IServiceLocator serviceLocator_p) {
    locator = serviceLocator_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContributionItem[] getContributionItems() {
    IContributionItem item = new NewRepresentationActionProvider().createContributionItem();
    return new IContributionItem[] { item };
  }
}
