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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__invokedOperationMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__receivingFunctionMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__receivingPartMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__sendingFunctionMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.SequenceMessage__sendingPartMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.SequenceMessage__invokedOperationQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.SequenceMessage__receivingFunctionQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.SequenceMessage__receivingPartQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.SequenceMessage__sendingFunctionQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.SequenceMessage__sendingPartQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in SequenceMessage.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SequenceMessage extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SequenceMessage instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SequenceMessage();
    }
    return INSTANCE;
  }
  
  private static SequenceMessage INSTANCE;
  
  private SequenceMessage() throws ViatraQueryException {
    querySpecifications.add(SequenceMessage__invokedOperationQuerySpecification.instance());
    querySpecifications.add(SequenceMessage__sendingPartQuerySpecification.instance());
    querySpecifications.add(SequenceMessage__receivingPartQuerySpecification.instance());
    querySpecifications.add(SequenceMessage__sendingFunctionQuerySpecification.instance());
    querySpecifications.add(SequenceMessage__receivingFunctionQuerySpecification.instance());
  }
  
  public SequenceMessage__invokedOperationQuerySpecification getSequenceMessage__invokedOperation() throws ViatraQueryException {
    return SequenceMessage__invokedOperationQuerySpecification.instance();
  }
  
  public SequenceMessage__invokedOperationMatcher getSequenceMessage__invokedOperation(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SequenceMessage__invokedOperationMatcher.on(engine);
  }
  
  public SequenceMessage__sendingPartQuerySpecification getSequenceMessage__sendingPart() throws ViatraQueryException {
    return SequenceMessage__sendingPartQuerySpecification.instance();
  }
  
  public SequenceMessage__sendingPartMatcher getSequenceMessage__sendingPart(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SequenceMessage__sendingPartMatcher.on(engine);
  }
  
  public SequenceMessage__receivingPartQuerySpecification getSequenceMessage__receivingPart() throws ViatraQueryException {
    return SequenceMessage__receivingPartQuerySpecification.instance();
  }
  
  public SequenceMessage__receivingPartMatcher getSequenceMessage__receivingPart(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SequenceMessage__receivingPartMatcher.on(engine);
  }
  
  public SequenceMessage__sendingFunctionQuerySpecification getSequenceMessage__sendingFunction() throws ViatraQueryException {
    return SequenceMessage__sendingFunctionQuerySpecification.instance();
  }
  
  public SequenceMessage__sendingFunctionMatcher getSequenceMessage__sendingFunction(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SequenceMessage__sendingFunctionMatcher.on(engine);
  }
  
  public SequenceMessage__receivingFunctionQuerySpecification getSequenceMessage__receivingFunction() throws ViatraQueryException {
    return SequenceMessage__receivingFunctionQuerySpecification.instance();
  }
  
  public SequenceMessage__receivingFunctionMatcher getSequenceMessage__receivingFunction(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SequenceMessage__receivingFunctionMatcher.on(engine);
  }
}
