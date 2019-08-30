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
import org.polarsys.capella.viatra.core.data.interaction.surrogate.Scenario__containedFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.Scenario__containedPartsMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.Scenario__realizedScenariosMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.Scenario__realizingScenariosMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.Scenario__referencedScenariosMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.Scenario__containedFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.Scenario__containedPartsQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.Scenario__realizedScenariosQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.Scenario__realizingScenariosQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.Scenario__referencedScenariosQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Scenario.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Scenario.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Scenario__containedFunctions</li>
 * <li>Scenario__containedParts</li>
 * <li>Scenario__referencedScenarios</li>
 * <li>Scenario__realizedScenarios</li>
 * <li>Scenario__realizingScenarios</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Scenario extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Scenario instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Scenario();
    }
    return INSTANCE;
  }
  
  private static Scenario INSTANCE;
  
  private Scenario() throws ViatraQueryException {
    querySpecifications.add(Scenario__containedFunctionsQuerySpecification.instance());
    querySpecifications.add(Scenario__containedPartsQuerySpecification.instance());
    querySpecifications.add(Scenario__referencedScenariosQuerySpecification.instance());
    querySpecifications.add(Scenario__realizedScenariosQuerySpecification.instance());
    querySpecifications.add(Scenario__realizingScenariosQuerySpecification.instance());
  }
  
  public Scenario__containedFunctionsQuerySpecification getScenario__containedFunctions() throws ViatraQueryException {
    return Scenario__containedFunctionsQuerySpecification.instance();
  }
  
  public Scenario__containedFunctionsMatcher getScenario__containedFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Scenario__containedFunctionsMatcher.on(engine);
  }
  
  public Scenario__containedPartsQuerySpecification getScenario__containedParts() throws ViatraQueryException {
    return Scenario__containedPartsQuerySpecification.instance();
  }
  
  public Scenario__containedPartsMatcher getScenario__containedParts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Scenario__containedPartsMatcher.on(engine);
  }
  
  public Scenario__referencedScenariosQuerySpecification getScenario__referencedScenarios() throws ViatraQueryException {
    return Scenario__referencedScenariosQuerySpecification.instance();
  }
  
  public Scenario__referencedScenariosMatcher getScenario__referencedScenarios(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Scenario__referencedScenariosMatcher.on(engine);
  }
  
  public Scenario__realizedScenariosQuerySpecification getScenario__realizedScenarios() throws ViatraQueryException {
    return Scenario__realizedScenariosQuerySpecification.instance();
  }
  
  public Scenario__realizedScenariosMatcher getScenario__realizedScenarios(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Scenario__realizedScenariosMatcher.on(engine);
  }
  
  public Scenario__realizingScenariosQuerySpecification getScenario__realizingScenarios() throws ViatraQueryException {
    return Scenario__realizingScenariosQuerySpecification.instance();
  }
  
  public Scenario__realizingScenariosMatcher getScenario__realizingScenarios(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Scenario__realizingScenariosMatcher.on(engine);
  }
}
