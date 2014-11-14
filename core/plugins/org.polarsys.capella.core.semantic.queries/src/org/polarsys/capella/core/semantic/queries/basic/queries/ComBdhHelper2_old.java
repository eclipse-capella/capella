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
package org.polarsys.capella.core.semantic.queries.basic.queries;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionSpecification;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.data.activity.AbstractActivity;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Tentative COM + BDH Helper for the Capella M2 !!!! LIMITATIONS : only handles
 * Function and LogicalComponent throws NotABreakDownObjectException for other
 * objects !!!!
 * 
 * 
 */
public class ComBdhHelper2_old  {

    private static ComBdhHelper2_old instance = new ComBdhHelper2_old();

    private ComBdhHelper2_old() {
        super();
    }

    public static ComBdhHelper2_old getInstance() {
        return instance;
    }

    /**
     * @return true only for Functions and LogicalComponents.
     */
    private boolean hasBDH(ModelElement current) {
        if (current instanceof AbstractFunction || current instanceof Component && !(current instanceof Actor)) {
            return true;
        }
        return false;
    }

    /**
     * @return all the subPackages until BDH
     */
    private EList<LogicalComponentPkg> getAllLCPkg_BDH(LogicalComponentPkg lcPkg) {
        EList<LogicalComponentPkg> subLogicalComponentPkgs = new BasicEList<LogicalComponentPkg>();
        EList<LogicalComponentPkg> resultList = new BasicEList<LogicalComponentPkg>();

        EList<LogicalComponentPkg> listLCPkg = new BasicEList<LogicalComponentPkg>();
        listLCPkg.add(lcPkg);

        while (!listLCPkg.isEmpty()) {
            for (LogicalComponentPkg logicalComponentPkg : listLCPkg) {
                subLogicalComponentPkgs.addAll(logicalComponentPkg.getOwnedLogicalComponentPkgs());
                resultList.addAll(logicalComponentPkg.getOwnedLogicalComponentPkgs());
            }
            listLCPkg = subLogicalComponentPkgs;
            subLogicalComponentPkgs.clear();
        }

        return resultList;
    }

    /**
     * @return all the subComponent : for the LC with partitions
     */
    private List<Component> getSubComponents(Component object_p) {
        List<Component> subLCs = new ArrayList<Component>();
        if (!(object_p instanceof Actor)) {
            Component currentLC = object_p;
            List<Partition> partitions = currentLC.getOwnedPartitions();

            for (Partition thePartition : partitions) {
                Type representedElement = thePartition.getType();
                if (representedElement instanceof Component) {
                    subLCs.add((Component) representedElement);
                }
            }
        }
        return subLCs;
    }

    /**
     * @return all the subComponent : for the LC with partitions
     */
    private List<AbstractActivity> getSubFunctions(AbstractActivity object_p) {
        List<AbstractActivity> fa = new ArrayList<AbstractActivity>();
        if (object_p instanceof FunctionSpecification) {
        	FunctionSpecification currentF = (FunctionSpecification) object_p;
            EList<ActivityNode> ownedNodes = currentF.getOwnedNodes();
            for (ActivityNode activityNode : ownedNodes) {
                if (activityNode instanceof AbstractFunction) {
                    AbstractBehavior behavior = ((AbstractFunction) activityNode).getBehavior();
                    if (behavior instanceof FunctionSpecification) {
                        fa.add((FunctionSpecification) behavior);
                    }
                }
            }
        }
        return fa;
    }

    /**
     * @param current
     * @return The parent in the breakdown hierarchy of this Block or null if
     *         none.
     * @throws NotABreakDownObjectException
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public PartitionableElement getParentTEC(PartitionableElement current)  {
        TreeIterator<Object> allContents = EcoreUtil.getAllContents(current.eResource().getContents().get(0), true);

        if (current instanceof LogicalComponent) {
            LogicalComponent lc = (LogicalComponent) current;
            Object obj = null;
            List<LogicalComponent> scopeList = new ArrayList<LogicalComponent>();
            while (allContents.hasNext()) {
                obj = allContents.next();
                if (obj instanceof LogicalComponent) {
                    LogicalComponent lcme = (LogicalComponent) obj;
                    scopeList.add(lcme);
                }
            }
            for (LogicalComponent logicalComponent : scopeList) {
                // check if subLogicalComonent contain 'current' LC
                //
                List<LogicalComponent> subLogicalComponentHelper = logicalComponent.getOwnedLogicalComponents();
                if (subLogicalComponentHelper.contains(lc)) {
                    // return logicalComponent which is parent of current LC
                    return logicalComponent;
                }

                // check if ownedLogicalcomponent contain 'current' LC + check
                // in subPackages also
                //
                List<LogicalComponentPkg> subLogicalComponentHelperPkg = logicalComponent.getOwnedLogicalComponentPkgs();
                for (LogicalComponentPkg logicalComponentPkgs : subLogicalComponentHelperPkg) {
                    // getOwnLCP
                    List<LogicalComponent> subLogicalComponentHelperLc = logicalComponentPkgs.getOwnedLogicalComponents();
                    if (subLogicalComponentHelperLc.contains(lc)) {
                        // return logicalComponent which is parent of current LC
                        return logicalComponent;
                    }

                    // getOwnLCPkg
                    LogicalComponentPkg lcpkgme = logicalComponentPkgs;
                    for (LogicalComponentPkg logicalComponentPkgsBDH : getAllLCPkg_BDH(lcpkgme)) {
                        List<LogicalComponent> subLogicalComponentHelperOfPkg = logicalComponentPkgsBDH.getOwnedLogicalComponents();
                        if (subLogicalComponentHelperOfPkg.contains(lc)) {
                            // return logicalComponent which is parent of
                            // current LC
                            return logicalComponent;
                        }
                    }
                }
            }
            return null;
        }
        return null;
    }

    /**
     * @returns true if : current or target port type can not be determined (no
     *          req or provides interface) || current and target have same port
     *          type false otherwise.
     */
    public boolean isSameType(Port current, Port target) {
        // TODO implement this ... in progress

        return false;
    }

    /**
     * 
     * @param current
     * @param target
     * @return true if : Current or target port type can not be determined (no
     *         req or provides interface)<BR> || At least one implemented
     *         interface of current is required by target <BR> || at least one
     *         required interface of current is implemented by target <BR>
     *         false otherwise.
     */
    public boolean isCompatible(Partition current, Partition target) {
        // TODO implement this ... in progress
        PartitionableElement currentPE = (PartitionableElement) current.eContainer();
        PartitionableElement targetPE = (PartitionableElement) target.eContainer();

        if (currentPE != null && targetPE != null) {
            List<Interface> currentPE_ImplIntList = new ArrayList<Interface>();
            List<Interface> currentPE_UseIntList = new ArrayList<Interface>();
            List<Interface> targetPE_ImplIntList = new ArrayList<Interface>();
            List<Interface> targetPE_UseIntList = new ArrayList<Interface>();
            currentPE_ImplIntList = ((Component) currentPE).getImplementedInterfaces();
            currentPE_UseIntList = ((Component) currentPE).getUsedInterfaces();
            targetPE_ImplIntList = ((Component) targetPE).getImplementedInterfaces();
            targetPE_UseIntList = ((Component) targetPE).getUsedInterfaces();

            for (Interface interface1 : currentPE_ImplIntList) {
                if (targetPE_UseIntList.contains(interface1))
                    return true;
            }
            for (Interface interface1 : currentPE_UseIntList) {
                if (targetPE_ImplIntList.contains(interface1))
                    return true;
            }
        }

        return false;
    }

    /**
     * 
     * @param current
     * @param target
     * @return true if : implemented interfaces of current are exactly required
     *         interfaces of target && required interfaces of current are
     *         exactly implemented by target<BR>
     *         false otherwise.
     */
    public boolean isConjugate(Partition current, Partition target) {
        // TODO implement this ... in progress
        PartitionableElement currentPE = (PartitionableElement) current.eContainer();
        PartitionableElement targetPE = (PartitionableElement) target.eContainer();

        if (currentPE != null && targetPE != null) {
            List<Interface> currentPE_ImplIntList = new ArrayList<Interface>();
            List<Interface> currentPE_UseIntList = new ArrayList<Interface>();
            List<Interface> targetPE_ImplIntList = new ArrayList<Interface>();
            List<Interface> targetPE_UseIntList = new ArrayList<Interface>();
            currentPE_ImplIntList = ((Component) currentPE).getImplementedInterfaces();
            currentPE_UseIntList = ((Component) currentPE).getUsedInterfaces();
            targetPE_ImplIntList = ((Component) targetPE).getImplementedInterfaces();
            targetPE_UseIntList = ((Component) targetPE).getUsedInterfaces();

            if (!targetPE_UseIntList.isEmpty() && !currentPE_ImplIntList.isEmpty()) {
                if (currentPE_ImplIntList.containsAll(targetPE_UseIntList))
                    return true;
            }

            if (!currentPE_UseIntList.isEmpty() && !targetPE_ImplIntList.isEmpty()) {
                if (currentPE_UseIntList.containsAll(targetPE_ImplIntList))
                    return true;
            }
        }

        return false;
    }

    /**
     * 
     * @param current
     * @param target
     * @return true if : At least one implemented interface of current is
     *         required by target<BR> || at least one required interface of
     *         current is implemented by target<BR>
     *         false otherwise.
     */
    public boolean isCompatible(AbstractType current, AbstractType target) {
        // TODO implement this
        return false;
    }

    /**
     * 
     * @param current
     * @param target
     * @return true if : implemented interfaces of current are exactly required
     *         interfaces of target && required interfaces of current are
     *         exactly implemented by target<BR>
     *         false otherwise.
     */
    public boolean isConjugate(AbstractType current, AbstractType target) {
        // TODO implement this
        return false;
    }

    /**
     * 
     * @param current
     * @return true if : this port has at least one Delegation PortCommunication
     *         from another port<BR>
     *         false otherwise.
     */
    public boolean isDelegated(Port current) {
        // TODO implement this
        return false;
    }

    /**
     * 
     * @param current
     * @return true if : this port has at least one Delegation PortCommunication
     *         to another port<BR>
     *         false otherwise.
     */
    public boolean isDelegation(Port current) {
        // TODO implement this
        return false;
    }

    /**
     * 
     * @param current
     * @return the list of all conjugate ports of current Port as evaluated by
     *         isConjugate ( current, listMember ). List may be null or empty.
     *         ***? Must evaluate search scope with and without break down.
     *         ?***
     */
    public List<Port> getConjugates(Port current) {
        // TODO implement this
        return null;
    }

    /**
     * 
     * @param current
     * @return The list of ports this port delegates to via a Delegation
     *         PortCommunication. List may be null or empty.
     */
    public List<Port> getDelegatedToList(Port current) {
        // TODO implement this
        return null;
    }

    /**
     * 
     * @param current
     * @return The list of ports current is a delegation of via a Delegation
     *         PortCommunication. List may be null or empty."
     */
    public List<Port> getDelegationFrom(Port current) {
        // TODO implement this
        return null;
    }

    /**
     * 
     * @param current
     * @param target
     * @param internalBlock
     * @return true if : it is possible to reach target block from current block
     *         following EXTERNAL || DELEGATION PortCommunications internalBlock
     *         comms are followed or not according to internalBlock flag<BR>
     *         false otherwise.
     */
    public boolean communicate(Block current, Block target, boolean internalBlock) {
        // TODO implement this
        return false;
    }

    /**
     * 
     * @param current
     * @param target
     * @param internalBlock
     * @return true if : there is at least one PortCommunication between current
     *         and target ; internalBlock comms are followed or not according to
     *         internalBlock flag<BR>
     *         false otherwise. "
     */
    public boolean communicate(Port current, Port target, boolean internalBlock) {
        // TODO implement this
        return false;
    }

    /**
     * 
     * @param current
     * @param target
     * @return An ordered list of Blocks which could "connect" current to target
     *         using the breakdown hierarchy. If current and target are carried
     *         by the same Block the returned list is empty. Otherwise the first
     *         element of the list is always current's owning Block and the last
     *         target's owningBlock.
     * @throws NotABreakDownObjectException
     *             if current's owning block has no breakdown tree.
     * @throws NoOwningBlockException
     *             if current or target has no owning block (in this situation
     *             an ERROR log is sent to the Capella console).
     */
    public ArrayList<Block> getShortestBlockPath(Port current, Port target) {
        // TODO implement this
        return null;
    }

    /**
     * 
     * @param current
     * @param targetBlock
     * @return An ordered list of Blocks which could "connect" current to
     *         targetBlock using the breakdown hierarchy. If current owning
     *         Block and targetBlock are the same, the returned list is empty.
     *         Otherwise the first element of the list is always current's
     *         owning Block and the last targetBlock.
     * @throws NotABreakDownObjectException
     *             if current's owning block has no breakdown tree.
     * @throws NoOwningBlockException
     *             if current has no owning block (in this situation an ERROR
     *             log is sent to the Capella console).
     */
    public ArrayList<Block> getShortestBlockPath(Port current, Block targetBlock) {
        // TODO implement this
        return null;
    }

    /**
     * 
     * @param current
     * @param targetPort
     * @return An ordered list of Blocks which could "connect" current to
     *         targetPort's owningBlock using the breakdown hierarchy. If
     *         targetPort is carried by current the returned list is empty.
     *         Otherwise the first element of the list is always current and the
     *         last target's owningBlock.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     * @throws NoOwningBlockException
     *             if targetPort has no owning block (in this situation an ERROR
     *             log is sent to the Capella console).
     */
    public ArrayList<Block> getShortestBlockPath(Block current, Port targetPort)  {
        // TODO implement this
        return null;
    }

    /**
     * 
     * @param current
     * @return returns the parent in the Partition.
     * @throws NoOwningBlockException
     *             if current has no owning block (in this situation an ERROR
     *             log is sent to the Capella console).
     */
    public PartitionableElement getParent(Partition current)  {
        return (PartitionableElement) current.eContainer();
    }

    /**
     * 
     * @param current
     * @return returns the parent in the ActivityNode.
     * @throws NoOwningBlockException
     *             if current has no owning block (in this situation an ERROR
     *             log is sent to the Capella console).
     */
    public AbstractActivity getParent(ActivityNode current)  {
        return (AbstractActivity) current.eContainer();
    }

    /**
     * 
     * @param current
     * @return The deepness in the breakdown hierarchy of this port's owning
     *         block.
     * @throws NotABreakDownObjectException
     *             if current owning block has no breakdown tree.
     * @throws NoOwningBlockException
     *             if current has no owning block (in this situation an ERROR
     *             log is sent to the Capella console).
     */
    public int depth(Port current)  {
        // TODO implement this
        return -1;
    }

    /**
     * 
     * @param current
     * @param target
     * @return true if : current and target's owning Blocks are siblings. <BR>
     *         false otherwise.
     * @throws NotABreakDownObjectException
     *             if current's owning block has no breakdown tree
     * @throws NoOwningBlockException
     *             if current has no owning block (in this situation an ERROR
     *             log is sent to the Capella console.
     */
    public boolean isSibling(Port current, Port target)  {
        // TODO implement this
        return false;
    }

    /**
     * 
     * @param current
     * @return The list of all ports which are siblings of current. List may be
     *         null or empty.
     * @throws NotABreakDownObjectException
     *             if current's owning block has no breakdown tree.
     * @throws NoOwningBlockException
     *             if current has no owning block (in this situation an ERROR
     *             log is sent to the Capella console).
     */
    public List<Port> getSiblings(Port current) {
        // TODO implement this ... in progress
        return null;
    }

    /**
     * @param current
     * @return The parent in the breakdown hierarchy of this Block or null if
     *         none.
     * @throws NotABreakDownObjectException
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public List<PartitionableElement> getParent(PartitionableElement current)  {
        TreeIterator<Notifier> allContents = current.eResource().getResourceSet().getAllContents();

        List<PartitionableElement> parentList = new ArrayList<PartitionableElement>();
        if (current instanceof Component && !(current instanceof Actor)) {
            Component lc = (Component) current;
            Object obj = null;
            List<Component> scopeList = new ArrayList<Component>();
            while (allContents.hasNext()) {
                obj = allContents.next();
                if (obj instanceof Component) {
                    Component lcme = (Component) obj;
                    scopeList.add(lcme);
                }
            }
            for (Component logicalComponent : scopeList) {
                List<Component> subLogicalComponentHelper = getSubComponents(logicalComponent);
                // check if subLogicalComponent contains lc
                if (subLogicalComponentHelper.contains(lc)) {
                    // add 'logicalComponent' in parentList if same Type
                    if (current.eClass().getName().equalsIgnoreCase(logicalComponent.eClass().getName()))
                        parentList.add(logicalComponent);
                }
            }
            return parentList;
        }
        return null;
    }

    /**
     * @param current
     * @return The list of children in the breakdown hierarchy of this
     *         PartitionableElement or null if none.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public List<PartitionableElement> getChildren(PartitionableElement current) {
        if (current instanceof Component && !(current instanceof Actor)) {
            Component component = (Component) current;
            List<Component> subLogicalComponentHelper = getSubComponents(component);
            List<PartitionableElement> result = new ArrayList<PartitionableElement>();
            for (Component component2 : subLogicalComponentHelper) {
                if (!component2.getName().equalsIgnoreCase(current.getName()) && current.eClass().getName().equalsIgnoreCase(component2.eClass().getName()))
                    result.add(component2);
            }
            return result;
        }
        return null;
    }

    /**
     * @return all the Descendant of the current element
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree
     * @param current
     * @return
     */
    public List<PartitionableElement> getAllDescendant(PartitionableElement current) {
        List<PartitionableElement> result = new ArrayList<PartitionableElement>();

        result.addAll(getChildren(current));

        for (PartitionableElement partitionableElement : getChildren(current)) {
            result.addAll(getChildren(partitionableElement));
        }

        return result;
    }

    /**
     * @param current
     * @return The list of siblings of current block in a breakdown
     *         hierarchy.List may be null or empty.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public List<PartitionableElement> getSiblings(PartitionableElement current)  {
        List<Component> subLogicalComponentHelper = new ArrayList<Component>();
        List<PartitionableElement> partEleList = new ArrayList<PartitionableElement>();

        List<PartitionableElement> parent = getParent(current);
        for (PartitionableElement partitionableElement : parent) {
            subLogicalComponentHelper.addAll(getSubComponents((Component) partitionableElement));
        }
        for (PartitionableElement partitionableElement2 : subLogicalComponentHelper) {
            if (partitionableElement2 != current)
                partEleList.add(partitionableElement2);
        }
        return partEleList;
    }

    /**
     * @param current
     * @param target
     * @return true if : current and target are not the same current and target
     *         belong to the same breakdown hierarchy && have the same parent.
     *         <BR>
     *         false otherwise.
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree.
     */
    public boolean isSibling(PartitionableElement current, PartitionableElement target) {

        if (current == target) {
            return true;
        }

        if (!getParent(current).isEmpty() && !getParent(target).isEmpty())
            for (PartitionableElement partitionableElement : getParent(current)) {
                if (getParent(target).contains(partitionableElement)) {
                    return true;
                }
            }

        return false;
    }

    /**
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree.
     * @param current
     * @param target
     * @return true if : current is the direct parent or an ancestor of target
     *         in the same breakdown hierarchy<BR>
     *         false otherwise.
     */
    public boolean isAncestor(PartitionableElement current, PartitionableElement target) {
        if (getAllAncestor(target).contains(current)) {
            return true;
        }

        return false;
    }

    /**
     * @return true if : this partitionElement is a root (ie has no parent) in
     *         it's breakdown hierarchy <BR>
     *         false otherwise.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public boolean isRoot(PartitionableElement current)  {
        if (!hasBDH(current))
            return false;
        List<PartitionableElement> parent = getParent(current);
        return parent.isEmpty();
    }

    /**
     * 
     * @param current
     * @return true if : this PartitionableElement is a leaf (has no children)
     *         in it's breakdown hierarchy false otherwise.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public boolean isLeaf(PartitionableElement current) {
        if (current instanceof Component && !(current instanceof Actor)) {
            Component component = (Component) current;
            List<Component> subLogicalComponents = getSubComponents(component);
            if (subLogicalComponents.isEmpty()) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * @return true if : current and target belong to the same breakdown
     *         hierarchy false otherwise. NotABreakDownObjectException if
     *         current or target has no breakdown tree.
     * @param current
     * @param target
     * @throws NotABreakDownObjectException
     */
    public boolean isSameDepth(PartitionableElement current, PartitionableElement target) {

        List<PartitionableElement> currenParents = new ArrayList<PartitionableElement>();
        List<PartitionableElement> targetParents = new ArrayList<PartitionableElement>();

        currenParents = getParent(current);
        targetParents = getParent(target);

        if (currenParents.isEmpty() || targetParents.isEmpty())
            return false;

        for (PartitionableElement partitionableElement : currenParents) {
            if (targetParents.contains(partitionableElement)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return all the Ancestor of the current element
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree
     * @param current
     * @return
     */
    public List<PartitionableElement> getAllAncestor(PartitionableElement current) {
        List<PartitionableElement> result = new ArrayList<PartitionableElement>();

        result.addAll(getParent(current));

        for (PartitionableElement partitionableElement : getParent(current)) {
            result.addAll(getAllAncestor(partitionableElement));
        }

        return result;
    }

    /**
     * @return the List of common ancestor of current and target in the break
     *         down hierarchy - current or target may be valid solutions
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree
     * @param current
     * @param target
     * @return
     */
    public List<PartitionableElement> getCommonParent(PartitionableElement current, PartitionableElement target) {
        List<PartitionableElement> result = new ArrayList<PartitionableElement>();
        List<PartitionableElement> currenParents = new ArrayList<PartitionableElement>();
        List<PartitionableElement> targetParents = new ArrayList<PartitionableElement>();

        currenParents = getAllAncestor(current);
        targetParents = getAllAncestor(target);

        for (PartitionableElement partitionableElement : currenParents) {
            if (targetParents.contains(partitionableElement)) {
                if (!result.contains(partitionableElement))
                    result.add(partitionableElement);
            }
        }

        return result;
    }

    /**
     * @param current
     * @return The parent in the breakdown hierarchy of this AbstractActivity or
     *         null if none.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public List<AbstractActivity> getParent(AbstractActivity current)  {
        TreeIterator<Notifier> allContents = current.eResource().getResourceSet().getAllContents();

        List<AbstractActivity> parentList = new ArrayList<AbstractActivity>();
        if (current instanceof AbstractFunction) {
            AbstractActivity lc = current;
            Object obj = null;
            List<AbstractActivity> scopeList = new ArrayList<AbstractActivity>();
            while (allContents.hasNext()) {
                obj = allContents.next();
                if (obj instanceof AbstractActivity) {
                    AbstractActivity lcme = (AbstractActivity) obj;
                    scopeList.add(lcme);
                }
            }
            for (AbstractActivity abstractActivity : scopeList) {
                List<AbstractActivity> subLogicalComponentHelper = getSubFunctions(abstractActivity);
                // check if subFunction contains lc
                if (subLogicalComponentHelper.contains(lc)) {
                    // add 'logicalComponent' in parentList if same Type
                    if (current.eClass().getName().equalsIgnoreCase(abstractActivity.eClass().getName()))
                        parentList.add(abstractActivity);
                }
            }
            return parentList;
        }
        return null;

    }

    /**
     * @param current
     * @return The list of children in the breakdown hierarchy of this
     *         PartitionableElement or null if none.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public List<AbstractActivity> getChildren(AbstractActivity current) {
        if (current instanceof AbstractFunction) {
            AbstractActivity abstractActivity = current;
            List<AbstractActivity> subLogicalComponentHelper = getSubFunctions(abstractActivity);
            List<AbstractActivity> result = new ArrayList<AbstractActivity>();
            for (AbstractActivity component2 : subLogicalComponentHelper) {
                if (!component2.getName().equalsIgnoreCase(current.getName()) && current.eClass().getName().equalsIgnoreCase(component2.eClass().getName()))
                    result.add(component2);
            }
            return result;
        }
        return null;
    }

    /**
     * @return all the Descendant of the current element
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree
     * @param current
     * @return
     */
    public List<AbstractActivity> getAllDescendant(AbstractActivity current) {
        List<AbstractActivity> result = new ArrayList<AbstractActivity>();

        result.addAll(getChildren(current));

        for (AbstractActivity partitionableElement : getChildren(current)) {
            result.addAll(getChildren(partitionableElement));
        }

        return result;
    }

    /**
     * @param current
     * @return The list of siblings of current block in a breakdown
     *         hierarchy.List may be null or empty.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public List<AbstractActivity> getSiblings(AbstractActivity current) {
        List<AbstractActivity> subLogicalComponentHelper = new ArrayList<AbstractActivity>();
        List<AbstractActivity> partEleList = new ArrayList<AbstractActivity>();

        List<AbstractActivity> parent = getParent(current);
        for (AbstractActivity partitionableElement : parent) {
            subLogicalComponentHelper.addAll(getSubFunctions(partitionableElement));
        }
        for (AbstractActivity partitionableElement2 : subLogicalComponentHelper) {
            if (partitionableElement2 != current)
                partEleList.add(partitionableElement2);
        }
        return partEleList;
    }

    /**
     * @param current
     * @param target
     * @return true if : current and target are not the same current and target
     *         belong to the same breakdown hierarchy && have the same parent.
     *         <BR>
     *         false otherwise.
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree.
     */
    public boolean isSibling(AbstractActivity current, AbstractActivity target) {

        if (current == target) {
            return true;
        }

        if (!getParent(current).isEmpty() && !getParent(target).isEmpty())
            for (AbstractActivity partitionableElement : getParent(current)) {
                if (getParent(target).contains(partitionableElement)) {
                    return true;
                }
            }

        return false;
    }

    /**
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree.
     * @param current
     * @param target
     * @return true if : current is the direct parent or an ancestor of target
     *         in the same breakdown hierarchy<BR>
     *         false otherwise.
     */
    public boolean isAncestor(AbstractActivity current, AbstractActivity target) {
        if (getAllAncestor(target).contains(current)) {
            return true;
        }

        return false;
    }

    /**
     * @return true if : this partitionElement is a root (ie has no parent) in
     *         it's breakdown hierarchy <BR>
     *         false otherwise.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public boolean isRoot(AbstractActivity current) {
        if (!hasBDH(current))
            return false;
        List<AbstractActivity> parent = getParent(current);
        return parent.isEmpty();
    }

    /**
     * 
     * @param current
     * @return true if : this PartitionableElement is a leaf (has no children)
     *         in it's breakdown hierarchy false otherwise.
     * @throws NotABreakDownObjectException
     *             if current has no breakdown tree.
     */
    public boolean isLeaf(AbstractActivity current) {
        if (current instanceof AbstractFunction) {
            AbstractActivity abstractActivity = current;
            List<AbstractActivity> subLogicalComponents = getSubFunctions(abstractActivity);
            if (subLogicalComponents.isEmpty()) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * @return true if : current and target belong to the same breakdown
     *         hierarchy false otherwise. NotABreakDownObjectException if
     *         current or target has no breakdown tree.
     * @param current
     * @param target
     * @throws NotABreakDownObjectException
     */
    public boolean isSameDepth(AbstractActivity current, AbstractActivity target)  {

        List<AbstractActivity> currenParents = new ArrayList<AbstractActivity>();
        List<AbstractActivity> targetParents = new ArrayList<AbstractActivity>();

        currenParents = getParent(current);
        targetParents = getParent(target);

        if (currenParents.isEmpty() || targetParents.isEmpty())
            return false;

        for (AbstractActivity partitionableElement : currenParents) {
            if (targetParents.contains(partitionableElement)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return all the Ancestor of the current element
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree
     * @param current
     * @return
     */
    public List<AbstractActivity> getAllAncestor(AbstractActivity current)  {
        List<AbstractActivity> result = new ArrayList<AbstractActivity>();

        result.addAll(getParent(current));

        for (AbstractActivity partitionableElement : getParent(current)) {
            result.addAll(getAllAncestor(partitionableElement));
        }

        return result;
    }

    /**
     * @return the List of common ancestor of current and target in the break
     *         down hierarchy - current or target may be valid solutions
     * @throws NotABreakDownObjectException
     *             if current or target has no breakdown tree
     * @param current
     * @param target
     * @return
     */
    public List<AbstractActivity> getCommonParent(AbstractActivity current, AbstractActivity target) {
        List<AbstractActivity> result = new ArrayList<AbstractActivity>();
        List<AbstractActivity> currenParents = new ArrayList<AbstractActivity>();
        List<AbstractActivity> targetParents = new ArrayList<AbstractActivity>();

        currenParents = getAllAncestor(current);
        targetParents = getAllAncestor(target);

        for (AbstractActivity partitionableElement : currenParents) {
            if (targetParents.contains(partitionableElement)) {
                if (!result.contains(partitionableElement))
                    result.add(partitionableElement);
            }
        }

        return result;
    }
}
