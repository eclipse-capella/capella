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
package org.polarsys.capella.viatra.core.data.pa.surrogate.util;

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
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__deployingPhysicalActorsMatch;
import org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__deployingPhysicalActorsMatcher;

/**
 * A pattern-specific query specification that can instantiate PhysicalComponent__deployingPhysicalActorsMatcher in a type-safe way.
 * 
 * @see PhysicalComponent__deployingPhysicalActorsMatcher
 * @see PhysicalComponent__deployingPhysicalActorsMatch
 * 
 */
@SuppressWarnings("all")
public final class PhysicalComponent__deployingPhysicalActorsQuerySpecification extends BaseGeneratedEMFQuerySpecification<PhysicalComponent__deployingPhysicalActorsMatcher> {
  private PhysicalComponent__deployingPhysicalActorsQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static PhysicalComponent__deployingPhysicalActorsQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected PhysicalComponent__deployingPhysicalActorsMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalComponent__deployingPhysicalActorsMatcher.on(engine);
  }
  
  @Override
  public PhysicalComponent__deployingPhysicalActorsMatcher instantiate() throws ViatraQueryException {
    return PhysicalComponent__deployingPhysicalActorsMatcher.create();
  }
  
  @Override
  public PhysicalComponent__deployingPhysicalActorsMatch newEmptyMatch() {
    return PhysicalComponent__deployingPhysicalActorsMatch.newEmptyMatch();
  }
  
  @Override
  public PhysicalComponent__deployingPhysicalActorsMatch newMatch(final Object... parameters) {
    return PhysicalComponent__deployingPhysicalActorsMatch.newMatch((org.polarsys.capella.core.data.pa.PhysicalComponent) parameters[0], (org.polarsys.capella.core.data.pa.PhysicalActor) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link PhysicalComponent__deployingPhysicalActorsQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link PhysicalComponent__deployingPhysicalActorsQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static PhysicalComponent__deployingPhysicalActorsQuerySpecification INSTANCE = new PhysicalComponent__deployingPhysicalActorsQuerySpecification();
    
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
    private final static PhysicalComponent__deployingPhysicalActorsQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSelf = new PParameter("self", "org.polarsys.capella.core.data.pa.PhysicalComponent", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/pa/1.3.0", "PhysicalComponent")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pTarget = new PParameter("target", "org.polarsys.capella.core.data.pa.PhysicalActor", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/pa/1.3.0", "PhysicalActor")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSelf, parameter_pTarget);
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.pa.surrogate.PhysicalComponent__deployingPhysicalActors";
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
              PVariable var_part = body.getOrCreateVariableByName("part");
              PVariable var_deployedPart = body.getOrCreateVariableByName("deployedPart");
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.3.0", "PhysicalComponent")));
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.3.0", "PhysicalActor")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_self, parameter_pSelf),
                 new ExportedParameter(body, var_target, parameter_pTarget)
              ));
              // 	Part.abstractType(part, self)
              new TypeConstraint(body, new FlatTuple(var_part), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "Part")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_part, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractTypedElement", "abstractType")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractType")));
              new Equality(body, var__virtual_0_, var_self);
              // 	Part.deployingLinks.location(part, deployedPart)
              new TypeConstraint(body, new FlatTuple(var_part), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "Part")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_part, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "DeployableElement", "deployingLinks")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "AbstractDeploymentLink")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var__virtual_1_, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "AbstractDeploymentLink", "location")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "DeploymentTarget")));
              new Equality(body, var__virtual_2_, var_deployedPart);
              // 	Part.abstractType(deployedPart, target)
              new TypeConstraint(body, new FlatTuple(var_deployedPart), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "Part")));
              PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
              new TypeConstraint(body, new FlatTuple(var_deployedPart, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractTypedElement", "abstractType")));
              new TypeConstraint(body, new FlatTuple(var__virtual_3_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractType")));
              new Equality(body, var__virtual_3_, var_target);
              bodies.add(body);
          }
          {
              PBody body = new PBody(this);
              PVariable var_self = body.getOrCreateVariableByName("self");
              PVariable var_target = body.getOrCreateVariableByName("target");
              PVariable var_part = body.getOrCreateVariableByName("part");
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.3.0", "PhysicalComponent")));
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/pa/1.3.0", "PhysicalActor")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_self, parameter_pSelf),
                 new ExportedParameter(body, var_target, parameter_pTarget)
              ));
              // 	Part.abstractType(part, self)
              new TypeConstraint(body, new FlatTuple(var_part), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "Part")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_part, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractTypedElement", "abstractType")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/common/core/1.3.0", "AbstractType")));
              new Equality(body, var__virtual_0_, var_self);
              // 	Part.deploymentLinks.deployedElement(part, target)
              new TypeConstraint(body, new FlatTuple(var_part), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "Part")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_part, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "DeploymentTarget", "deploymentLinks")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "AbstractDeploymentLink")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var__virtual_1_, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "AbstractDeploymentLink", "deployedElement")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/1.3.0", "DeployableElement")));
              new Equality(body, var__virtual_2_, var_target);
              bodies.add(body);
          }
          {
              PAnnotation annotation = new PAnnotation("Surrogate");
              annotation.addAttribute("feature", "deployingPhysicalActors");
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
