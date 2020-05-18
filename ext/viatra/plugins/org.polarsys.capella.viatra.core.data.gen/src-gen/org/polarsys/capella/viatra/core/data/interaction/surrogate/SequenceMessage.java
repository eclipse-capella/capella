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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__invokedOperation;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__receivingFunction;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__receivingPart;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__sendingFunction;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__sendingPart;

/**
 * A pattern group formed of all public patterns defined in SequenceMessage.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SequenceMessage.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SequenceMessage__invokedOperation</li>
 * <li>SequenceMessage__sendingPart</li>
 * <li>SequenceMessage__receivingPart</li>
 * <li>SequenceMessage__sendingFunction</li>
 * <li>SequenceMessage__receivingFunction</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class SequenceMessage extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SequenceMessage instance() {
    if (INSTANCE == null) {
        INSTANCE = new SequenceMessage();
    }
    return INSTANCE;
  }
  
  private static SequenceMessage INSTANCE;
  
  private SequenceMessage() {
    querySpecifications.add(SequenceMessage__invokedOperation.instance());
    querySpecifications.add(SequenceMessage__sendingPart.instance());
    querySpecifications.add(SequenceMessage__receivingPart.instance());
    querySpecifications.add(SequenceMessage__sendingFunction.instance());
    querySpecifications.add(SequenceMessage__receivingFunction.instance());
  }
  
  public SequenceMessage__invokedOperation getSequenceMessage__invokedOperation() {
    return SequenceMessage__invokedOperation.instance();
  }
  
  public SequenceMessage__invokedOperation.Matcher getSequenceMessage__invokedOperation(final ViatraQueryEngine engine) {
    return SequenceMessage__invokedOperation.Matcher.on(engine);
  }
  
  public SequenceMessage__sendingPart getSequenceMessage__sendingPart() {
    return SequenceMessage__sendingPart.instance();
  }
  
  public SequenceMessage__sendingPart.Matcher getSequenceMessage__sendingPart(final ViatraQueryEngine engine) {
    return SequenceMessage__sendingPart.Matcher.on(engine);
  }
  
  public SequenceMessage__receivingPart getSequenceMessage__receivingPart() {
    return SequenceMessage__receivingPart.instance();
  }
  
  public SequenceMessage__receivingPart.Matcher getSequenceMessage__receivingPart(final ViatraQueryEngine engine) {
    return SequenceMessage__receivingPart.Matcher.on(engine);
  }
  
  public SequenceMessage__sendingFunction getSequenceMessage__sendingFunction() {
    return SequenceMessage__sendingFunction.instance();
  }
  
  public SequenceMessage__sendingFunction.Matcher getSequenceMessage__sendingFunction(final ViatraQueryEngine engine) {
    return SequenceMessage__sendingFunction.Matcher.on(engine);
  }
  
  public SequenceMessage__receivingFunction getSequenceMessage__receivingFunction() {
    return SequenceMessage__receivingFunction.instance();
  }
  
  public SequenceMessage__receivingFunction.Matcher getSequenceMessage__receivingFunction(final ViatraQueryEngine engine) {
    return SequenceMessage__receivingFunction.Matcher.on(engine);
  }
}
