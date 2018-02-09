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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
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
   * Latest selection of representations to move.
   */
  private List<DRepresentationDescriptor> _repDescToMove;

  private Session _session;

  private Collection<Resource> _availableTargetResources;

  public void run(IAction action) {
    // Do nothing because this action is not run.
  }

  public void dispose() {
    if (null != _repDescToMove) {
      _repDescToMove.clear();
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
    _repDescToMove = new ArrayList<DRepresentationDescriptor>(0);
    _session = null;
    Iterator<?> iterator = selection.toList().iterator();
    while (iterator.hasNext() && enabled) {
      Object element = iterator.next();
      // Got a representation, store it.
      if (element instanceof ItemWrapper) {
        element = ((ItemWrapper) element).getWrappedObject();
      }
      if (element instanceof DRepresentationDescriptor) {
        // Add representation.
        _repDescToMove.add((DRepresentationDescriptor) element);
        if (null != _session) {
          enabled = SessionManager.INSTANCE.getSession(((DRepresentationDescriptor) element).getTarget())
              .equals(_session);
        } else {
          _session = SessionManager.INSTANCE.getSession(((DRepresentationDescriptor) element).getTarget());
          enabled = null != _session;
        }
      } else {
        // One selected is not a representation.
        enabled = false;
        break;
      }
    }
    if (enabled) {
      _availableTargetResources = new ArrayList<Resource>(((DAnalysisSession) _session).getAllSessionResources());
      Collection<Resource> representationResources = collectRepresentationResources(_repDescToMove);
      _availableTargetResources.removeAll(representationResources);
      // Check if a common target resource exist to move on.
      enabled = !_availableTargetResources.isEmpty();
    }
    // Return the action enablement state.
    return enabled;
  }

  /**
   * Collect the representations resources.
   * 
   * @param movableRepresentations
   * @return a not <code>null</code> collection.
   */
  private Collection<Resource> collectRepresentationResources(Collection<DRepresentationDescriptor> movableRepDescs) {
    Collection<Resource> result = new HashSet<Resource>();
    for (DRepresentationDescriptor repDesc : movableRepDescs) {
      result.add(repDesc.eResource());
    }
    return result;
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
        actions.add(createMoveRepresentationsActions((DAnalysisSession) _session, _repDescToMove, dAnalysis));
      }
    }
    return actions;
  }

  /**
   * @see {@link org.eclipse.sirius.ui.tools.internal.views.sessionview.DesignerSessionView#createMoveRepresentationsActions(DAnalysisSession, Collection, DAnalysis)}
   * @param session
   * @param repDescs
   * @param targetAnalysis
   * @return
   */
  private Action createMoveRepresentationsActions(final DAnalysisSession session,
      Collection<DRepresentationDescriptor> repDescs, final DAnalysis targetAnalysis) {
    ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID,
        "/icons/full/others/forward.gif"); //$NON-NLS-1$
    final Collection<DRepresentationDescriptor> movableRepDescs = new ArrayList<DRepresentationDescriptor>(repDescs);
    return new Action(Messages.MoveRepresentationAction_Title + targetAnalysis.eResource().getURI(), descriptor) {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @Override
      public void run() {
        TransactionHelper.getExecutionManager(movableRepDescs).execute(new AbstractReadWriteCommand() {
          /**
           * @see java.lang.Runnable#run()
           */
          @Override
          public void run() {
            final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            if (uiSession != null) {
              for (final DRepresentationDescriptor repDesc : movableRepDescs) {
                closeOpenedEditor(uiSession, repDesc);
              }
            }
            for (final DRepresentationDescriptor repDesc : movableRepDescs) {
              session.moveRepresentation(targetAnalysis, repDesc);
            }
          }

          private void closeOpenedEditor(final IEditingSession uiSession, final DRepresentationDescriptor repDesc) {
            if (repDesc.isLoadedRepresentation()) {
              final IEditorPart editor = uiSession.getEditor(repDesc.getRepresentation());
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
