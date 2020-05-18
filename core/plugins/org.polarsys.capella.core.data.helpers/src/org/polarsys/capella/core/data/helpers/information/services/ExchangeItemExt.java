/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Type;
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

/**
 */
public class ExchangeItemExt {

  public static ExchangeItem copyExchangeItem(ExchangeItem exchangeItem1) {
    ExchangeItem element = InformationFactory.eINSTANCE.createExchangeItem();
    element.setName(exchangeItem1.getName());
    element.setDescription(exchangeItem1.getDescription());
    element.setExchangeMechanism(exchangeItem1.getExchangeMechanism());

    for (ExchangeItemElement kid : exchangeItem1.getOwnedElements()) {
      element.getOwnedElements().add(copyExchangeItemElement(kid));
    }

    return element;
  }

  public static ExchangeItemElement copyExchangeItemElement(ExchangeItemElement exchangeItemElement1) {
    ExchangeItemElement element = InformationFactory.eINSTANCE.createExchangeItemElement();
    element.setName(exchangeItemElement1.getName());
    element.setDirection(exchangeItemElement1.getDirection());
    element.setDescription(exchangeItemElement1.getDescription());
    element.setAbstractType(exchangeItemElement1.getAbstractType());
    element.setOwnedMaxCard(exchangeItemElement1.getOwnedMaxCard());
    element.setOwnedMinCard(exchangeItemElement1.getOwnedMinCard());

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
   * @param item the given exchangeItem
   * @return the type of parameters associated to the exchange item
   */
  public static List<AbstractType> getData(AbstractExchangeItem item) {
    List<AbstractType> types = new ArrayList<>();

    if (item instanceof ExchangeItem) {
      for (ExchangeItemElement element : ((ExchangeItem) item).getOwnedElements()) {
        if (element.getType() != null) {
          types.add(element.getType());
        }
      }
    }

    return types;
  }

  /**
   * @param exchangeItems
   * @return
   */
  public static List<ExchangeItem> getEvents(EList<ExchangeItem> exchangeItems) {
    List<ExchangeItem> types = new ArrayList<>();
    for (ExchangeItem item : exchangeItems) {
      if (ExchangeMechanism.EVENT.equals(item.getExchangeMechanism())) {
        types.add(item);
      }
    }
    return types;
  }

  /**
   * Gets the parameters.
   * @param item the given exchangeItem
   * @return the type of parameters associated to the exchange item
   */
  public static List<AbstractType> getExceptions(ExchangeItem item) {
    List<AbstractType> types = new ArrayList<>();
    for (ExchangeItemElement element : item.getOwnedElements()) {
      if (ElementKind.MEMBER.equals(element.getKind()) && ParameterDirection.EXCEPTION.equals(element.getDirection())) {
        Type type = element.getType();
        if (type != null) {
          types.add(type);
        }
      }
    }
    return types;
  }

  /**
   * @see #getExchangeItemDependencies(AbstractExchangeItem)
   */
  public static Map<AbstractDependenciesPkg, Collection<EObject>> getExchangeItemDependencies2(AbstractExchangeItem exchangeItem) {

    Map<AbstractDependenciesPkg, Collection<EObject>> result = new HashMap<>();

    for (AbstractType aType : getData(exchangeItem)) {
      checkDependenciesAndAddToResult(result, aType);
    }

    return result;
  }

  /** for internal use */
  private static void checkDependenciesAndAddToResult(Map<AbstractDependenciesPkg, Collection<EObject>> map, EObject eobject) {
    // TODO we'd like to use AbstractDependenciesPkgExt.checkDependenciesAndAddToResult, but that would cause a plugin-dependency cycle.
    if (null != eobject) {
      AbstractDependenciesPkg adp = (AbstractDependenciesPkg) EcoreUtil2.getFirstContainer(eobject, CapellacorePackage.Literals.ABSTRACT_DEPENDENCIES_PKG);
      if (adp != null) {
        if (!map.containsKey(adp)) {
          Set<EObject> set = new HashSet<>();
          map.put(adp, set);
        }
        map.get(adp).add(eobject);
      }
    }
  }

  /**
   * @param exchangeItem
   * @return all dependent packages of exchangeItem
   */
  public static Collection<AbstractDependenciesPkg> getExchangeItemDependencies(AbstractExchangeItem exchangeItem) {
    return getExchangeItemDependencies2(exchangeItem).keySet();
  }

  /**
   * @param exchangeItems
   * @return
   */
  public static List<ExchangeItem> getExchangeItems(Interface element) {
    List<ExchangeItem> types = new ArrayList<>();
    for (AbstractExchangeItem item : element.getExchangeItems()) {
      if (item instanceof ExchangeItem) {
        types.add((ExchangeItem) item);
      }
    }
    return types;
  }

  /**
   * @param exchangeItems
   * @return
   */
  public static List<ExchangeItem> getOperations(List<? extends AbstractExchangeItem> exchangeItems) {
    List<ExchangeItem> types = new ArrayList<>();
    for (AbstractExchangeItem item : exchangeItems) {
      if ((item instanceof ExchangeItem) && (((ExchangeItem) item).getExchangeMechanism() == ExchangeMechanism.OPERATION)) {
        types.add((ExchangeItem) item);
      }
    }
    return types;
  }

  /**
   * Gets the parameters.
   * @param item the given exchangeItem
   * @return the type of parameters associated to the exchange item
   */
  public static List<AbstractType> getParameters(AbstractExchangeItem item) {
    List<AbstractType> types = new ArrayList<>();

    if (item instanceof ExchangeItem) {
      for (ExchangeItemElement element : ((ExchangeItem) item).getOwnedElements()) {
        if (ElementKind.MEMBER.equals(element.getKind())) {
          Type type = element.getType();
          if (type != null) {
            types.add(type);
          }
        }
      }
    }

    return types;
  }

  public static Collection<CommunicationLink> getRelatedCommunicationLinks(AbstractExchangeItem sndItem) {
    HashSet<CommunicationLink> result = new HashSet<>();

    List<EReference> refs = new ArrayList<>();
    refs.add(CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM);

    for (Object objectRef : EObjectExt.getReferencers(sndItem, refs)) {
      result.add((CommunicationLink) objectRef);
    }

    return result;
  }

  /**
   * @param item
   * @return
   */
  public static Collection<ExchangeItemAllocation> getRelatedExchangeItemAllocations(ExchangeItem item) {
    HashSet<ExchangeItemAllocation> result = new HashSet<>();
    List<EReference> refs = new ArrayList<>();
    refs.add(CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM);

    for (Object objectRef : EObjectExt.getReferencers(item, refs)) {
      result.add((ExchangeItemAllocation) objectRef);
    }

    return result;
  }

  /**
   * Returns all communication exchanger which are related to the exchange item. Include also childs by generalization
   * @param item
   * @return
   */
  public static Collection<CommunicationLinkExchanger> getRelatedExchangers(AbstractExchangeItem item) {
    Collection<CommunicationLinkExchanger> result = new ArrayList<>();
    for (CommunicationLink link : getRelatedCommunicationLinks(item)) {
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
   * @param item the given exchangeItem
   * @return the types associated to the exchange item
   */
  public static List<AbstractType> getTypes(AbstractExchangeItem item) {
    List<AbstractType> types = new ArrayList<>();

    if (item instanceof ExchangeItem) {
      for (ExchangeItemElement element : ((ExchangeItem) item).getOwnedElements()) {
        if (ElementKind.TYPE.equals(element.getKind())) {
          Type type = element.getType();
          if(type != null) {
            types.add(type);            
          }
        }
      }
    }

    return types;
  }

  /**
   * Checks if the exchange item is ACCESS by the component (including inheritance links)
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isAccess(Component sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt), CommunicationLinkKind.ACCESS)
        .contains(sndItem);
  }

  /**
   * Checks if the exchange item is CALL by the component (including inheritance links)
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isCall(Component sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt), CommunicationLinkKind.CALL).contains(sndItem);
  }

  /**
   * Checks if both sndItem is produce/call/send/write by the sndCpnt and if rcvItem is consume/execute/receive/access by rcvCpnt with cohesion between
   * CommunicaionLinks
   * @param sndCpnt
   * @param sndItem
   * @param rcvCpnt
   * @param rcvItem
   * @return
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static boolean isCoherentExchangeItem(Component sndCpnt, AbstractExchangeItem sndItem, Component rcvCpnt, AbstractExchangeItem rcvItem) {
    List<GeneralizableElement> sndComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(sndCpnt);
    List<GeneralizableElement> rcvComponents = GeneralizableElementExt.getAllSuperGeneralizableElements(rcvCpnt);

    sndComponents.add(sndCpnt);
    rcvComponents.add(rcvCpnt);

    // Retrieve all hierarchy of CommunicationLinks
    HashSet<AbstractExchangeItem> sndProduce = new HashSet<>();
    HashSet<AbstractExchangeItem> sndCall = new HashSet<>();
    HashSet<AbstractExchangeItem> sndSend = new HashSet<>();
    HashSet<AbstractExchangeItem> sndWrite = new HashSet<>();

    for (GeneralizableElement snd : sndComponents) {
      if (snd instanceof Component) {
        Component sndCompo = (Component) snd;
        sndProduce.addAll((Collection) sndCompo.getProduce());
        sndCall.addAll((Collection) sndCompo.getCall());
        sndSend.addAll((Collection) sndCompo.getSend());
        sndWrite.addAll((Collection) sndCompo.getWrite());
      }
    }

    HashSet<AbstractExchangeItem> rcvConsume = new HashSet<>();
    HashSet<AbstractExchangeItem> rcvExecute = new HashSet<>();
    HashSet<AbstractExchangeItem> rcvReceive = new HashSet<>();
    HashSet<AbstractExchangeItem> rcvAccess = new HashSet<>();

    for (GeneralizableElement snd : rcvComponents) {
      if (snd instanceof Component) {
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
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isConsume(Component sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt), CommunicationLinkKind.CONSUME).contains(
        sndItem);
  }

  /**
   * Checks if the exchange item is EXECUTE by the component (including inheritance links)
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isExecute(Component sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt), CommunicationLinkKind.EXECUTE).contains(
        sndItem);
  }

  /**
   * Checks if the exchange item is PRODUCE by the component (including inheritance links)
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isProduce(Component sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt), CommunicationLinkKind.PRODUCE).contains(
        sndItem);
  }

  /**
   * Checks if the exchange item is RECEIVE by the component (including inheritance links)
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isReceive(Component sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt), CommunicationLinkKind.RECEIVE).contains(
        sndItem);
  }

  /**
   * Checks if the exchange item is SEND by the component (including inheritance links)
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isSend(Component sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt), CommunicationLinkKind.SEND).contains(sndItem);
  }

  /**
   * Checks if the exchange item is CONSUME, EXECUTE, RECEIVE, ACCESS by the component (including inheritance links)
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isSendingByLinks(CommunicationLinkExchanger sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt
        .getExchangeItemsByKinds(
            CommunicationLinkExt.getAllCommunicationLinks(sndCpnt),
            new CommunicationLinkKind[] { CommunicationLinkKind.CONSUME, CommunicationLinkKind.EXECUTE, CommunicationLinkKind.RECEIVE,
                                         CommunicationLinkKind.ACCESS }).contains(sndItem);
  }

  /**
   * Checks if the exchange item is PRODUCE, CALL, SEND, WRITE by the component (including inheritance links)
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isUsingByLinks(CommunicationLinkExchanger sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKinds(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt),
        new CommunicationLinkKind[] { CommunicationLinkKind.PRODUCE, CommunicationLinkKind.CALL, CommunicationLinkKind.SEND, CommunicationLinkKind.WRITE })
        .contains(sndItem);
  }

  /**
   * Checks if the exchange item is WRITE by the component (including inheritance links)
   * @param sndCpnt the given component
   * @param sndItem the given abstractExchangeItem
   * @return true, if is produce
   */
  public static boolean isWrite(Component sndCpnt, AbstractExchangeItem sndItem) {
    return CommunicationLinkExt.getExchangeItemsByKind(CommunicationLinkExt.getAllCommunicationLinks(sndCpnt), CommunicationLinkKind.WRITE).contains(sndItem);
  }
}
