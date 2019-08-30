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
package org.polarsys.capella.viatra.core.data.cs.surrogate.util;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalLink__realizedPhysicalLinksMatch;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalLink__realizedPhysicalLinksMatcher;

/**
 * A pattern-specific query specification that can instantiate PhysicalLink__realizedPhysicalLinksMatcher in a type-safe way.
 * 
 * @see PhysicalLink__realizedPhysicalLinksMatcher
 * @see PhysicalLink__realizedPhysicalLinksMatch
 * 
 */
@SuppressWarnings("all")
public final class PhysicalLink__realizedPhysicalLinksQuerySpecification extends BaseGeneratedEMFQuerySpecification<PhysicalLink__realizedPhysicalLinksMatcher> {
  private PhysicalLink__realizedPhysicalLinksQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static PhysicalLink__realizedPhysicalLinksQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected PhysicalLink__realizedPhysicalLinksMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalLink__realizedPhysicalLinksMatcher.on(engine);
  }
  
  @Override
  public PhysicalLink__realizedPhysicalLinksMatcher instantiate() throws ViatraQueryException {
    return PhysicalLink__realizedPhysicalLinksMatcher.create();
  }
  
  @Override
  public PhysicalLink__realizedPhysicalLinksMatch newEmptyMatch() {
    return PhysicalLink__realizedPhysicalLinksMatch.newEmptyMatch();
  }
  
  @Override
  public PhysicalLink__realizedPhysicalLinksMatch newMatch(final Object... parameters) {
    return PhysicalLink__realizedPhysicalLinksMatch.newMatch((org.polarsys.capella.core.data.cs.PhysicalLink) parameters[0], (org.polarsys.capella.core.data.cs.PhysicalLink) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link PhysicalLink__realizedPhysicalLinksQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link PhysicalLink__realizedPhysicalLinksQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static PhysicalLink__realizedPhysicalLinksQuerySpecification INSTANCE = new PhysicalLink__realizedPhysicalLinksQuerySpecification();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private final static Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternalSneaky();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static PhysicalLink__realizedPhysicalLinksQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSelf = new PParameter("self", "org.polarsys.capella.core.data.cs.PhysicalLink", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/cs/1.3.0", "PhysicalLink")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pTarget = new PParameter("target", "org.polarsys.capella.core.data.cs.PhysicalLink", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/cs/1.3.0", "PhysicalLink")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSelf, parameter_pTarget);
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalLink__realizedPhysicalLinks";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("self","target");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      setEvaluationHints(new QueryEvaluationHint(null, (IQueryBackendFactory)null));
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
          {
              PBody body = new PBody(this);
              PVariable var_self = body.getOrCreateVariableByName("self");
              PVariable var_target = body.getOrCreateVariableByName("target");
              PVariable var_plr = body.getOrCreateVariableByName("plr");
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "PhysicalLink")));
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "PhysicalLink")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_self, parameter_pSelf),
                 new ExportedParameter(body, var_target, parameter_pTarget)
              ));
              // 	PhysicalLinkRealization.sourceElement(plr, self)
              new TypeConstraint(body, new FlatTuple(var_plr), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "PhysicalLinkRealization")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_plr, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractTrace", "sourceElement")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "TraceableElement")));
              new Equality(body, var__virtual_0_, var_self);
              // PhysicalLinkRealization.targetElement(plr, target)
              new TypeConstraint(body, new FlatTuple(var_plr), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "PhysicalLinkRealization")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_plr, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractTrace", "targetElement")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "TraceableElement")));
              new Equality(body, var__virtual_1_, var_target);
              bodies.add(body);
          }
          {
              PAnnotation annotation = new PAnnotation("Surrogate");
              annotation.addAttribute("feature", "realizedPhysicalLinks");
              addAnnotation(annotation);
          }
          // to silence compiler error
          if (false) throw new ViatraQueryException("Never", "happens");
      } catch (ViatraQueryException ex) {
          throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
