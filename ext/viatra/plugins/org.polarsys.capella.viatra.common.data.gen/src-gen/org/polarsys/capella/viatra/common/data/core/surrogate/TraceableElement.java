/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.common.data.core.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.common.data.core.surrogate.TraceableElement__incomingTraces;
import org.polarsys.capella.viatra.common.data.core.surrogate.TraceableElement__outgoingTraces;

/**
 * A pattern group formed of all public patterns defined in TraceableElement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file TraceableElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.core.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>TraceableElement__incomingTraces</li>
 * <li>TraceableElement__outgoingTraces</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class TraceableElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static TraceableElement instance() {
    if (INSTANCE == null) {
        INSTANCE = new TraceableElement();
    }
    return INSTANCE;
  }
  
  private static TraceableElement INSTANCE;
  
  private TraceableElement() {
    querySpecifications.add(TraceableElement__incomingTraces.instance());
    querySpecifications.add(TraceableElement__outgoingTraces.instance());
  }
  
  public TraceableElement__incomingTraces getTraceableElement__incomingTraces() {
    return TraceableElement__incomingTraces.instance();
  }
  
  public TraceableElement__incomingTraces.Matcher getTraceableElement__incomingTraces(final ViatraQueryEngine engine) {
    return TraceableElement__incomingTraces.Matcher.on(engine);
  }
  
  public TraceableElement__outgoingTraces getTraceableElement__outgoingTraces() {
    return TraceableElement__outgoingTraces.instance();
  }
  
  public TraceableElement__outgoingTraces.Matcher getTraceableElement__outgoingTraces(final ViatraQueryEngine engine) {
    return TraceableElement__outgoingTraces.Matcher.on(engine);
  }
}
