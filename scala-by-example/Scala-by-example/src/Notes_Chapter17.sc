object Notes_Chapter17 {

	class BoundedBuffer[A: reflect.ClassTag](N: Int) {
		var in = 0
		var out = 0
		var n = 0
		val elems = new Array[A](N)
		
		def put(x: A) = synchronized {
			while (n >= N) wait()
			elems(in) = x; in = (in + 1) % N; n = n + 1
			if (n == 1) notifyAll()
		}
		
		def get: A = synchronized {
			while (n == 0) wait()
			val x = elems(out) ; out = (out + 1) % N ; n = n - 1
			if (n == N - 1) notifyAll()
			x
		}
	}

	import scala.concurrent.ops._
	
	def produceString: String = ""
	def consumeString(s: String) = println(s)
	
	val buf = new BoundedBuffer[String](10)
	//spawn { while (true) { val s = produceString; buf.put(s) } }
	//spawn { while (true) { val s = buf.get ; consumeString(s) } }
	
	def replicate(start: Int, end: Int)(p: Int => Unit) {
		if (start == end)
			()
		else if (start + 1 == end)
			p(start)
		else {
			val mid = (start + end) / 2
			spawn { replicate(start, mid)(p) }
			replicate(mid, end)(p)
		}
	}
	
	def parMap[A,B: reflect.ClassTag](f: A => B, xs: Array[A]): Array[B] = {
		val results = new Array[B](xs.length)
		replicate(0, xs.length) { i => results(i) = f(xs(i)) }
		results
	}
		
	var a = Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
	parMap[Int, Int]( (x => x * x), a )

		
	import scala.concurrent._
	
	class ComputeServer(n: Int) {
	
		private abstract class Job {
			type T
			def task: T
			def ret(x: T)
		}
		
		private val openJobs = new Channel[Job]()
		
		private def processor(i: Int) {
			while (true) {
				val job = openJobs.read
				job.ret(job.task)
			}
		}
		
		def future[A](p: => A): () => A = {
			val reply = new SyncVar[A]()
			openJobs.write{
				new Job {
					type T = A
					def task = p
					def ret(x: A) = reply.set(x)
				}
			}
		() => reply.get
		}
		
		spawn(replicate(0, n) { processor })
	}
	
	object Test {
		val server = new ComputeServer(1)
		val f = server.future(41 + 1)
		println(f())
	}
	
	case object TIMEOUT
	class LinkedList[A] {
		var elem: A = _
		var next: LinkedList[A] = null
	}
	
	class OnePlaceBuffer {
		private val m = new MailBox // An internal mailbox
		private case class Empty
		private case class Full(x: Int) // Types of messages we deal with
		m send Empty // Initialization
		def write(x: Int) {
			m receive { case Empty => m send Full(x) } }
		def read: Int =
			m receive { case Full(x) => m send Empty; x } }
		
  def main(args: Array[String]) {}
}