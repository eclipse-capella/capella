/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package ms.configuration.services.cs;

import java.util.IllegalFormatException;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.OrientationPortKind;

public class ContextualComponentInterfacesImages {

  public String getImage(EObject e, DDiagramElement view) {
    if (e instanceof ComponentPort) {
      return getComponentPortImage((ComponentPort) e, view);
    }
    return null;
  }
  
  public String getComponentPortImage(ComponentPort pc, DDiagramElement view) {

    String result = "/ms.design/images/StandardPortSmall.png";
    
    if (pc.getRequiredInterfaces().size() > 0 && pc.getProvidedInterfaces().size() > 0 && pc.getKind() == ComponentPortKind.STANDARD) { 
     result = "/ms.design/images/StandardPort_providedrequired_%s.png";
    }

    else if (pc.getRequiredInterfaces().size()>0 && pc.getProvidedInterfaces().size()==0 && pc.getKind()==ComponentPortKind.STANDARD) {
      result = "/ms.design/images/StandardPort_required_%s.png";
    }
    
    else if (pc.getRequiredInterfaces().size()==0 && pc.getProvidedInterfaces().size()>0 && pc.getKind() ==ComponentPortKind.STANDARD) {
      result = "/ms.design/images/StandardPort_provided_%s.png";
    }
    
    else if (pc.getKind() ==ComponentPortKind.FLOW && pc.getOrientation() == OrientationPortKind.IN) {
      result = "/ms.design/images/InFlowPort.png";
    }

    else if (pc.getKind() == ComponentPortKind.FLOW &&  pc.getOrientation() == OrientationPortKind.OUT) {
      result = "/ms.design/images/OutFlowPort.png";
    }
    
    else if (pc.getKind() == ComponentPortKind.FLOW && pc.getOrientation() == OrientationPortKind.INOUT) {
      result = "/ms.design/images/FlowPort.png";
    }

    return rotate(result, view);

  }

  private String rotate(String result, DDiagramElement view) {

    Bounds bounds = getBounds(view);
    Bounds containerBounds = getBounds((DDiagramElement) view.eContainer());
    
    if (bounds != null && containerBounds != null) {
      Rectangle rectangle = new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
      Rectangle containerRectangle = new Rectangle(containerBounds.getX(), containerBounds.getY(), containerBounds.getWidth(), containerBounds.getHeight());
      int side = DBorderItemLocator.findClosestSideOfParent(containerRectangle, rectangle);
      
      // the returned value is exactly the opposite of what's needed:
      String suffix = "";
      switch (side) {
      case PositionConstants.WEST: suffix = "east"; break;
      case PositionConstants.EAST: suffix = "west"; break;
      case PositionConstants.NORTH: suffix = "south"; break;
      case PositionConstants.SOUTH: suffix = "north"; break;
      }
      
      try {
        result = String.format(result, suffix);
      } catch (IllegalFormatException e) {
        /* swallow */
      }
    }

    return result;
  }
  
  private Bounds getBounds(DDiagramElement view) {

    EObjectQuery eq = new EObjectQuery(view);
    for (EObject e : eq.getInverseReferences(NotationPackage.Literals.VIEW__ELEMENT)) {
      View gmfView = (View) e;
      if (gmfView instanceof Node) {
        if (((Node) gmfView).getLayoutConstraint() instanceof Bounds) {
          return (Bounds) ((Node) gmfView).getLayoutConstraint();
        }
      }
    }
    return null;
    
  }
  
  
  
  
}
