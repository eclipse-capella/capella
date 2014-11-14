/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.actors.oa2ctx.rules.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;

/**
 *
 */
public class CommunicationMeanHelper {
  public static List<EObject> getMeansToProject (Entity entity_p, ITransfo transfo_p) {
    List<EObject> result = new ArrayList<EObject>();
    
    for (AbstractInformationFlow iflow : entity_p.getInformationFlows()) {
      InformationsExchanger src = iflow.getSource();
      InformationsExchanger tgt = iflow.getTarget();
      
      @SuppressWarnings("unused")
      Object src2 = Query.retrieveTransformedElement(src, transfo_p);
      @SuppressWarnings("unused")
      Object tgt2 = Query.retrieveTransformedElement(tgt, transfo_p);
    }
    return result;
  }
}
