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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.SiriusCopier;
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
public class CapellaCloneDiagramCommand extends RecordingCommand {
  /**
   * The representations to clone.
   */
  private Collection<DRepresentationDescriptor> _descriptors = new ArrayList<>();

  /**
   * Constructor.
   * 
   * @param descriptors
   */
  public CapellaCloneDiagramCommand(TransactionalEditingDomain domain,Collection<DRepresentationDescriptor> descriptors) {
    super(domain, Messages.CapellaCloneDiagramCommand_CommandLabel);
    _descriptors.addAll(descriptors);
  }

  /**
   * @see org.eclipse.emf.common.command.Command#execute()
   */
  @Override
  public void doExecute() {
    // Copy all representations.
    for (DRepresentationDescriptor descriptor : _descriptors) {
      // Copy all the Dannotation of DRepresentationDescriptor
      SiriusCopier copier = new SiriusCopier();
      Collection<DAnnotation> results = copier.copyAll(descriptor.getEAnnotations());
      copier.copyReferences();

      DRepresentation representation = descriptor.getRepresentation();
      if (representation instanceof DSemanticDecorator) {
        // Get target semantic element.
        EObject target = ((DSemanticDecorator) representation).getTarget();
        // Get session.
        Session session = SessionManager.INSTANCE.getSession(target);
        // Copy representation.
        DRepresentation copyRepresentation = DialectManager.INSTANCE.copyRepresentation(representation,
            getCloneName(representation, session), session, null);
        DRepresentationDescriptor copyRepresentationDescriptor = RepresentationHelper
            .getRepresentationDescriptor(session, copyRepresentation);
        if (copyRepresentationDescriptor != null) {
          // put the list of copy Dannotation in the copied DRepresentationDescriptor
          copyRepresentationDescriptor.getEAnnotations().addAll(results);
        }
      } else {
        CapellaActionsActivator activator = CapellaActionsActivator.getDefault();
        activator.getLog().log(new Status(IStatus.WARNING, activator.getPluginId(),
            "Clone is not supported for " + representation.getName())); //$NON-NLS-1$
      }
    }
  }

  /**
   * Get clone name for specified representation.
   * 
   * @param representation
   * @return
   */
  protected String getCloneName(DRepresentation representation, Session session) {
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

}
