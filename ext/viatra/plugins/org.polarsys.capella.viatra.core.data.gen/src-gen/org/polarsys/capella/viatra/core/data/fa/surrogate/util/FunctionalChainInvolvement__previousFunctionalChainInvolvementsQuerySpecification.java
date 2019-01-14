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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher;

/**
 * A pattern-specific query specification that can instantiate FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher in a type-safe way.
 * 
 * @see FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher
 * @see FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification extends BaseGeneratedEMFQuerySpecification<FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher> {
  private FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher.on(engine);
  }
  
  @Override
  public FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher instantiate() throws ViatraQueryException {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher.create();
  }
  
  @Override
  public FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch newEmptyMatch() {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch.newEmptyMatch();
  }
  
  @Override
  public FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch newMatch(final Object... parameters) {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatch.newMatch((org.polarsys.capella.core.data.fa.FunctionalChainInvolvement) parameters[0], (org.polarsys.capella.core.data.fa.FunctionalChainInvolvement) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification INSTANCE = new FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification();
    
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
    private final static FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSelf = new PParameter("self", "org.polarsys.capella.core.data.fa.FunctionalChainInvolvement", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChainInvolvement")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pTarget = new PParameter("target", "org.polarsys.capella.core.data.fa.FunctionalChainInvolvement", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChainInvolvement")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSelf, parameter_pTarget);
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvement__previousFunctionalChainInvolvements";
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
              PVariable var_fc = body.getOrCreateVariableByName("fc");
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChainInvolvement")));
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChainInvolvement")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_self, parameter_pSelf),
                 new ExportedParameter(body, var_target, parameter_pTarget)
              ));
              // 	FunctionalChainInvolvement.nextFunctionalChainInvolvements(target, self)
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChainInvolvement")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_target, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChainInvolvement", "nextFunctionalChainInvolvements")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChainInvolvement")));
              new Equality(body, var__virtual_0_, var_self);
              // // TODO understand why we should verify that target is in the same chain than self ...FunctionalChain.ownedFunctionalChainInvolvements(fc, self)
              new TypeConstraint(body, new FlatTuple(var_fc), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChain")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_fc, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChain", "ownedFunctionalChainInvolvements")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChainInvolvement")));
              new Equality(body, var__virtual_1_, var_self);
              // FunctionalChain.ownedFunctionalChainInvolvements(fc, target)
              new TypeConstraint(body, new FlatTuple(var_fc), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChain")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var_fc, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChain", "ownedFunctionalChainInvolvements")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionalChainInvolvement")));
              new Equality(body, var__virtual_2_, var_target);
              bodies.add(body);
          }
          {
              PAnnotation annotation = new PAnnotation("Surrogate");
              annotation.addAttribute("feature", "previousFunctionalChainInvolvements");
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
