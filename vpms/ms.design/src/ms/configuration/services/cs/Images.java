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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.data.information.Port;

public class Images {


  public static final String FUNCTION_OUTPUT_PORT = "ms.design/images/FunctionOutputPort.png";
  public static final String FUNCTION_INPUT_PORT = "ms.design/images/FunctionOutputPort.png";
  public static final String INFLOW_PORT = "ms.design/images/InFlowPort.png";
  public static final String OUTFLOW_PORT = "ms.design/images/OutFlowPort.png";
  public static final String FLOW_PORT = "ms.design/images/FlowPort.gif";
  public static final String STANDARD_PORT_SMALL = "ms.design/images/StandardPortSmall.png";

  public static String getImagePath(EObject element, DDiagramElement view) {
    
//    String dname = view.getParentDiagram().getDescription().getName();
//
//    if (DiagramConstants.CDI_NAME.equals(dname)) {
//      return new ContextualComponentInterfacesImages().getImage(element, view);
//    }
//    
//    if (DiagramConstants.CEI_NAME.equals(dname)) {
//      return new ContextualComponentInterfacesImages().getImage(element, view);
//    }
//
//    if (element instanceof FunctionInputPort) {
//      return FUNCTION_INPUT_PORT;
//    }
//    
//    if (element instanceof FunctionOutputPort) {
//      return FUNCTION_OUTPUT_PORT;
//    }
    
    if (element instanceof Port) {
      return FLOW_PORT;
    }
    
    return null;

  }
  
}
