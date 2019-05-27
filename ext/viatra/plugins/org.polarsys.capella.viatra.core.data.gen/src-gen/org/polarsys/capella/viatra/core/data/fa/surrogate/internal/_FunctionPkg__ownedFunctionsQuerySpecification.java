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
 * A pattern-specific query specification that can instantiate _FunctionPkg__ownedFunctionsMatcher in a type-safe way.
 * 
 * @see _FunctionPkg__ownedFunctionsMatcher
 * @see _FunctionPkg__ownedFunctionsMatch
 * 
 */
@SuppressWarnings("all")
public final class _FunctionPkg__ownedFunctionsQuerySpecification extends BaseGeneratedPrivateEMFQuerySpecification {
  private _FunctionPkg__ownedFunctionsQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static _FunctionPkg__ownedFunctionsQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  /**
   * Inner class allowing the singleton instance of {@link _FunctionPkg__ownedFunctionsQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link _FunctionPkg__ownedFunctionsQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static _FunctionPkg__ownedFunctionsQuerySpecification INSTANCE = new _FunctionPkg__ownedFunctionsQuerySpecification();
    
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
    private final static _FunctionPkg__ownedFunctionsQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pPkg = new PParameter("pkg", "org.polarsys.capella.core.data.fa.FunctionPkg", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.4.0", "FunctionPkg")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pAf = new PParameter("af", "org.polarsys.capella.core.data.fa.AbstractFunction", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/fa/1.4.0", "AbstractFunction")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pPkg, parameter_pAf);
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.fa.surrogate._FunctionPkg__ownedFunctions";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("pkg","af");
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
              PVariable var_pkg = body.getOrCreateVariableByName("pkg");
              PVariable var_af = body.getOrCreateVariableByName("af");
              new TypeConstraint(body, new FlatTuple(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "FunctionPkg")));
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "AbstractFunction")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_pkg, parameter_pPkg),
                 new ExportedParameter(body, var_af, parameter_pAf)
              ));
              // 	OperationalActivityPkg.ownedOperationalActivities(pkg, af)
              new TypeConstraint(body, new FlatTuple(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/oa/1.4.0", "OperationalActivityPkg")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_pkg, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/oa/1.4.0", "OperationalActivityPkg", "ownedOperationalActivities")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/oa/1.4.0", "OperationalActivity")));
              new Equality(body, var__virtual_0_, var_af);
              bodies.add(body);
          }
          {
              PBody body = new PBody(this);
              PVariable var_pkg = body.getOrCreateVariableByName("pkg");
              PVariable var_af = body.getOrCreateVariableByName("af");
              new TypeConstraint(body, new FlatTuple(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "FunctionPkg")));
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "AbstractFunction")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_pkg, parameter_pPkg),
                 new ExportedParameter(body, var_af, parameter_pAf)
              ));
              // 	SystemFunctionPkg.ownedSystemFunctions(pkg, af)
              new TypeConstraint(body, new FlatTuple(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/ctx/1.4.0", "SystemFunctionPkg")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_pkg, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/ctx/1.4.0", "SystemFunctionPkg", "ownedSystemFunctions")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/ctx/1.4.0", "SystemFunction")));
              new Equality(body, var__virtual_0_, var_af);
              bodies.add(body);
          }
          {
              PBody body = new PBody(this);
              PVariable var_pkg = body.getOrCreateVariableByName("pkg");
              PVariable var_af = body.getOrCreateVariableByName("af");
              new TypeConstraint(body, new FlatTuple(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "FunctionPkg")));
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "AbstractFunction")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_pkg, parameter_pPkg),
                 new ExportedParameter(body, var_af, parameter_pAf)
              ));
              // 	LogicalFunctionPkg.ownedLogicalFunctions(pkg, af)
              new TypeConstraint(body, new FlatTuple(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/la/1.4.0", "LogicalFunctionPkg")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_pkg, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/la/1.4.0", "LogicalFunctionPkg", "ownedLogicalFunctions")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/la/1.4.0", "LogicalFunction")));
              new Equality(body, var__virtual_0_, var_af);
              bodies.add(body);
          }
          {
              PBody body = new PBody(this);
              PVariable var_pkg = body.getOrCreateVariableByName("pkg");
              PVariable var_af = body.getOrCreateVariableByName("af");
              new TypeConstraint(body, new FlatTuple(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "FunctionPkg")));
              new TypeConstraint(body, new FlatTuple(var_af), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/fa/1.4.0", "AbstractFunction")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_pkg, parameter_pPkg),
                 new ExportedParameter(body, var_af, parameter_pAf)
              ));
              // 	PhysicalFunctionPkg.ownedPhysicalFunctions(pkg, af)
              new TypeConstraint(body, new FlatTuple(var_pkg), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.4.0", "PhysicalFunctionPkg")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_pkg, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/pa/1.4.0", "PhysicalFunctionPkg", "ownedPhysicalFunctions")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.4.0", "PhysicalFunction")));
              new Equality(body, var__virtual_0_, var_af);
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
