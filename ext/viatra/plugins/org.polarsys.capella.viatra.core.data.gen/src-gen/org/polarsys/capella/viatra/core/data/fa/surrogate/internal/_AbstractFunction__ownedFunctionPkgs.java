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
package org.polarsys.capella.viatra.core.data.fa.surrogate.internal;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecificationWithGenericMatcher;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PVisibility;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuples;

/**
 * A pattern-specific query specification that can instantiate GenericPatternMatcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         private pattern _AbstractFunction__ownedFunctionPkgs(af : AbstractFunction, ownedpkg : FunctionPkg) {
 *         	OperationalActivity.ownedOperationalActivityPkgs(af, ownedpkg);
 *         } or {
 *         	SystemFunction.ownedSystemFunctionPkgs(af, ownedpkg);
 *         } or {
 *         	LogicalFunction.ownedLogicalFunctionPkgs(af, ownedpkg);
 *         } or {
 *         	PhysicalFunction.ownedPhysicalFunctionPkgs(af, ownedpkg);
 *         }
 * </pre></code>
 * 
 * @see GenericPatternMatcher
 * @see GenericPatternMatch
 * 
 */
@SuppressWarnings("all")
public final class _AbstractFunction__ownedFunctionPkgs extends BaseGeneratedEMFQuerySpecificationWithGenericMatcher {
  private _AbstractFunction__ownedFunctionPkgs() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static _AbstractFunction__ownedFunctionPkgs instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  /**
   * Inner class allowing the singleton instance of {@link _AbstractFunction__ownedFunctionPkgs} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link _AbstractFunction__ownedFunctionPkgs#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final _AbstractFunction__ownedFunctionPkgs INSTANCE = new _AbstractFunction__ownedFunctionPkgs();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private static final Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternal();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private static final _AbstractFunction__ownedFunctionPkgs.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_af = new PParameter("af", "org.polarsys.capella.core.data.fa.AbstractFunction", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/7.0.0", "AbstractFunction")), PParameterDirection.INOUT);
    
    private final PParameter parameter_ownedpkg = new PParameter("ownedpkg", "org.polarsys.capella.core.data.fa.FunctionPkg", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_af, parameter_ownedpkg);
    
    private GeneratedPQuery() {
      super(PVisibility.PRIVATE);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.fa.surrogate._AbstractFunction__ownedFunctionPkgs";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("af","ownedpkg");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() {
      setEvaluationHints(new QueryEvaluationHint(null, QueryEvaluationHint.BackendRequirement.UNSPECIFIED));
      Set<PBody> bodies = new LinkedHashSet<>();
      {
          PBody body = new PBody(this);
          PVariable var_af = body.getOrCreateVariableByName("af");
          PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
          new TypeConstraint(body, Tuples.flatTupleOf(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "AbstractFunction")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_af, parameter_af),
             new ExportedParameter(body, var_ownedpkg, parameter_ownedpkg)
          ));
          // 	OperationalActivity.ownedOperationalActivityPkgs(af, ownedpkg)
          new TypeConstraint(body, Tuples.flatTupleOf(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/oa/7.0.0", "OperationalActivity")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_af, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/oa/7.0.0", "OperationalActivity", "ownedOperationalActivityPkgs")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/oa/7.0.0", "OperationalActivityPkg")));
          new Equality(body, var__virtual_0_, var_ownedpkg);
          bodies.add(body);
      }
      {
          PBody body = new PBody(this);
          PVariable var_af = body.getOrCreateVariableByName("af");
          PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
          new TypeConstraint(body, Tuples.flatTupleOf(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "AbstractFunction")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_af, parameter_af),
             new ExportedParameter(body, var_ownedpkg, parameter_ownedpkg)
          ));
          // 	SystemFunction.ownedSystemFunctionPkgs(af, ownedpkg)
          new TypeConstraint(body, Tuples.flatTupleOf(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/ctx/7.0.0", "SystemFunction")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_af, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/ctx/7.0.0", "SystemFunction", "ownedSystemFunctionPkgs")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/ctx/7.0.0", "SystemFunctionPkg")));
          new Equality(body, var__virtual_0_, var_ownedpkg);
          bodies.add(body);
      }
      {
          PBody body = new PBody(this);
          PVariable var_af = body.getOrCreateVariableByName("af");
          PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
          new TypeConstraint(body, Tuples.flatTupleOf(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "AbstractFunction")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_af, parameter_af),
             new ExportedParameter(body, var_ownedpkg, parameter_ownedpkg)
          ));
          // 	LogicalFunction.ownedLogicalFunctionPkgs(af, ownedpkg)
          new TypeConstraint(body, Tuples.flatTupleOf(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/la/7.0.0", "LogicalFunction")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_af, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/la/7.0.0", "LogicalFunction", "ownedLogicalFunctionPkgs")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/la/7.0.0", "LogicalFunctionPkg")));
          new Equality(body, var__virtual_0_, var_ownedpkg);
          bodies.add(body);
      }
      {
          PBody body = new PBody(this);
          PVariable var_af = body.getOrCreateVariableByName("af");
          PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
          new TypeConstraint(body, Tuples.flatTupleOf(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "AbstractFunction")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_af, parameter_af),
             new ExportedParameter(body, var_ownedpkg, parameter_ownedpkg)
          ));
          // 	PhysicalFunction.ownedPhysicalFunctionPkgs(af, ownedpkg)
          new TypeConstraint(body, Tuples.flatTupleOf(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/7.0.0", "PhysicalFunction")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_af, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/pa/7.0.0", "PhysicalFunction", "ownedPhysicalFunctionPkgs")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/7.0.0", "PhysicalFunctionPkg")));
          new Equality(body, var__virtual_0_, var_ownedpkg);
          bodies.add(body);
      }
      return bodies;
    }
  }
}
