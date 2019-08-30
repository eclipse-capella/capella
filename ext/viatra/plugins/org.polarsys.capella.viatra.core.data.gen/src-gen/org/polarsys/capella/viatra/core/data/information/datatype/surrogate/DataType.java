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
package org.polarsys.capella.viatra.core.data.information.datatype.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.DataType__defaultValueMatcher;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.DataType__nullValueMatcher;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.DataType__realizedDataTypesMatcher;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.DataType__realizingDataTypesMatcher;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.util.DataType__defaultValueQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.util.DataType__nullValueQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.util.DataType__realizedDataTypesQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.datatype.surrogate.util.DataType__realizingDataTypesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in DataType.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class DataType extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static DataType instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new DataType();
    }
    return INSTANCE;
  }
  
  private static DataType INSTANCE;
  
  private DataType() throws ViatraQueryException {
    querySpecifications.add(DataType__defaultValueQuerySpecification.instance());
    querySpecifications.add(DataType__nullValueQuerySpecification.instance());
    querySpecifications.add(DataType__realizedDataTypesQuerySpecification.instance());
    querySpecifications.add(DataType__realizingDataTypesQuerySpecification.instance());
  }
  
  public DataType__defaultValueQuerySpecification getDataType__defaultValue() throws ViatraQueryException {
    return DataType__defaultValueQuerySpecification.instance();
  }
  
  public DataType__defaultValueMatcher getDataType__defaultValue(final ViatraQueryEngine engine) throws ViatraQueryException {
    return DataType__defaultValueMatcher.on(engine);
  }
  
  public DataType__nullValueQuerySpecification getDataType__nullValue() throws ViatraQueryException {
    return DataType__nullValueQuerySpecification.instance();
  }
  
  public DataType__nullValueMatcher getDataType__nullValue(final ViatraQueryEngine engine) throws ViatraQueryException {
    return DataType__nullValueMatcher.on(engine);
  }
  
  public DataType__realizedDataTypesQuerySpecification getDataType__realizedDataTypes() throws ViatraQueryException {
    return DataType__realizedDataTypesQuerySpecification.instance();
  }
  
  public DataType__realizedDataTypesMatcher getDataType__realizedDataTypes(final ViatraQueryEngine engine) throws ViatraQueryException {
    return DataType__realizedDataTypesMatcher.on(engine);
  }
  
  public DataType__realizingDataTypesQuerySpecification getDataType__realizingDataTypes() throws ViatraQueryException {
    return DataType__realizingDataTypesQuerySpecification.instance();
  }
  
  public DataType__realizingDataTypesMatcher getDataType__realizingDataTypes(final ViatraQueryEngine engine) throws ViatraQueryException {
    return DataType__realizingDataTypesMatcher.on(engine);
  }
}
