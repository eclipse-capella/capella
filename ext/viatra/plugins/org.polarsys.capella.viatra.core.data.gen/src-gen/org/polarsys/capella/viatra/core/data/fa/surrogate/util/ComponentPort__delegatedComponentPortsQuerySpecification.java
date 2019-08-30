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
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EDataTypeInSlotsKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.ConstantValue;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__delegatedComponentPortsMatch;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__delegatedComponentPortsMatcher;

/**
 * A pattern-specific query specification that can instantiate ComponentPort__delegatedComponentPortsMatcher in a type-safe way.
 * 
 * @see ComponentPort__delegatedComponentPortsMatcher
 * @see ComponentPort__delegatedComponentPortsMatch
 * 
 */
@SuppressWarnings("all")
public final class ComponentPort__delegatedComponentPortsQuerySpecification extends BaseGeneratedEMFQuerySpecification<ComponentPort__delegatedComponentPortsMatcher> {
  private ComponentPort__delegatedComponentPortsQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static ComponentPort__delegatedComponentPortsQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected ComponentPort__delegatedComponentPortsMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentPort__delegatedComponentPortsMatcher.on(engine);
  }
  
  @Override
  public ComponentPort__delegatedComponentPortsMatcher instantiate() throws ViatraQueryException {
    return ComponentPort__delegatedComponentPortsMatcher.create();
  }
  
  @Override
  public ComponentPort__delegatedComponentPortsMatch newEmptyMatch() {
    return ComponentPort__delegatedComponentPortsMatch.newEmptyMatch();
  }
  
  @Override
  public ComponentPort__delegatedComponentPortsMatch newMatch(final Object... parameters) {
    return ComponentPort__delegatedComponentPortsMatch.newMatch((org.polarsys.capella.core.data.fa.ComponentPort) parameters[0], (org.polarsys.capella.core.data.fa.ComponentPort) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link ComponentPort__delegatedComponentPortsQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link ComponentPort__delegatedComponentPortsQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static ComponentPort__delegatedComponentPortsQuerySpecification INSTANCE = new ComponentPort__delegatedComponentPortsQuerySpecification();
    
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
    private final static ComponentPort__delegatedComponentPortsQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSelf = new PParameter("self", "org.polarsys.capella.core.data.fa.ComponentPort", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentPort")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pTarget = new PParameter("target", "org.polarsys.capella.core.data.fa.ComponentPort", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentPort")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSelf, parameter_pTarget);
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__delegatedComponentPorts";
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
              PVariable var_ce = body.getOrCreateVariableByName("ce");
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentPort")));
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentPort")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_self, parameter_pSelf),
                 new ExportedParameter(body, var_target, parameter_pTarget)
              ));
              // 	ComponentPort.outgoingInformationFlows(self, ce)
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentPort")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_self, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "InformationsExchanger", "outgoingInformationFlows")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractInformationFlow")));
              new Equality(body, var__virtual_0_, var_ce);
              // ComponentExchange.kind(ce, ::DELEGATION)
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new ConstantValue(body, var__virtual_1_, getEnumLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchangeKind", "DELEGATION").getInstance());
              new TypeConstraint(body, new FlatTuple(var_ce), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchange")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var_ce, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchange", "kind")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EDataTypeInSlotsKey((EDataType)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchangeKind")));
              new Equality(body, var__virtual_2_, var__virtual_1_);
              // ComponentExchange.targetPort(ce, target)
              new TypeConstraint(body, new FlatTuple(var_ce), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchange")));
              PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
              new TypeConstraint(body, new FlatTuple(var_ce, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "ComponentExchange", "targetPort")));
              new TypeConstraint(body, new FlatTuple(var__virtual_3_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/information/1.3.0", "Port")));
              new Equality(body, var__virtual_3_, var_target);
              bodies.add(body);
          }
          {
              PAnnotation annotation = new PAnnotation("Surrogate");
              annotation.addAttribute("feature", "delegatedComponentPorts");
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
