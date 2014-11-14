/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.information.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.ParameterDirection;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 */
public class ExchangeItemExt {

  public static ExchangeItem copyExchangeItem(ExchangeItem element_p) {
    ExchangeItem element = InformationFactory.eINSTANCE.createExchangeItem();
    element.setName(element_p.getName());
    element.setDescription(element_p.getDescription());
    element.setExchangeMechanism(element_p.getExchangeMechanism());

    for (ExchangeItemElement kid : element_p.getOwnedElements()) {
      element.getOwnedElements().add(copyExchangeItemElement(kid));
    }

    return element;
  }

  public static ExchangeItemElement copyExchangeItemElement(ExchangeItemElement element_p) {
    ExchangeItemElement element = InformationFactory.eINSTANCE.createExchangeItemElement();
    element.setName(element_p.getName());
    element.setDirection(element_p.getDirection());
    element.setDescription(element_p.getDescription());
    element.setAbstractType(element_p.getAbstractType());
    element.setOwnedMaxCard(element_p.getOwnedMaxCard());
    element.setOwnedMinCard(element_p.getOwnedMinCard());

    return element;
  }

  public static ExchangeItemElement createExchangeItemElement(boolean initCard) {
    ExchangeItemElement item = InformationFactory.eINSTANCE.createExchangeItemElement();

    if (initCard) {
      LiteralNumericValue valueMin = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
      valueMin.setValue("1"); //$NON-NLS-1$
      item.setOwnedMinCard(valueMin);

      LiteralNumericValue valueMax = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
      valueMax.setValue("1"); //$NON-NLS-1$
      item.setOwnedMaxCard(valueMax);
    }

    return item;
  }

  /**
   * Gets the parameters.
   * @param item_p the given exchangeItem
   * @return the type of parameters associated to the exchange item
   */
  public static List<AbstractType> getData(AbstractExchangeItem item_p) {
    List<AbstractType> types = new ArrayList<AbstractType>();

    if (item_p instanceof ExchangeItem) {
      for (ExchangeItemElement element : ((ExchangeItem) item_p).getOwnedElements()) {
        if (element.getType() != null) {
          types.add(element.getType());
        }
      }
    }

    return types;
  }

  /**
   * @param exchangeItems_p
   * @return
   */
  public static List<ExchangeItem> getEvents(EList<ExchangeItem> exchangeItems_p) {
    List<ExchangeItem> types = new ArrayList<ExchangeItem>();
    for (ExchangeItem item : exchangeItems_p) {
      if (ExchangeMechanism.EVENT.equals(item.getExchangeMechanism())) {
        types.add(item);
      }
    }
    return types;
  }

  /**
   * Gets the parameters.
   * @param item_p the given exchangeItem
   * @return the type of parameters associated to the exchange item
   */
  public static List<AbstractType> getExceptions(ExchangeItem item_p) {
    List<AbstractType> types = new ArrayList<AbstractType>();
    for (ExchangeItemElement element : item_p.getOwnedElements()) {
      if (ElementKind.PARAMETER.equals(element.getKind()) && ParameterDirection.EXCEPTION.equals(element.getDirection())) {
        types.add(element.getType());
      }
    }
    return types;
  }

  /**
   * @see #getExchangeItemDependencies(AbstractExchangeItem)
   */
  public static Map<AbstractDependenciesPkg, Collection<EObject>> getExchangeItemDependencies2(AbstractExchangeItem exchangeItem_p) {

    Map<AbstractDependenciesPkg, Collection<EObject>> result = new HashMap<AbstractDependenciesPkg, Collection<EObject>>();

    for (AbstractType aType : getData(exchangeItem_p)) {
      checkDependenciesAndAddToResult(result, aType);
    }

    return result;
  }

  /** for internal use */
  private static void checkDependenciesAndAddToResult(Map<AbstractDependenciesPkg, Collection<EObject>> map_p, EObject eobject_p) {
    // TODO we'd like to use AbstractDependenciesPkgExt.checkDependenciesAndAddToResult, but that would cause a plugin-dependency cycle.
    if (null != eobject_p) {
      AbstractDependenciesPkg adp = (AbstractDependenciesPkg) EcoreUtil2.getFirstContainer(eobject_p, CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
      if (adp != null) {
        if (!map_p.containsKey(adp)) {
          Set<EObject> set = new HashSet<EObject>();
          map_p.put(adp, set);
        }
        map_p.get(adp).add(eobject_p);
      }
    }
  }

  /**
   * @param exchangeItem_p
   * @return all dependent packages of exchangeItem_p
   */
  public static Collection<AbstractDependenciesPkg> getExchangeItemDependencies(AbstractExchangeItem exchangeItem_p) {
    return getExchangeItemDependencies2(exchangeItem_p).keySet();
  }

  /**
   * @param exchangeItems_p
   * @return
   */
  public static List<ExchangeItem> getExchangeItems(Interface interface_p) {
    List<ExchangeItem> types = new ArrayList<ExchangeItem>();
    for (AbstractExchangeItem item : interface_p.getExchangeItems()) {
      if (item instanceof ExchangeItem) {
        types.add((ExchangeItem) item);
      }
    }
    return types;
  }

  /**
   * @param exchangeItems_p
   * @return
   */
  public static List<ExchangeItem> getOperations(List<? extends AbstractExchangeItem> exchangeItems_p) {
    List<ExchangeItem> types = new ArrayList<ExchangeItem>();
    for (AbstractExchangeItem item : exchangeItems_p) {
      if ((item instanceof ExchangeItem) && (((ExchangeItem) item).getExchangeMechanism() == ExchangeMechanism.OPERATION)) {
        types.add((ExchangeItem) item);
      }
    }
    return types;
  }

  /**
   * Gets the parameters.
   * @param item_p the given exchangeItem
   * @return the type of parameters associated to the exchange item
   */
  public static List<AbstractType> getParameters(AbstractExchangeItem item_p) {
    List<AbstractType> types = new ArrayList<AbstractType>();

    if (item_p instanceof ExchangeItem) {
      for (ExchangeItemElement element : ((ExchangeItem) item_p).getOwnedElements()) {
        if (ElementKind.PARAMETER.equals(element.getKind())) {
          types.add(element.getType());
        }
      }
    }

    return types;
  }

  public static Collection<CommunicationLink> getRelatedCommunicationLinks(AbstractExchangeItem sndItem) {
    HashSet<CommunicationLink> result = new HashSet<CommunicationLink>();

    List<EReference> refs = new ArrayList<EReference>();
    refs.add(CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM);

    for (Object objectRef : EObjectExt.getReferencers(sndItem, refs)) {
      result.add((CommunicationLink) objectRef);
    }

    return result;
  }

  /**
   * @param item_p
   * @return
   */
  public static Collection<ExchangeItemAllocation> getRelatedExchangeItemAllocations(ExchangeItem item_p) {
    HashSet<ExchangeItemAllocation> result = new HashSet<ExchangeItemAllocation>();
    List<EReference> refs = new ArrayList<EReference>();
    refs.add(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);

    for (Object objectRef : EObjectExt.getReferencers(item_p, refs)) {
      result.add((ExchangeItemAllocation) objectRef);
    }

    return result;
  }

  /**
   * Returns all communication exchanger which are related to the exchange item. Include also childs by generalization
   * @param item_p
   * @return
   */
  public static Collection<CommunicationLinkExchanger> getRelatedExchangers(AbstractExchangeItem item_p) {
    Collection<CommunicationLinkExchanger> result = new ArrayList<CommunicationLinkExchanger>();
    for (CommunicationLink link : getRelatedCommunicationLinks(item_p)) {
      if (link.eContainer() instanceof CommunicationLinkExchanger) {
        CommunicationLinkExchanger exchanger = (CommunicationLinkExchanger) link.eContainer();
        result.add(exchanger);

        if (exchanger instanceof GeneralizableElement) {
          for (GeneralizableElement child : GeneralizableElementExt.getAllSubGeneralizableElements((GeneralizableElement) exchanger)) {
            if (child instanceof CommunicationLinkExchanger) {
              result.add((CommunicationLinkExchanger) child);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Gets the types.
   * @param item_p the given exchangeItem
   * @return the types associated to the exchange item
   */
  public static List<AbstractType> getTypes(AbstractExchangeItem item_p) {
    List<AbstractType> types = new ArrayList<AbstractType>();

    if (item_p instanceof ExchangeItem) {
      for (ExchangeItemElement element : ((ExchangeItem) item_p).getOwnedElements()) {
        if (ElementKind.TYPE.equals(element.getKind())) {
          types.add(element.getType());
        }
      }
    }

    return types;
  }

  /**
   * Checks if the exchange item is ACCESS by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isAccess(Component sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p), CommunicationLinkKind.ACCESS)
        .contains(sndItem);
  }

  /**
   * Checks if the exchange item is CALL by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isCall(Component sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p), CommunicationLinkKind.CALL).contains(sndItem);
  }

  /**
   * Checks if both sndItem is produce/call/send/write by the sndCpnt_p and if rcvItem is consume/execute/receive/access by rcvCpnt_p with cohesion between
   * CommunicaionLinks
   * @param sndCpnt_p
   * @param sndItem
   * @param rcvCpnt_p
   * @param rcvItem
   * @return
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static boolean isCoherentExchangeItem(Component sndCpnt_p, AbstractExchangeItem sndItem, Component rcvCpnt_p, AbstractExchangeItem rcvItem) {
    List<GeneralizableElement> sndComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(sndCpnt_p);
    List<GeneralizableElement> rcvComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(rcvCpnt_p);

    sndComponents.add(sndCpnt_p);
    rcvComponents.add(rcvCpnt_p);

    // Retrieve all hierarchy of CommunicationLinks
    HashSet<AbstractExchangeItem> sndProduce = new HashSet<AbstractExchangeItem>();
    HashSet<AbstractExchangeItem> sndCall = new HashSet<AbstractExchangeItem>();
    HashSet<AbstractExchangeItem> sndSend = new HashSet<AbstractExchangeItem>();
    HashSet<AbstractExchangeItem> sndWrite = new HashSet<AbstractExchangeItem>();

    for (GeneralizableElement snd : sndComponents) {
      if ((snd != null) && (snd instanceof Component)) {
        Component sndCompo = (Component) snd;
        sndProduce.addAll((Collection) sndCompo.getProduce());
        sndCall.addAll((Collection) sndCompo.getCall());
        sndSend.addAll((Collection) sndCompo.getSend());
        sndWrite.addAll((Collection) sndCompo.getWrite());
      }
    }

    HashSet<AbstractExchangeItem> rcvConsume = new HashSet<AbstractExchangeItem>();
    HashSet<AbstractExchangeItem> rcvExecute = new HashSet<AbstractExchangeItem>();
    HashSet<AbstractExchangeItem> rcvReceive = new HashSet<AbstractExchangeItem>();
    HashSet<AbstractExchangeItem> rcvAccess = new HashSet<AbstractExchangeItem>();

    for (GeneralizableElement snd : rcvComponents) {
      if ((snd != null) && (snd instanceof Component)) {
        Component sndCompo = (Component) snd;
        rcvConsume.addAll((Collection) sndCompo.getConsume());
        rcvExecute.addAll((Collection) sndCompo.getExecute());
        rcvReceive.addAll((Collection) sndCompo.getReceive());
        rcvAccess.addAll((Collection) sndCompo.getAccess());
      }
    }

    return (sndProduce.contains(sndItem) && rcvConsume.contains(rcvItem)) || (sndCall.contains(sndItem) && rcvExecute.contains(rcvItem))
           || (sndSend.contains(sndItem) && rcvReceive.contains(rcvItem)) || (sndWrite.contains(sndItem) && rcvAccess.contains(rcvItem));
  }

  /**
   * Checks if the exchange item is CONSUME by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isConsume(Component sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p), CommunicationLinkKind.CONSUME).contains(
        sndItem);
  }

  /**
   * Checks if the exchange item is EXECUTE by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isExecute(Component sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p), CommunicationLinkKind.EXECUTE).contains(
        sndItem);
  }

  /**
   * Checks if the exchange item is PRODUCE by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isProduce(Component sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p), CommunicationLinkKind.PRODUCE).contains(
        sndItem);
  }

  /**
   * Checks if the exchange item is RECEIVE by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isReceive(Component sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p), CommunicationLinkKind.RECEIVE).contains(
        sndItem);
  }

  /**
   * Checks if the exchange item is SEND by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isSend(Component sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p), CommunicationLinkKind.SEND).contains(sndItem);
  }

  /**
   * Checks if the exchange item is CONSUME, EXECUTE, RECEIVE, ACCESS by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isSendingByLinks(CommunicationLinkExchanger sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt
        .getExchangeItemsByKinds(
            CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p),
            new CommunicationLinkKind[] { CommunicationLinkKind.CONSUME, CommunicationLinkKind.EXECUTE, CommunicationLinkKind.RECEIVE,
                                         CommunicationLinkKind.ACCESS }).contains(sndItem);
  }

  /**
   * Checks if the exchange item is PRODUCE, CALL, SEND, WRITE by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isUsingByLinks(CommunicationLinkExchanger sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKinds(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p),
        new CommunicationLinkKind[] { CommunicationLinkKind.PRODUCE, CommunicationLinkKind.CALL, CommunicationLinkKind.SEND, CommunicationLinkKind.WRITE })
        .contains(sndItem);
  }

  /**
   * Checks if the exchange item is WRITE by the component (including inheritance links)
   * @param sndCpnt_p the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isWrite(Component sndCpnt_p, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt_p), CommunicationLinkKind.WRITE).contains(sndItem);
  }
}
