object NoteChapter11 {
	type Action = () => Unit
	
	abstract class Simulation {
		def currentTime: Int
		def afterDelay(delay: Int, action: => Action)
	
		case class WorkItem(time: Int, action: Action)
		private type Agenda = List[WorkItem]
		private var agenda: Agenda = List()
		def run()
		private def insert(ag: Agenda, item: WorkItem): Agenda =
			if (ag.isEmpty || item.time < ag.head.time) item :: ag
			else ag.head :: insert(ag.tail, item)
		private def next() {
			agenda match {
				case WorkItem(time, action) :: rest =>
					agenda = rest; currentTime = time; action()
				case List() =>
			}
		}
		def afterDelay(delay: Int)(block: => Unit) {
			val item = WorkItem(currentTime + delay, () => block)
			agenda = insert(agenda, item)
		}
		
	}

	class Wire {
		private var sigVal = false
		private var actions: List[Action] = List()
		def getSignal = sigVal
		def setSignal(s: Boolean) =
			if (s != sigVal) {
				sigVal = s
				actions.foreach(action => action())
			}
		def addAction(a: Action) {
			actions = a :: actions; a()
		}
	}
	
	val a = new Wire
	val b = new Wire
	val c = new Wire
	
	def inverter(input: Wire, output: Wire) {
		def invertAction() {
			val inputSig = input.getSignal
			afterDelay(InverterDelay) { output setSignal !inputSig}
		}
		input addAction invertAction
	}
	
	def andGate(a1: Wire, a2: Wire, output: Wire) {
		def andAction() {
			val a1Sig = a1.getSignal
			val a2Sig = a2.getSignal
			afterDelay(AndGateDelay) { output setSignal(a1Sig & a2Sig) }
		}
		a1 addAction andAction
		a2 addAction andAction
	}
	
	def orGate(o1: Wire, o2: Wire, output: Wire) {
		def orAction() {
			val o1Sig = o1.getSignal
			val o2Sig = o2.getSignal
			afterDelay(OrGateDelay) { output setSignal(o1Sig | o2Sig) }
		}
		o1 addAction orAction
		o2 addAction orAction
	}
	
	def halfAdder(a: Wire, b: Wire, s: Wire, c: Wire) {
		val d = new Wire
		val e = new Wire
		orGate(a, b, d)
		andGate(a, b, c)
		inverter(c, e)
		andGate(d, e, s)
	}
	
	def fullAdder(a: Wire, b: Wire, cin: Wire, sum: Wire, cout: Wire) {
		val s = new Wire
		val c1 = new Wire
		val c2 = new Wire
		halfAdder(a, cin, s, c1)
		halfAdder(b, s, sum, c2)
		orGate(c1, c2, cout)
	}
}