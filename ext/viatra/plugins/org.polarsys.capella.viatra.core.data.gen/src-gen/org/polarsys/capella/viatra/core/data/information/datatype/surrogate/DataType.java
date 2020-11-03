/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
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
package org.polarsys.capella.viatra.core.data.information.datatype.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.DataType__defaultValue;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.DataType__nullValue;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.DataType__realizedDataTypes;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.DataType__realizingDataTypes;

/**
 * A pattern group formed of all public patterns defined in DataType.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file DataType.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.datatype.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>DataType__defaultValue</li>
 * <li>DataType__nullValue</li>
 * <li>DataType__realizedDataTypes</li>
 * <li>DataType__realizingDataTypes</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class DataType extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static DataType instance() {
    if (INSTANCE == null) {
        INSTANCE = new DataType();
    }
    return INSTANCE;
  }
  
  private static DataType INSTANCE;
  
  private DataType() {
    querySpecifications.add(DataType__defaultValue.instance());
    querySpecifications.add(DataType__nullValue.instance());
    querySpecifications.add(DataType__realizedDataTypes.instance());
    querySpecifications.add(DataType__realizingDataTypes.instance());
  }
  
  public DataType__defaultValue getDataType__defaultValue() {
    return DataType__defaultValue.instance();
  }
  
  public DataType__defaultValue.Matcher getDataType__defaultValue(final ViatraQueryEngine engine) {
    return DataType__defaultValue.Matcher.on(engine);
  }
  
  public DataType__nullValue getDataType__nullValue() {
    return DataType__nullValue.instance();
  }
  
  public DataType__nullValue.Matcher getDataType__nullValue(final ViatraQueryEngine engine) {
    return DataType__nullValue.Matcher.on(engine);
  }
  
  public DataType__realizedDataTypes getDataType__realizedDataTypes() {
    return DataType__realizedDataTypes.instance();
  }
  
  public DataType__realizedDataTypes.Matcher getDataType__realizedDataTypes(final ViatraQueryEngine engine) {
    return DataType__realizedDataTypes.Matcher.on(engine);
  }
  
  public DataType__realizingDataTypes getDataType__realizingDataTypes() {
    return DataType__realizingDataTypes.instance();
  }
  
  public DataType__realizingDataTypes.Matcher getDataType__realizingDataTypes(final ViatraQueryEngine engine) {
    return DataType__realizingDataTypes.Matcher.on(engine);
  }
}
