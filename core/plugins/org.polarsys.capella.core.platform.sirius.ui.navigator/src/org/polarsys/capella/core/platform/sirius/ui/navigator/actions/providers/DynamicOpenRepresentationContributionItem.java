/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.description.DiagramDescription;
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
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionUse;
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
          createOpenDiagramMenu(firstSelectedEObject, menu, currentSession);
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
      }

      // create scenarios from capabilities
      if (hasDerivedOpenRepresentationContribution(firstSelectedEObject)) {
          addDerivedOpenRepresentationActions(firstSelectedEObject, menu, currentSession,
            selectedViewpoints);
      }
    }
    
    private boolean hasDerivedOpenRepresentationContribution(EObject semanticElement) {
        return (semanticElement instanceof AbstractCapability) || 
                (semanticElement instanceof FunctionalChainReference) || 
                (semanticElement instanceof PhysicalPathReference) ||
                (semanticElement instanceof InteractionUse);
    }
    
    private void addDerivedOpenRepresentationActions(EObject semanticElement, IMenuManager menu, Session currentSession, Collection<Viewpoint> selectedViewpoints) {
        for (Viewpoint vp : selectedViewpoints) {
            for (RepresentationDescription representationDescription : vp.getOwnedRepresentations()) {
                Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE.getRepresentationDescriptors(representationDescription, currentSession);
                if (descriptors != null) {
                    createOpenRepresentationAction(semanticElement, menu, currentSession, representationDescription, descriptors);
                }
            }
        }
    }

    private void createOpenRepresentationAction(EObject semanticElement, IMenuManager menu, Session currentSession, RepresentationDescription representationDescription,
            Collection<DRepresentationDescriptor> descriptors) {
        Collection<DRepresentationDescriptor> ownedDescriptors = new ArrayList<>();
        List<EObject> derivedElements = new ArrayList<>();
        if (semanticElement instanceof AbstractCapability && representationDescription instanceof SequenceDiagramDescription) {
            derivedElements.addAll(((AbstractCapability) semanticElement).getOwnedScenarios());
        } else if ((semanticElement instanceof FunctionalChainReference || semanticElement instanceof PhysicalPathReference) && representationDescription instanceof DiagramDescription) {
            if (semanticElement instanceof FunctionalChainReference) {
                derivedElements.add(((FunctionalChainReference) semanticElement).getReferencedFunctionalChain());
            } else if (semanticElement instanceof PhysicalPathReference) {
                derivedElements.add(((PhysicalPathReference) semanticElement).getReferencedPhysicalPath());
            }
        } else if (semanticElement instanceof InteractionUse && representationDescription instanceof SequenceDiagramDescription) {
            derivedElements.add(((InteractionUse)semanticElement).getReferencedScenario());
        }
        for (EObject object : derivedElements) {
            Collection<DRepresentationDescriptor> repDescScenario = DialectManager.INSTANCE.getRepresentationDescriptors(object, currentSession);
            ownedDescriptors.addAll(repDescScenario);
        }
        descriptors.retainAll(ownedDescriptors);

        for (DRepresentationDescriptor descriptor : descriptors) {
            menu.add(new OpenRepresentationsAction(descriptor));
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
