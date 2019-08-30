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
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizedLogicalInterfacesMatch;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizedLogicalInterfacesMatcher;

/**
 * A pattern-specific query specification that can instantiate Interface__realizedLogicalInterfacesMatcher in a type-safe way.
 * 
 * @see Interface__realizedLogicalInterfacesMatcher
 * @see Interface__realizedLogicalInterfacesMatch
 * 
 */
@SuppressWarnings("all")
public final class Interface__realizedLogicalInterfacesQuerySpecification extends BaseGeneratedEMFQuerySpecification<Interface__realizedLogicalInterfacesMatcher> {
  private Interface__realizedLogicalInterfacesQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static Interface__realizedLogicalInterfacesQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected Interface__realizedLogicalInterfacesMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__realizedLogicalInterfacesMatcher.on(engine);
  }
  
  @Override
  public Interface__realizedLogicalInterfacesMatcher instantiate() throws ViatraQueryException {
    return Interface__realizedLogicalInterfacesMatcher.create();
  }
  
  @Override
  public Interface__realizedLogicalInterfacesMatch newEmptyMatch() {
    return Interface__realizedLogicalInterfacesMatch.newEmptyMatch();
  }
  
  @Override
  public Interface__realizedLogicalInterfacesMatch newMatch(final Object... parameters) {
    return Interface__realizedLogicalInterfacesMatch.newMatch((org.polarsys.capella.core.data.cs.Interface) parameters[0], (org.polarsys.capella.core.data.cs.Interface) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link Interface__realizedLogicalInterfacesQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link Interface__realizedLogicalInterfacesQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static Interface__realizedLogicalInterfacesQuerySpecification INSTANCE = new Interface__realizedLogicalInterfacesQuerySpecification();
    
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
    private final static Interface__realizedLogicalInterfacesQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSelf = new PParameter("self", "org.polarsys.capella.core.data.cs.Interface", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/cs/1.3.0", "Interface")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pTarget = new PParameter("target", "org.polarsys.capella.core.data.cs.Interface", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/cs/1.3.0", "Interface")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSelf, parameter_pTarget);
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizedLogicalInterfaces";
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
              PVariable var_cir = body.getOrCreateVariableByName("cir");
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "Interface")));
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "Interface")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_self, parameter_pSelf),
                 new ExportedParameter(body, var_target, parameter_pTarget)
              ));
              // 	LogicalInterfaceRealization.sourceElement(cir, self)
              new TypeConstraint(body, new FlatTuple(var_cir), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.3.0", "LogicalInterfaceRealization")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_cir, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractTrace", "sourceElement")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "TraceableElement")));
              new Equality(body, var__virtual_0_, var_self);
              // LogicalInterfaceRealization.targetElement(cir, target)
              new TypeConstraint(body, new FlatTuple(var_cir), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.3.0", "LogicalInterfaceRealization")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_cir, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractTrace", "targetElement")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "TraceableElement")));
              new Equality(body, var__virtual_1_, var_target);
              bodies.add(body);
          }
          {
              PAnnotation annotation = new PAnnotation("Surrogate");
              annotation.addAttribute("feature", "realizedLogicalInterfaces");
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
