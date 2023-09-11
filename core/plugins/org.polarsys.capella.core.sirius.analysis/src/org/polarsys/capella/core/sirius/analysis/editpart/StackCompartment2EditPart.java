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

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.AbstractLayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.RootLayoutData;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.ext.base.Option;
import org.polarsys.capella.core.data.capellacommon.Region;

public class StackCompartment2EditPart extends DNodeContainerViewNodeContainerCompartment2EditPart {

  public StackCompartment2EditPart(View view) {
    super(view);
  }

  protected class ModeFromRegionLayoutData extends Command {

    @Override
    public void execute() {
      super.execute();

      try {
        // When we create a port inside a region, the location is stored according to the region.
        // We also add a layout according to the owning Mode
        Option<AbstractLayoutData> result = SiriusLayoutDataManager.INSTANCE.getData();
        if (result.some() && result.get() instanceof RootLayoutData) {
          RootLayoutData data = (RootLayoutData) result.get();
          if (data.getTarget() instanceof DNodeContainer
              && ((DNodeContainer) data.getTarget()).getTarget() instanceof Region) {
            Point parentOrigin = StackCompartment2EditPart.this.getFigure().getBounds().getTopLeft().getCopy();
            RootLayoutData rData = new RootLayoutData(StackCompartment2EditPart.this.getParent().getParent(),
                new Point(data.getLocation().x + parentOrigin.x, data.getLocation().y + parentOrigin.y),
                data.getSize());
            SiriusLayoutDataManager.INSTANCE.addData(rData);
          }
        }
      } catch (Exception e) {
        // We can't add a layout for the region, entry point will be created on top-left
      }

    }
  }

  @Override
  public Command getCommand(Request request) {
    if (REQ_CREATE.equals(request.getType())) {
      Command command = super.getCommand(request);
      if (command instanceof CompoundCommand) {
        CompoundCommand ccommand = ((CompoundCommand) command);
        if (ccommand.size() > 0) {
          // We add a command after the Create Command
          List commands = ccommand.getCommands();
          commands.add(ccommand.size() - 1, new ModeFromRegionLayoutData());
        }
      }
      return command;
    }
    return super.getCommand(request);
  }

}
