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
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__containedLogicalFunctionPkgMatch;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalArchitecture__containedLogicalFunctionPkgQuerySpecification;

/**
 * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.la.surrogate.LogicalArchitecture__containedLogicalFunctionPkg pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link LogicalArchitecture__containedLogicalFunctionPkgMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * {@literal @}Surrogate(feature="containedLogicalFunctionPkg")
 * pattern LogicalArchitecture__containedLogicalFunctionPkg(self : LogicalArchitecture, target : LogicalFunctionPkg) {
 * 	LogicalArchitecture.ownedFunctionPkg(self, target);
 * }
 * </pre></code>
 * 
 * @see LogicalArchitecture__containedLogicalFunctionPkgMatch
 * @see LogicalArchitecture__containedLogicalFunctionPkgProcessor
 * @see LogicalArchitecture__containedLogicalFunctionPkgQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class LogicalArchitecture__containedLogicalFunctionPkgMatcher extends BaseMatcher<LogicalArchitecture__containedLogicalFunctionPkgMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static LogicalArchitecture__containedLogicalFunctionPkgMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    LogicalArchitecture__containedLogicalFunctionPkgMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (LogicalArchitecture__containedLogicalFunctionPkgMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static LogicalArchitecture__containedLogicalFunctionPkgMatcher create() throws ViatraQueryException {
    return new LogicalArchitecture__containedLogicalFunctionPkgMatcher();
  }
  
  private final static int POSITION_SELF = 0;
  
  private final static int POSITION_TARGET = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(LogicalArchitecture__containedLogicalFunctionPkgMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private LogicalArchitecture__containedLogicalFunctionPkgMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return matches represented as a LogicalArchitecture__containedLogicalFunctionPkgMatch object.
   * 
   */
  public Collection<LogicalArchitecture__containedLogicalFunctionPkgMatch> getAllMatches(final LogicalArchitecture pSelf, final LogicalFunctionPkg pTarget) {
    return rawGetAllMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return a match represented as a LogicalArchitecture__containedLogicalFunctionPkgMatch object, or null if no match is found.
   * 
   */
  public LogicalArchitecture__containedLogicalFunctionPkgMatch getOneArbitraryMatch(final LogicalArchitecture pSelf, final LogicalFunctionPkg pTarget) {
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
  public boolean hasMatch(final LogicalArchitecture pSelf, final LogicalFunctionPkg pTarget) {
    return rawHasMatch(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final LogicalArchitecture pSelf, final LogicalFunctionPkg pTarget) {
    return rawCountMatches(new Object[]{pSelf, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSelf the fixed value of pattern parameter self, or null if not bound.
   * @param pTarget the fixed value of pattern parameter target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final LogicalArchitecture pSelf, final LogicalFunctionPkg pTarget, final IMatchProcessor<? super LogicalArchitecture__containedLogicalFunctionPkgMatch> processor) {
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
  public boolean forOneArbitraryMatch(final LogicalArchitecture pSelf, final LogicalFunctionPkg pTarget, final IMatchProcessor<? super LogicalArchitecture__containedLogicalFunctionPkgMatch> processor) {
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
  public LogicalArchitecture__containedLogicalFunctionPkgMatch newMatch(final LogicalArchitecture pSelf, final LogicalFunctionPkg pTarget) {
    return LogicalArchitecture__containedLogicalFunctionPkgMatch.newMatch(pSelf, pTarget);
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
  public Set<LogicalArchitecture> getAllValuesOfself(final LogicalArchitecture__containedLogicalFunctionPkgMatch partialMatch) {
    return rawAccumulateAllValuesOfself(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for self.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LogicalArchitecture> getAllValuesOfself(final LogicalFunctionPkg pTarget) {
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
  protected Set<LogicalFunctionPkg> rawAccumulateAllValuesOftarget(final Object[] parameters) {
    Set<LogicalFunctionPkg> results = new HashSet<LogicalFunctionPkg>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LogicalFunctionPkg> getAllValuesOftarget() {
    return rawAccumulateAllValuesOftarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LogicalFunctionPkg> getAllValuesOftarget(final LogicalArchitecture__containedLogicalFunctionPkgMatch partialMatch) {
    return rawAccumulateAllValuesOftarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for target.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<LogicalFunctionPkg> getAllValuesOftarget(final LogicalArchitecture pSelf) {
    return rawAccumulateAllValuesOftarget(new Object[]{
    pSelf, 
    null
    });
  }
  
  @Override
  protected LogicalArchitecture__containedLogicalFunctionPkgMatch tupleToMatch(final Tuple t) {
    try {
        return LogicalArchitecture__containedLogicalFunctionPkgMatch.newMatch((LogicalArchitecture) t.get(POSITION_SELF), (LogicalFunctionPkg) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected LogicalArchitecture__containedLogicalFunctionPkgMatch arrayToMatch(final Object[] match) {
    try {
        return LogicalArchitecture__containedLogicalFunctionPkgMatch.newMatch((LogicalArchitecture) match[POSITION_SELF], (LogicalFunctionPkg) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected LogicalArchitecture__containedLogicalFunctionPkgMatch arrayToMatchMutable(final Object[] match) {
    try {
        return LogicalArchitecture__containedLogicalFunctionPkgMatch.newMutableMatch((LogicalArchitecture) match[POSITION_SELF], (LogicalFunctionPkg) match[POSITION_TARGET]);
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
  public static IQuerySpecification<LogicalArchitecture__containedLogicalFunctionPkgMatcher> querySpecification() throws ViatraQueryException {
    return LogicalArchitecture__containedLogicalFunctionPkgQuerySpecification.instance();
  }
}
