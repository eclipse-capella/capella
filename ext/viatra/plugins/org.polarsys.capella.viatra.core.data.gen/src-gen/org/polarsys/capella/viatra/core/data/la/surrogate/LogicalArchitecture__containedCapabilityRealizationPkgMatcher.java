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
package org.polarsys.capella.viatra.core.data.la.surrogate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__containedCapabilityRealizationPkgMatch;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalArchitecture__containedCapabilityRealizationPkgQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__containedCapabilityRealizationPkg pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link LogicalArchitecture__containedCapabilityRealizationPkgMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * // End of user code
 * 
 * {@literal @}Surrogate(feature="containedCapabilityRealizationPkg")
 * pattern LogicalArchitecture__containedCapabilityRealizationPkg(self : LogicalArchitecture, target : CapabilityRealizationPkg) {
 * 	LogicalArchitecture.ownedAbstractCapabilityPkg(self, target);
 * }
 * </pre></code>
 * 
 * @see LogicalArchitecture__containedCapabilityRealizationPkgMatch
 * @see LogicalArchitecture__containedCapabilityRealizationPkgProcessor
 * @see LogicalArchitecture__containedCapabilityRealizationPkgQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class LogicalArchitecture__containedCapabilityRealizationPkgMatcher extends BaseMatcher<LogicalArchitecture__containedCapabilityRealizationPkgMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static LogicalArchitecture__containedCapabilityRealizationPkgMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    LogicalArchitecture__containedCapabilityRealizationPkgMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (LogicalArchitecture__containedCapabilityRealizationPkgMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static LogicalArchitecture__containedCapabilityRealizationPkgMatcher create() throws ViatraQueryException {
    return new LogicalArchitecture__containedCapabilityRealizationPkgMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(LogicalArchitecture__containedCapabilityRealizationPkgMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private LogicalArchitecture__containedCapabilityRealizationPkgMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a LogicalArchitecture__containedCapabilityRealizationPkgMatch object.
   * 
   */
  public Collection<LogicalArchitecture__containedCapabilityRealizationPkgMatch> getAllMatches(final LogicalArchitecture pSelf, final CapabilityRealizationPkg pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a LogicalArchitecture__containedCapabilityRealizationPkgMatch object, or null if no match is found.
   * 
   */
  public LogicalArchitecture__containedCapabilityRealizationPkgMatch getOneArbitraryMatch(final LogicalArchitecture pSelf, final CapabilityRealizationPkg pTarget) {
    return rawGetOneArbitraryMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final LogicalArchitecture pSelf, final CapabilityRealizationPkg pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final LogicalArchitecture pSelf, final CapabilityRealizationPkg pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final LogicalArchitecture pSelf, final CapabilityRealizationPkg pTarget, final IMatchProcessor<? super LogicalArchitecture__containedCapabilityRealizationPkgMatch> processor) {
    rawForEachMatch(new Object[]{pSelf, pTarget}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final LogicalArchitecture pSelf, final CapabilityRealizationPkg pTarget, final IMatchProcessor<? super LogicalArchitecture__containedCapabilityRealizationPkgMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSelf, pTarget}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public LogicalArchitecture__containedCapabilityRealizationPkgMatch newMatch(final LogicalArchitecture pSelf, final CapabilityRealizationPkg pTarget) {
    return LogicalArchitecture__containedCapabilityRealizationPkgMatch.newMatch(pSelf, pTarget);
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<LogicalArchitecture> rawAccumulateAllValuesOfself(final Object[] parameters) {
    Set<LogicalArchitecture> results = new HashSet<LogicalArchitecture>();
    rawAccumulateAllValues(POSITION_SELF, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LogicalArchitecture> getAllValuesOfself() {
    return rawAccumulateAllValuesOfself(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LogicalArchitecture> getAllValuesOfself(final LogicalArchitecture__containedCapabilityRealizationPkgMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LogicalArchitecture> getAllValuesOfself(final CapabilityRealizationPkg pTarget) {
    return rawAccumulateAllValuesOfself(new Object[]{
    null, 
    pTarget
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<CapabilityRealizationPkg> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<CapabilityRealizationPkg> results = new HashSet<CapabilityRealizationPkg>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CapabilityRealizationPkg> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CapabilityRealizationPkg> getAllValuesOftarget(final LogicalArchitecture__containedCapabilityRealizationPkgMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<CapabilityRealizationPkg> getAllValuesOftarget(final LogicalArchitecture pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected LogicalArchitecture__containedCapabilityRealizationPkgMatch tupleToMatch(final Tuple t) {
    try {
        return LogicalArchitecture__containedCapabilityRealizationPkgMatch.newMatch((LogicalArchitecture) t.get(POSITION_SELF), (CapabilityRealizationPkg) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected LogicalArchitecture__containedCapabilityRealizationPkgMatch arrayToMatch(final Object[] match) {
    try {
        return LogicalArchitecture__containedCapabilityRealizationPkgMatch.newMatch((LogicalArchitecture) match[POSITION_SELF], (CapabilityRealizationPkg) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected LogicalArchitecture__containedCapabilityRealizationPkgMatch arrayToMatchMutable(final Object[] match) {
    try {
        return LogicalArchitecture__containedCapabilityRealizationPkgMatch.newMutableMatch((LogicalArchitecture) match[POSITION_SELF], (CapabilityRealizationPkg) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<LogicalArchitecture__containedCapabilityRealizationPkgMatcher> querySpecification() throws ViatraQueryException {
    return LogicalArchitecture__containedCapabilityRealizationPkgQuerySpecification.instance();
  }
}
