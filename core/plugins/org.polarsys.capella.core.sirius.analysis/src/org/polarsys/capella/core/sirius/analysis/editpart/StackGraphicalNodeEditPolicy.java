/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.editpart;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.RegionGraphicalNodeEditPolicy;

public class StackGraphicalNodeEditPolicy extends RegionGraphicalNodeEditPolicy {

  public Command getCommand(Request request) {

    EditPart host = getHost();
    try {
      setHost(host.getParent().getParent());
      if (request instanceof ReconnectRequest) {
        ReconnectRequest hRequest = (ReconnectRequest) request;
        if (host.equals(hRequest.getConnectionEditPart())) {
          hRequest.setConnectionEditPart((ConnectionEditPart) getHost());
        }
        if (host.equals(hRequest.getTarget())) {
          hRequest.setTargetEditPart(getHost());
        }
      }
      if (request instanceof CreateConnectionRequest) {
        CreateConnectionRequest hRequest = (CreateConnectionRequest) request;
        if (host.equals(hRequest.getSourceEditPart())) {
          hRequest.setSourceEditPart(getHost());
        }
        if (host.equals(hRequest.getTargetEditPart())) {
          hRequest.setTargetEditPart(getHost());
        }
      }
      return super.getCommand(request);
    } finally {
      setHost(host);
    }
  }

}
