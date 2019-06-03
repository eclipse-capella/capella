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
import org.polarsys.capella.viatra.core.data.information.surrogate.PartitionableElement__ownedPartitions;
import org.polarsys.capella.viatra.core.data.information.surrogate.PartitionableElement__representingPartitions;

/**
 * A pattern group formed of all public patterns defined in PartitionableElement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PartitionableElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PartitionableElement__ownedPartitions</li>
 * <li>PartitionableElement__representingPartitions</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PartitionableElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PartitionableElement instance() {
    if (INSTANCE == null) {
        INSTANCE = new PartitionableElement();
    }
    return INSTANCE;
  }
  
  private static PartitionableElement INSTANCE;
  
  private PartitionableElement() {
    querySpecifications.add(PartitionableElement__ownedPartitions.instance());
    querySpecifications.add(PartitionableElement__representingPartitions.instance());
  }
  
  public PartitionableElement__ownedPartitions getPartitionableElement__ownedPartitions() {
    return PartitionableElement__ownedPartitions.instance();
  }
  
  public PartitionableElement__ownedPartitions.Matcher getPartitionableElement__ownedPartitions(final ViatraQueryEngine engine) {
    return PartitionableElement__ownedPartitions.Matcher.on(engine);
  }
  
  public PartitionableElement__representingPartitions getPartitionableElement__representingPartitions() {
    return PartitionableElement__representingPartitions.instance();
  }
  
  public PartitionableElement__representingPartitions.Matcher getPartitionableElement__representingPartitions(final ViatraQueryEngine engine) {
    return PartitionableElement__representingPartitions.Matcher.on(engine);
  }
}
