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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.representation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * Move a representation between airds owned by the same session.
 */
public class MoveRepresentationAction extends BaseSelectionListenerAction {
  /**
   * Menu manager id created to contain available move actions.
   */
  public static final String MOVE_DIAGRAMS_MENU_ID = "MoveDiagrams.Menu.ID"; //$NON-NLS-1$

  /**
   * Constructor.
   */
  public MoveRepresentationAction() {
    super(ICommonConstants.EMPTY_STRING); // This action is not displayed.
  }

  /**
   * Latest selection of representation descriptors to move.
   */
  private Collection<DRepresentationDescriptor> _descriptorsToMove;
  
  private Session _session;
  
  private Collection<Resource> _availableTargetResources;

  public void run(IAction action) {
    // Do nothing because this action is not run.
  }

  public void dispose() {
    if (null != _descriptorsToMove) {
      _descriptorsToMove.clear();
    }
    if (null != _availableTargetResources) {
      _availableTargetResources.clear();
    }
    _session = null;
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public boolean updateSelection(IStructuredSelection selection) {
    if (selection.isEmpty()) {
      return false;
    }
    boolean enabled = true;
    // Clean previous execution.
    _descriptorsToMove = new ArrayList<DRepresentationDescriptor>(0);
    _session = null;

    List<?> selectionList = ((IStructuredSelection) selection).toList();

    // Retrieve descriptors from selection
    Collection<DRepresentationDescriptor> descriptors = RepresentationHelper.getSelectedDescriptors(selectionList);

    // Retrieve given sessions. If they belongs to the same session, we continue
    List<Session> sessions = descriptors.stream().map(descriptor -> descriptor.getTarget())
        .map(t -> SessionManager.INSTANCE.getSession(t)).distinct().filter(s -> s != null).collect(Collectors.toList());

    enabled = false;

    // If all selected elements were EObjects, retrieve editing domain if elements are from the same session
    if (selectionList.size() == descriptors.size() && sessions.size() == 1) {
      _session = sessions.get(0);
      _descriptorsToMove = descriptors;
      enabled = true;
    }

    if (enabled) {

      _availableTargetResources = new ArrayList<Resource>(((DAnalysisSession) _session).getAllSessionResources());

      // Retrieve resources of descriptors
      Collection<Resource> representationResources = _descriptorsToMove.stream().map(d -> d.eResource()).distinct()
          .collect(Collectors.toList());

      _availableTargetResources.removeAll(representationResources);

      // Check if a common target resource exist to move on.
      enabled = !_availableTargetResources.isEmpty();
    }
    // Return the action enablement state.
    return enabled;
  }

  /**
   * Get all available move actions.
   * 
   * @return a not <code>null</code> collection.
   */
  public Collection<IAction> getMoveActions() {
    ArrayList<IAction> actions = new ArrayList<IAction>(0);
    for (final Resource availableTargetResource : _availableTargetResources) {
      Collection<DAnalysis> availableDAnalysys = EcoreUtil.getObjectsByType(availableTargetResource.getContents(),
          ViewpointPackage.eINSTANCE.getDAnalysis());
      for (DAnalysis dAnalysis : availableDAnalysys) {
        actions.add(createMoveRepresentationsActions((DAnalysisSession) _session, _descriptorsToMove, dAnalysis));
      }
    }
    return actions;
  }

  /**
   * @see {@link org.eclipse.sirius.ui.tools.internal.views.sessionview.DesignerSessionView#createMoveRepresentationsActions(DAnalysisSession, Collection, DAnalysis)}
   * @param session
   * @param descriptors
   * @param targetAnalysis
   * @return
   */
  private Action createMoveRepresentationsActions(final DAnalysisSession session,
      Collection<DRepresentationDescriptor> descriptors, final DAnalysis targetAnalysis) {
    ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID,
        "/icons/full/others/forward.gif"); //$NON-NLS-1$
    
    return new Action(Messages.MoveRepresentationAction_Title + targetAnalysis.eResource().getURI(), descriptor) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        TransactionHelper.getExecutionManager(descriptors).execute(new AbstractReadWriteCommand() {
          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            if (uiSession != null) {
              for (DRepresentationDescriptor descriptor : descriptors) {
                closeOpenedEditor(uiSession, descriptor);
              }
            }
            for (DRepresentationDescriptor descriptor : descriptors) {
              session.moveRepresentation(targetAnalysis, descriptor);
            }
          }

          private void closeOpenedEditor(IEditingSession uiSession, DRepresentationDescriptor descriptor) {
            if (descriptor.isLoadedRepresentation()) {
              IEditorPart editor = uiSession.getEditor(descriptor.getRepresentation());
              if (editor != null) {
                editor.getEditorSite().getPage().closeEditor(editor, false);
              }
            }
          }
        });
      }
    };
  }

  /**
   * Fill context sub menu for move diagrams action.
   * 
   * @param structuredSelection
   * @return a sub menu manager with all real move actions.
   */
  public IMenuManager fillContextMenu(IStructuredSelection structuredSelection) {
    IMenuManager subMenuManager = new MenuManager(Messages.RepresentationActionProvider_MovediagramSubMenu_Title,
        MOVE_DIAGRAMS_MENU_ID);
    updateSelection(structuredSelection);
    // In this case, check really if the action is compatible with current selection.
    if (isEnabled()) {
      for (IAction containedAction : getMoveActions()) {
        addAction(subMenuManager, null, containedAction);
      }
    }
    return subMenuManager;
  }

  /**
   * Add a dynamic action
   * 
   * @param menu
   * @param groupId
   * @param action
   */
  protected void addAction(IMenuManager menu, String groupId, IAction action) {
    // Override the action contribution item to force the context menu to be
    // refreshed even if the selected object has not changed.
    ActionContributionItem item = new ActionContributionItem(action) {
      @Override
      public boolean isDirty() {
        return true;
      }

      @Override
      public boolean isDynamic() {
        return true;
      }
    };
    // Append the action to a group if provided.
    if (null != groupId) {
      menu.appendToGroup(groupId, item);
    } else {
      menu.add(item);
    }
  }
}
