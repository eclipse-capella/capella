/*******************************************************************************
 * Copyright (c)  2022 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

public class AbstractNewRepresentationCommand extends AbstractReadWriteCommand {
  protected String newName;
  protected DRepresentation representation;
  protected RepresentationDescription repDescription;
  protected Session currentSession;

  protected EObject eObject;

  /**
   * Constructs the command allowing to create a new representation.
   * 
   * @param newName
   *          The new representation name.
   * @param eObject
   *          The selected EObject.
   * @param repDescription
   *          The current representation description.
   * @param session
   *          The current session.
   */
  protected AbstractNewRepresentationCommand(String newName, EObject eObject, RepresentationDescription repDescription,
      Session session) {
    this.newName = newName;
    this.repDescription = repDescription;
    this.currentSession = session;
    this.eObject = eObject;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void commandInterrupted() {
    commandRolledBack();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void commandRolledBack() {
    representation = null;
  }

  /**
   * Gets the new representation.
   * 
   * @return The new representation.
   */
  public DRepresentation getRepresentation() {
    return representation;
  }

  @Override
  public void run() {
    // To be overriden by subclasses
  }

}