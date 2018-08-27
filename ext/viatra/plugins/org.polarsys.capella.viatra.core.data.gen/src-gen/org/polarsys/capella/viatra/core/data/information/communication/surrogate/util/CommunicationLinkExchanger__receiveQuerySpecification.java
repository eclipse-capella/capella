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
package org.polarsys.capella.viatra.core.data.information.communication.surrogate.util;

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
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__receiveMatch;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__receiveMatcher;

/**
 * A pattern-specific query specification that can instantiate CommunicationLinkExchanger__receiveMatcher in a type-safe way.
 * 
 * @see CommunicationLinkExchanger__receiveMatcher
 * @see CommunicationLinkExchanger__receiveMatch
 * 
 */
@SuppressWarnings("all")
public final class CommunicationLinkExchanger__receiveQuerySpecification extends BaseGeneratedEMFQuerySpecification<CommunicationLinkExchanger__receiveMatcher> {
  private CommunicationLinkExchanger__receiveQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static CommunicationLinkExchanger__receiveQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected CommunicationLinkExchanger__receiveMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationLinkExchanger__receiveMatcher.on(engine);
  }
  
  @Override
  public CommunicationLinkExchanger__receiveMatcher instantiate() throws ViatraQueryException {
    return CommunicationLinkExchanger__receiveMatcher.create();
  }
  
  @Override
  public CommunicationLinkExchanger__receiveMatch newEmptyMatch() {
    return CommunicationLinkExchanger__receiveMatch.newEmptyMatch();
  }
  
  @Override
  public CommunicationLinkExchanger__receiveMatch newMatch(final Object... parameters) {
    return CommunicationLinkExchanger__receiveMatch.newMatch((org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger) parameters[0], (org.polarsys.capella.core.data.information.communication.CommunicationLink) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link CommunicationLinkExchanger__receiveQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link CommunicationLinkExchanger__receiveQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static CommunicationLinkExchanger__receiveQuerySpecification INSTANCE = new CommunicationLinkExchanger__receiveQuerySpecification();
    
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
    private final static CommunicationLinkExchanger__receiveQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSelf = new PParameter("self", "org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLinkExchanger")), PParameterDirection.INOUT);
    
    private final PParameter parameter_pTarget = new PParameter("target", "org.polarsys.capella.core.data.information.communication.CommunicationLink", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLink")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSelf, parameter_pTarget);
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationLinkExchanger__receive";
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
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLinkExchanger")));
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLink")));
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_self, parameter_pSelf),
                 new ExportedParameter(body, var_target, parameter_pTarget)
              ));
              // 	CommunicationLinkExchanger.ownedCommunicationLinks(self, target)
              new TypeConstraint(body, new FlatTuple(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLinkExchanger")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_self, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLinkExchanger", "ownedCommunicationLinks")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLink")));
              new Equality(body, var__virtual_0_, var_target);
              // CommunicationLink.kind(target, ::RECEIVE)
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new ConstantValue(body, var__virtual_1_, getEnumLiteral("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLinkKind", "RECEIVE").getInstance());
              new TypeConstraint(body, new FlatTuple(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLink")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var_target, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLink", "kind")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EDataTypeInSlotsKey((EDataType)getClassifierLiteral("http://www.polarsys.org/capella/core/information/communication/1.3.0", "CommunicationLinkKind")));
              new Equality(body, var__virtual_2_, var__virtual_1_);
              bodies.add(body);
          }
          {
              PAnnotation annotation = new PAnnotation("Surrogate");
              annotation.addAttribute("feature", "receive");
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
