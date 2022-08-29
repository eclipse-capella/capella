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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IIdentifier;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.menu.dynamic.DynamicActionProvider;
import org.polarsys.capella.core.menu.dynamic.DynamicCreationAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;

/**
 * A wrapper to the ContributionItem used to create elements on Capella Specific case: we use the serviceLocator to
 * retrieve selection made on the TreeViewer instead of global selection
 */
public class DynamicOpenRepresentationContributionItem extends CompoundContributionItem
    implements IWorkbenchContribution {
  IServiceLocator locator = null;

  class OpenRepresentationActionProvider extends DynamicActionProvider {
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
          Messages.OpenRepresentationActionProvider_OpenRepresentationAction_Title, CAPELLA_PROJECT_DIAGRAMS_MENU_ID);

      IActivityManager activityManager = PlatformUI.getWorkbench().getActivitySupport().getActivityManager();
      IIdentifier identifier = activityManager.getIdentifier(CAPELLA_PROJECT_DIAGRAMS_MENU_ID);

      if (identifier.isEnabled()) {
        fillContextMenu(subMenuManager, dynamicAction, null);
      }

      return subMenuManager;
    }

    /**
     * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    private void createMenu(IMenuManager menu, IStructuredSelection selection) {
      Object firstElement = selection.getFirstElement();
      if (firstElement instanceof EObject) {
        EObject firstSelectedEObject = (EObject) firstElement;
        Session currentSession = SessionManager.INSTANCE.getSession(firstSelectedEObject);

        if (currentSession != null) {
          if (firstSelectedEObject instanceof FunctionalChainReference) {
              FunctionalChain fc = ((FunctionalChainReference)firstSelectedEObject).getReferencedFunctionalChain();
              createOpenDiagramMenu(fc, menu, currentSession);
          } else if (firstSelectedEObject instanceof PhysicalPathReference) {
              PhysicalPath pp = ((PhysicalPathReference)firstSelectedEObject).getReferencedPhysicalPath();
              createOpenDiagramMenu(pp, menu, currentSession);
          } else {
              createOpenDiagramMenu(firstSelectedEObject, menu, currentSession);
          }
        }
      }
    }

    private void createOpenDiagramMenu(EObject firstSelectedEObject, IMenuManager menu, Session currentSession) {
      Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);
      
      Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE
          .getAvailableRepresentationDescriptions(selectedViewpoints, firstSelectedEObject);

      if (!descriptions.isEmpty()) {
        // Creates the "New Diagram / Table" menu.
        for (RepresentationDescription description : descriptions) {
          // Computes the "Open Diagram / Table..." menu content according to the current selection.
          Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE
              .getRepresentationDescriptors(description, currentSession);
          if (descriptors != null) {
            Collection<DRepresentationDescriptor> ownedRep = DialectManager.INSTANCE
                .getRepresentationDescriptors(firstSelectedEObject, currentSession);
            descriptors.retainAll(ownedRep);
            for (DRepresentationDescriptor descriptor : descriptors) {
              menu.add(new OpenRepresentationsAction(descriptor));
            }
          }
        }

        // create scenarios from capabilities
        if (firstSelectedEObject instanceof AbstractCapability) {
          addScenariosFromCapabilitiesMenuEntries((AbstractCapability) firstSelectedEObject, menu, currentSession,
              selectedViewpoints);
        }
      }
    }
  }

  private void addScenariosFromCapabilitiesMenuEntries(AbstractCapability capa, IMenuManager menu,
      Session currentSession, Collection<Viewpoint> selectedViewpoints) {
    for (Viewpoint vp : selectedViewpoints) {
      for (RepresentationDescription representationDescription : vp.getOwnedRepresentations()) {
        if (representationDescription instanceof SequenceDiagramDescription) {
          SequenceDiagramDescription sdd = (SequenceDiagramDescription) representationDescription;
          Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE.getRepresentationDescriptors(sdd,
              currentSession);
          if (descriptors != null) {
            // Select only scenarios belonging to the Capability
            Collection<DRepresentationDescriptor> ownedDescriptors = new ArrayList<>();
            EList<Scenario> lstScenarios = capa.getOwnedScenarios();
            for (Scenario scenario : lstScenarios) {
              Collection<DRepresentationDescriptor> repDescScenario = DialectManager.INSTANCE
                  .getRepresentationDescriptors(scenario, currentSession);
              ownedDescriptors.addAll(repDescScenario);
            }

            descriptors.retainAll(ownedDescriptors);

            for (DRepresentationDescriptor descriptor : descriptors) {
              menu.add(new OpenRepresentationsAction(descriptor));
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
    IContributionItem item = new OpenRepresentationActionProvider().createContributionItem();
    return new IContributionItem[] { item };
  }
}
