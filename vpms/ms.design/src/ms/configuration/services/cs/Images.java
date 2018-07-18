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
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;

public class Images {


  public static final String FUNCTION_OUTPUT_PORT = "ms.design/images/FunctionOutputPort.png";
  public static final String FUNCTION_INPUT_PORT = "ms.design/images/FunctionOutputPort.png";
  public static final String INFLOW_PORT = "ms.design/images/InFlowPort.png";
  public static final String OUTFLOW_PORT = "ms.design/images/OutFlowPort.png";
  public static final String FLOW_PORT = "ms.design/images/FlowPort.gif";
  public static final String STANDARD_PORT_SMALL = "ms.design/images/StandardPortSmall.png";

  public static String getImagePath(EObject e) {
    
    //
    // Until rotation is solved, use the same grey box icon for all ports.
    //
    
//    if (e instanceof ComponentPort) {
//    
//      if (PortExt.isInStrictFlowPort(e)) {
//        return INFLOW_PORT;
//      }
//    
//      if (PortExt.isOutStrictFlowPort(e)) {
//        return OUTFLOW_PORT;
//      }
//    
//      if (PortExt.isInoutStrictFlowPort(e)) {
//        return FLOW_PORT;
//      }
//      
//      return STANDARD_PORT_SMALL;
//      return FLOW_PORT;
//    }
//    
//    if (e instanceof FunctionOutputPort ) {
//      //return FUNCTION_OUTPUT_PORT;
//      return FLOW_PORT;
//    }
//    
//    if (e instanceof FunctionInputPort) {
//      //return FUNCTION_INPUT_PORT;
//      return FLOW_PORT;
//    }
//    
//    if (e instanceof PhysicalPort) {
//      //return STANDARD_PORT_SMALL;
//      return FLOW_PORT;
//    }

    return null;
    
  }
  
}
