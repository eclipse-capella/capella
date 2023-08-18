/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.common;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.test.transition.ju.transitions.CreateRule_ES2ES_01;
import org.polarsys.capella.test.transition.ju.transitions.CreateRule_ESF2ESB_01;

/**
 *
 */
public class SimpleResolver implements IResolver {

  /**
   * @see org.polarsys.capella.core.tiger.IResolver#resolve(org.eclipse.emf.ecore.EObject, java.util.List,
   *      java.lang.String, java.lang.String, boolean, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public List<EObject> resolve(EObject source_p, List<EObject> items_p, String title_p, String message_p,
      boolean multipleSelection_p, ITransfo transfo_p, EObject[] context) {
    List<EObject> result = new LinkedList<EObject>();
    if (items_p != null && items_p.size() > 0) {

      if (source_p instanceof AbstractNamedElement) {
        AbstractNamedElement ane = (AbstractNamedElement) source_p;

        if (items_p.size() > 1) {

          AbstractNamedElement a1 = (AbstractNamedElement) items_p.get(0);
          AbstractNamedElement a2 = (AbstractNamedElement) items_p.get(1);

          AbstractNamedElement c1 = a1.getId().equals(CreateRule_ESF2ESB_01.id_c_1) ? a1 : a2;
          AbstractNamedElement e1 = a1.getId().equals(CreateRule_ESF2ESB_01.id_exchange_1) ? a1 : a2;

          if (ane.getId().equals(CreateRule_ES2ES_01.id_fe3) || ane.getId().equals(CreateRule_ES2ES_01.id_fe4)) {
            // When we have this scenario, we don't want to create a Scenario with Logical System but the components
            // allocating functions of both FE.
            EObject lcPart = items_p.stream()
                .filter(p -> p instanceof Part && !(CreateRule_ES2ES_01.id_part_ls.equals(((Part) p).getId())))
                .findFirst().get();
            result.add(lcPart);

          } else if (ane.getId().equals(CreateRule_ESF2ESB_01.id_sm21)) {
            result.add(c1);

          } else if (ane.getId().equals(CreateRule_ESF2ESB_01.id_sm21)) {
            result.add(c1);

          } else if (ane.getId().equals(CreateRule_ESF2ESB_01.id_sm22)) {
            result.add(e1);

          } else if (ane.getId().equals(CreateRule_ESF2ESB_01.id_sm23)) {
            result.add(e1);

          } else if (ane.getId().equals(CreateRule_ESF2ESB_01.id_sm24)) {
            result.add(c1);

          } else if (ane.getId().equals(CreateRule_ESF2ESB_01.id_sm25)) {
            result.add(c1);

          } else {

            result.add(items_p.get(0));
          }

        } else {

          result.add(items_p.get(0));
        }

      } else {

        result.add(items_p.get(0));
      }
    }

    return result;
  }

}
