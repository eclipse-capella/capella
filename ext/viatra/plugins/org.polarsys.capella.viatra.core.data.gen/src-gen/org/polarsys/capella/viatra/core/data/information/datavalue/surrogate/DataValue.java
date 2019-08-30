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
package org.polarsys.capella.viatra.core.data.information.datavalue.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.DataValue__typeMatcher;
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.util.DataValue__typeQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in DataValue.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file DataValue.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.datavalue.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>DataValue__type</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class DataValue extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static DataValue instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new DataValue();
    }
    return INSTANCE;
  }
  
  private static DataValue INSTANCE;
  
  private DataValue() throws ViatraQueryException {
    querySpecifications.add(DataValue__typeQuerySpecification.instance());
  }
  
  public DataValue__typeQuerySpecification getDataValue__type() throws ViatraQueryException {
    return DataValue__typeQuerySpecification.instance();
  }
  
  public DataValue__typeMatcher getDataValue__type(final ViatraQueryEngine engine) throws ViatraQueryException {
    return DataValue__typeMatcher.on(engine);
  }
}
