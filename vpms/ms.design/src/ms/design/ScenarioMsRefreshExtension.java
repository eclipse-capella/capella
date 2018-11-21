/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package ms.design;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.vp.ms.CSConfiguration;

public class ScenarioMsRefreshExtension extends DefaultMsRefreshExtension {

  ScenarioMsRefreshExtension(CSSRefreshExtension css) {
    super(css);
  }

  @Override
  protected ScopeVisitor<?> createVisitor() {
    return new ConfigurationScopeVisitor(css) {

      Map<DEdge, Scope> rememberedEdgeScopes = new HashMap<DEdge, Scope>();

      @Override
      public Collection<CSConfiguration> visitAbstracDNodeScope(AbstractDNodeScope asd) {
        Collection<CSConfiguration> result = super.visitAbstracDNodeScope(asd);
        AbstractDNode node = (AbstractDNode) asd.getElement();
        if (node instanceof EdgeTarget) {
          for (DEdge edge : (((EdgeTarget) node).getOutgoingEdges())) {
            updateEdgeStyle(asd, edge, true);
          }
          for (DEdge edge : (((EdgeTarget) node).getIncomingEdges())) {
            updateEdgeStyle(asd, edge, false);
          }
        }
        return result;
      }

      public Collection<CSConfiguration> visitDNodeScope(DNodeScope dns){
        DNode node = (DNode) dns.getElement();
        if (!(node.getActualMapping() instanceof EndOfLifeMapping)) {
          return visitAbstracDNodeScope(dns);
        }
        return Collections.emptyList();
      }

      @Override
      protected EObject getTarget(DDiagramElement e) {
        if (e.getTarget() instanceof StateFragment) {
          return ((StateFragment) e.getTarget()).getRelatedAbstractFunction();
        } else if (e.getTarget() instanceof InstanceRole) {
          return (((InstanceRole) e.getTarget()).getRepresentedInstance().getType());
        }
        return super.getTarget(e);
      }

      private void updateFunctionalExchangeStyle(DEdge edge, FunctionalExchange fe, Scope fipScope, Scope fopScope) {
        updateExchangeStyle(edge, fipScope, fe.getTargetFunctionInputPort(), fopScope, fe.getSourceFunctionOutputPort());
      }

      private void updateExchangeStyle(DEdge edge, Scope inScope, EObject inScopeTarget, Scope outScope, EObject outScopeTarget) {

        CSConfigurationStyle style = getCSConfigurationStyle(edge).clear();
        updateStyle(style, outScopeTarget, getAllScopeConfigurations(outScope));
        updateStyle(style, inScopeTarget, getAllScopeConfigurations(inScope));

        applyExecutionStyleFromEdge(edge, style);
      }

      private void applyExecutionStyleFromEdge(DEdge edge, CSConfigurationStyle style) {
        if (edge.getTargetNode() instanceof DDiagramElement && ((DDiagramElement) edge.getTargetNode()).getMapping() instanceof ExecutionMapping) {
          CSConfigurationStyle execStyle = getCSConfigurationStyle((DDiagramElement) edge.getTargetNode()).clear();
          for (String s : style.getStyle()) {
            execStyle.addClass(s);
          }
        }
      }

      private void updateComponentExchangeStyle(DEdge edge, ComponentExchange ce, Scope inScope, Scope outScope) {
        updateExchangeStyle(edge, inScope, ce.getTargetPort(), outScope, ce.getSourcePort());
      }

      private void updateEdgeStyle(AbstractDNodeScope thisScope, DEdge edge, boolean outgoing) {
        if (edge.getTarget() instanceof SequenceMessage) {
          Scope opposite = getOppositeScope(edge, thisScope);
          if (opposite != null) {
            AbstractEventOperation op = SequenceMessageExt.getOperation((SequenceMessage) edge.getTarget());
            Scope in = outgoing ? opposite : thisScope;
            Scope out = outgoing ? thisScope : opposite;
            if (op instanceof FunctionalExchange) {
              updateFunctionalExchangeStyle(edge, (FunctionalExchange) op, in, out);
            } else if (op instanceof ComponentExchange) {
              updateComponentExchangeStyle(edge, (ComponentExchange) op, in, out);
            } else if (op instanceof ExchangeItemAllocation) {
              updateExchangeItemAllocationStyle(edge, (ExchangeItemAllocation) op, in, out);
            }
          }
        }
      }

      private void updateExchangeItemAllocationStyle(DEdge edge, ExchangeItemAllocation op, Scope in, Scope out) {

        CSConfigurationStyle style = getCSConfigurationStyle(edge).clear();
        SequenceMessage msg = (SequenceMessage) edge.getTarget();

        Classifier sender = (Classifier) msg.getSendingPart().getType();
        for (ComponentPort port : getRequiringPorts(sender.getOwnedFeatures(), op.getAllocatingInterface())){
          updateStyle(style, port, getAllScopeConfigurations(out));
        }

        Classifier receiver = (Classifier) msg.getReceivingPart().getType();
        for (ComponentPort port : getProvidingPorts(receiver.getOwnedFeatures(), op.getAllocatingInterface())) {
          updateStyle(style, port, getAllScopeConfigurations(in));
        }
 
        applyExecutionStyleFromEdge(edge, style);

      }

      private Collection<ComponentPort> getRequiringPorts(Collection<Feature> features, Interface iface){
        Collection<ComponentPort> requiringPorts = new ArrayList<ComponentPort>();
        for (Feature feature : features) {
          if (feature instanceof ComponentPort) {
            if (((ComponentPort) feature).getRequiredInterfaces().contains(iface)) {
              requiringPorts.add((ComponentPort) feature);
            }
          }
        }
        return requiringPorts;
      }

      private Collection<ComponentPort> getProvidingPorts(Collection<Feature> features, Interface iface){
        Collection<ComponentPort> providingPorts = new ArrayList<ComponentPort>();
        for (Feature feature : features) {
          if (feature instanceof ComponentPort) {
            if (((ComponentPort) feature).getProvidedInterfaces().contains(iface)) {
              providingPorts.add((ComponentPort) feature);
            }
          }
        }
        return providingPorts;
      }

      // Have we already handled the opposite scope, then return it,
      // otherwise this scope becomes the opposite scope and the edge 
      // is handled later from the other end
      private Scope getOppositeScope(DEdge edge, Scope thisScope) {
        Scope otherEnd = rememberedEdgeScopes.remove(edge);
        if (otherEnd == null) {
          rememberedEdgeScopes.put(edge, thisScope);
        }
        return otherEnd;
      }

    };
  }

  @Override
  public void postRefresh(DDiagram dDiagram) {
    refreshNodes(dDiagram);
  }

}