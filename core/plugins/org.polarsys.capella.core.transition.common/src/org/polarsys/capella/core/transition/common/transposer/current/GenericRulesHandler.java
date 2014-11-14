/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

  public GenericRulesHandler(String purpose_p, String mappingId_p) throws NonExistingPurposeException {
    _context = null;
    _runtimePurpose = null;
    _knownObjects = new HashMap();
    _knownObjectsWithoutApplicablePossibitity = new HashSet();
    init(purpose_p, mappingId_p);
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

  public void setContext(IContext defaultContext_p) {
    _context = defaultContext_p;
  }

  public List getPremises(Object object_p) throws ComputePremisesException {
    MappingPossibility applicablePossibility = null;
    try {
      applicablePossibility = getApplicablePossibility(object_p);
    } catch (MappingPossibilityResolutionException e) {
      throw new ComputePremisesException(e, object_p, getPurpose());
    }
    if (applicablePossibility != null) {
      IRule completeRule = applicablePossibility.getCompleteRule();
      return completeRule.getPremises(object_p);
    } else {
      return Collections.emptyList();
    }
  }

  public void init(String purpose_p, String mappingId_p) throws NonExistingPurposeException {
    _runtimePurpose = createRuntimePurpose(purpose_p, mappingId_p);

    if ((_runtimePurpose != null) && (_runtimePurpose.getMapping() != null)) {
      _context = _runtimePurpose.getMapping().getContext();
    }
  }

  /**
   * @param purpose_p
   * @param mappingId_p
   * @return
   */
  protected RuntimePurpose createRuntimePurpose(String purpose_p, String mappingId_p) {
    return GenericPurposeRegistry.getInstance().getRegisteredPurpose(purpose_p, mappingId_p);
  }

  public IDomainHelper getDomainHelper() {
    return _runtimePurpose.getMapping().getDomainHelper();
  }

  public String getPurpose() {
    return _runtimePurpose.getName();
  }

  public boolean apply(Object object_p, boolean complete_p, IProgressMonitor monitor_p) throws RuleExecutionException {
    MappingPossibility applicablePossibility = null;
    try {
      applicablePossibility = getApplicablePossibility(object_p);
    } catch (MappingPossibilityResolutionException e) {
      throw new RuleExecutionException(e.getMessage(), e);
    }
    if (applicablePossibility == null) {
      return false;
    }
    if (applicablePossibility.getContext() != null) {
      applicablePossibility.updateContext(object_p, _context);
    }
    if (monitor_p != null) {
      monitor_p.subTask((new StringBuilder(String.valueOf(applicablePossibility.getName()))).append(" (").append(complete_p ? "complete" : "incomplete")
          .append(") ").append(object_p.getClass().getSimpleName()).toString());
    }
    applicablePossibility.applyRule(object_p, _context, complete_p);
    return true;
  }

  public MappingPossibility getApplicablePossibility(Object object_p) throws MappingPossibilityResolutionException {
    MappingPossibility applicablePossibility = null;
    if (_knownObjects.containsKey(object_p)) {
      return (MappingPossibility) _knownObjects.get(object_p);
    }
    if (_knownObjectsWithoutApplicablePossibitity.contains(object_p)) {
      return null;
    }
    applicablePossibility = _runtimePurpose.getMapping().resolveApplicablePossibility(object_p, _runtimePurpose.getMapping().getDomainHelper());
    if (applicablePossibility != null) {
      _knownObjects.put(object_p, applicablePossibility);
    } else {
      _knownObjectsWithoutApplicablePossibitity.add(object_p);
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
