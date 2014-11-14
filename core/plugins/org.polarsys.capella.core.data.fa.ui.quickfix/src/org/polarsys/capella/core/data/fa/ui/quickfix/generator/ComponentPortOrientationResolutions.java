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
package org.polarsys.capella.core.data.fa.ui.quickfix.generator;

import static org.polarsys.capella.core.data.fa.OrientationPortKind.IN;
import static org.polarsys.capella.core.data.fa.OrientationPortKind.INOUT;
import static org.polarsys.capella.core.data.fa.OrientationPortKind.OUT;
import static org.polarsys.capella.core.data.fa.OrientationPortKind.UNSET;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.fa.ui.quickfix.resolver.command.InvertComponentExchangeDirection;
import org.polarsys.capella.core.data.fa.ui.quickfix.resolver.command.InvertComponentExchangePortOrientations;
import org.polarsys.capella.core.data.fa.ui.quickfix.resolver.command.SetComponentPortOrientation;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CommandMarkerResolution;

/**
 * Find marker resolutions for inconsistent ComponentPort orientations. Because the available solutions may be different on a case to case basis, we cannot use
 * the CapellaQuickfix extension point, which only supports purely static marker resolutions.
 */
public class ComponentPortOrientationResolutions implements IMarkerResolutionGenerator {

  public IMarkerResolution[] getResolutions(IMarker marker_p) {

    List<? extends IMarkerResolution> resolutions = Collections.emptyList();
    List<EObject> objects = MarkerViewHelper.getModelElementsFromMarker(marker_p);
    if ((objects.size() > 0) && (objects.get(0) instanceof ComponentExchange)) {
      ComponentExchange exchange = (ComponentExchange) objects.get(0);
      ComponentPort sourceCompPort = null;
      ComponentPort targetCompPort = null;
      Port sourcePort = ComponentExchangeExt.getSourcePort(exchange);
      Port targetPort = ComponentExchangeExt.getTargetPort(exchange);
      if (sourcePort instanceof ComponentPort) {
        sourceCompPort = (ComponentPort) sourcePort;
      }
      if (targetPort instanceof ComponentPort) {
        targetCompPort = (ComponentPort) targetPort;
      }
      // all values should be non null. other cases are possible of course but we don't handle them yet.
      if ((sourceCompPort != null) && (targetCompPort != null)) {
        OrientationPortKind source = sourceCompPort.getOrientation();
        OrientationPortKind target = targetCompPort.getOrientation();
        if ((source != null) && (target != null)) {
          resolutions = generateResolutions(exchange, sourceCompPort, targetCompPort, source, target);
        }
      }
    }
    return resolutions.toArray(new IMarkerResolution[0]);
  }

  private List<? extends IMarkerResolution> generateResolutions(ComponentExchange exchange_p, ComponentPort sourcePort_p, ComponentPort targetPort_p,
      OrientationPortKind source_p, OrientationPortKind target_p) {
    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();

    ComponentExchangeKind kind = exchange_p.getKind();
    if (!kind.equals(ComponentExchangeKind.DELEGATION)) {
      if ((source_p == IN) && (target_p == OUT)) {
        resolutions.add(new CommandMarkerResolution(new InvertComponentExchangeDirection(exchange_p)));
        resolutions.add(new CommandMarkerResolution(new InvertComponentExchangePortOrientations(exchange_p)));
      } else if (source_p == IN) {
        resolutions.add(new CommandMarkerResolution(new SetComponentPortOrientation(sourcePort_p, OUT)));
        resolutions.add(new CommandMarkerResolution(new SetComponentPortOrientation(sourcePort_p, INOUT)));
        resolutions.add(new CommandMarkerResolution(new SetComponentPortOrientation(sourcePort_p, UNSET)));
      } else if (target_p == OUT) {
        resolutions.add(new CommandMarkerResolution(new SetComponentPortOrientation(targetPort_p, IN)));
        resolutions.add(new CommandMarkerResolution(new SetComponentPortOrientation(targetPort_p, INOUT)));
        resolutions.add(new CommandMarkerResolution(new SetComponentPortOrientation(targetPort_p, UNSET)));
      }
    } else {
      // Component Exchange of kind DELEGATION should have same orientation
      resolutions.add(new CommandMarkerResolution(new SetComponentPortOrientation(targetPort_p, source_p)));
      resolutions.add(new CommandMarkerResolution(new SetComponentPortOrientation(targetPort_p, INOUT)));
      resolutions.add(new CommandMarkerResolution(new SetComponentPortOrientation(targetPort_p, UNSET)));
    }

    return resolutions;
  }

}
