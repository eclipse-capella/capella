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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * This class represents a possible candidate to selection for the sequence message creation wizard between two
 * components. Three kinds of communication are defined : - communication by Communication links (see class
 * LinkCommunication), - communication by interface (see class InterfaceCommunication), - communication by an orphan EI
 * (see class UndefinedCommunication).
 * 
 * This class implements EObject for technical reason : sequence message creation wizard viex (see class
 * SelectInvokedOperationView) is based on a generic view in capella (see SelectElementsDialog) whose selectionable
 * elements must be EObject (these elements are prompted in a tree that is calculated according to EMF containement
 * property).
 */
public abstract class AbstractCommunication implements EObject, CapellaElement {

  public boolean isPartial;
  public ExchangeItem exchangeItem;
  public ExchangeItemAllocation exchangeItemAllocation;
  public Interface interfaze;
  public CommunicationLink senderLink;
  public CommunicationLink receiverLink;
  public Component source;
  public Component target;

  public AbstractCommunication(ExchangeItem exchangeItem, boolean isPartial) {
    this.exchangeItem = exchangeItem;
    this.isPartial = isPartial;
  }

  public abstract CommunicationInfo toCommunicationInfo();

  /** Return the Capella element represented by this communication */
  public abstract CapellaElement getRepresentativeElement();

  public String toString() {
    StringBuilder buffer = new StringBuilder(getClass().getSimpleName() + "[" + (isPartial ? "P" : "C") + "] {" + '\n'); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    buffer.append("    exchangeItem : " //$NON-NLS-1$
        + (exchangeItem != null ? exchangeItem.getName() + " " + exchangeItem.getExchangeMechanism() : "") + '\n'); //$NON-NLS-1$ //$NON-NLS-2$
    buffer.append("    allocation   : " //$NON-NLS-1$
        + (exchangeItemAllocation != null
            ? exchangeItemAllocation.getName() + " " + exchangeItemAllocation.getSendProtocol() + " " //$NON-NLS-1$ //$NON-NLS-2$
                + exchangeItemAllocation.getReceiveProtocol()
            : "") //$NON-NLS-1$
        + '\n');
    buffer.append("    interfaze    : " + (interfaze != null ? interfaze.getName() : "") + '\n'); //$NON-NLS-1$ //$NON-NLS-2$
    buffer.append("    senderLink   : " + (senderLink != null ? senderLink.getProtocol() : "") + '\n'); //$NON-NLS-1$ //$NON-NLS-2$
    buffer.append("    receiverLink : " + (receiverLink != null ? receiverLink.getProtocol() : "") + '\n'); //$NON-NLS-1$ //$NON-NLS-2$
    buffer.append("    source       : " + (source != null ? source : "") + '\n'); //$NON-NLS-1$ //$NON-NLS-2$
    buffer.append("    target       : " + (target != null ? target : "") + '\n'); //$NON-NLS-1$ //$NON-NLS-2$
    buffer.append("}"); //$NON-NLS-1$
    return buffer.toString();
  }

  @Override
  public EList<Adapter> eAdapters() {
    return getRepresentativeElement().eAdapters();
  }

  @Override
  public boolean eDeliver() {
    return getRepresentativeElement().eDeliver();
  }

  @Override
  public void eSetDeliver(boolean deliver) {
    getRepresentativeElement().eSetDeliver(deliver);
  }

  @Override
  public void eNotify(Notification notification) {
    getRepresentativeElement().eNotify(notification);
  }

  @Override
  public EClass eClass() {
    return getRepresentativeElement().eClass();
  }

  @Override
  public Resource eResource() {
    return getRepresentativeElement().eResource();
  }

  @Override
  public EObject eContainer() {
    return getRepresentativeElement().eContainer();
  }

  @Override
  public EStructuralFeature eContainingFeature() {
    return getRepresentativeElement().eContainingFeature();
  }

  @Override
  public EReference eContainmentFeature() {
    return getRepresentativeElement().eContainmentFeature();
  }

  @Override
  public EList<EObject> eContents() {
    return getRepresentativeElement().eContents();
  }

  @Override
  public TreeIterator<EObject> eAllContents() {
    return getRepresentativeElement().eAllContents();
  }

  @Override
  public boolean eIsProxy() {
    return getRepresentativeElement().eIsProxy();
  }

  @Override
  public EList<EObject> eCrossReferences() {
    return getRepresentativeElement().eCrossReferences();
  }

  @Override
  public Object eGet(EStructuralFeature feature) {
    return getRepresentativeElement().eGet(feature);
  }

  @Override
  public Object eGet(EStructuralFeature feature, boolean resolve) {
    return getRepresentativeElement().eGet(feature, resolve);
  }

  @Override
  public void eSet(EStructuralFeature feature, Object newValue) {
    getRepresentativeElement().eSet(feature, newValue);
  }

  @Override
  public boolean eIsSet(EStructuralFeature feature) {
    return getRepresentativeElement().eIsSet(feature);
  }

  @Override
  public void eUnset(EStructuralFeature feature) {
    getRepresentativeElement().eUnset(feature);
  }

  @Override
  public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
    return getRepresentativeElement().eInvoke(operation, arguments);
  }

  @Override
  public EList<AbstractTrace> getIncomingTraces() {
    return getRepresentativeElement().getIncomingTraces();
  }

  @Override
  public EList<AbstractTrace> getOutgoingTraces() {
    return getRepresentativeElement().getOutgoingTraces();
  }

  @Override
  public String getId() {
    return getRepresentativeElement().getId();
  }

  @Override
  public void setId(String value) {
    getRepresentativeElement().setId(value);
  }

  @Override
  public String getSid() {
    return getRepresentativeElement().getSid();
  }

  @Override
  public void setSid(String value) {
    getRepresentativeElement().setSid(value);
  }

  @Override
  public EList<AbstractConstraint> getConstraints() {
    return getRepresentativeElement().getConstraints();
  }

  @Override
  public void destroy() {
    getRepresentativeElement().destroy();
  }

  @Override
  public String getFullLabel() {
    return getRepresentativeElement().getFullLabel();
  }

  @Override
  public String getLabel() {
    return getRepresentativeElement().getLabel();
  }

  @Override
  public boolean hasUnnamedLabel() {
    return getRepresentativeElement().hasUnnamedLabel();
  }

  @Override
  public EList<ElementExtension> getOwnedExtensions() {
    return getRepresentativeElement().getOwnedExtensions();
  }

  @Override
  public boolean isVisibleInDoc() {
    return getRepresentativeElement().isVisibleInDoc();
  }

  @Override
  public void setVisibleInDoc(boolean value) {
    getRepresentativeElement().setVisibleInDoc(value);
  }

  @Override
  public boolean isVisibleInLM() {
    return getRepresentativeElement().isVisibleInLM();
  }

  @Override
  public void setVisibleInLM(boolean value) {
    getRepresentativeElement().setVisibleInLM(value);
  }

  @Override
  public String getSummary() {
    return getRepresentativeElement().getSummary();
  }

  @Override
  public void setSummary(String value) {
    getRepresentativeElement().setSummary(value);
  }

  @Override
  public String getDescription() {
    return getRepresentativeElement().getDescription();
  }

  @Override
  public void setDescription(String value) {
    getRepresentativeElement().setDescription(value);
  }

  @Override
  public String getReview() {
    return getRepresentativeElement().getReview();
  }

  @Override
  public void setReview(String value) {
    getRepresentativeElement().setReview(value);
  }

  @Override
  public EList<AbstractPropertyValue> getOwnedPropertyValues() {
    return getRepresentativeElement().getOwnedPropertyValues();
  }

  @Override
  public EList<EnumerationPropertyType> getOwnedEnumerationPropertyTypes() {
    return getRepresentativeElement().getOwnedEnumerationPropertyTypes();
  }

  @Override
  public EList<AbstractPropertyValue> getAppliedPropertyValues() {
    return getRepresentativeElement().getAppliedPropertyValues();
  }

  @Override
  public EList<PropertyValueGroup> getOwnedPropertyValueGroups() {
    return getRepresentativeElement().getOwnedPropertyValueGroups();
  }

  @Override
  public EList<PropertyValueGroup> getAppliedPropertyValueGroups() {
    return getRepresentativeElement().getAppliedPropertyValueGroups();
  }

  @Override
  public EnumerationPropertyLiteral getStatus() {
    return getRepresentativeElement().getStatus();
  }

  @Override
  public void setStatus(EnumerationPropertyLiteral value) {
    getRepresentativeElement().setStatus(value);
  }

  @Override
  public EList<EnumerationPropertyLiteral> getFeatures() {
    return getRepresentativeElement().getFeatures();
  }

}
