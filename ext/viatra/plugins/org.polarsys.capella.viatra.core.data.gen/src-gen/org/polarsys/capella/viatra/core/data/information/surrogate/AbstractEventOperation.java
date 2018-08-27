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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.surrogate.AbstractEventOperation__invokingSequenceMessagesMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.AbstractEventOperation__invokingSequenceMessagesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractEventOperation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractEventOperation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractEventOperation__invokingSequenceMessages</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractEventOperation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractEventOperation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractEventOperation();
    }
    return INSTANCE;
  }
  
  private static AbstractEventOperation INSTANCE;
  
  private AbstractEventOperation() throws ViatraQueryException {
    querySpecifications.add(AbstractEventOperation__invokingSequenceMessagesQuerySpecification.instance());
  }
  
  public AbstractEventOperation__invokingSequenceMessagesQuerySpecification getAbstractEventOperation__invokingSequenceMessages() throws ViatraQueryException {
    return AbstractEventOperation__invokingSequenceMessagesQuerySpecification.instance();
  }
  
  public AbstractEventOperation__invokingSequenceMessagesMatcher getAbstractEventOperation__invokingSequenceMessages(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractEventOperation__invokingSequenceMessagesMatcher.on(engine);
  }
}
