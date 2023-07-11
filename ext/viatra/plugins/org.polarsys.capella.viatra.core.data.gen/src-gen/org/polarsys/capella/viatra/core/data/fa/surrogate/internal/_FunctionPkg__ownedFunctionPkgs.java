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
 *         private pattern _FunctionPkg__ownedFunctionPkgs(pkg : FunctionPkg, ownedpkg : FunctionPkg) {
 *         	OperationalActivityPkg.ownedOperationalActivityPkgs(pkg, ownedpkg);
 *         } or {
 *         	SystemFunctionPkg.ownedSystemFunctionPkgs(pkg, ownedpkg);
 *         } or {
 *         	LogicalFunctionPkg.ownedLogicalFunctionPkgs(pkg, ownedpkg);
 *         } or {
 *         	PhysicalFunctionPkg.ownedPhysicalFunctionPkgs(pkg, ownedpkg);
 *         }
 * </pre></code>
 * 
 * @see GenericPatternMatcher
 * @see GenericPatternMatch
 * 
 */
@SuppressWarnings("all")
public final class _FunctionPkg__ownedFunctionPkgs extends BaseGeneratedEMFQuerySpecificationWithGenericMatcher {
  private _FunctionPkg__ownedFunctionPkgs() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static _FunctionPkg__ownedFunctionPkgs instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  /**
   * Inner class allowing the singleton instance of {@link _FunctionPkg__ownedFunctionPkgs} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link _FunctionPkg__ownedFunctionPkgs#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final _FunctionPkg__ownedFunctionPkgs INSTANCE = new _FunctionPkg__ownedFunctionPkgs();
    
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
    private static final _FunctionPkg__ownedFunctionPkgs.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pkg = new PParameter("pkg", "org.polarsys.capella.core.data.fa.FunctionPkg", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")), PParameterDirection.INOUT);
    
    private final PParameter parameter_ownedpkg = new PParameter("ownedpkg", "org.polarsys.capella.core.data.fa.FunctionPkg", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pkg, parameter_ownedpkg);
    
    private GeneratedPQuery() {
      super(PVisibility.PRIVATE);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.fa.surrogate._FunctionPkg__ownedFunctionPkgs";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("pkg","ownedpkg");
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
          PVariable var_pkg = body.getOrCreateVariableByName("pkg");
          PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_pkg, parameter_pkg),
             new ExportedParameter(body, var_ownedpkg, parameter_ownedpkg)
          ));
          // 	OperationalActivityPkg.ownedOperationalActivityPkgs(pkg, ownedpkg)
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/oa/7.0.0", "OperationalActivityPkg")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/oa/7.0.0", "OperationalActivityPkg", "ownedOperationalActivityPkgs")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/oa/7.0.0", "OperationalActivityPkg")));
          new Equality(body, var__virtual_0_, var_ownedpkg);
          bodies.add(body);
      }
      {
          PBody body = new PBody(this);
          PVariable var_pkg = body.getOrCreateVariableByName("pkg");
          PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_pkg, parameter_pkg),
             new ExportedParameter(body, var_ownedpkg, parameter_ownedpkg)
          ));
          // 	SystemFunctionPkg.ownedSystemFunctionPkgs(pkg, ownedpkg)
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/ctx/7.0.0", "SystemFunctionPkg")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/ctx/7.0.0", "SystemFunctionPkg", "ownedSystemFunctionPkgs")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/ctx/7.0.0", "SystemFunctionPkg")));
          new Equality(body, var__virtual_0_, var_ownedpkg);
          bodies.add(body);
      }
      {
          PBody body = new PBody(this);
          PVariable var_pkg = body.getOrCreateVariableByName("pkg");
          PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_pkg, parameter_pkg),
             new ExportedParameter(body, var_ownedpkg, parameter_ownedpkg)
          ));
          // 	LogicalFunctionPkg.ownedLogicalFunctionPkgs(pkg, ownedpkg)
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/la/7.0.0", "LogicalFunctionPkg")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/la/7.0.0", "LogicalFunctionPkg", "ownedLogicalFunctionPkgs")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/la/7.0.0", "LogicalFunctionPkg")));
          new Equality(body, var__virtual_0_, var_ownedpkg);
          bodies.add(body);
      }
      {
          PBody body = new PBody(this);
          PVariable var_pkg = body.getOrCreateVariableByName("pkg");
          PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/7.0.0", "FunctionPkg")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_pkg, parameter_pkg),
             new ExportedParameter(body, var_ownedpkg, parameter_ownedpkg)
          ));
          // 	PhysicalFunctionPkg.ownedPhysicalFunctionPkgs(pkg, ownedpkg)
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/7.0.0", "PhysicalFunctionPkg")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_pkg, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/pa/7.0.0", "PhysicalFunctionPkg", "ownedPhysicalFunctionPkgs")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/7.0.0", "PhysicalFunctionPkg")));
          new Equality(body, var__virtual_0_, var_ownedpkg);
          bodies.add(body);
      }
      return bodies;
    }
  }
}
