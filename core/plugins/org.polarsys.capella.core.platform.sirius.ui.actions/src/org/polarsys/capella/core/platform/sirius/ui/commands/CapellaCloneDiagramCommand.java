/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;

/**
 * A command thats clone specified representations.<br>
 * Warning, this command does not handle the transactional behavior.<br>
 * Thus it must be executed within a "calling" transaction.
 */
public class CapellaCloneDiagramCommand extends AbstractCommand {
  /**
   * The representations to clone.
   */
  private Collection<DRepresentation> _representations;
  /**
   * Cloned representations.
   */
  private Collection<DRepresentation> _clones;
  /**
   * Clone life cycle listeners.
   */
  private Collection<ICloneListener> _listeners;

  /**
   * Constructor.
   * @param representations_p
   */
  public CapellaCloneDiagramCommand(Collection<DRepresentation> representations_p) {
    super(Messages.CapellaCloneDiagramCommand_CommandLabel);
    _representations = representations_p;
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    if (null != _clones) {
      _clones.clear();
      _clones = null;
    }
    if (null != _listeners) {
      _listeners.clear();
      _listeners = null;
    }
    if (null != _representations) {
      _representations = null;
    }
  }

  /**
   * Add a clone life cycle listener.
   * @param listener_p
   */
  public void addCloneListener(ICloneListener listener_p) {
    if (null == listener_p) {
      return;
    }
    // Lazy allocation.
    if (null == _listeners) {
      _listeners = new HashSet<ICloneListener>(1);
    }
    // Add listener.
    _listeners.add(listener_p);
  }

  /**
   * Remove a registered clone life cycle listener.
   * @param listener_p
   */
  public void removeCloneListener(ICloneListener listener_p) {
    if ((null == _listeners) || (null == listener_p)) {
      return;
    }
    // Remove listener.
    _listeners.remove(listener_p);
  }

  /**
   * Send clone life cycle event.
   * @param type_p
   * @param clone_p
   * @param session_p
   */
  protected void notifyListeners(EventType type_p, DRepresentation clone_p, Session session_p) {
    if ((null == _listeners) || _listeners.isEmpty()) {
      return;
    }
    // Clone listeners collection.
    ArrayList<ICloneListener> listeners = new ArrayList<ICloneListener>(_listeners);
    // Call listeners.
    for (ICloneListener listener : listeners) {
      try {
        if (EventType.ADD.equals(type_p)) {
          listener.cloneCreated(clone_p, session_p);
        } else if (EventType.REMOVE.equals(type_p)) {
          listener.cloneAboutToBeRemoved(clone_p, session_p);
        }
      } catch (Exception exception_p) {
        CapellaActionsActivator activator = CapellaActionsActivator.getDefault();
        activator.getLog().log(new Status(IStatus.ERROR, activator.getPluginId(), "Unable to notify listeners !", exception_p)); //$NON-NLS-1$
      }
    }
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#canUndo()
   */
  @Override
  public boolean canUndo() {
    return (null != _clones) && (_clones.size() > 0);
  }

  /**
   * @see org.eclipse.emf.common.command.Command#execute()
   */
  public void execute() {
    // Initialize clones list.
    if (null == _clones) {
      _clones = new ArrayList<DRepresentation>(0);
    } else {
      // Ensure emptiness.
      if (_clones.size() > 0) {
        _clones.clear();
      }
    }
    // Copy all representations.
    for (DRepresentation representation : _representations) {
      if (representation instanceof DSemanticDecorator) {
        // Get target semantic element.
        EObject target = ((DSemanticDecorator) representation).getTarget();
        // Get session.
        Session session = SessionManager.INSTANCE.getSession(target);
        // Copy representation.
        DRepresentation copyRepresentation = DialectManager.INSTANCE.copyRepresentation(representation, getCloneName(representation, session), session, null);
        // Retain copied reference.
        _clones.add(copyRepresentation);
      } else {
        CapellaActionsActivator activator = CapellaActionsActivator.getDefault();
        activator.getLog().log(new Status(IStatus.WARNING, activator.getPluginId(), "Clone is not supported for " + representation.getName())); //$NON-NLS-1$
      }
    }
  }

  /**
   * Get clone name for specified representation.
   * @param representation_p
   * @return
   */
  protected String getCloneName(DRepresentation representation_p, Session session_p) {
    String message = Messages.CapellaCloneDiagramCommand_CloneName_Prefix;
    String cloneName = StringHelper.formatMessage(message, new Object[] { ICommonConstants.EMPTY_STRING, representation_p.getName() });
    boolean cloneNameFound = false;
    Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session_p);
    int i = 1;
    while (!cloneNameFound) {
      boolean collision = false;
      for (DRepresentation representation : allRepresentations) {
        if (cloneName.equals(representation.getName())) {
          collision = true;
          break;
        }
      }
      if (collision) {
        cloneName =
            StringHelper.formatMessage(message, new Object[] { ICommonConstants.EMPTY_STRING + ++i + ICommonConstants.WHITE_SPACE_CHARACTER,
                                                              representation_p.getName() });
      }
      cloneNameFound = !collision;
    }
    return cloneName;
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
   */
  @Override
  protected boolean prepare() {
    return true;
  }

  /**
   * @see org.eclipse.emf.common.command.Command#redo()
   */
  public void redo() {
    execute();
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#undo()
   */
  @Override
  public void undo() {
    // Delete all cloned representations.
    for (DRepresentation representation : _clones) {
      Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) representation).getTarget());
      DialectManager.INSTANCE.deleteRepresentation(representation, session);
    }
    // Clean clones collection.
    _clones.clear();
  }

  /**
   * Clone event type.
   */
  protected enum EventType {
    ADD, REMOVE
  }

  /**
   * Clone listener.
   */
  public interface ICloneListener {
    /**
     * Specified clone has just been added to specified session.
     * @param clone_p
     * @param session_p
     */
    void cloneCreated(DRepresentation clone_p, Session session_p);

    /**
     * Specified clone is about to be removed from specified session.
     * @param clone_p
     * @param session_p
     */
    void cloneAboutToBeRemoved(DRepresentation clone_p, Session session_p);
  }
}
