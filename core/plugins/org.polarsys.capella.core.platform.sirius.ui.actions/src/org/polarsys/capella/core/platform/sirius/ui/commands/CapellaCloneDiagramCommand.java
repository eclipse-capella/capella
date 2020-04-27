/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
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
  private Collection<DRepresentationDescriptor> _descriptors = new ArrayList<>();

  /**
   * Cloned representations.
   */
  private Collection<DRepresentationDescriptor> _clones;

  /**
   * Clone life cycle listeners.
   */
  private Collection<ICloneListener> _listeners;

  /**
   * Constructor.
   * 
   * @param descriptors
   */
  public CapellaCloneDiagramCommand(Collection<DRepresentationDescriptor> descriptors) {
    super(Messages.CapellaCloneDiagramCommand_CommandLabel);
    _descriptors.addAll(descriptors);
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
    if (null != _descriptors) {
      _descriptors = null;
    }
  }

  /**
   * Add a clone life cycle listener.
   * 
   * @param listener
   */
  public void addCloneListener(ICloneListener listener) {
    if (null == listener) {
      return;
    }
    // Lazy allocation.
    if (null == _listeners) {
      _listeners = new HashSet<ICloneListener>(1);
    }
    // Add listener.
    _listeners.add(listener);
  }

  /**
   * Remove a registered clone life cycle listener.
   * 
   * @param listener
   */
  public void removeCloneListener(ICloneListener listener) {
    if ((null == _listeners) || (null == listener)) {
      return;
    }
    // Remove listener.
    _listeners.remove(listener);
  }

  /**
   * Send clone life cycle event.
   * 
   * @param type
   * @param clone
   * @param session
   */
  protected void notifyListeners(EventType type, DRepresentation clone, Session session) {
    if ((null == _listeners) || _listeners.isEmpty()) {
      return;
    }
    // Clone listeners collection.
    ArrayList<ICloneListener> listeners = new ArrayList<ICloneListener>(_listeners);
    // Call listeners.
    for (ICloneListener listener : listeners) {
      try {
        if (EventType.ADD.equals(type)) {
          listener.cloneCreated(clone, session);
        } else if (EventType.REMOVE.equals(type)) {
          listener.cloneAboutToBeRemoved(clone, session);
        }
      } catch (Exception exception) {
        CapellaActionsActivator activator = CapellaActionsActivator.getDefault();
        activator.getLog()
            .log(new Status(IStatus.ERROR, activator.getPluginId(), "Unable to notify listeners !", exception)); //$NON-NLS-1$
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
  @Override
  public void execute() {
    // Initialize clones list.
    if (null == _clones) {
      _clones = new ArrayList<DRepresentationDescriptor>(0);
    } else {
      // Ensure emptiness.
      if (_clones.size() > 0) {
        _clones.clear();
      }
    }
    // Copy all representations.
    for (DRepresentationDescriptor descriptor : _descriptors) {
      // Copy all the Dannotation of DRepresentationDescriptor
      Collection<DAnnotation> results = SiriusCopierHelper.copyAllWithNoUidDuplication(descriptor.getEAnnotations());

      // Get target semantic element.
      EObject target = descriptor.getTarget();
      // Get session.
      Session session = SessionManager.INSTANCE.getSession(target);
      // Copy representation.
      DRepresentation copyRepresentation = DialectManager.INSTANCE.copyRepresentation(descriptor,
          getCloneName(descriptor, session), session, null);
      DRepresentationDescriptor copyRepresentationDescriptor = RepresentationHelper.getRepresentationDescriptor(session,
          copyRepresentation);
      if (copyRepresentationDescriptor != null) {
        // put the list of copy Dannotation in the copied DRepresentationDescriptor
        copyRepresentationDescriptor.getEAnnotations().addAll(results);
        // Retain copied reference.
        _clones.add(copyRepresentationDescriptor);
      }
    }
  }

  /**
   * Get clone name for specified representation.
   * 
   * @param representation
   * @return
   */
  protected String getCloneName(DRepresentationDescriptor representation, Session session) {
    String message = Messages.CapellaCloneDiagramCommand_CloneName_Prefix;
    String cloneName = StringHelper.formatMessage(message,
        new Object[] { ICommonConstants.EMPTY_STRING, representation.getName() });
    boolean cloneNameFound = false;
    Collection<DRepresentationDescriptor> allDescriptors = DialectManager.INSTANCE
        .getAllRepresentationDescriptors(session);
    int i = 1;
    while (!cloneNameFound) {
      boolean collision = false;
      for (DRepresentationDescriptor rep : allDescriptors) {
        if (cloneName.equals(rep.getName())) {
          collision = true;
          break;
        }
      }
      if (collision) {
        cloneName = StringHelper.formatMessage(message, new Object[] {
            ICommonConstants.EMPTY_STRING + ++i + ICommonConstants.WHITE_SPACE_CHARACTER, representation.getName() });
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
  @Override
  public void redo() {
    execute();
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#undo()
   */
  @Override
  public void undo() {
    // Delete all cloned representations.
    for (DRepresentationDescriptor descriptor : _clones) {
      Session session = SessionManager.INSTANCE.getSession(descriptor.getTarget());
      DialectManager.INSTANCE.deleteRepresentation(descriptor, session);
    }
    // Clean clones collection.
    _clones.clear();
  }

  /**
   * Returns a list of clones DRepresentationDescriptor.
   * 
   * @return _clones.
   */
  @Override
  public Collection<DRepresentationDescriptor> getResult() {
    return _clones;
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
     * 
     * @param clone
     * @param session
     */
    void cloneCreated(DRepresentation clone, Session session);

    /**
     * Specified clone is about to be removed from specified session.
     * 
     * @param clone
     * @param session
     */
    void cloneAboutToBeRemoved(DRepresentation clone, Session session);
  }
}
