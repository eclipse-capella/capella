/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.OneLineBorder;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class StackCompartmentEditPart extends DNodeContainerViewNodeContainerCompartmentEditPart {

  public StackCompartmentEditPart(View view) {
    super(view);
  }

  protected void configureBorder(ResizableCompartmentFigure rcf) {
    super.configureBorder(rcf);

    // This allows to remove the line displayed on top of compartments if there is only one compatment
    if (rcf.getBorder() != null && rcf.getBorder() instanceof OneLineBorder) {
      OneLineBorder border = (OneLineBorder) rcf.getBorder();
      List<View> modelChildren = Lists.newArrayList(Iterables.filter(super.getModelChildren(), View.class));
      if (modelChildren != null && modelChildren.size() == 1) {
        border.setColor(rcf.getBackgroundColor());
      } else {
        border.setColor(null);
      }
    }
  }

}
