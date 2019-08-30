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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Part__deployedPartsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Part__deployingPartsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Part__providedInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Part__requiredInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Part__deployedPartsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Part__deployingPartsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Part__providedInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Part__requiredInterfacesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Part.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Part.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Part__providedInterfaces</li>
 * <li>Part__requiredInterfaces</li>
 * <li>Part__deployedParts</li>
 * <li>Part__deployingParts</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Part extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Part instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Part();
    }
    return INSTANCE;
  }
  
  private static Part INSTANCE;
  
  private Part() throws ViatraQueryException {
    querySpecifications.add(Part__providedInterfacesQuerySpecification.instance());
    querySpecifications.add(Part__requiredInterfacesQuerySpecification.instance());
    querySpecifications.add(Part__deployedPartsQuerySpecification.instance());
    querySpecifications.add(Part__deployingPartsQuerySpecification.instance());
  }
  
  public Part__providedInterfacesQuerySpecification getPart__providedInterfaces() throws ViatraQueryException {
    return Part__providedInterfacesQuerySpecification.instance();
  }
  
  public Part__providedInterfacesMatcher getPart__providedInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Part__providedInterfacesMatcher.on(engine);
  }
  
  public Part__requiredInterfacesQuerySpecification getPart__requiredInterfaces() throws ViatraQueryException {
    return Part__requiredInterfacesQuerySpecification.instance();
  }
  
  public Part__requiredInterfacesMatcher getPart__requiredInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Part__requiredInterfacesMatcher.on(engine);
  }
  
  public Part__deployedPartsQuerySpecification getPart__deployedParts() throws ViatraQueryException {
    return Part__deployedPartsQuerySpecification.instance();
  }
  
  public Part__deployedPartsMatcher getPart__deployedParts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Part__deployedPartsMatcher.on(engine);
  }
  
  public Part__deployingPartsQuerySpecification getPart__deployingParts() throws ViatraQueryException {
    return Part__deployingPartsQuerySpecification.instance();
  }
  
  public Part__deployingPartsMatcher getPart__deployingParts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Part__deployingPartsMatcher.on(engine);
  }
}
