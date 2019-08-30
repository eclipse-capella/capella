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
package org.polarsys.capella.viatra.core.data.fa.surrogate.internal;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPrivateEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate _AbstractFunction__ownedFunctionPkgsMatcher in a type-safe way.
 * 
 * @see _AbstractFunction__ownedFunctionPkgsMatcher
 * @see _AbstractFunction__ownedFunctionPkgsMatch
 * 
 */
@SuppressWarnings("all")
public final class _AbstractFunction__ownedFunctionPkgsQuerySpecification extends BaseGeneratedPrivateEMFQuerySpecification {
  private _AbstractFunction__ownedFunctionPkgsQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static _AbstractFunction__ownedFunctionPkgsQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  /**
   * Inner class allowing the singleton instance of {@link _AbstractFunction__ownedFunctionPkgsQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link _AbstractFunction__ownedFunctionPkgsQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static _AbstractFunction__ownedFunctionPkgsQuerySpecification INSTANCE = new _AbstractFunction__ownedFunctionPkgsQuerySpecification();
    
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
    private final static _AbstractFunction__ownedFunctionPkgsQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pAf = new PParameter("af", "org.polarsys.capella.core.data.fa.AbstractFunction", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.3.0", "AbstractFunction")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pOwnedpkg = new PParameter("ownedpkg", "org.polarsys.capella.core.data.fa.FunctionPkg", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionPkg")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pAf, parameter_pOwnedpkg);
    
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
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      setEvaluationHints(new QueryEvaluationHint(null, (IQueryBackendFactory)null));
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
          {
              PBody body = new PBody(this);
              PVariable var_af = body.getOrCreateVariableByName("af");
              PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "AbstractFunction")));
              new TypeConstraint(body, new FlatTuple(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionPkg")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_af, parameter_pAf),
                 new ExportedParameter(body, var_ownedpkg, parameter_pOwnedpkg)
              ));
              // 	OperationalActivity.ownedOperationalActivityPkgs(af, ownedpkg)
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/oa/1.3.0", "OperationalActivity")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_af, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/oa/1.3.0", "OperationalActivity", "ownedOperationalActivityPkgs")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/oa/1.3.0", "OperationalActivityPkg")));
              new Equality(body, var__virtual_0_, var_ownedpkg);
              bodies.add(body);
          }
          {
              PBody body = new PBody(this);
              PVariable var_af = body.getOrCreateVariableByName("af");
              PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "AbstractFunction")));
              new TypeConstraint(body, new FlatTuple(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionPkg")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_af, parameter_pAf),
                 new ExportedParameter(body, var_ownedpkg, parameter_pOwnedpkg)
              ));
              // 	SystemFunction.ownedSystemFunctionPkgs(af, ownedpkg)
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/ctx/1.3.0", "SystemFunction")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_af, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/ctx/1.3.0", "SystemFunction", "ownedSystemFunctionPkgs")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/ctx/1.3.0", "SystemFunctionPkg")));
              new Equality(body, var__virtual_0_, var_ownedpkg);
              bodies.add(body);
          }
          {
              PBody body = new PBody(this);
              PVariable var_af = body.getOrCreateVariableByName("af");
              PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "AbstractFunction")));
              new TypeConstraint(body, new FlatTuple(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionPkg")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_af, parameter_pAf),
                 new ExportedParameter(body, var_ownedpkg, parameter_pOwnedpkg)
              ));
              // 	LogicalFunction.ownedLogicalFunctionPkgs(af, ownedpkg)
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/la/1.3.0", "LogicalFunction")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_af, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/la/1.3.0", "LogicalFunction", "ownedLogicalFunctionPkgs")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/la/1.3.0", "LogicalFunctionPkg")));
              new Equality(body, var__virtual_0_, var_ownedpkg);
              bodies.add(body);
          }
          {
              PBody body = new PBody(this);
              PVariable var_af = body.getOrCreateVariableByName("af");
              PVariable var_ownedpkg = body.getOrCreateVariableByName("ownedpkg");
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "AbstractFunction")));
              new TypeConstraint(body, new FlatTuple(var_ownedpkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.3.0", "FunctionPkg")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_af, parameter_pAf),
                 new ExportedParameter(body, var_ownedpkg, parameter_pOwnedpkg)
              ));
              // 	PhysicalFunction.ownedPhysicalFunctionPkgs(af, ownedpkg)
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.3.0", "PhysicalFunction")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_af, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/pa/1.3.0", "PhysicalFunction", "ownedPhysicalFunctionPkgs")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.3.0", "PhysicalFunctionPkg")));
              new Equality(body, var__virtual_0_, var_ownedpkg);
              bodies.add(body);
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
