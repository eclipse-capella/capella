/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.common.data.core.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.common.data.core.surrogate.TraceableElement__incomingTracesMatcher;
import org.polarsys.capella.viatra.common.data.core.surrogate.TraceableElement__outgoingTracesMatcher;
import org.polarsys.capella.viatra.common.data.core.surrogate.util.TraceableElement__incomingTracesQuerySpecification;
import org.polarsys.capella.viatra.common.data.core.surrogate.util.TraceableElement__outgoingTracesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in TraceableElement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file TraceableElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.core.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>TraceableElement__incomingTraces</li>
 * <li>TraceableElement__outgoingTraces</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class TraceableElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static TraceableElement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new TraceableElement();
    }
    return INSTANCE;
  }
  
  private static TraceableElement INSTANCE;
  
  private TraceableElement() throws ViatraQueryException {
    querySpecifications.add(TraceableElement__incomingTracesQuerySpecification.instance());
    querySpecifications.add(TraceableElement__outgoingTracesQuerySpecification.instance());
  }
  
  public TraceableElement__incomingTracesQuerySpecification getTraceableElement__incomingTraces() throws ViatraQueryException {
    return TraceableElement__incomingTracesQuerySpecification.instance();
  }
  
  public TraceableElement__incomingTracesMatcher getTraceableElement__incomingTraces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return TraceableElement__incomingTracesMatcher.on(engine);
  }
  
  public TraceableElement__outgoingTracesQuerySpecification getTraceableElement__outgoingTraces() throws ViatraQueryException {
    return TraceableElement__outgoingTracesQuerySpecification.instance();
  }
  
  public TraceableElement__outgoingTracesMatcher getTraceableElement__outgoingTraces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return TraceableElement__outgoingTracesMatcher.on(engine);
  }
}
