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

public interface ScopeVisitor<T> {
  default T visitDiagramScope(DiagramScope ds) { return null; }
  default T visitAbstracDNodeScope(AbstractDNodeScope asd) { return null; }
  default T visitDNodeContainerScope(DNodeContainerScope dnc) { return null; }
  default T visitDNodeScope(DNodeScope dns) { return null; }
  default T visitDefaultScope(DefaultScope ds) { return null; }
}
