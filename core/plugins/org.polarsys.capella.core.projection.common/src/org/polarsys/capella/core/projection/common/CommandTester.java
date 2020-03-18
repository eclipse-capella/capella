/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common;

import org.eclipse.core.expressions.PropertyTester;

import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class CommandTester extends PropertyTester {

  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
   */
  public boolean test(Object object, String propertyName, Object[] params, Object testedValue) {
    if (propertyName.equals("projectionMode") || propertyName.equals("graphicalProjectionMode")) { //$NON-NLS-1$ //$NON-NLS-2$
      ModelElement element = ModelAdaptation.adaptToCapella(object);

      if ((element != null) && (testedValue instanceof String)) {
        String value = (String) testedValue;

        if (value.startsWith("transition")) { //$NON-NLS-1$
          value = value.substring(10);

          if (value.equals("ES2ES-OASA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isES2ESForOASATransitionAvailable(element);

          } else if (value.equals("ES2ES-SALA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isES2ESForSALATransitionAvailable(element);

          } else if (value.equals("ES2ES-LAPA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isES2ESForLAPATransitionAvailable(element);

          } else if (value.equals("ES2IS")) { //$NON-NLS-1$
            return TransitionHelper.getService().isES2ISTransitionAvailable(element);

          } else if (value.equals("ESF2ESB")) { //$NON-NLS-1$
            return TransitionHelper.getService().isESF2ESBTransitionAvailable(element);

          } else if (value.equals("FS2ES-SALAPA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2ESForSALAPATransitionAvailable(element);

          } else if (value.equals("FS2ES-OASA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2ESForOASATransitionAvailable(element);

          } else if (value.equals("FS2FS-OASA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2FSForOASATransitionAvailable(element);

          } else if (value.equals("FS2FS-SALA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2FSForSALATransitionAvailable(element);

          } else if (value.equals("FS2FS-LAPA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2FSForLAPATransitionAvailable(element);

          } else if (value.equals("FC2FS")) {
            return element instanceof FunctionalChain && !(element instanceof OperationalProcess);
          
          } else if (value.equals("OP2OAS")) {
            return element instanceof OperationalProcess;
            
          } else if(value.equals("IS2IS-SALA")) {
            return TransitionHelper.getService().isIS2ISSALATransitionAvailable(element);
          
          } else if(value.equals("IS2IS-LAPA")) {
            return TransitionHelper.getService().isIS2ISLAPATransitionAvailable(element);
          
          } else if(value.equals("IS2IS-PAEPBS")) {
            return TransitionHelper.getService().isIS2ISPAEPBSTransitionAvailable(element);
          }

        } else if (value.startsWith("generate")) { //$NON-NLS-1$
          value = value.substring(8);

          if (value.equals("Interfaces")) { //$NON-NLS-1$
            return TransitionHelper.getService().isInterfaceGenerationAvailable(element);

          } else if (value.equals("CommunicationMeans")) { //$NON-NLS-1$
            return TransitionHelper.getService().isCommunicationMeansGenerationAvailable(element);

          } else if (value.equals("ComponentExchanges")) { //$NON-NLS-1$
            return TransitionHelper.getService().isComponentExchangesGenerationAvailable(element);

          } else if (value.equals("PhysicalLinks")) { //$NON-NLS-1$
            return TransitionHelper.getService().isPhysicalLinksGenerationAvailable(element);

          }

        }
      }
    }

    return false;
  }
}
