/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.Operation;

public class UndefinedCommunication extends AbstractCommunication implements ExchangeItem {

  public UndefinedCommunication(ExchangeItem exchangeItem) {
    super(exchangeItem, true);
  }

  @Override
  public CommunicationInfo toCommunicationInfo() {
    return new CommunicationInfo(null, exchangeItem.getExchangeMechanism(), null);
  }

  @Override
  public ExchangeItem getRepresentativeElement() {
    return exchangeItem;
  }

  @Override
  public String getName() {
    return getRepresentativeElement().getName();
  }

  @Override
  public void setName(String value) {
    getRepresentativeElement().setName(value);
  }

  @Override
  public EList<AbstractConstraint> getOwnedConstraints() {
    return getRepresentativeElement().getOwnedConstraints();
  }

  @Override
  public boolean isFinal() {
    return getRepresentativeElement().isFinal();
  }

  @Override
  public void setFinal(boolean value) {
    getRepresentativeElement().setFinal(value);
  }

  @Override
  public EList<AbstractTypedElement> getAbstractTypedElements() {
    return getRepresentativeElement().getAbstractTypedElements();
  }

  @Override
  public boolean isAbstract() {
    return getRepresentativeElement().isAbstract();
  }

  @Override
  public void setAbstract(boolean value) {
    getRepresentativeElement().setAbstract(value);
  }

  @Override
  public EList<Generalization> getOwnedGeneralizations() {
    return getRepresentativeElement().getOwnedGeneralizations();
  }

  @Override
  public EList<Generalization> getSuperGeneralizations() {
    return getRepresentativeElement().getSuperGeneralizations();
  }

  @Override
  public EList<Generalization> getSubGeneralizations() {
    return getRepresentativeElement().getSubGeneralizations();
  }

  @Override
  public EList<GeneralizableElement> getSuper() {
    return getRepresentativeElement().getSuper();
  }

  @Override
  public EList<GeneralizableElement> getSub() {
    return getRepresentativeElement().getSub();
  }

  @Override
  public EList<TypedElement> getTypedElements() {
    return getRepresentativeElement().getTypedElements();
  }

  @Override
  public EList<Trace> getOwnedTraces() {
    return getRepresentativeElement().getOwnedTraces();
  }

  @Override
  public EList<GenericTrace> getContainedGenericTraces() {
    return getRepresentativeElement().getContainedGenericTraces();
  }

  @Override
  public EList<NamingRule> getNamingRules() {
    return getRepresentativeElement().getNamingRules();
  }

  @Override
  public ExchangeMechanism getExchangeMechanism() {
    return getRepresentativeElement().getExchangeMechanism();
  }

  @Override
  public void setExchangeMechanism(ExchangeMechanism value) {
    getRepresentativeElement().setExchangeMechanism(value);
  }

  @Override
  public EList<ExchangeItemElement> getOwnedElements() {
    return getRepresentativeElement().getOwnedElements();
  }

  @Override
  public EList<InformationRealization> getOwnedInformationRealizations() {
    return getRepresentativeElement().getOwnedInformationRealizations();
  }

  @Override
  public EList<ExchangeItemInstance> getOwnedExchangeItemInstances() {
    return getRepresentativeElement().getOwnedExchangeItemInstances();
  }

  @Override
  public EList<Interface> getAllocatorInterfaces() {
    return getRepresentativeElement().getAllocatorInterfaces();
  }

  @Override
  public EList<ExchangeItem> getRealizedExchangeItems() {
    return getRepresentativeElement().getRealizedExchangeItems();
  }

  @Override
  public EList<ExchangeItem> getRealizingExchangeItems() {
    return getRepresentativeElement().getRealizingExchangeItems();
  }

  @Override
  public EList<Operation> getRealizingOperations() {
    return getRepresentativeElement().getRealizingOperations();
  }

  @Override
  public EList<ModelElement> getOwnedMigratedElements() {
    return getRepresentativeElement().getOwnedMigratedElements();
  }

}
