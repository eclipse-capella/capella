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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in Scenario.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Scenario extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Scenario instance() {
    if (INSTANCE == null) {
        INSTANCE = new Scenario();
    }
    return INSTANCE;
  }
  
  private static Scenario INSTANCE;
  
  private Scenario() {
    querySpecifications.add(Scenario__containedFunctions.instance());
    querySpecifications.add(Scenario__containedParts.instance());
    querySpecifications.add(Scenario__referencedScenarios.instance());
    querySpecifications.add(Scenario__realizedScenarios.instance());
    querySpecifications.add(Scenario__realizingScenarios.instance());
  }
  
  public Scenario__containedFunctions getScenario__containedFunctions() {
    return Scenario__containedFunctions.instance();
  }
  
  public Scenario__containedFunctions.Matcher getScenario__containedFunctions(final ViatraQueryEngine engine) {
    return Scenario__containedFunctions.Matcher.on(engine);
  }
  
  public Scenario__containedParts getScenario__containedParts() {
    return Scenario__containedParts.instance();
  }
  
  public Scenario__containedParts.Matcher getScenario__containedParts(final ViatraQueryEngine engine) {
    return Scenario__containedParts.Matcher.on(engine);
  }
  
  public Scenario__referencedScenarios getScenario__referencedScenarios() {
    return Scenario__referencedScenarios.instance();
  }
  
  public Scenario__referencedScenarios.Matcher getScenario__referencedScenarios(final ViatraQueryEngine engine) {
    return Scenario__referencedScenarios.Matcher.on(engine);
  }
  
  public Scenario__realizedScenarios getScenario__realizedScenarios() {
    return Scenario__realizedScenarios.instance();
  }
  
  public Scenario__realizedScenarios.Matcher getScenario__realizedScenarios(final ViatraQueryEngine engine) {
    return Scenario__realizedScenarios.Matcher.on(engine);
  }
  
  public Scenario__realizingScenarios getScenario__realizingScenarios() {
    return Scenario__realizingScenarios.instance();
  }
  
  public Scenario__realizingScenarios.Matcher getScenario__realizingScenarios(final ViatraQueryEngine engine) {
    return Scenario__realizingScenarios.Matcher.on(engine);
  }
}
