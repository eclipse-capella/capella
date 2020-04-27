/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.common.transposer.current;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.business.registry.GenericPurposeRegistry;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.mappings.purposes.NonExistingPurposeException;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.rules.ComputePremisesException;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.rules.RuleExecutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IDomainHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;
import org.polarsys.kitalpha.transposer.rules.handler.rules.runtime.RuntimePurpose;

/**
 * Creation of _runtimePurpose is externalized into createRuntimePurpose
 */
public class GenericRulesHandler implements IRulesHandler {

  public GenericRulesHandler(String purpose, String mappingId) throws NonExistingPurposeException {
    _context = null;
    _runtimePurpose = null;
    _knownObjects = new HashMap();
    _knownObjectsWithoutApplicablePossibitity = new HashSet();
    init(purpose, mappingId);
  }

  public void dispose() {
    if (_context != null) {
      _context.reset();
      _context = null;
    }
    _knownObjects.clear();
    _knownObjectsWithoutApplicablePossibitity.clear();
    _runtimePurpose = null;
  }

  public void setContext(IContext defaultContext) {
    _context = defaultContext;
  }

  public List getPremises(Object object) throws ComputePremisesException {
    MappingPossibility applicablePossibility = null;
    try {
      applicablePossibility = getApplicablePossibility(object);
    } catch (MappingPossibilityResolutionException e) {
      throw new ComputePremisesException(e, object, getPurpose());
    }
    if (applicablePossibility != null) {
      IRule completeRule = applicablePossibility.getCompleteRule();
      return completeRule.getPremises(object);
    } else {
      return Collections.emptyList();
    }
  }

  public void init(String purpose, String mappingId) throws NonExistingPurposeException {
    _runtimePurpose = createRuntimePurpose(purpose, mappingId);

    if ((_runtimePurpose != null) && (_runtimePurpose.getMapping() != null)) {
      _context = _runtimePurpose.getMapping().getContext();
    }
  }

  /**
   * @param purpose
   * @param mappingId
   * @return
   */
  protected RuntimePurpose createRuntimePurpose(String purpose, String mappingId) {
    return GenericPurposeRegistry.getInstance().getRegisteredPurpose(purpose, mappingId);
  }

  public IDomainHelper getDomainHelper() {
    return _runtimePurpose.getMapping().getDomainHelper();
  }

  public String getPurpose() {
    return _runtimePurpose.getName();
  }

  public boolean apply(Object object, boolean complete, IProgressMonitor monitor) throws RuleExecutionException {
    MappingPossibility applicablePossibility = null;
    try {
      applicablePossibility = getApplicablePossibility(object);
    } catch (MappingPossibilityResolutionException e) {
      throw new RuleExecutionException(e.getMessage(), e);
    }
    if (applicablePossibility == null) {
      return false;
    }
    if (applicablePossibility.getContext() != null) {
      applicablePossibility.updateContext(object, _context);
    }
    if (monitor != null) {
      monitor.subTask((new StringBuilder(String.valueOf(applicablePossibility.getName()))).append(" (").append(complete ? "complete" : "incomplete")
          .append(") ").append(object.getClass().getSimpleName()).toString());
    }
    applicablePossibility.applyRule(object, _context, complete);
    return true;
  }

  public MappingPossibility getApplicablePossibility(Object object) throws MappingPossibilityResolutionException {
    MappingPossibility applicablePossibility = null;
    if (_knownObjects.containsKey(object)) {
      return (MappingPossibility) _knownObjects.get(object);
    }
    if (_knownObjectsWithoutApplicablePossibitity.contains(object)) {
      return null;
    }
    applicablePossibility = _runtimePurpose.getMapping().resolveApplicablePossibility(object, _runtimePurpose.getMapping().getDomainHelper());
    if (applicablePossibility != null) {
      _knownObjects.put(object, applicablePossibility);
    } else {
      _knownObjectsWithoutApplicablePossibitity.add(object);
    }
    return applicablePossibility;
  }

  public void validatePurpose() {
    _runtimePurpose.validate();
  }

  public IContext getContext() {
    return _context;
  }

  private IContext _context;
  private RuntimePurpose _runtimePurpose;
  private Map _knownObjects;
  private Set _knownObjectsWithoutApplicablePossibitity;
}
