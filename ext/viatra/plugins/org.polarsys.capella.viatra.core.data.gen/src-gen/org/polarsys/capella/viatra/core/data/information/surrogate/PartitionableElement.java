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
import org.polarsys.capella.viatra.core.data.information.surrogate.PartitionableElement__ownedPartitionsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.PartitionableElement__representingPartitionsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.PartitionableElement__ownedPartitionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.PartitionableElement__representingPartitionsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PartitionableElement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PartitionableElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PartitionableElement__ownedPartitions</li>
 * <li>PartitionableElement__representingPartitions</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PartitionableElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PartitionableElement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PartitionableElement();
    }
    return INSTANCE;
  }
  
  private static PartitionableElement INSTANCE;
  
  private PartitionableElement() throws ViatraQueryException {
    querySpecifications.add(PartitionableElement__ownedPartitionsQuerySpecification.instance());
    querySpecifications.add(PartitionableElement__representingPartitionsQuerySpecification.instance());
  }
  
  public PartitionableElement__ownedPartitionsQuerySpecification getPartitionableElement__ownedPartitions() throws ViatraQueryException {
    return PartitionableElement__ownedPartitionsQuerySpecification.instance();
  }
  
  public PartitionableElement__ownedPartitionsMatcher getPartitionableElement__ownedPartitions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PartitionableElement__ownedPartitionsMatcher.on(engine);
  }
  
  public PartitionableElement__representingPartitionsQuerySpecification getPartitionableElement__representingPartitions() throws ViatraQueryException {
    return PartitionableElement__representingPartitionsQuerySpecification.instance();
  }
  
  public PartitionableElement__representingPartitionsMatcher getPartitionableElement__representingPartitions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PartitionableElement__representingPartitionsMatcher.on(engine);
  }
}
