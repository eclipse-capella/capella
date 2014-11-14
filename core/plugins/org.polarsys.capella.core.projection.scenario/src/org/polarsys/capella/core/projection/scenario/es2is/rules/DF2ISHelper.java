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
package org.polarsys.capella.core.projection.scenario.es2is.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.projection.scenario.CommonScenarioHelper;

/**
 */
public class DF2ISHelper {

	static boolean mustInverse(Execution exec_p, int i) {
		return mustInverse((AbstractEnd) exec_p.getStart(), i);
	}

	static boolean getOpposite(Execution exec_p, int i) {
		return mustInverse((AbstractEnd) exec_p.getStart(), i);
	}

	@SuppressWarnings("unchecked")
	static Collection<AbstractEnd> getRelateds(MessageEnd exec_p) {
		List<AbstractEnd> ends = new ArrayList<AbstractEnd>();

		AbstractEnd firstBound = exec_p.getMessage().getSendingEnd();
		if (firstBound != null) {
			ends.add(firstBound);
		}

		AbstractEnd otherBound = exec_p.getMessage().getReceivingEnd();
		if (otherBound != null) {
			ends.add(otherBound);

			List<Execution> allocations = (List) EObjectExt.getReferencers(otherBound, InteractionPackage.Literals.TIME_LAPSE__START);
			for (Execution obj : allocations) {
				if (obj.getFinish() instanceof ExecutionEnd) {
					ends.add((ExecutionEnd) obj.getFinish());

				} else if (obj.getFinish() instanceof MessageEnd) {
					SequenceMessage messageReply = ((MessageEnd) obj.getFinish()).getMessage();
					if (messageReply != null) {
						firstBound = messageReply.getSendingEnd();
						if (firstBound != null) {
							ends.add(firstBound);
						}

						otherBound = messageReply.getReceivingEnd();
						if (otherBound != null) {
							ends.add(otherBound);
						}
					}
				}
			}
		}

		return ends;
	}

	@SuppressWarnings("unchecked")
	public static Collection<AbstractEnd> getDirectRelateds(MessageEnd exec_p) {
		List<AbstractEnd> ends = new ArrayList<AbstractEnd>();

		AbstractEnd firstBound = exec_p.getMessage().getSendingEnd();
		if (firstBound != null) {
			ends.add(firstBound);
		}

		AbstractEnd otherBound = exec_p.getMessage().getReceivingEnd();
		if (otherBound != null) {
			ends.add(otherBound);
		}

		List<Execution> allocations = (List) EObjectExt.getReferencers(otherBound, InteractionPackage.Literals.TIME_LAPSE__START);
		for (Execution obj : allocations) {
			if (obj.getFinish() instanceof ExecutionEnd) {
				ends.add((ExecutionEnd) obj.getFinish());
			}
		}

		return ends;
	}

	@SuppressWarnings("unchecked")
	public static AbstractEnd getEndEnd(MessageEnd exec_p) {

		AbstractEnd receivingEnd = null;

		if (exec_p.getEvent() instanceof EventSentOperation) {
			receivingEnd = exec_p.getMessage().getReceivingEnd();

		} else if (exec_p.getEvent() instanceof EventReceiptOperation) {
			receivingEnd = exec_p;
		}

		if (receivingEnd != null) {
			List<Execution> allocations = (List) EObjectExt.getReferencers(receivingEnd, InteractionPackage.Literals.TIME_LAPSE__START);
			for (Execution obj : allocations) {
				if (obj.getFinish() != null) {
					if (obj.getFinish() instanceof ExecutionEnd) {
						return (AbstractEnd) obj.getFinish();

					} else if (obj.getFinish() instanceof MessageEnd) {
						SequenceMessage messageReply = ((MessageEnd) obj.getFinish()).getMessage();
						if (messageReply != null) {
							if (obj.getFinish().equals(messageReply.getSendingEnd()) && (messageReply.getReceivingEnd() != null)) {
								return messageReply.getReceivingEnd();
							}
							return messageReply.getSendingEnd();
						}
					}
				}
			}
		}

		return exec_p;
	}

	static boolean mustInverse(AbstractEnd end, int i) {
		if (end instanceof MessageEnd) {
			return mustInverse(((MessageEnd) end).getMessage(), i);
		}
		if (end instanceof ExecutionEnd) {
			return mustInverse(((ExecutionEnd) end).getExecution(), i);
		}

		return false;
	}

	static boolean mustInverse(SequenceMessage message_p, int i) {
		//List<ExchangeItem> items = getExchangeItems(message_p);
		List<ExchangeItem> items = message_p.getExchangedItems();

		if (items.size() > i) {
			ExchangeItem item = items.get(i);
			if (item != null) {
				return item.getExchangeMechanism() == ExchangeMechanism.EVENT;
			}
		}
		return false;
	}

	static List<ExchangeItem> getExchangeItems(SequenceMessage mesg_p) {
		MessageEnd end = mesg_p.getSendingEnd();
		if (end == null) {
			end = mesg_p.getReceivingEnd();
		}
		return getExchangeItems(end);
	}

	static List<ExchangeItem> getExchangeItems(Execution exec) {
		return getExchangeItems((AbstractEnd) exec.getStart());
	}

	static List<ExchangeItem> getExchangeItems(AbstractEnd end_p) {
		if (end_p == null) {
			return new ArrayList<ExchangeItem>();
		}
		Event e = end_p.getEvent();
		if (e == null) {
			return new ArrayList<ExchangeItem>();
		}
		if (end_p instanceof ExecutionEnd) {
			return getExchangeItems(((ExecutionEnd) end_p).getExecution());
		}
		if (end_p instanceof MessageEnd) {
			return ((MessageEnd) end_p).getMessage().getExchangedItems();
		}
		return getExchangeItems(e);
	}

	public static SequenceMessage getSequenceMessage(Event e) {
		Collection<EObject> msgs = new HashSet<EObject>();
		for (EObject obj : EObjectExt.getReferencers(e, InteractionPackage.Literals.ABSTRACT_END__EVENT)) {
			msgs.addAll(EObjectExt.getReferencers(obj, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END));
			msgs.addAll(EObjectExt.getReferencers(obj, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END));
		}
		if (!msgs.isEmpty()) {
			return (SequenceMessage) msgs.iterator().next();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	static List<ExchangeItem> getExchangeItems(Event e) {
		if ((e instanceof EventSentOperation) || (e instanceof EventReceiptOperation)) {
			SequenceMessage sm = getSequenceMessage(e);
			return sm.getExchangedItems();
		}
		return CommonScenarioHelper.getExchangeItems(getOperation(e));
	}

	static AbstractEventOperation getOperation(Event e) {
		AbstractEventOperation operation = null;
		if (e instanceof EventSentOperation) {
			EventSentOperation eso = (EventSentOperation) e;
			operation = eso.getOperation();
		}
		if (e instanceof EventReceiptOperation) {
			EventReceiptOperation ero = (EventReceiptOperation) e;
			operation = ero.getOperation();
		}
		return operation;

	}

}
