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
package org.polarsys.capella.core.projection.common;

import java.util.List;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;


/**
 */
public class LcToPcProjectionUtils {

  public enum ProjectionStrategy {
    LEAF, BREAKDOWN, NONE
  }
  
  public static final String BREAK_DOWN_PROJECTION_STRATEGY = "BreakDown Strategy"; //$NON-NLS-1$
  public static final String LEAF_PROJECTION_STRATEGY = "Leaf Strategy"; //$NON-NLS-1$

  /**
   * Return true if exist clone in Pa Layer for the Interface given in parameter
   */
  public static boolean existInterfaceCloneInPA(Interface srcItf_p) {
    List<AbstractTrace> traceList = srcItf_p.getIncomingTraces();
    for (AbstractTrace trace : traceList) {
      if ((trace instanceof ContextInterfaceRealization || trace instanceof LogicalInterfaceRealization)
        && trace.getSourceElement() instanceof Interface)
      {
        if (CapellaLayerCheckingExt.isInPhysicalLayer((Interface) trace.getSourceElement()))
          return true;
      }
    }

    return false;
  }

  /**
   * Return Interface clone in Pa Layer from the Interface source given in parameter
   */
  public static Interface retrieveTransformedInterfaceInPA(Interface srcItf_p) {
    List<AbstractTrace> traceList = srcItf_p.getIncomingTraces();
    for (AbstractTrace trace : traceList) {
      if ((trace instanceof ContextInterfaceRealization || trace instanceof LogicalInterfaceRealization)
        && trace.getSourceElement() instanceof Interface)
      {
        if (CapellaLayerCheckingExt.isInPhysicalLayer((Interface) trace.getSourceElement()))
          return (Interface) trace.getSourceElement();
      }
    }
    return null;
  }

  public static boolean isBreakDownModel(PhysicalArchitecture pa_p) {
    List<PhysicalComponent> allPc = PhysicalArchitectureExt.getAllPhysicalComponents(pa_p);
    for (PhysicalComponent pc : allPc) {
      for (Partition partition : pc.getOwnedPartitions()) {
        if (partition instanceof Part) {
          List<AbstractTrace> traceList = partition.getOutgoingTraces();
          for (AbstractTrace abstractTrace : traceList) {
            if (abstractTrace instanceof GenericTrace) {
              GenericTrace genericTrace = (GenericTrace) abstractTrace;
              for (KeyValue keyValue : genericTrace.getKeyValuePairs()) {
                String key = keyValue.getKey();
                String value = keyValue.getValue();
                if (key.equals(LcToPcProjectionUtils.BREAK_DOWN_PROJECTION_STRATEGY) && value.equals(Boolean.TRUE.toString())) {
                  return true;
                }
              }
            }
          }
        }
      }
    }
    return false;
  }

  public static boolean isLeafModel(PhysicalArchitecture pa_p) {
    List<PhysicalComponent> allPc = PhysicalArchitectureExt.getAllPhysicalComponents(pa_p);
    PhysicalComponent pcRoot = null;

    for (PhysicalComponent pc : allPc) {
      if (PhysicalComponentExt.isPhysicalComponentRoot(pc)) {
        pcRoot = pc;
        break;
      }
    }
    if (null != pcRoot) {
      for (PhysicalComponent pc : allPc) {
        if (leafPartExist(pcRoot, pc))
          return true;
      }
    }
    return false;
  }

  public static boolean leafPartExist(PhysicalComponent pcRoot, PhysicalComponent pcCurrent) {
    for (AbstractTrace abstractTrace : pcCurrent.getOutgoingTraces()) {
      if (abstractTrace instanceof GenericTrace && abstractTrace.getTargetElement() instanceof Part) {
        Part partLinked = (Part) abstractTrace.getTargetElement();
        if (partLinked.eContainer().equals(pcRoot)) {
          for (KeyValue keyValue : ((GenericTrace) abstractTrace).getKeyValuePairs()) {
            String key = keyValue.getKey();
            String value = keyValue.getValue();
            if (key.equals(LcToPcProjectionUtils.LEAF_PROJECTION_STRATEGY) && value.equals(Boolean.TRUE.toString()))
              return true;
          }
        }
      }
    }
    return false;
  }

  
  /**
   * Return the projection strategy used from LA toward PA layer 3 possible cases : NONE | BREAKDOWN | LEAF NONE
   * Check specific flag on 'RefinementLink' between 'Part' 
   */
  public static ProjectionStrategy getModelProjectionStrategy(ModelElement modelElement_p) {
    LogicalArchitecture la = null;
    if (modelElement_p instanceof LogicalArchitecture)
      la = (LogicalArchitecture) modelElement_p;
    else
      la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(modelElement_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);

    for (AbstractTrace trace : la.getIncomingTraces()) {
      if ((trace instanceof LogicalArchitectureRealization) && (trace.getSourceElement() instanceof PhysicalArchitecture)) {
        PhysicalArchitecture pa = (PhysicalArchitecture) trace.getSourceElement();
        if (null != pa) {
          if (LcToPcProjectionUtils.isBreakDownModel(pa))
            return ProjectionStrategy.BREAKDOWN;
          else if (LcToPcProjectionUtils.isLeafModel(pa))
            return ProjectionStrategy.LEAF;
        }
      }
    }
    return ProjectionStrategy.NONE;
  }
}
