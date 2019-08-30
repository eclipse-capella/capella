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
package org.polarsys.capella.viatra.core.data.fa.surrogate.util;

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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeAllocator__allocatedComponentExchangesMatch;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeAllocator__allocatedComponentExchangesMatcher;

/**
 * A pattern-specific query specification that can instantiate ComponentExchangeAllocator__allocatedComponentExchangesMatcher in a type-safe way.
 * 
 * @see ComponentExchangeAllocator__allocatedComponentExchangesMatcher
 * @see ComponentExchangeAllocator__allocatedComponentExchangesMatch
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification extends BaseGeneratedEMFQuerySpecification<ComponentExchangeAllocator__allocatedComponentExchangesMatcher> {
  private ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected ComponentExchangeAllocator__allocatedComponentExchangesMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchangeAllocator__allocatedComponentExchangesMatcher.on(engine);
  }
  
  @Override
  public ComponentExchangeAllocator__allocatedComponentExchangesMatcher instantiate() throws ViatraQueryException {
    return ComponentExchangeAllocator__allocatedComponentExchangesMatcher.create();
  }
  
  @Override
  public ComponentExchangeAllocator__allocatedComponentExchangesMatch newEmptyMatch() {
    return ComponentExchangeAllocator__allocatedComponentExchangesMatch.newEmptyMatch();
  }
  
  @Override
  public ComponentExchangeAllocator__allocatedComponentExchangesMatch newMatch(final Object... parameters) {
    return ComponentExchangeAllocator__allocatedComponentExchangesMatch.newMatch((org.polarsys.capella.core.data.fa.ComponentExchangeAllocator) parameters[0], (org.polarsys.capella.core.data.fa.ComponentExchange) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification INSTANCE = new ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification();
    
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
    private final static ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSelf = new PParameter("self", "org.polarsys.capella.core.data.fa.ComponentExchangeAllocator", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchangeAllocator")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pTarget = new PParameter("target", "org.polarsys.capella.core.data.fa.ComponentExchange", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchange")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSelf, parameter_pTarget);
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeAllocator__allocatedComponentExchanges";
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
              PVariable var_cea = body.getOrCreateVariableByName("cea");
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchangeAllocator")));
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchange")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_self, parameter_pSelf),
                 new ExportedParameter(body, var_target, parameter_pTarget)
              ));
              // 	ComponentExchangeAllocator.outgoingTraces(self, cea)
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchangeAllocator")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_self, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "TraceableElement", "outgoingTraces")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractTrace")));
              new Equality(body, var__virtual_0_, var_cea);
              // ComponentExchangeAllocation.componentExchangeAllocated(cea, target)
              new TypeConstraint(body, new FlatTuple(var_cea), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchangeAllocation")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_cea, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchangeAllocation", "componentExchangeAllocated")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchange")));
              new Equality(body, var__virtual_1_, var_target);
              bodies.add(body);
          }
          {
              PAnnotation annotation = new PAnnotation("Surrogate");
              annotation.addAttribute("feature", "allocatedComponentExchanges");
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
