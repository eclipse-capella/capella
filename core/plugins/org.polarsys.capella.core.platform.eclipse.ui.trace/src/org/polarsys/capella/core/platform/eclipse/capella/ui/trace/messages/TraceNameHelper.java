/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.requirement.RequirementFactory;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * TODO To be removed when a good operating is reached for the name of the trace which will be human readable (in the metamodel level), for the evolution of this class.
 */
public class TraceNameHelper {

  /**
   * <code>getTraceNameFromClass</code> return a readable name for a given concrete class of {@link org.polarsys.capella.core.common.model.Trace}
   * @param clazz_p Class of a concrete Trace class
   * @return a human readable name
   */
  @SuppressWarnings("nls")
  public static String getTraceNameFromClass(Class<? extends AbstractTrace> clazz_p) {
    String result = null;
    // Don't use *Impl (it will not work with Capella Team).
    if (GenericTrace.class.isAssignableFrom(clazz_p)) {
      result = Messages.getString("TraceType.name.generic");
    } else if (MergeLink.class.isAssignableFrom(clazz_p)) {
      result = Messages.getString("TraceType.name.merge");
    } else if (RefinementLink.class.isAssignableFrom(clazz_p)) {
      result = Messages.getString("TraceType.name.refinement");
    } else if (RequirementsTrace.class.isAssignableFrom(clazz_p)) {
      result = Messages.getString("TraceType.name.requirement");
    } else {
      result = Messages.getString("TraceType.name.undefined");
    }

    return result;
  }


  /**
   * <code>getNewTraceInstanceFromTraceName</code> return a new Trace instance for a given readable name class
   * @param className_p human readable name of trace class
   * @return new Trace instance for a given readable name clas
   */
  @SuppressWarnings("nls")
  public static Trace getNewTraceInstanceFromTraceName(String className_p) {
    Trace result = null;

    if (className_p.equals(Messages.getString("TraceType.name.generic"))) {
      result = CapellacommonFactory.eINSTANCE.createGenericTrace();
    } else if (className_p.equals(Messages.getString("TraceType.name.merge"))) {
      result = InteractionFactory.eINSTANCE.createMergeLink();
    } else if (className_p.equals(Messages.getString("TraceType.name.refinement"))) {
      result = InteractionFactory.eINSTANCE.createRefinementLink();
    } else if (className_p.equals(Messages.getString("TraceType.name.requirement"))) {
      result = RequirementFactory.eINSTANCE.createRequirementsTrace();
    }

    return result;
  }

  /**
   * <code>getTraceTypes</code>
   * @return list of readable trace types
   */
  @SuppressWarnings("nls")
  public static List<String> getTraceTypes() {
    List<String> result = new ArrayList<String>();
    result.add(Messages.getString("TraceType.name.generic"));
    result.add(Messages.getString("TraceType.name.merge"));
    result.add(Messages.getString("TraceType.name.refinement"));
    result.add(Messages.getString("TraceType.name.requirement"));
    return result;
  }

  /**
   * <code>getTraceTypes</code>
   * @return list of readable trace types
   */
  @SuppressWarnings("nls")
  public static List<String> getManualTraceTypes() {
    List<String> result = new ArrayList<String>();
    result.add(Messages.getString("TraceType.name.generic"));
    result.add(Messages.getString("TraceType.name.requirement"));
    return result;
  }
}
